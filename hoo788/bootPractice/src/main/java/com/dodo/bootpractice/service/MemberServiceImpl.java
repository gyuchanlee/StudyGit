package com.dodo.bootpractice.service;

import com.dodo.bootpractice.controller.dto.MemberDto;
import com.dodo.bootpractice.domain.Member;
import com.dodo.bootpractice.exception.CustomMemberException;
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
    private final PasswordEncoder passwordEncoder;

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
    public void joinMember(MemberDto memberDto) {

        // custom exception 임의 발생
        if (memberDto.getUserId().equals("error")) {
            throw new CustomMemberException("회원 등록 중 발생한 커스텀 exception!!!");
        }

        Member member = Member.builder()
            .userId(memberDto.getUserId())
            .name(memberDto.getName())
            .password(passwordEncoder.encode(memberDto.getPassword())) // Bcrypt로 인코딩해서 넣기.
            .tel(memberDto.getTel())
            .rating(memberDto.getRating())
            .height(memberDto.getHeight())
            .build();


        memberRepository.save(member);
    }

    @Transactional
    @Override
    public void updateMember(Long id, MemberDto memberDto) {
        Member findMember = memberRepository.findById(id).orElseThrow(() -> new RuntimeException("MemberService.updateMember(id, memberDto) Error"));
        // dirty checking
        findMember.updateMember(memberDto.getName(), memberDto.getPassword(), memberDto.getEmail(), memberDto.getTel(), memberDto.getRating(), memberDto.getHeight());
    }

    @Transactional
    @Override
    public void deleteMember(Long id) {

    }

}
