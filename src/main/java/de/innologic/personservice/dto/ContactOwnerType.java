package de.innologic.personservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Owner types that are used to dock contact information.")
public enum ContactOwnerType {

    @Schema(description = "PERSON owner is the person-service entity.")
    PERSON
}
