package de.innologic.personservice.domain;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Status flags the lifecycle state of a person entity.")
public enum PersonStatus {

    @Schema(description = "Active person record that can be read and edited.")
    ACTIVE,

    @Schema(description = "Person has been soft-deleted / trashed.")
    TRASHED
}
