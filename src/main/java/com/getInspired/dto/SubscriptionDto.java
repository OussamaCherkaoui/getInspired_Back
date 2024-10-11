package com.getInspired.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubscriptionDto {
    private Long id;
    private String username;
    private String type;
    private LocalDate start_date;
    private LocalDate end_date;
    private Boolean isConfirmed;
    private String notification;
    private Long idMembre;
}
