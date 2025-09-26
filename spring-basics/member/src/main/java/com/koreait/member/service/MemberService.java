package com.koreait.member.service;

import com.koreait.member.dto.MemberDTO;
import jakarta.servlet.http.HttpSession;

public interface MemberService {
    void register(MemberDTO member);
    boolean login(String username, String password, HttpSession session);
    void update(MemberDTO member);
    void logout(HttpSession session);
}
