package com.Mijnqien.Ondersteunend;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import com.Mijnqien.Admin.EmailBericht;

@Component
public class EmailServer{


	@Autowired
	Properties QienMail;
		// set the user details

	   synchronized void SetUser(String username, String password) {
		   QienMail.put("mail.smtp.user",username );
		   QienMail.put("mail.smtp.pwd",password );
	   }
	   
	   synchronized void AutoResetUser() {
		   // load the user and password from the properties file
			ReadProperties.readConfig(); 
			// reset the user
			this.SetUser(ReadProperties.setUsername, ReadProperties.setPassword);	
	   }
	   
	   
	   // Set the server details of the server from which the message is send
	   
	   synchronized void SetServer(String name,String port, boolean auth, boolean enable) {
		   QienMail.put("mail.smtp.host", name);
		   QienMail.put("mail.smtp.port", port);
		   QienMail.put("mail.smtp.auth", auth);
		   QienMail.put("mail.smtp.starttls.enable", enable);
	   }
	   
	   public void send(EmailBericht bericht) {
		   this.send(bericht.getEmail(),bericht.getSubject(),bericht.getContent());
	   }
	   
	   public void send(String recipient, String subject,String content ) {
		   	AutoResetUser();
			Session session = Session.getInstance(QienMail, null);
		    session.setDebug(true);
		    Message msg = new MimeMessage(session);
		    
		    try {
		    	int i=1;
		    	System.out.println(QienMail.getProperty("mail.smtp.user"));
		    	System.out.println("hoi");
			    msg.setFrom(new InternetAddress(QienMail.getProperty("mail.smtp.user")));
			    System.out.println("hoi"+i++);
			    if (subject != null) {
			        msg.setSubject(subject);
	
			    }
			    System.out.println("hoi"+i++);
			    msg.setText(content);
			    System.out.println("hoi"+i++);
			    msg.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
			    System.out.println("hoi"+i++);
			    Transport transport = session.getTransport("smtp");
			    System.out.println("hoi"+i++);
			    transport.connect("smtp.live.com", 587, QienMail.getProperty("mail.smtp.user"), QienMail.getProperty("mail.smtp.pwd"));
			    System.out.println("hoi"+i++);
			    transport.sendMessage(msg, msg.getAllRecipients());
			    System.out.println("hoi"+i++);
			    System.out.println("hoi"+i++);
			    System.out.println("Mail sent successfully at " + recipient);
			    transport.close();
		    } catch (MessagingException e) {
		    	System.out.println("Failed to send message to " + recipient);
		    	System.out.println(e.getMessage());
		    }
	   }
}

