package com.dodo.bootpractice.jpa.test;

import com.dodo.bootpractice.jpa.domain.JpaMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMemberRepository extends JpaRepository<JpaMember, Long> {
}
