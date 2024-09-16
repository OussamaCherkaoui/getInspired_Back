package com.getInspired.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubscriptionHistoryDto {
    private Long id;
    private String type;
    private LocalDate start_date;
    private LocalDate end_date;
    private Long idSubscription;
}
