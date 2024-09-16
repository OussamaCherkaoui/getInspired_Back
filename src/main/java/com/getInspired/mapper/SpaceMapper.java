package com.getInspired.mapper;

import com.getInspired.dto.ReservationDto;
import com.getInspired.dto.SpaceDto;
import com.getInspired.model.Reservation;
import com.getInspired.model.Space;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SpaceMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "price_per_day", target = "price_per_day")
    @Mapping(source = "picture", target = "picture")
    @Mapping(source = "isReserved", target = "isReserved")
    SpaceDto toDTO(Space space);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "price_per_day", target = "price_per_day")
    @Mapping(source = "picture", target = "picture")
    @Mapping(source = "isReserved", target = "isReserved")
    Space toEntity(SpaceDto spaceDto);

    List<SpaceDto> toDTO(List<Space> spaceList);
    List<Space> toEntity(List<SpaceDto> spaceDtoList);
}
