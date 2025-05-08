package com.almuhsin.saham.sevices;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
    // Kirim OTP ke email
    public void sendOtpEmail(String toEmail, String otp) {
        try {
            // Buat pesan email
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(toEmail);
            helper.setSubject("Kode OTP Pendaftaran Akun Saham");
            String content = """
                <p>Kode OTP Anda adalah: <b>%s</b></p>
                <p>Kode ini berlaku selama 5 menit. Silakan masukkan kode ini untuk menyelesaikan pendaftaran akun Anda.</p>
                <p>Jika Anda tidak melakukan pendaftaran, abaikan email ini.</p>
                <p>Terima kasih telah menggunakan layanan kami.</p>
                """.formatted(otp);

            helper.setText(content, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
