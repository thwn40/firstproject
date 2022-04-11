package com.sjboard.firstproject.repository;

import com.sjboard.firstproject.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByName(String name);
    Member findByLoginId(String loginId);
}
