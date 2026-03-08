package de.innologic.personservice.mapper;

import de.innologic.personservice.domain.TeamMember;
import de.innologic.personservice.dto.TeamMemberAddRequest;
import de.innologic.personservice.dto.TeamMemberResponse;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TeamMemberMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "companyId", ignore = true)
    @Mapping(target = "team", ignore = true)
    @Mapping(target = "person", ignore = true)
    @Mapping(target = "teamRole", source = "role")
    @Mapping(target = "leftAt", ignore = true)
    @Mapping(target = "audit", ignore = true)
    @Mapping(target = "joinedAt", source = "joinedAt")
    @Mapping(target = "isPrimary", source = "isPrimary")
    TeamMember toEntity(TeamMemberAddRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "companyId", ignore = true)
    @Mapping(target = "team", ignore = true)
    @Mapping(target = "person", ignore = true)
    @Mapping(target = "teamRole", source = "role")
    @Mapping(target = "leftAt", ignore = true)
    @Mapping(target = "audit", ignore = true)
    @Mapping(target = "joinedAt", source = "joinedAt")
    @Mapping(target = "isPrimary", source = "isPrimary")
    void updateFromAddRequest(TeamMemberAddRequest request, @MappingTarget TeamMember teamMember);

    @Mapping(target = "membershipId", source = "membershipId")
    @Mapping(target = "teamId", source = "team.teamId")
    @Mapping(target = "personId", source = "person.publicId")
    @Mapping(target = "isPrimary", source = "isPrimary")
    @Mapping(target = "role", source = "teamRole")
    @Mapping(target = "createdAt", source = "audit.createdAt")
    @Mapping(target = "createdBy", source = "audit.createdBy")
    @Mapping(target = "modifiedAt", source = "audit.modifiedAt")
    @Mapping(target = "modifiedBy", source = "audit.modifiedBy")
    @Mapping(target = "trashedAt", source = "audit.trashedAt")
    @Mapping(target = "trashedBy", source = "audit.trashedBy")
    TeamMemberResponse toResponse(TeamMember teamMember);
}
