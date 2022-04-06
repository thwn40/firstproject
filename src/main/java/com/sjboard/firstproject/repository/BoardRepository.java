package com.sjboard.firstproject.repository;

import com.sjboard.firstproject.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface BoardRepository extends JpaRepository<Board, Long> {


    @Modifying
    @Query("update Board b set b.view = b.view + 1 where b.id = :id")
    int updateView(Long id);

    @Modifying
    @Query("update Board b set b.view = b.view - 1 where b.id = :id")
    int minusView(Long id);

    Page<Board> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable);

}
