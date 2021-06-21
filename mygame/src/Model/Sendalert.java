package Model;

import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Sendalert {
	private String destiation, Source, password;
	private String code;

	public Sendalert(String dest, String admin, String pass) {
		this.destiation = dest;
		this.password = pass;
		this.Source = admin;
	}

	public void sendMail(String type) throws MessagingException {
		Properties prop = new Properties();
		prop.put("mail.smtp.auth", true);
		prop.put("mail.smtp.starttls.enable", true);
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");

		String myaccout = Source;
		String pass = password;
		Session session = Session.getInstance(prop, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(myaccout, pass);
			}
		});
		String Recipient = destiation;
		Message message = prepareMessage(session, myaccout, Recipient, type);
		Transport.send(message);
		System.out.println("Done");

	}

	private Message prepareMessage(Session session, String mycount, String Recipient, String type) {
		Message msg = new MimeMessage(session);
		try {
			msg.setFrom(new InternetAddress(mycount));
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(Recipient));

			Integer[] index = new Random().ints(0, 100).distinct().limit(100).boxed().toArray(Integer[]::new);
			code = index[0] + "" + index[50] + "" + index[10] + "" + index[20] + "" + index[5];
			String mymsg = "";
			mymsg = selectmsg(type, msg, mymsg);

			mymsg += "<div>    <img src=\"https://drive.google.com/uc?export=view&id=1TW-u2w4YhdXgDbz77-bIvj06pQD-zSux\"></div>";
			msg.setContent(mymsg, "text/html");

			return msg;
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private String selectmsg(String type, Message msg, String mymsg) throws MessagingException {
		mymsg = "<html>";
		switch (type) {
		case "reset":
			msg.setSubject("Code de validation memorygame");
			mymsg += "Nous avons reçu une demande d'accès à votre compte " + destiation
					+ " envoyée avec votre adresse e-mail. " + "<br/>" + " Votre code de validation  est :   <h1> "
					+ code + "</h1>";
			break;
		case "wlc":
			msg.setSubject("FX GAME Zone - You’re almost there!");
			mymsg += "<h2> Welcome To FX GAME ZONE  </h2> </br> <h2>Just play  Have fun Enjoy the game </h2>  "
					+ "<h2>Wishing you lots and lots of undefeated games because it is Video Game Day and you must win what you play.</h2>";
			break;
		}
		return mymsg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}