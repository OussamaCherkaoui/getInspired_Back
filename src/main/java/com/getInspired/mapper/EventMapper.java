package com.getInspired.mapper;

import com.getInspired.dto.EventDto;
import com.getInspired.model.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EventMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "date", target = "date")
    @Mapping(source = "picture", target = "picture")
    EventDto toDTO(Event event);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "date", target = "date")
    @Mapping(source = "picture", target = "picture")
    Event toEntity(EventDto eventDto);
    List<EventDto> toDTO(List<Event> eventList);
    List<Event> toEntity(List<EventDto> eventDTOList);
}
