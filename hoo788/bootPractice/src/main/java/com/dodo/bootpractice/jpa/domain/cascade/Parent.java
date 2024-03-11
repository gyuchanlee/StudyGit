package com.dodo.bootpractice.jpa.domain.cascade;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
public class Parent {

    @Id @GeneratedValue
    @Column(name = "parent_id")
    private Long id;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Child> children = new ArrayList<>();
}
