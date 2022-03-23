package com.sjboard.firstproject.repository;

import com.sjboard.firstproject.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {

}
