package com.dodo.bootpractice.repository;

import com.dodo.bootpractice.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findAllByUserId(String userId);

    Iterable<Member> findAllByUserIdOrderByHeightDesc(String userId);

    boolean existsById(Long id);

    Page<Member> findAll(Pageable pageable);

    /**
     * JPQL을 활용한 @Query 방식
     * 레포지토리에 데이터접근/변경을 수행하므로 레포지토리에 데이터 관련 책임이 올바르게 주어진다는 장점
     * (네임드쿼리는 엔티티에 책임이 묶임)
     * 파라미터 지정 : 이름을 지정하는 네임드 파라미터 방식 지향. @Param을 활용 (위치 기반은 시간에 따라 코드가 바뀔 위험이 존재함)
     *
     */
    @Query(value = "select m from MEMBER m where m.id =:id")
    List<Member> findByIdCustom(@Param("id") Long id);
}
