package com.koreait.jpa.service;

import com.koreait.jpa.entity.MemberEntity;
import com.koreait.jpa.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void save(MemberEntity member) {
        memberRepository.save(member);
    }

    public MemberEntity findByUsername(String username) {
        return memberRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
    }

    public void update(String username, String name, String encodedPassword) {
        MemberEntity member = findByUsername(username);
        member.setName(name);
        member.setPassword(encodedPassword);
        memberRepository.save(member);
    }
}