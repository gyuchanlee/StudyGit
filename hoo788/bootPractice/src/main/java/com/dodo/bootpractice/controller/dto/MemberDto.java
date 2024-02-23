package com.dodo.bootpractice.controller.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {

    private Long id;
    @NotEmpty
    private String userId;
    @NotEmpty
    private String name;
    @NotEmpty
    private String password;
    private String email;
    private String tel;
    @Max(value = 5, message = "member rating must be under 5 rating")
    @Min(value = 0, message = "member rating must be over 0 rating")
    private int rating;
    @Max(value = 300, message = "height must be under 300")
    @Min(value = 100, message = "height must be over 100")
    private double height;
    private String role;

    public MemberDto(String userId, String name, String password, String tel, String email, int rating, double height) {
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.tel = tel;
        this.email = email;
        this.rating = rating;
        this.height = height;
    }
}
