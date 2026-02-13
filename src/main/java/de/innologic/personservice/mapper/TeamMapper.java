package de.innologic.personservice.mapper;

import de.innologic.personservice.domain.Team;
import de.innologic.personservice.dto.TeamCreateRequest;
import de.innologic.personservice.dto.TeamResponse;
import de.innologic.personservice.dto.TeamUpdateRequest;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TeamMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "audit", ignore = true)
    Team toEntity(TeamCreateRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "companyId", ignore = true)
    @Mapping(target = "audit", ignore = true)
    void updateEntity(TeamUpdateRequest request, @MappingTarget Team team);

    @Mapping(target = "createdAt", source = "audit.createdAt")
    @Mapping(target = "createdBy", source = "audit.createdBy")
    @Mapping(target = "modifiedAt", source = "audit.modifiedAt")
    @Mapping(target = "modifiedBy", source = "audit.modifiedBy")
    @Mapping(target = "trashedAt", source = "audit.trashedAt")
    @Mapping(target = "trashedBy", source = "audit.trashedBy")
    TeamResponse toResponse(Team team);
}
