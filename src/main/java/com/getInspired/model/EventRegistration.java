package com.getInspired.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Boolean isConfirmed;
    @ManyToOne
    @JoinColumn(name = "idMembre")
    private Membre membre;
    @ManyToOne
    @JoinColumn(name = "idEvent")
    private Event event;
}
