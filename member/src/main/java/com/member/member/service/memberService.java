package com.member.member.service;

import com.member.member.dto.memberDTO;
import com.member.member.entity.memberEntity;
import com.member.member.repository.memberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class memberService {

    private final memberRepository memberRepository;

    public void save(memberDTO memberDTO) {
        /*1. dto -> entity
        * 2. repository save method call
        * */
        memberEntity memberEntity = com.member.member.entity.memberEntity.toMemberEntity(memberDTO);
        memberRepository.save(memberEntity);
    }
}
