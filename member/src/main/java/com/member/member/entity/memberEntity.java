package com.member.member.entity;

import com.member.member.dto.memberDTO;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name= "member")
public class memberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//auto_increament
    private Long id;

    @Column(unique = true)
    private String email;
    @Column
    private String password;
    @Column
    private String name;

    public static memberEntity toMemberEntity(memberDTO memberDTO){
        memberEntity memberEntity = new memberEntity();
        memberEntity.setEmail(memberDTO.getEmail());
        memberEntity.setId(memberDTO.getId());
        memberEntity.setPassword(memberDTO.getPassword());
        memberEntity.setName(memberDTO.getName());
        return memberEntity;
    }
}
