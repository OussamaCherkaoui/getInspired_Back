package com.getInspired.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@DiscriminatorValue("MEMBRE")
public class Membre extends User {

}
