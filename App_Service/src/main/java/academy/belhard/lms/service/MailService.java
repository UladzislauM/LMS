package academy.belhard.lms.service;

public interface MailService {
    void sandEmail(String to, String subject, String text);
}
