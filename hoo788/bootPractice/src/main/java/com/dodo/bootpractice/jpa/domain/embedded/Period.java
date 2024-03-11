package com.dodo.bootpractice.jpa.domain.embedded;

import jakarta.persistence.Embeddable;

import java.time.LocalDateTime;

@Embeddable // 값 타입에 지정
public class Period {
    // 근무 기간
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    
    // 값 타입을 위한 메서드 작성 가능
    public boolean isWork(LocalDateTime date) {
        if (startDate.isBefore(date) && endDate.isAfter(date)) {
            System.out.println("i am working now");
            return true;
        }
        return false;
    }
}
