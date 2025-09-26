package com.koreait.member.service;

import com.koreait.member.dto.MemberDTO;
import com.koreait.member.mapper.MemberMapper;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberMapper mapper;

    @Override
    public void register(MemberDTO member) {
        mapper.save(member);
    }

    @Override
    public boolean login(String username, String password, HttpSession session) {
        MemberDTO member = mapper.findByUsername(username);
        if (member != null && StringUtils.hasText(password) && password.equals(member.getPassword())) {
            session.setAttribute("loginUser", member);
            return true;
        }
        return false;
    }

    @Override
    public void update(MemberDTO member) {
        mapper.update(member);
    }

    @Override
    public void logout(HttpSession session) {
        session.invalidate();
    }
}
