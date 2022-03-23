package com.sjboard.firstproject.repository;

import com.sjboard.firstproject.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
