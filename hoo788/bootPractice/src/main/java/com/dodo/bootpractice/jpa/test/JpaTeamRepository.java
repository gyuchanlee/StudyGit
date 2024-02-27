package com.dodo.bootpractice.jpa.test;

import com.dodo.bootpractice.jpa.domain.JpaMember;
import com.dodo.bootpractice.jpa.domain.JpaTeam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaTeamRepository extends JpaRepository<JpaTeam, Long> {
}
