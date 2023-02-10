package academy.belhard.lms.service.plugin;

import java.util.ResourceBundle;
import org.springframework.context.i18n.LocaleContextHolder;

//@Configuration
public class InternalizationMessageManagerConfig {

    public static String getExceptionMessage(String keyMessage) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("exceptionsMessages", LocaleContextHolder.getLocale());
        return resourceBundle.getString(keyMessage);
    }

    public static String getMessage(String keyMessage) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("textMessages", LocaleContextHolder.getLocale());
        return resourceBundle.getString(keyMessage);
    }
}
