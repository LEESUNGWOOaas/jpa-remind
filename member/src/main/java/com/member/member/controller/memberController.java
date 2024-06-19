package com.member.member.controller;

import com.member.member.dto.memberDTO;
import com.member.member.service.memberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class memberController {

    private final memberService memberService;

    @GetMapping("/member/save")
    public String saveForm(){
        return "save";
    }
    @PostMapping("/member/save")
    public String save(@ModelAttribute memberDTO memberDTO){
        System.out.println("MemberController.save");
        System.out.println("memberDTO"+memberDTO);
        memberService.save(memberDTO);
        return "login";
    }
    @GetMapping("/member/")
        public String findAll(Model model){
            List<memberDTO> list = memberService.findAll();
            model.addAttribute("list",list);
            return "list";
        }
    @GetMapping()
        public String detail(@PathVariable Long id,Model model){
            memberDTO memberDTO = memberService.findById(id);
            model.addAttribute("member",memberDTO);
        return "memberDetail";
    }
    @GetMapping("/member/login")
    public String loginForm(){
        return "login";
    }
    @PostMapping("/member/login")
    public String login(@ModelAttribute memberDTO memberDTO,HttpSession session){
        memberDTO loginResult = memberService.login(memberDTO);
        if(loginResult != null){
            // 성공
            session.setAttribute("loginEmail",loginResult.getEmail());
            return "index";
        }else{
            //실패
            return "login";
        }
    }



}
