package com.sjboard.firstproject.repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sjboard.firstproject.domain.Board;
import com.sjboard.firstproject.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;


public interface BoardRepository extends JpaRepository<Board, Long>, QuerydslPredicateExecutor<Board> {


    @Modifying
    @Query("update Board b set b.view = b.view + 1 where b.id = :id")
    int updateView(Long id);

    @Modifying
    @Query("update Board b set b.view = b.view - 1 where b.id = :id")
    int minusView(Long id);

    Page<Board> findByMember(Member member, Pageable pageable);

    Page<Board> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable);



}
