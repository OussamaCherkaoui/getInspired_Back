package com.getInspired.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String type;
    @Column
    private LocalDate start_date;
    @Column
    private LocalDate end_date;
    @Column
    private Boolean isConfirmed;
    @Column
    private String notification;
    @ManyToOne
    @JoinColumn(name = "idMembre")
    private Membre membre;
}
