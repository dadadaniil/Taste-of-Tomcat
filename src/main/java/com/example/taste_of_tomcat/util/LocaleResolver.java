package com.example.taste_of_tomcat.util;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Locale;
import java.util.ResourceBundle;

public class LocaleResolver {

    public Locale resolveLocale(HttpServletRequest request) {
        String acceptLanguage = request.getHeader("Accept-Language");
        Locale locale = acceptLanguage != null ? Locale.forLanguageTag(acceptLanguage) : Locale.getDefault();
        ResourceBundle bundle = ResourceBundle.getBundle("localization/messages", locale);
        request.getSession().setAttribute("bundle", bundle);
        return locale;
    }
}