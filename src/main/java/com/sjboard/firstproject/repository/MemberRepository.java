package com.sjboard.firstproject.repository;

import com.sjboard.firstproject.domain.Member;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByName(String name);

    Optional<Member> findByLoginId(String loginId);

    boolean existsByName(String name);
    boolean existsByLoginId(String loginId);
}
