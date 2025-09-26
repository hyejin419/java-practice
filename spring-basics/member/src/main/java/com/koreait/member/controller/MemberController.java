package com.koreait.member.controller;


import com.koreait.member.dto.MemberDTO;
import com.koreait.member.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService service;

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String register(MemberDTO member){
        service.register(member);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        Model model){
        if(service.login(username, password, session)) {
            return "redirect:/home";
        }else{
            model.addAttribute("error","로그인실패");
            return "login";
        }
    }

    @GetMapping("/home")
    public String home(HttpSession session, Model model) {
        MemberDTO member = (MemberDTO) session.getAttribute("loginUser");
        if(member == null) return "redirect:/login";
        model.addAttribute("user", member);
        return "home";
    }

    @GetMapping("/update")
    public String updatePage(HttpSession session, Model model) {
        MemberDTO member = (MemberDTO) session.getAttribute("loginUser");
        if(member == null) return "redirect:/login";
        model.addAttribute("member", member);
        return "update";
    }

    @PostMapping("/update")
    public String update(MemberDTO member, HttpSession session) {
        service.update(member);
        session.setAttribute("loginUser", member);
        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        service.logout(session);
        return "redirect:/login";
    }
}
