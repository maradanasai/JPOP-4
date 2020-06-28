package com.jpop4.i18n;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MessageResolver {

    private static final String FIND_FORMAT = "([{+},\\d]d)";
    private static final Pattern FIND_FORMAT_PATTERN = Pattern.compile(FIND_FORMAT);
    public static final String EMPTY = "";

    @Autowired
    private MessageSource messageSource;

    public String getMessage(final String key) {
        return messageSource.getMessage(key, new Object[0], getLocale());
    }

    /**
     * replace params in format curly brace f.e. {0}
     * and
     * replace params in java format %s
     */
    public String getMessage(final String key, final Object... args) {
        String message = getMessage(key);
        final Matcher matcher = FIND_FORMAT_PATTERN.matcher(message);
        if (message.contains("%s")) {
            //replace params in java format %s
            message = messageFormatter(message, args);
        } else if (message.matches(".*\\{\\d+\\}.*")) {
            //replace params in format curly brace
            message = messageSource.getMessage(key, args, getLocale());
        } else if (matcher.find()) {
            Integer outputString = Integer.valueOf(String.valueOf(args[args.length - 1]));
            message = String.format(message, outputString);
        }

        return message.replaceAll("\\n", EMPTY);
    }

    private String messageFormatter(final String message, final Object... args) {
        return String.format(message, args);
    }

    private Locale getLocale() {
        return Locale.US;
    }
}
