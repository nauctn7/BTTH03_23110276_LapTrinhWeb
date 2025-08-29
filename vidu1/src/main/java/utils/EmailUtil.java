package utils;

import java.util.Properties;
import jakarta.mail.*;
import jakarta.mail.internet.*;

public class EmailUtil {
    // ĐỔI 2 giá trị này bằng Gmail + App Password của bạn
    private static final String FROM = "nhancap25@gmail.com";
    private static final String APP_PASSWORD = "ztlf pxfz vibo qvkc";

    // Gửi email text thuần
    public static boolean send(String to, String subject, String body) {
        try {
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");

            Session session = Session.getInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(FROM, APP_PASSWORD);
                }
            });

            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(FROM));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            msg.setSubject(subject);
            msg.setText(body);

            Transport.send(msg);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("[EmailUtil] send error: " + e.getMessage());
            return false;
        }
    }
}
