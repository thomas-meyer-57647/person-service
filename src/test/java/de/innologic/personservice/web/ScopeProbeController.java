package de.innologic.personservice.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class ScopeProbeController {

    @GetMapping("/api/v1/scope-probe/read")
    @PreAuthorize("hasAuthority('SCOPE_person:read')")
    String read() {
        return "ok";
    }
}
