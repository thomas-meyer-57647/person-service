package de.innologic.personservice.mapper;

import de.innologic.personservice.domain.PersonClassificationAssignment;
import de.innologic.personservice.dto.PersonClassificationAssignmentRequest;
import de.innologic.personservice.dto.PersonClassificationAssignmentResponse;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PersonClassificationAssignmentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "audit", ignore = true)
    @Mapping(target = "assignmentId", ignore = true)
    @Mapping(target = "companyId", ignore = true)
    @Mapping(target = "personId", ignore = true)
    PersonClassificationAssignment toEntity(PersonClassificationAssignmentRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "audit", ignore = true)
    @Mapping(target = "assignmentId", ignore = true)
    @Mapping(target = "companyId", ignore = true)
    @Mapping(target = "personId", ignore = true)
    void updateEntity(PersonClassificationAssignmentRequest request, @MappingTarget PersonClassificationAssignment assignment);

    @Mapping(target = "assignmentId", source = "assignmentId")
    @Mapping(target = "createdAt", source = "audit.createdAt")
    @Mapping(target = "createdBy", source = "audit.createdBy")
    @Mapping(target = "modifiedAt", source = "audit.modifiedAt")
    @Mapping(target = "modifiedBy", source = "audit.modifiedBy")
    @Mapping(target = "trashedAt", source = "audit.trashedAt")
    @Mapping(target = "trashedBy", source = "audit.trashedBy")
    PersonClassificationAssignmentResponse toResponse(PersonClassificationAssignment assignment);
}
