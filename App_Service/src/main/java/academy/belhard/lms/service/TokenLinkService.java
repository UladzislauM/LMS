package academy.belhard.lms.service;

public interface TokenLinkService {
    String generateToken(int seconds);

    void activate(String token);

    boolean isActivated(String token);
}
