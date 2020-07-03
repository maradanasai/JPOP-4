package com.jpop4.error.resolver;

import com.jpop4.error.message.ErrorMessage;
import com.jpop4.i18n.MessageResolver;
import org.springframework.beans.factory.annotation.Autowired;

public class ErrorResolver {

    private MessageResolver messageResolver;

    @Autowired
    public ErrorResolver(MessageResolver messageResolver) {
        this.messageResolver = messageResolver;
    }

    public ErrorMessage getErrorMessageFromProperties(String key) {
        return getErrorMessageFromProperties(key, new Object[0]);
    }

    public ErrorMessage getErrorMessageFromProperties(String key, Object[] args) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setMessage(messageResolver.getMessage(key, args));
        return errorMessage;
    }
}
