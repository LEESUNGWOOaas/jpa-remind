package com.member.member.service;

import com.member.member.dto.memberDTO;
import com.member.member.entity.memberEntity;
import com.member.member.repository.memberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public memberDTO login(memberDTO memberDTO) {
        /*
         *   1. 회원이 입력한 이메일로 DB에서 조회를 함
         *   2. DB에서 조회한 비밀번호와 사용자가 입력한 비밀번호가 일하는지 판단
         * */
        Optional<memberEntity> byMemberEmail = memberRepository.findByEmail(memberDTO.getEmail());
        if (byMemberEmail.isPresent()) {
            //조회결과가 있는 것
            memberEntity memberEntity = byMemberEmail.get();
            if (memberEntity.getPassword().equals(memberDTO.getPassword())) {
                //일치
                memberDTO dto = memberDTO.toMemberDTO(memberEntity);
                return dto;
            } else {
                //불일치
                return null;
            }
        } else {
            //조회결과가 없다
            return null;
        }
    }

    public List<memberDTO> findAll() {
        List<memberEntity> memberEntityList = memberRepository.findAll();
        List<memberDTO> memberList = new ArrayList<>();
        for(memberEntity memberEntity: memberEntityList){
            memberList.add(memberDTO.toMemberDTO(memberEntity));
         //   memberDTO DTO = memberDTO.toMemberDTO(memberEntity);
         //   memberList.add(DTO);
        }
        return memberList;
    }

    public memberDTO findById(Long id) {
        Optional<memberEntity> optionalMemberEntity = memberRepository.findById(id);
        if (optionalMemberEntity.isPresent()) {
//            MemberEntity memberEntity = optionalMemberEntity.get();
//            MemberDTO memberDTO = MemberDTO.toMemberDTO(memberEntity);
//            return memberDTO;
            return memberDTO.toMemberDTO(optionalMemberEntity.get());
        } else {
            return null;
        }

    }

    public memberDTO updateForm(String myEmail) {
        Optional<memberEntity> optionalMemberEntity = memberRepository.findByEmail(myEmail);
        if(optionalMemberEntity.isPresent()){
          //  memberEntity memberEntity = optionalMemberEntity.get();
          //  memberDTO DTO = memberDTO.toMemberDTO(memberEntity);
          //  return DTO; 을 한줄로 표기
            return  memberDTO.toMemberDTO(optionalMemberEntity.get());
        }else{
            return null;
        }
    }

    public void update(memberDTO memberDTO) {
         memberRepository.save(memberEntity.toUpdateMemberEntity(memberDTO));//save 는 id가 없으면 insert 로 하고 id가 있는 Entity객체가 오면 update 쿼리 실행
    }

    public void deleteById(Long id) {
        memberRepository.deleteById(id);
    }
}