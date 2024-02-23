package com.dodo.bootpractice.controller;

import com.dodo.bootpractice.domain.Member;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "views/security/login";
    }

//    @PostMapping("/login")
//    public String loginProc() {
//
//        // 로그인 로직
//        System.out.println("???????????");
//
//        return "redirect:/login_success";
//    }

    @PostMapping("/login_success")
    public String login_success(HttpServletRequest request) {

        // 현재 인증된 사용자 정보 가져오기
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        // 사용자 이름 가져오기 > member의 userId값
        String username = ((UserDetails) auth.getPrincipal()).getUsername();


        // 세션에 사용자 이름 저장
        HttpSession session = request.getSession();
        session.setAttribute("username", username);

        return "redirect:/";
    }

    @PostMapping("/login_failure")
    public String login_failure() {

        System.out.println("로그인 실패 error 발생!!!!!!!");

        return "redirect:/login";
    }

}
