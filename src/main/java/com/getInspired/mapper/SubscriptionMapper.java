package com.getInspired.mapper;

import com.getInspired.dto.SubscriptionDto;
import com.getInspired.dto.SubscriptionHistoryDto;
import com.getInspired.model.Subscription;
import com.getInspired.model.SubscriptionHistory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SubscriptionMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "type", target = "type")
    @Mapping(source = "start_date", target = "start_date")
    @Mapping(source = "end_date", target = "end_date")
    @Mapping(source = "isConfirmed", target = "isConfirmed")
    @Mapping(source = "notification", target = "notification")
    @Mapping(source = "membre.id", target = "idMembre")
    SubscriptionDto toDTO(Subscription subscription);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "type", target = "type")
    @Mapping(source = "start_date", target = "start_date")
    @Mapping(source = "end_date", target = "end_date")
    @Mapping(source = "isConfirmed", target = "isConfirmed")
    @Mapping(source = "notification", target = "notification")
    @Mapping(source = "idMembre", target = "membre.id")
    Subscription toEntity(SubscriptionDto subscriptionDto);

    List<SubscriptionDto> toDTO(List<Subscription> subscriptionList);
    List<Subscription> toEntity(List<SubscriptionDto> subscriptionDtoList);
}
