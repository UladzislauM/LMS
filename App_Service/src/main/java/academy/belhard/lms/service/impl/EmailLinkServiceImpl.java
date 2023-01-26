package academy.belhard.lms.service.impl;

import academy.belhard.lms.data.entity.EmailLink;
import academy.belhard.lms.data.repository.EmailLinkRepository;
import academy.belhard.lms.service.EmailLinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service("linkService")
@RequiredArgsConstructor
public class EmailLinkServiceImpl implements EmailLinkService {
    private final EmailLinkRepository emailLinkRepository;
    @Override
    public String generate(int seconds) {
        EmailLink emailLink = setEmailLink(seconds);
        emailLinkRepository.save(emailLink);
        return emailLink.getEmailToken();
    }

    private EmailLink setEmailLink(int seconds) {
        EmailLink emailLink = new EmailLink();
        emailLink.setEmailToken(generateToken());
        emailLink.setActiveTime(seconds);
        emailLink.setCreateTime(LocalDateTime.now());
        emailLink.setActive(true);
        return emailLink;
    }

    private String generateToken() {
        String uuid = UUID.randomUUID().toString();
        return uuid;
    }


    @Override
    public void activate(String link) {

    }

    @Override
    public boolean isActivated(String link) {
        return false;
    }
}
