package academy.belhard.lms.controller;

import academy.belhard.lms.filter.NotAuthorizationFilter;
import academy.belhard.lms.filter.UserPermissionsFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class Internalization implements WebMvcConfigurer {
    @Bean
    public FilterRegistrationBean<NotAuthorizationFilter> authorizationFilter() {
        FilterRegistrationBean<NotAuthorizationFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new NotAuthorizationFilter());
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<UserPermissionsFilter> UserPermissionsFilter() {
        FilterRegistrationBean<UserPermissionsFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new UserPermissionsFilter());
        return registrationBean;
    }

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
        return sessionLocaleResolver;
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
