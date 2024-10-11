package com.getInspired.dto;

import lombok.*;

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
}
