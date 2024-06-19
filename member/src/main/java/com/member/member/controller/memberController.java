package com.member.member.controller;

import com.member.member.dto.memberDTO;
import com.member.member.service.memberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
        return null;
    }
}
