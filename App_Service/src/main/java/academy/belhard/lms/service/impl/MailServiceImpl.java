package academy.belhard.lms.service.impl;

import academy.belhard.lms.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("mailService")
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {
    @Override
    public void sandEmail(String to, String subject, String text) {

    }
}
