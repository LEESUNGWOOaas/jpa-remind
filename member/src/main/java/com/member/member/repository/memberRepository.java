package com.member.member.repository;

import com.member.member.entity.memberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface memberRepository extends JpaRepository<memberEntity, Long> {
}
