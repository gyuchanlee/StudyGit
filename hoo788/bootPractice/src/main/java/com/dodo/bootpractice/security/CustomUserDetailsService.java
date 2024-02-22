package com.dodo.bootpractice.security;

import com.dodo.bootpractice.domain.Member;
import com.dodo.bootpractice.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;


@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        Member findMember = memberRepository.findByUserId(userId).orElseThrow(() -> new UsernameNotFoundException("userId가 없다"));

        return User.builder()
                .username(findMember.getUserId())
                .password(findMember.getPassword())
                .disabled(!findMember.isEnabled()) // 사용자에 의해 확정되지 않았다면 비활성으로 분류
                .accountExpired(!findMember.isAccountNonExpired()) // 만료 여부
                .accountLocked(!findMember.isAccountNonLocked()) // 잠금 여부
                .roles("USER")
                .build();
    }
}
