package com.dodo.bootpractice.jpa.domain.inheritance;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Movie")
public class Movie extends Item {

    private String director;
    private String actor;
}
