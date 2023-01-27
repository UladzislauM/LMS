package academy.belhard.lms.service.impl;

import academy.belhard.lms.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service("mailService")
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {
    private static final String EMAIL_SUBJECT = "Password confirmation";

    private final JavaMailSender emailSender;
    @Value("${spring.mail.username}")
    private String fromEmail;

    @Override
    public void sendEmail(String to, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(to);
        message.setSubject(EMAIL_SUBJECT);
        message.setText(text);
        emailSender.send(message);
    }
}
