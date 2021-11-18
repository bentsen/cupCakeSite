package business.services;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail
{


    String to = "";
    String from = "OlskerLotta@gmail.com";
    String host = "smtp.gmail.com";
    Properties properties = System.getProperties();

    public void sendmail()
    {
        //TODO: Få metoden til at tage imod en array af strings som den kan bruge i message.setText
        // hvor der står pænt hvad man har bestilt.

        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator()
        {

            protected PasswordAuthentication getPasswordAuthentication()
            {

                return new PasswordAuthentication("OlskerLotta@gmail.com", "#_CupCakeLovR1337_#");

            }

        });

        session.setDebug(true);

        try
        {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("Order confirmation!");

            // Now set the actual message
            message.setText("\n" +
                    "- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -" +
                    "Mange tak for bestillingen!"+
                    "Det her er din ordre:" +
                    "- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -"
            );



            System.out.println("sending...");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex)
        {
            mex.printStackTrace();
        }
    }

    public void setTo(String to)
    {
        this.to = to;
    }
}