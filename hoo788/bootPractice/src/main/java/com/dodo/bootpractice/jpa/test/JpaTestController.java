package com.dodo.bootpractice.jpa.test;

import com.dodo.bootpractice.domain.Member;
import com.dodo.bootpractice.jpa.domain.JpaMember;
import com.dodo.bootpractice.jpa.domain.JpaTeam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/jpa")
@RequiredArgsConstructor
@Slf4j
public class JpaTestController {

    private final JpaMemberRepository jpaMemberRepository;
    private final JpaTeamRepository jpaTeamRepository;

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
}
