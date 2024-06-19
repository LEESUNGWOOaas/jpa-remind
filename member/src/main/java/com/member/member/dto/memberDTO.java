package com.member.member.dto;

import com.member.member.entity.memberEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class memberDTO {
    private Long  id;
    private String email;
    private String password;
    private String name;

    public static memberDTO toMemberDTO(memberEntity memberEntity){
        memberDTO memberDTO = new memberDTO();
        memberDTO.setId(memberEntity.getId());
        memberDTO.setEmail(memberEntity.getEmail());
        memberDTO.setPassword(memberEntity.getPassword());
        memberDTO.setName(memberEntity.getName());
        return memberDTO;
    }
}
