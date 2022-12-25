package academy.belhard.lms.web.controller.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class Index {
    private static final Logger log = LogManager.getLogger(Index.class);

    @GetMapping
    public String home() {
        log.debug("Start method Index");
        return "index";
    }
}
