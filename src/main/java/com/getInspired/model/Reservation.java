package com.getInspired.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private LocalDateTime start_time;
    @Column
    private LocalDateTime end_time;
    @Column
    private Boolean isConfirmed;
    @ManyToOne
    @JoinColumn(name = "idMembre")
    private Membre membre;
    @ManyToOne
    @JoinColumn(name = "idSpace")
    private Space space;
}