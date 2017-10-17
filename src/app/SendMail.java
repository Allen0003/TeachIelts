package app;

import java.util.Properties;
import java.util.logging.Level;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import entity.Mail;
import util.Const;

@Path("/sendMail")
public class SendMail {
	@POST
	public Response sned(Mail mail) {
		try {
			Properties properties = new Properties();
			Session session = null;

			MimeMessage generateMailMessage;
			properties = System.getProperties();
			properties.put("mail.smtp.port", "587");
			properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.starttls.enable", "true");
			// Step2
			session = Session.getDefaultInstance(properties, null);
			generateMailMessage = new MimeMessage(session);
			generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(Const.email));
			generateMailMessage.setSubject(mail.getTitle());
			String emailBody = mail.getContext() + " <br>here is my email: " + mail.getEmailAddress();
			generateMailMessage.setContent(emailBody, "text/html");
			// Step3
			Transport transport = session.getTransport("smtp");

			transport.connect("smtp.gmail.com", "thinkielts.manuk", "labour123");
			transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
			transport.close();
		} catch (Exception e) {
			e.printStackTrace();
			Const.LOGGER.log(Level.WARNING, e.toString(), e);
		}

		return Response.status(200).build();
	}
}
