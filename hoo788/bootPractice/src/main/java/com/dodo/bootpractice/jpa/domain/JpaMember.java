package com.dodo.bootpractice.jpa.domain;

import com.dodo.bootpractice.jpa.domain.embedded.Address;
import com.dodo.bootpractice.jpa.domain.embedded.Period;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class JpaMember {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, length = 20)
    private String username;

//    private Integer age;
//
//    @Enumerated(EnumType.STRING)
//    private RoleType roleType;
//
//    private LocalDateTime createdDate;
//
////    @Temporal(TemporalType.TIMESTAMP) // 보통 Date를 사용할때 사용. 하이버네이트가 LocalDate를 지원하기 때문에 안써도됨
//    private LocalDateTime lastModifiedDate;
//
//    @Lob
//    private String description;

    // 임베디드 값 타입 적용
    @Embedded
    private Period workPeriod;
    @Embedded
    private Address address;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY) // n대1을 나타내는 어노테이션. 다중성을 나타냄
    @JoinColumn(name = "team_id") // 외래키를 매핑.
    private JpaTeam team;

    // 연관관계 편의 메서드 정의
    public void setTeam(JpaTeam team) {
        this.team = team;
        team.getMembers().add(this);
    }

}
