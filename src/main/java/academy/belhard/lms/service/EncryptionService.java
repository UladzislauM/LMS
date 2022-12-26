package academy.belhard.lms.service;

import org.springframework.stereotype.Component;

@Component
public interface EncryptionService {
    String digest(String input);
}
