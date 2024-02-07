package com.dodo.bootpractice.service;

import com.dodo.bootpractice.domain.Member;
import org.springframework.stereotype.Service;

import java.util.List;

public interface MemberService {
    // 회원 한건 조회
    Member findMember(Long id);
    // 회원 전체 조회
    List<Member> findMembers();
    // 회원 등록
    void joinMember(Member member);
    // 회원 수정
    void updateMember(Long id, Member member);
    // 회원 삭제
    void deleteMember(Long id);
}
