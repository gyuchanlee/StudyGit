package com.dodo.bootpractice.jpa.test;

import com.dodo.bootpractice.domain.Member;
import com.dodo.bootpractice.jpa.domain.JpaMember;
import com.dodo.bootpractice.jpa.domain.JpaTeam;
import com.dodo.bootpractice.jpa.domain.cascade.Child;
import com.dodo.bootpractice.jpa.domain.cascade.Parent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/jpa")
@RequiredArgsConstructor
@Slf4j
public class JpaTestController {

    private final JpaMemberRepository jpaMemberRepository;
    private final JpaTeamRepository jpaTeamRepository;

    @PersistenceContext
    EntityManager em;

    @PostMapping("/testTeamSave")
    public List<JpaMember> testTeamSave() {

        JpaTeam jpaTeam = new JpaTeam();
        jpaTeam.setName("teamA");
        jpaTeamRepository.save(jpaTeam);

        JpaMember member1 = new JpaMember();
        member1.setUsername("member1");
        member1.setTeam(jpaTeam);
        JpaMember member2 = new JpaMember();
        member2.setUsername("member2");
        member2.setTeam(jpaTeam);
        jpaMemberRepository.save(member1);
        jpaMemberRepository.save(member2);

        return jpaMemberRepository.findAll();
    }

    @GetMapping("/test1")
    public List<JpaMember> test1() {
        JpaTeam findTeam = jpaTeamRepository.findById(1L).get();
        List<JpaMember> members = findTeam.getMembers();
        for (JpaMember member : members) {
            System.out.println("member.getUsername() = " + member.getUsername());
        }
        return members;
    }

    @GetMapping("/proxy1")
    public JpaMember proxy1() {
        JpaMember member = jpaMemberRepository.findById(1L).orElseThrow(() -> new RuntimeException("오류임!!"));
        System.out.println("일반 member 객체 탐색 1 = " + member.getUsername());
        System.out.println("연관 team 객체 탐색 = " + member.getTeam());
        System.out.println("연관 team 객체 탐색 + 실제 사용 = " + member.getTeam().getName());

        return member;
    }

    @GetMapping("/cascade1")
    @Transactional
    public String cascade1() {

        // 부모 저장
        Parent parent = new Parent();
        // 1번 자식 저장
        Child child1 = new Child();
        child1.setParent(parent); // 자식 -> 부모 연관관계 설정
        parent.getChildren().add(child1); // 연관관계 편의 메서드 역할
        // 2번 자식 저장
        Child child2 = new Child();
        child2.setParent(parent); // 자식 -> 부모 연관관계 설정
        parent.getChildren().add(child2); // 연관관계 편의 메서드 역할

        // cascade :  부모가 저장되며 연관된 자식도 함께 저장
        em.persist(parent);
//        em.persist(child1);
//        em.persist(child2);
        
        return "cascade study";
    }

    @Transactional
    @GetMapping("/paging1")
    public String paging1() {
        List<JpaMember> list = em.createQuery("select m from JpaMember m join fetch m.team order by m.username desc ", JpaMember.class)
                .setFirstResult(0)
                .setMaxResults(5)
                .getResultList();
        return "ok";
    }
}
