package com.dodo.bootpractice.jpa.domain.embedded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED) // JPA 사용을 위해 기본 생성자는 늘 열어두어야 하므로 PROTECTED로 최대한 막아둠
@Getter
// @Setter // 세터는 만들지 않는다
public class Address {

    @Column(name = "city") // 매핑할 컬럼 정의 가능
    private String city;
    private String street;
    private String zipcode;

    // 생성할 때, 값을 쥐어주고 변경하지 않도록 함
    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }

}
