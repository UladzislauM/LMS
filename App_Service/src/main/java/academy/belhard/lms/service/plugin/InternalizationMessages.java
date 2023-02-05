package academy.belhard.lms.service.plugin;

import java.util.Locale;
import java.util.ResourceBundle;

public class InternalizationMessages {

    public static String messageSource(Locale locale, String keyMessage) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("textMessages", locale);
        return resourceBundle.getString(keyMessage);
    }

}
