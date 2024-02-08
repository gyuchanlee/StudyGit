package com.dodo.bootpractice.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Setter @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Course {

    @Id @GeneratedValue
//    @Column(name = "course_id")
    private Long id;
    private String category;
    private String description;
    private String name;
    @NotNull
    private Integer rating;
    // 연관관계 주인이 아닌 쪽은 mappedBy로 연관관계를 설정한다.
    @ManyToMany(mappedBy = "courses")
    private Set<Author> authors = new HashSet<>();

    public Course(String category, String description, String name, Integer rating) {
        this.category = category;
        this.description = description;
        this.name = name;
        this.rating = rating;
    }
}
