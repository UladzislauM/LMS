package academy.belhard.lms.service.impl;

import academy.belhard.lms.data.entity.TokenLink;
import academy.belhard.lms.data.repository.TokenLinkRepository;
import academy.belhard.lms.service.TokenLinkService;
import academy.belhard.lms.service.exception.LmsException;
import academy.belhard.lms.service.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service("linkService")
@RequiredArgsConstructor
public class TokenLinkServiceImpl implements TokenLinkService {
    private static final String TOKEN_NOT_FOUND = "Token not found";

    private final TokenLinkRepository tokenLinkRepository;

    @Override
    public String generateToken(int seconds) {
        TokenLink tokenLink = setTokenLink(seconds);
        tokenLinkRepository.save(tokenLink);
        return tokenLink.getToken();
    }

    @Override
    public void activate(String token) {
        TokenLink existingToken = tokenLinkRepository.findByEmailToken(token).orElseThrow(() -> {
            throw new NotFoundException(TOKEN_NOT_FOUND);
        });
        if (existingToken.isActive()) {
            throw new LmsException("Token already activated");
        }
        if (isExpired(existingToken)) {
            throw new LmsException("Token expired");
        }
        existingToken.setActive(true);
        tokenLinkRepository.save(existingToken);
    }

    private boolean isExpired(TokenLink token) {
        LocalDateTime expirationTime = token.getCreateTime().plusSeconds(token.getActiveTime());
        return LocalDateTime.now().isAfter(expirationTime);
    }

    @Override
    public boolean isActivated(String emailToken) {
        TokenLink tokenLink = tokenLinkRepository.findByEmailToken(emailToken).orElseThrow(() -> {
            throw new NotFoundException(TOKEN_NOT_FOUND);
        });
        return tokenLink.isActive();
    }

    private TokenLink setTokenLink(int seconds) {
        TokenLink tokenLink = new TokenLink();
        tokenLink.setToken(generateTokenLink());
        tokenLink.setActiveTime(seconds);
        tokenLink.setCreateTime(LocalDateTime.now());
        return tokenLink;
    }

    private String generateTokenLink() {
        return UUID.randomUUID().toString();
    }
}
