package com.dodo.bootpractice.service;

import com.dodo.bootpractice.domain.Member;
import com.dodo.bootpractice.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public Member findMember(Long id) {
        return memberRepository.findById(id).orElseThrow(() -> new IllegalStateException("MemberServiceImpl.findMember(id) Error"));
    }

    @Override
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    @Transactional
    @Override
    public void joinMember(Member member) {
        memberRepository.save(member);
    }

    @Transactional
    @Override
    public void updateMember(Long id, Member member) {
        Member findMember = memberRepository.findById(id).orElseThrow(() -> new RuntimeException("MemberService.updateMember(id, member) Error"));
        findMember.updateMember(findMember.getName(), findMember.getPassword(), findMember.getTel(), findMember.getRating(), findMember.getHeight());
        // dirty checking
    }

    @Transactional
    @Override
    public void deleteMember(Long id) {

    }

}
