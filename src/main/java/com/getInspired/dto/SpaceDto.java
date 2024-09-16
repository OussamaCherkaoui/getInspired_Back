package com.getInspired.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SpaceDto {
    private Long id;
    private String name;
    private String description;
    private Double price_per_day;
    private String picture;
    private Boolean isReserved;
}
