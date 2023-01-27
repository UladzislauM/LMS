package academy.belhard.lms.service;

public interface EmailLinkService {
    String getRegisterLink(Long userId);

    String getRecoveryPassLink(Long userId);

    void activate(String token);

    boolean isActivated(String token);
}
