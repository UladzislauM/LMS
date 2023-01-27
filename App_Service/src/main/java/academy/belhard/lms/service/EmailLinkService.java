package academy.belhard.lms.service;

public interface EmailLinkService {
    String getEmailLink(Long userId);

    void activate(String token);

    boolean isActivated(String token);
}
