package com.dodo.bootpractice.jpa.domain.inheritance;

import jakarta.persistence.*;

/**
 * 상속에서 부모 엔티티 클래스 -> @Inheritance 붙임.
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // 3가지 매핑 전략 사용 1.조인, 2.단일 테이블, 3.구현 클래스마다 테이블 전략(비추천)
@DiscriminatorColumn(name = "DTYPE") // 부모 클래스에 구분 컬럼 지정.
public class Item {
    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;
    private String name;
    private int price;
}
