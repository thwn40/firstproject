package com.sjboard.firstproject.repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sjboard.firstproject.domain.Board;
import com.sjboard.firstproject.domain.Member;
import com.sjboard.firstproject.dto.BoardResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Optional;


public interface BoardRepository extends JpaRepository<Board, Long>, QuerydslPredicateExecutor<Board> {




    @Modifying
    @Query("update Board b set b.view = b.view + 1 where b.id = :id")
    int updateView(Long id);


//    @Query(value = "select b from Board b"+
//    " left outer join fetch b.likeCount"+
//    " where b.member =:member",countQuery = "select count(b) from Board b left outer join b.likeCount where b.member = :member"
//   )
    Page<Board> findByMember(@Param("member")Member member, Pageable pageable);
    @Query("select b from Board b"+

            " left outer join fetch b.comments cm"+
            " join fetch cm.member"+
            " where b.id =:id ")
    Board QueryFindById(@Param("id") Long boardId);

    Page<Board> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable);



}
