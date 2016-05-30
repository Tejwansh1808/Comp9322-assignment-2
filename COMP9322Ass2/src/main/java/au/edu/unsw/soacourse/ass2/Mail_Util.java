package au.edu.unsw.soacourse.ass2;

import java.util.Calendar;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;

public class Mail_Util {

	public static void sendMail(final String from, final String password,String To, String message, String subject) throws Exception{
		String host = "smtp.gmail.com";

		// String pass = jPasswordField1.getText();

		// Create properties, get Session
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.debug", "true");
		props.put("mail.smtp.user", from);
		props.put("mail.smtp.password", password);
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

		Session session = Session.getInstance(props);

		
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(from));
			InternetAddress[] address = { new InternetAddress(To) };
			msg.setRecipients(Message.RecipientType.TO, address);
			msg.setSubject(subject);
			msg.setSentDate(Calendar.getInstance().getTime());
			msg.setText(message);
			// Transport.send(msg);
			Transport transport = session.getTransport("smtp");
			transport.connect(host, from, password);
			transport.sendMessage(msg, msg.getAllRecipients());
			transport.close();
		
			
		

	}
	
}
