package com.yourapp.service;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailService {
    public static void sendEmail(String toEmail, String fullName) {
        final String username = "toandao626@gmail.com"; // Thay bằng email của bạn
        final String password = "eewt uwbp quzk ulwz";  // Mật khẩu ứng dụng

        // Cấu hình SMTP
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Xác thực tài khoản Gmail
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Tạo email
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("Xác nhận phê duyệt tài khoản ✅");



            // Nội dung email (Hỗ trợ UTF-8 & HTML)
            String emailContent = "<h2>Chào " + fullName + ",</h2>"
                    + "<p>Chúng tôi xin thông báo rằng đơn đăng ký của bạn đã được <b>phê duyệt thành công</b>! 🎉</p>"
                    +"<p>Mã OPT làm bài thi của bạn là:123456 (lưu ý hãy đăng nhập lại account trước khi làm bài)</p>"
                    + "<p>Nếu bạn có bất kỳ thắc mắc nào, vui lòng liên hệ với chúng tôi.</p>"
                    + "<p><b>Trân trọng,</b><br>Đội ngũ hỗ trợ.</p>";

            message.setContent(emailContent, "text/html; charset=UTF-8");

            // Gửi email
            Transport.send(message);
            System.out.println("✅ Email đã được gửi thành công đến " + toEmail);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    public static void sendEmailgetPassword(String toEmail, String otp) {
    final String username = "toandao626@gmail.com";
    final String password = "eewt uwbp quzk ulwz";

    Properties props = new Properties();
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", "587");

    Session session = Session.getInstance(props, new Authenticator() {
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
        }
    });

    try {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
        message.setSubject("Mã OTP của bạn");

        String emailContent = "<h3>Mã OTP xác thực email là: <b>" + otp + "</b></h3>";
        message.setContent(emailContent, "text/html; charset=UTF-8");

        Transport.send(message);
        System.out.println("✅ OTP đã gửi đến " + toEmail);
    } catch (MessagingException e) {
        e.printStackTrace();
    }
}

     public static void sendEmailcourse(String toEmail, String fullName) {
        final String username = "toandao626@gmail.com"; 
        final String password = "eewt uwbp quzk ulwz"; 
        // Cấu hình SMTP
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Xác thực tài khoản Gmail
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Tạo email
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("Xác nhận phê duyệt khóa học ✅");



          
            String emailContent = "<h2>Chào " + fullName + ",</h2>"
                    + "<p>Chúng tôi xin thông báo rằng đơn đăng ký khóa học của bạn đã được <b>phê duyệt thành công</b>! 🎉</p>"
                    + "<p>xin vui lòng truy cập link https://taplaixe.vn/bo-600-cau-hoi-ly-thuyet-thi-gplx để học trước khi tham gia thi .</p>"
                    + "<p><b>Trân trọng,</b><br>Đội ngũ hỗ trợ.</p>";

            message.setContent(emailContent, "text/html; charset=UTF-8");

            // Gửi email
            Transport.send(message);
            System.out.println("✅ Email đã được gửi thành công đến " + toEmail);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
     
      
}
