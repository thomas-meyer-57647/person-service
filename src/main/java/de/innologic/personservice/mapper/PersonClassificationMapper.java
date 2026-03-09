package de.innologic.personservice.mapper;

import de.innologic.personservice.domain.PersonClassification;
import de.innologic.personservice.dto.PersonClassificationCreateRequest;
import de.innologic.personservice.dto.PersonClassificationResponse;
import de.innologic.personservice.dto.PersonClassificationUpdateRequest;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PersonClassificationMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "audit", ignore = true)
    @Mapping(target = "classificationId", ignore = true)
    PersonClassification toEntity(PersonClassificationCreateRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "audit", ignore = true)
    @Mapping(target = "classificationId", ignore = true)
    void updateEntity(PersonClassificationUpdateRequest request, @MappingTarget PersonClassification classification);

    @Mapping(target = "classificationId", source = "classificationId")
    @Mapping(target = "createdAt", source = "audit.createdAt")
    @Mapping(target = "createdBy", source = "audit.createdBy")
    @Mapping(target = "modifiedAt", source = "audit.modifiedAt")
    @Mapping(target = "modifiedBy", source = "audit.modifiedBy")
    @Mapping(target = "trashedAt", source = "audit.trashedAt")
    @Mapping(target = "trashedBy", source = "audit.trashedBy")
    PersonClassificationResponse toResponse(PersonClassification classification);
}
