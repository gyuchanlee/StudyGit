package com.dodo.bootpractice.repository;

import com.dodo.bootpractice.domain.Member;

import java.util.List;

public interface MemberRepositoryCustom {

    List<Member> getMembers();

    Member getMemberById(Long id);
}
