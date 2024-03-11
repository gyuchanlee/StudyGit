package com.dodo.bootpractice.jpa.domain.inheritance;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Album")
public class Album extends Item {

    private String artist;
}
