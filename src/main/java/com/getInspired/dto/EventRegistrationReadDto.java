package com.getInspired.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventRegistrationReadDto {
    private Long id;
    private Boolean isConfirmed;
    private String username;
    private String nameEvent;
    private String picture;
    private LocalDate date;
}
