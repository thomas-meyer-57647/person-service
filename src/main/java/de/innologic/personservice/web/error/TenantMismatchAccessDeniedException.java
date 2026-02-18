package de.innologic.personservice.web.error;

import org.springframework.security.access.AccessDeniedException;

public class TenantMismatchAccessDeniedException extends AccessDeniedException {

    public TenantMismatchAccessDeniedException(String message) {
        super(message);
    }
}
