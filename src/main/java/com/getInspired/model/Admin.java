package com.getInspired.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@DiscriminatorValue("ADMIN")
public class Admin extends User {

}
