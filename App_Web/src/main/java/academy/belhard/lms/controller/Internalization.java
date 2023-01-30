package academy.belhard.lms.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
public class Internalization implements WebMvcConfigurer {

    @Bean  //FIXME //Add switch to navbar
    public LocaleResolver localeResolver() {
        //For session
//        SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
//        sessionLocaleResolver.setDefaultLocale(Locale.US);
//        return sessionLocaleResolver;

        //If no session supported
        CookieLocaleResolver resolver = new CookieLocaleResolver();
//        resolver.setDefaultLocale(Locale.US);
        resolver.setCookieDomain("localhost");

        // 60 minutes
        resolver.setCookieMaxAge(60 * 60);
        return resolver;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("language");
        return localeChangeInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }

}
