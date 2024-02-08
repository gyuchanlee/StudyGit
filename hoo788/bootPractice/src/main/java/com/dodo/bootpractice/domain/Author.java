package com.dodo.bootpractice.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Author {

    @Id @GeneratedValue
//    @Column(name = "author_id")
    private Long id;
    private String name;
    private String bio;

    // 강사 엔티티와 강의 엔티티 사이의 다대다 관계 설정 (강사쪽이 연관관계 주인.)
    @ManyToMany
    @JoinTable(name = "authors_courses",
    joinColumns = {
            // 연관관계 주인쪽 식별자 컬럼 지정
            @JoinColumn(name = "author_id", referencedColumnName = "id")},
    inverseJoinColumns = {
            // 연관관계 비소유자쪽 식별자 컬럼 지정
            @JoinColumn(name = "course_id", referencedColumnName = "id")
    })
    private Set<Course> courses = new HashSet<>();

    public Author(String name, String bio) {
        this.name = name;
        this.bio = bio;
    }
}
