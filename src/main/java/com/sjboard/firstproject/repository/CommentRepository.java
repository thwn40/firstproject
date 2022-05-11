package com.sjboard.firstproject.repository;

import com.sjboard.firstproject.domain.Comment;
import com.sjboard.firstproject.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {


    @Query("select distinct c from Comment c"+
            " join fetch c.member" +
            " left outer join fetch c.children"+
        " where c.board.id=:id")
    List<Comment> findByBoardId(@Param("id")Long boardId);

    Page<Comment> findByMember(Member member, Pageable pageable);



    }


