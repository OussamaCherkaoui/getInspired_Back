package com.getInspired.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventRegistrationDto {
    private Long id;
    private Boolean isConfirmed;
    private Long idMembre;
    private Long idEvent;
}
