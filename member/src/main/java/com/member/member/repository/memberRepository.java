package com.member.member.repository;

import com.member.member.entity.memberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface memberRepository extends JpaRepository<memberEntity, Long> {
        // 이메일로 회원 정보 조회
        Optional<memberEntity> findByEmail(String email);


}
