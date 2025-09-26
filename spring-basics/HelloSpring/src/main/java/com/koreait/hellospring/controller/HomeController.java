package com.koreait.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("name", "김사과");
        return "home";  //WEB-INF/views/home.jsp  (home)파일명과 일치해야 함.
    }
}
