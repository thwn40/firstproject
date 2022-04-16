package com.sjboard.firstproject.repository;

import com.sjboard.firstproject.domain.Comment;
import com.sjboard.firstproject.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByBoardId(Long boardId);

    Page<Comment> findByMember(Member member, Pageable pageable);



    }


