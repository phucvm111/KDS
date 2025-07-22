package com.yourapp.service;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


import java.util.List;
import java.util.Properties;
import model.Account;

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
                    + "<p>Mã OPT làm bài thi của bạn là:123456 (lưu ý hãy đăng nhập lại account trước khi làm bài)</p>"
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
    public static void sendReminderEmail(String toEmail, String parentName, String childName, String amount) {
    final String username = "toandao626@gmail.com";
    final String password = "eewt uwbp quzk ulwz"; // Mật khẩu ứng dụng Gmail (App Password)

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
        message.setSubject("🔔 Nhắc nhở học phí cho bé " + childName);

        // Nội dung email HTML
        String emailContent = ""
            + "<h3>Xin chào phụ huynh <b>" + parentName + "</b>,</h3>"
            + "<p>Hệ thống nhà trường xin thông báo rằng học sinh <b>" + childName + "</b> "
            + "hiện đang còn <b>" + amount + " VND</b> học phí chưa nộp.</p>"
            + "<p>Vui lòng hoàn tất khoản thanh toán trước thời hạn để tránh gián đoạn học tập.</p>"
            + "<p>Trân trọng!</p>";

        message.setContent(emailContent, "text/html; charset=UTF-8");

        Transport.send(message);
        System.out.println("✅ Đã gửi nhắc nhở học phí đến: " + toEmail);
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

    public static void sendNotificationToRole(List<Account> accounts, String subject, String content) {
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

        for (Account acc : accounts) {
            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(username));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(acc.getEmail()));
                message.setSubject(subject);

                String htmlContent = "<h2>Xin chào " + acc.getFirstName() + " " + acc.getLastName() + ",</h2>"
                        + "<p>" + content + "</p>"
                        + "<p><b>Trân trọng,</b><br>Đội ngũ Admin Childcare</p>";

                message.setContent(htmlContent, "text/html; charset=UTF-8");

                Transport.send(message);
                System.out.println("✅ Notification đã gửi thành công đến " + acc.getEmail());
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean sendNotificationToAccounts(List<Account> accounts, String subject, String content) {
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

        boolean allSuccess = true;

        for (Account acc : accounts) {
            if (acc.getEmail() == null || acc.getEmail().isEmpty()) {
                continue;
            }

            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(username));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(acc.getEmail()));
                message.setSubject(subject);

                String html = "<h3>Xin chào " + acc.getFirstName() + " " + acc.getLastName() + ",</h3>"
                        + "<p>" + content + "</p>"
                        + "<p><b>Trân trọng,</b><br>Đội ngũ Admin Childcare</p>";

                message.setContent(html, "text/html; charset=UTF-8");
                Transport.send(message);

                System.out.println("✅ Đã gửi đến: " + acc.getEmail());
            } catch (MessagingException e) {
                e.printStackTrace();
                allSuccess = false;
            }
        }
        return allSuccess;
    }

}
