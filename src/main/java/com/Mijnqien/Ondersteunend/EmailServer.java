package com.Mijnqien.Ondersteunend;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Component;

import com.Mijnqien.domain.EmailBericht;

import org.springframework.beans.factory.annotation.Autowired;

@Component
public class EmailServer{


	@Autowired
	Properties mailServer;
		// set the user details

	   synchronized void SetUser(String username, String password) {
		   mailServer.put("mail.smtp.user",username );
		   mailServer.put("mail.smtp.pwd",password );
	   }
	   
	   synchronized void AutoResetUser() {
		   // load the user and password from the properties file
			ReadProperties.readConfig(); 
			// reset the user
			this.SetUser(ReadProperties.setUsername, ReadProperties.setPassword);	
	   }
	   
	   
	   // Set the server details of the server from which the message is send
	   
	   synchronized void SetServer(String hostname,String port, boolean auth, boolean enable) {
		   mailServer.put("mail.smtp.host", hostname);
		   mailServer.put("mail.smtp.port", port);
		   mailServer.put("mail.smtp.auth", auth);
		   mailServer.put("mail.smtp.starttls.enable", enable);
	   }
	   
	   public void send(EmailBericht bericht) {
		   this.send(bericht.getEmail(),bericht.getSubject(),bericht.getContent());
	   }
	   
	   public void send(String recipient, String subject,String content ) {
		   	AutoResetUser();
			Session session = Session.getInstance(mailServer, null);
		    session.setDebug(true);
		    Message msg = new MimeMessage(session);
		    
		    try {
		    	System.out.println(mailServer.getProperty("mail.smtp.user"));
			    msg.setFrom(new InternetAddress(mailServer.getProperty("mail.smtp.user")));
			    if (subject != null) {
			        msg.setSubject(subject);
			    }
			    msg.setText(content);
			    msg.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
			    Transport transport = session.getTransport("smtp");
			    transport.connect(mailServer.getProperty("mail.smtp.host"),  Integer.parseInt(mailServer.getProperty("mail.smtp.port")), mailServer.getProperty("mail.smtp.user"), mailServer.getProperty("mail.smtp.pwd"));
			    transport.sendMessage(msg, msg.getAllRecipients());
			    System.out.println("Mail sent successfully at " + recipient);
			    transport.close();
		    } catch (MessagingException e) {
		    	System.out.println("Failed to send message to " + recipient);
		    	System.out.println(e.getMessage());
		    }
	   }
}



