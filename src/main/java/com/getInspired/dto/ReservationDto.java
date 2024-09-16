package com.getInspired.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationDto {
    private Long id;
    private LocalDateTime start_time;
    private LocalDateTime end_time;
    private Boolean isConfirmed;
    private Long idMembre;
    private Long idSpace;
}
