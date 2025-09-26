package com.koreait.jpa.service;

import com.koreait.jpa.entity.MemberEntity;
import com.koreait.jpa.repository.MemberRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    public CustomUserDetailsService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        MemberEntity member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("사용자 없음"));

        // Security가 제공하는 UserDetails의 기본 구현 클래스
        // 사용자ID, 암호화된 비밀번호, 권한(없을 경우 빈 리스트)
        // 권한: ROLE_USER, ROLE_ADMIN
        return new User(member.getUsername(),
                member.getPassword(), Collections.emptyList());
    }
}