package com.sjboard.firstproject.repository;

import com.sjboard.firstproject.domain.Comment;
import com.sjboard.firstproject.domain.Likes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikesRepository extends JpaRepository<Likes,Long> {
    Optional<Likes> findByBoardIdAndMemberId(Long boardId, Long memberId);


}
