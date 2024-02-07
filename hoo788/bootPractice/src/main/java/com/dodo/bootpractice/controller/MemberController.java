package com.dodo.bootpractice.controller;

import com.dodo.bootpractice.domain.Member;
import com.dodo.bootpractice.repository.MemberRepository;
import com.dodo.bootpractice.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
@Slf4j
public class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;

    // 회원 전체 조회
    @GetMapping("")
    public String member(Model model) {
        return "/views/member/members";
    }

    // 회원 한명 조회
    @GetMapping("/{id}")
    public String member(@PathVariable Long id, Model model) {
        Member member = memberService.findMember(id);
        model.addAttribute("member", member);
        return "/views/member/members";
    }

    // 회원 등록
    @PostMapping("")
    public String memberJoin(@ModelAttribute Member member, Model model) {
        // 나중에 DTO로 controller -> service단 옮기기.
        memberService.joinMember(member);
        return "redirect:/";
    }

    // 페이지 연습용
    @GetMapping("/paging")
    public String memberPaging(Pageable pageable, Model model) {
        Page<Member> all = memberRepository.findAll(pageable);
        model.addAttribute("members", all);
        return "/views/member/members";
    }
}
