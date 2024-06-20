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
        memberEntity.setPassword(memberDTO.getPassword());
        memberEntity.setName(memberDTO.getName());
        return memberEntity;
    }
// 회원가입시 아이디가 자동 부여 (auto_increament로 인해) 그러나 save를 사용해 update를 하기위해선 하기위해선 Id 값도 같이 넘어와야한다.
    public static memberEntity toUpdateMemberEntity(memberDTO memberDTO){
        memberEntity memberEntity = new memberEntity();
        memberEntity.setId(memberDTO.getId()); // JPA에서 update 쿼리가 정상적으로 실행됨
        memberEntity.setEmail(memberDTO.getEmail());
        memberEntity.setPassword(memberDTO.getPassword());
        memberEntity.setName(memberDTO.getName());
        return memberEntity;
    }
}
