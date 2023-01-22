package academy.belhard.lms.service.impl;

import academy.belhard.lms.service.LinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("linkService")
@RequiredArgsConstructor
public class LinkServiceImpl implements LinkService {
    @Override
    public String tokenGenerated(int seconds) {
        return null;
    }

    @Override
    public boolean activated(String link) {
        return false;
    }

    @Override
    public boolean isActivated(String link) {
        return false;
    }
}
