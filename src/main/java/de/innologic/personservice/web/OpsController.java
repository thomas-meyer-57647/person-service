package de.innologic.personservice.web;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.info.BuildProperties;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${app.base-path:/person}")
public class OpsController {

    private final String serviceName;
    private final String applicationVersion;
    private final ObjectProvider<BuildProperties> buildPropertiesProvider;

    public OpsController(
            @Value("${spring.application.name}") String serviceName,
            @Value("${application.version:unknown}") String applicationVersion,
            ObjectProvider<BuildProperties> buildPropertiesProvider
    ) {
        this.serviceName = serviceName;
        this.applicationVersion = applicationVersion;
        this.buildPropertiesProvider = buildPropertiesProvider;
    }

    @GetMapping("/ping")
    public PingResponse ping() {
        return new PingResponse(serviceName, resolveVersion());
    }

    @GetMapping("/version")
    public VersionResponse version() {
        BuildProperties buildProperties = buildPropertiesProvider.getIfAvailable();
        String buildTime = buildProperties != null && buildProperties.getTime() != null
                ? buildProperties.getTime().toString()
                : null;
        return new VersionResponse(serviceName, resolveVersion(), buildTime);
    }

    private String resolveVersion() {
        BuildProperties buildProperties = buildPropertiesProvider.getIfAvailable();
        if (buildProperties != null && StringUtils.hasText(buildProperties.getVersion())) {
            return buildProperties.getVersion();
        }
        if (StringUtils.hasText(applicationVersion)) {
            return applicationVersion;
        }
        return "unknown";
    }

    public record PingResponse(String service, String version) {
    }

    public record VersionResponse(String service, String version, String buildTime) {
    }
}
