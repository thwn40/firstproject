package com.sjboard.firstproject.repository;

import com.sjboard.firstproject.domain.Notice;
import org.springframework.data.jpa.repository.JpaRepository;


public interface NoticeRepository extends JpaRepository<Notice,Long> {
}
