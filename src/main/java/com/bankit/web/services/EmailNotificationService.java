package com.bankit.web.services;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class EmailNotificationService {

    public void sendNotification (String receiverEmail) {

        String from = "";
        String password = "";

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new jakarta.mail.Authenticator() {
            protected jakarta.mail.PasswordAuthentication getPasswordAuthentication() {
                return new jakarta.mail.PasswordAuthentication(from, password);
            }
        });

        try {
            // Create a default MimeMessage object
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiverEmail));

            // Set Subject: header field
            message.setSubject("New transaction in your account");

            // Set the actual message
            message.setText("Transaction successful");

            // Send the message
            Transport.send(message);

            //System.out.println("Notification sent successfully!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }


}
