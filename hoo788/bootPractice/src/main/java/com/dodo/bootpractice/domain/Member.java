package com.dodo.bootpractice.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

@Entity(name = "MEMBER")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;
    @Column(unique = true, nullable = false)
    private String userId;
    private String name;
    private String password;
    private String tel;
    @Max(value = 5, message = "member rating must be under 5 rating")
    @Min(value = 1, message = "member rating must be over 1 rating")
    private int rating;
    private double height;

    public Member(String userId, String name, String password, String tel, int rating, double height) {
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.tel = tel;
        this.rating = rating;
        this.height = height;
    }


    public void updateMember(String name, String password, String tel, int rating, double height) {
        this.name = name;
        this.password = password;
        this.tel = tel;
        this.rating = rating;
        this.height = height;
    }
}
