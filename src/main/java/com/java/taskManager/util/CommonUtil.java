package com.java.taskManager.util;


import org.springframework.http.HttpHeaders;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class CommonUtil {

    public static HttpHeaders setHttpHeaders()
    {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Strict-Transport-Security","max-age=31536000; includeSubDomains");
        return headers;
    }

    public static void sendMail(String emailId,String SUBJECT,String BODY) throws Exception{


        String FROM = "amit002pas@gmail.com";
        String FROMNAME = "Task Manager";

        String TO = emailId;

        String SMTP_USERNAME = "USERNAME";

        String SMTP_PASSWORD = "PASSWORD";


        String HOST = "email-smtp.ap-south-1.amazonaws.com";

        String PORT = "587";


        Properties props = System.getProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");

        // Create a Session object to represent a mail session with the specified properties.
        Session session = Session.getDefaultInstance(props);

        // Create a message with the specified information.
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(FROM,FROMNAME));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(TO));
        msg.setSubject(SUBJECT);
        msg.setContent(BODY,"text/plain");


        Transport transport = session.getTransport();

        // Send the message.
        try
        {
            System.out.println("Sending...");

            // Connect to Amazon SES using the SMTP username and password you specified above.
            transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);

            // Send the email.
            transport.sendMessage(msg, msg.getAllRecipients());
            System.out.println("Email sent!");
        }
        catch (Exception ex) {
            System.out.println("The email was not sent.");
            System.out.println("Error message: " + ex);
        }
        finally
        {
            // Close and terminate the connection.
            transport.close();
        }



    }
}
