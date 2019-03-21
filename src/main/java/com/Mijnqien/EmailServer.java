package com.Mijnqien;

import java.io.IOException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailServer{
	private static EmailServer emailserver;
	private Properties props;

	private EmailServer() {
	    if (this.props == null) {
	    	this.props = new Properties();
	        this.props.put("mail.smtp.auth", true);
	        this.props.put("mail.smtp.starttls.enable", true);
	        this.props.put("mail.smtp.host", "smtp.live.com");
	        this.props.put("mail.smtp.port", "587");
	        AutoResetUser();
	    }
	};
	
	// Zorg dat er maar 1 instance van EmailServer kan bestaan!
	
		
	public static EmailServer getInstance() {
		   if (emailserver==null) {
			   synchronized (EmailServer.class) {
				   if (emailserver==null) {
					   emailserver = new EmailServer();
				   }
			   }
		   }
		   return emailserver;
	   }
	
		// set the user details

	   synchronized void SetUser(String username, String password) {
	        props.put("mail.smtp.user",username );
	        props.put("mail.smtp.pwd",password );
	   }
	   
	   synchronized void AutoResetUser() {
		   // load the user and password from the properties file
			ReadProperties.readConfig(); 
			// reset the user
			this.SetUser(ReadProperties.setUsername, ReadProperties.setPassword);	
	   }
	   
	   
	   // Set the server details of the server from which the message is send
	   
	   synchronized void SetServer(String name,String port, boolean auth, boolean enable) {
	        props.put("mail.smtp.host", name);
	        props.put("mail.smtp.port", port);
	        props.put("mail.smtp.auth", auth);
	        props.put("mail.smtp.starttls.enable", enable);
	   }
	   
	   
	   public void send(String recipient, String subject,String content ) {
		   	AutoResetUser();
			Session session = Session.getInstance(props, null);
		    session.setDebug(true);
		    Message msg = new MimeMessage(session);
		    
		    try {
			    msg.setFrom(new InternetAddress(props.getProperty("mail.smtp.user")));
			    
			    if (subject != null) {
			        msg.setSubject(subject);
	
			    }
			    
			    msg.setText(content);
			    msg.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
			    Transport transport = session.getTransport("smtp");
			    transport.connect("smtp.live.com", 587, props.getProperty("mail.smtp.user"), props.getProperty("mail.smtp.pwd"));
			    transport.sendMessage(msg, msg.getAllRecipients());
			    System.out.println("Mail sent successfully at " + recipient);
			    transport.close();
		    } catch (MessagingException e) {
		    	System.out.println("Failed to send message to " + recipient);
		    	System.out.println(e.getMessage());
		    }
		   
	   }
}

