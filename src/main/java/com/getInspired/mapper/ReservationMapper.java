package com.getInspired.mapper;

import com.getInspired.dto.EventRegistrationDto;
import com.getInspired.dto.ReservationDto;
import com.getInspired.model.EventRegistration;
import com.getInspired.model.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReservationMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "start_time", target = "start_time")
    @Mapping(source = "end_time", target = "end_time")
    @Mapping(source = "isConfirmed", target = "isConfirmed")
    @Mapping(source = "membre.id", target = "idMembre")
    @Mapping(source = "space.id", target = "idSpace")
    ReservationDto toDTO(Reservation reservation);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "start_time", target = "start_time")
    @Mapping(source = "end_time", target = "end_time")
    @Mapping(source = "isConfirmed", target = "isConfirmed")
    @Mapping(source = "idMembre", target = "membre.id")
    @Mapping(source = "idSpace", target = "space.id")
    Reservation toEntity(ReservationDto reservationDto);

    List<ReservationDto> toDTO(List<Reservation> reservationList);
    List<Reservation> toEntity(List<ReservationDto> reservationDTOList);
}
