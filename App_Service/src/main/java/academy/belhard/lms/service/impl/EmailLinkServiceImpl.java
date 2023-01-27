package academy.belhard.lms.service.impl;

import academy.belhard.lms.data.entity.EmailLink;
import academy.belhard.lms.data.repository.EmailLinkRepository;
import academy.belhard.lms.service.EmailLinkService;
import academy.belhard.lms.service.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service("linkService")
@RequiredArgsConstructor
public class EmailLinkServiceImpl implements EmailLinkService {
    private static final int REGISTER_TOKEN_ACTIVITY_SECONDS = 60 * 60;
    private static final int RECOVERY_TOKEN_ACTIVITY_SECONDS = 5 * 60;
    private static final String ACTIVATE_LINK_PATTERN = "Please, visit link: http://localhost:8080/auth/activate/%s/%s";
    private static final String RECOVERY_PASS_LINK_PATTERN = "Please, visit link: http://localhost:8080/auth/recoveryPass/%s/%s";
    private static final String TOKEN_NOT_FOUND = "Email token not found";

    private final EmailLinkRepository emailLinkRepository;

    @Override
    public String getRegisterLink(Long userId) {
        return String.format(ACTIVATE_LINK_PATTERN, createEmailToken(REGISTER_TOKEN_ACTIVITY_SECONDS), userId);
    }

    @Override
    public String getRecoveryPassLink(Long userId) {
        return String.format(RECOVERY_PASS_LINK_PATTERN, createEmailToken(RECOVERY_TOKEN_ACTIVITY_SECONDS), userId);
    }

    @Override
    public void activate(String emailToken) {
        EmailLink emailLink = emailLinkRepository.findByEmailToken(emailToken).orElseThrow(() -> {
            throw new NotFoundException(TOKEN_NOT_FOUND);
        });
        emailLink.setActive(true);
        emailLinkRepository.save(emailLink);
    }

    @Override
    public boolean isActivated(String emailToken) {
        EmailLink emailLink = emailLinkRepository.findByEmailToken(emailToken).orElseThrow(() -> {
            throw new NotFoundException(TOKEN_NOT_FOUND);
        });
        LocalDateTime expirationTime = emailLink.getCreateTime().plusSeconds(emailLink.getActiveTime());
        return expirationTime.isAfter(LocalDateTime.now());
    }

    private String createEmailToken(int seconds) {
        EmailLink emailLink = setEmailLink(seconds);
        emailLinkRepository.save(emailLink);
        return emailLink.getEmailToken();
    }

    private EmailLink setEmailLink(int seconds) {
        EmailLink emailLink = new EmailLink();
        emailLink.setEmailToken(generateEmailToken());
        emailLink.setActiveTime(seconds);
        emailLink.setCreateTime(LocalDateTime.now());
        emailLink.setActive(true);
        return emailLink;
    }

    private String generateEmailToken() {
        String uuid = UUID.randomUUID().toString();
        return uuid;
    }
}
