package academy.belhard.lms.service;

public interface EmailLinkService {
    String generate(int seconds);

    void activate(String link);

    boolean isActivated(String link);
}
