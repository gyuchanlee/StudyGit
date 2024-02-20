package com.dodo.bootpractice.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Collection;

@Entity(name = "MEMBER")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member implements UserDetails {


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;
    @Column(unique = true, nullable = false)
    private String userId;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String password;
    private String tel;
    @Max(value = 5, message = "member rating must be under 5 rating")
    @Min(value = 1, message = "member rating must be over 1 rating")
    private int rating;
    private double height;
    private String role = "USER";

    // 권한 반환 (나중에 좀 더 알아보기!!)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add((GrantedAuthority) () -> role);
        return collection;
    }

    // 사용자 id 반환 (고유 값, pk와 같은 식별자들)
    @Override
    public String getUsername() {
        return userId;
    }

    // 사용자 만료 여부 반환
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정 잠금 여부
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 패스워드 만료 여부
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 계정 사용 가능 여부
    @Override
    public boolean isEnabled() {
        return true;
    }

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

    public void changeHash(String hashedPassword) {
        this.password = hashedPassword;
    }


}
