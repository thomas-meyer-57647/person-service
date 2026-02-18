package de.innologic.personservice.web.error;

import org.springframework.security.core.AuthenticationException;

public class InvalidTokenAuthenticationException extends AuthenticationException {

    public InvalidTokenAuthenticationException(String message) {
        super(message);
    }
}
