package de.innologic.personservice.mapper;

import de.innologic.personservice.domain.Person;
import de.innologic.personservice.dto.ContactOwnerType;
import de.innologic.personservice.dto.PersonCreateRequest;
import de.innologic.personservice.dto.PersonResponse;
import de.innologic.personservice.dto.PersonUpdateRequest;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PersonMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "audit", ignore = true)
    Person toEntity(PersonCreateRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "companyId", ignore = true)
    @Mapping(target = "audit", ignore = true)
    void updateEntity(PersonUpdateRequest request, @MappingTarget Person person);

    @Mapping(target = "createdAt", source = "audit.createdAt")
    @Mapping(target = "createdBy", source = "audit.createdBy")
    @Mapping(target = "modifiedAt", source = "audit.modifiedAt")
    @Mapping(target = "modifiedBy", source = "audit.modifiedBy")
    @Mapping(target = "trashedAt", source = "audit.trashedAt")
    @Mapping(target = "trashedBy", source = "audit.trashedBy")
    @Mapping(target = "personId", source = "publicId")
    @Mapping(target = "contactOwnerType", expression = "java(de.innologic.personservice.dto.ContactOwnerType.PERSON)")
    @Mapping(target = "contactOwnerId", source = "publicId")
    PersonResponse toResponse(Person person);
}
