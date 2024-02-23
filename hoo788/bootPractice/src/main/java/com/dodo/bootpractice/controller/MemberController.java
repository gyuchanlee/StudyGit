package com.dodo.bootpractice.controller;

import com.dodo.bootpractice.controller.dto.MemberDto;
import com.dodo.bootpractice.domain.Member;
import com.dodo.bootpractice.exception.CustomMemberException;
import com.dodo.bootpractice.repository.MemberRepository;
import com.dodo.bootpractice.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
@Slf4j
public class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;

    // 예외 처리
//    @ExceptionHandler({CustomMemberException.class})
//    public ResponseEntity<String> memberException(CustomMemberException ex) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Error", "CustomMemberException");
//
//        log.info("CustomMemberException Exception 발생 : ", ex);
//
//        return new ResponseEntity<>(ex.toString(), headers, HttpStatus.BAD_REQUEST);
//    }


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
        return "/views/member/memberOne";
    }

    // 회원 등록 페이지
    @GetMapping("/join")
    public String memberJoinPage(Model model) {
        return "/views/member/memberJoinForm";
    }

    // 회원 등록
    @PostMapping("")
    public String memberJoin(@ModelAttribute @Validated MemberDto memberDto) {
        memberService.joinMember(memberDto);
        return "redirect:/";
    }

    // 회원 수정
    @PutMapping("/{id}")
    public String memberUpdate(@PathVariable("id") Long id, @ModelAttribute @Validated MemberDto memberDto) {
        memberService.updateMember(id, memberDto);
        return "redirect:/";
    }

    // 페이지 연습용
    @GetMapping("/paging")
    @ResponseBody
    public Page<Member> memberPaging(Pageable pageable) {
        Page<Member> all = memberRepository.findAll(pageable);
        return all;
    }
}
