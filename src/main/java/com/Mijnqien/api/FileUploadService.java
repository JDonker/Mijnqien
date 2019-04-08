package com.Mijnqien.api;
import java.io.File;



import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.beans.factory.annotation.Autowired;

import com.Mijnqien.Exceptions.DeclaratieFormNotFoundException;
import com.Mijnqien.Exceptions.DeclaratieNotFoundException;
import com.Mijnqien.Trainee.Declaratie;
import com.Mijnqien.Trainee.DeclaratieForm;
import com.Mijnqien.service.DeclaratieFormService;
import com.Mijnqien.service.DeclaratieService;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
// this class was implemented using:
// https://javatutorial.net/java-file-upload-rest-service
// with the following modification
// https://stackoverflow.com/questions/30653012/multipart-form-data-no-injection-source-found-for-a-parameter-of-type-public-ja


@Path("/upload")
public class FileUploadService {
	
	@Autowired
	DeclaratieService declaratieService;
	
	@Autowired
	DeclaratieFormService declaratieFormService;

	/** The path to the folder where we want to store the uploaded files */
	private static final java.nio.file.Path UPLOAD_FOLDER = Paths.get(System.getProperty("user.dir")) ;

	public FileUploadService() {
	}

	@Context
	private UriInfo context;

	/**
	 * Returns text response to caller containing uploaded file location
	 * 
	 * @return error response in case of missing parameters an internal
	 *         exception or success response if file has been stored
	 *         successfully
	 */
	
	@POST
	@Path("/{FormID}/{declaratieID}")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadDeclaratieBijlage(
			@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail,@PathParam("FormID") long FormID,@PathParam("declaratieID") long declaratieID) {

		// huidge user ophalen 
		long traineeID = 1;
		java.nio.file.Path UPLOAD_FOLDER_LOCAL=Paths.get(traineeID + "/" + FormID + "/" + declaratieID  + "/");  

		java.nio.file.Path folder = UPLOAD_FOLDER.resolve(UPLOAD_FOLDER_LOCAL);
		
		// check if all form parameters are provided
		if (uploadedInputStream == null || fileDetail == null)
			return Response.status(400).entity("Invalid form data").build();
		// create our destination folder, if it not exists
		
		try {
			Files.createDirectory(folder);
			System.out.println(folder.toString());
		} catch (IOException se) {
			return Response.status(500)
					.entity("Can not create destination folder on server")
					.build();
		}
		
		// nu declaratie beijken bouw nog check voor access
		try {
			DeclaratieForm decForm = declaratieFormService.findById(FormID);
			Declaratie declaratie = declaratieService.findById(declaratieID);
			if (decForm.bewerkbaar() && decForm.getDeclaraties().contains(declaratie)) {
				declaratie.setBijlage(fileDetail.getFileName());
				declaratieService.save(declaratie);
			} else {
				return Response.status(Status.BAD_REQUEST).build();
			}
		} catch (DeclaratieFormNotFoundException| DeclaratieNotFoundException e) {
			return Response.status(Status.NOT_FOUND).build();
		}
		
		
		
		System.out.println(System.getProperty("user.dir"));
		String uploadedFileLocation = folder.relativize(Paths.get(fileDetail.getFileName())).toString()  ;
		try {
			saveToFile(uploadedInputStream, uploadedFileLocation);
		} catch (IOException e) {
			return Response.status(500).entity("Can not save file").build();
		}

		return Response.status(200)
				.entity("Huidig bestand:  " + fileDetail.getFileName()).build();
	}
	
	
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(
			@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail) {

		
		// check if all form parameters are provided
		if (uploadedInputStream == null || fileDetail == null)
			return Response.status(400).entity("Invalid form data").build();

		// create our destination folder, if it not exists
		try {
			createFolderIfNotExists(UPLOAD_FOLDER.toString());
		} catch (SecurityException se) {
			return Response.status(500)
					.entity("Can not create destination folder on server")
					.build();
		}
		System.out.println(System.getProperty("user.dir"));
		String uploadedFileLocation = UPLOAD_FOLDER + fileDetail.getFileName();
		try {
			saveToFile(uploadedInputStream, uploadedFileLocation);
		} catch (IOException e) {
			return Response.status(500).entity("Can not save file").build();
		}

		return Response.status(200)
				.entity("File saved to " + uploadedFileLocation).build();
	}
	
	@PUT
	public Response deleteUploadFile(
			@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail) {

		// create our destination folder, if it not exists
		try {
			createFolderIfNotExists(UPLOAD_FOLDER.toString());
		} catch (SecurityException se) {
			return Response.status(500)
					.entity("Can not create destination folder on server")
					.build();
		}
		
		System.out.println(System.getProperty("user.dir"));
		String uploadedFileLocation = UPLOAD_FOLDER + fileDetail.getFileName();
		try {
			saveToFile(uploadedInputStream, uploadedFileLocation);
		} catch (IOException e) {
			return Response.status(500).entity("Can not save file").build();
		}

		return Response.status(200)
				.entity("File saved to " + uploadedFileLocation).build();
	}

	/**
	 * Utility method to save InputStream data to target location/file
	 * 
	 * @param inStream
	 *            - InputStream to be saved
	 * @param target
	 *            - full path to destination file
	 */
	private void saveToFile(InputStream inStream, String target)
			throws IOException {
		OutputStream out = null;
		int read = 0;
		byte[] bytes = new byte[1024];

		out = new FileOutputStream(new File(target));
		while ((read = inStream.read(bytes)) != -1) {
			out.write(bytes, 0, read);
		}
		out.flush();
		out.close();
	}

	/**
	 * Creates a folder to desired location if it not already exists
	 * 
	 * @param dirName
	 *            - full path to the folder
	 * @throws SecurityException
	 *             - in case you don't have permission to create the folder
	 */
	private void createFolderIfNotExists(String dirName)
			throws SecurityException {
		File theDir = new File(dirName);
		if (!theDir.exists()) {
			theDir.mkdir();
		}
	}

}