package com.getInspired.mapper;

import com.getInspired.dto.SpaceDto;
import com.getInspired.dto.SubscriptionHistoryDto;
import com.getInspired.model.Space;
import com.getInspired.model.SubscriptionHistory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SubscriptionHistoryMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "type", target = "type")
    @Mapping(source = "start_date", target = "start_date")
    @Mapping(source = "end_date", target = "end_date")
    @Mapping(source = "subscription.id", target = "idSubscription")
    SubscriptionHistoryDto toDTO(SubscriptionHistory subscriptionHistory);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "type", target = "type")
    @Mapping(source = "end_date", target = "start_date")
    @Mapping(source = "end_date", target = "end_date")
    @Mapping(source = "idSubscription", target = "subscription.id")
    SubscriptionHistory toEntity(SubscriptionHistoryDto subscriptionHistoryDto);

    List<SubscriptionHistoryDto> toDTO(List<SubscriptionHistory> subscriptionHistoryList);
    List<SubscriptionHistory> toEntity(List<SubscriptionHistoryDto> subscriptionHistoryDtoList);
}
