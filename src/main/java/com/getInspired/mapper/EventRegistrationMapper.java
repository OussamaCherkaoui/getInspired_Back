package com.getInspired.mapper;

import com.getInspired.dto.EventDto;
import com.getInspired.dto.EventRegistrationDto;
import com.getInspired.model.Event;
import com.getInspired.model.EventRegistration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EventRegistrationMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "isConfirmed", target = "isConfirmed")
    @Mapping(source = "membre.id", target = "idMembre")
    @Mapping(source = "event.id", target = "idEvent")
    EventRegistrationDto toDTO(EventRegistration eventRegistration);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "isConfirmed", target = "isConfirmed")
    @Mapping(source = "idMembre", target = "membre.id")
    @Mapping(source = "idEvent", target = "event.id")
    EventRegistration toEntity(EventRegistrationDto eventRegistrationDto);

    List<EventRegistrationDto> toDTO(List<EventRegistration> eventRegistrationtList);
    List<EventRegistration> toEntity(List<EventRegistrationDto> eventRegistrationDTOList);
}
