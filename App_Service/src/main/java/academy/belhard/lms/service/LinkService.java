package academy.belhard.lms.service;

public interface LinkService {
    String tokenGenerated(int seconds);

    boolean activated(String link);

    boolean isActivated(String link);
}
