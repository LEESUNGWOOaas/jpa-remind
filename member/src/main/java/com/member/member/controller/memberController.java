package com.member.member.controller;

import com.member.member.dto.memberDTO;
import com.member.member.service.memberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/member/{id}")
    public String findById(@PathVariable("id") Long id, Model model) {
        memberDTO memberDTO = memberService.findById(id);
        model.addAttribute("member", memberDTO);
        return "memberDetail";
    }
    @GetMapping("/member/update")
        public String updateForm(HttpSession session,Model model){
        //getAttribute가 object 니깐 캐스팅해준다
        String myEmail = (String)session.getAttribute("loginEmail");
        memberDTO memberDTO = memberService.updateForm(myEmail);
        model.addAttribute("memberDTO",memberDTO);
        return "update";
    }
    @PostMapping("/member/update")
        public String update(@ModelAttribute memberDTO memberDTO) {
            memberService.update(memberDTO);
        //return "memberDetail" redirect로 다른 메서드 주소 요청
        return "redirect:/member/"+memberDTO.getId();
    }
    @GetMapping("/member/delete/{id}")
        public String delete(@PathVariable("id") Long id){
            memberService.deleteById(id);

        return "redirect:/list";
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
    @GetMapping("/member/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "index";
    }

}
