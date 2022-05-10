package com.sjboard.firstproject.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sjboard.firstproject.domain.Member;
import com.sjboard.firstproject.domain.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;


public interface NoticeRepository extends JpaRepository<Notice,Long> {


    @Modifying
    @Query("update Notice n set n.isChecked = true where n.receiver.id = :id")
    void checkingNotice(Long id);


    @Query(value = "select n from Notice n"+
    " join fetch n.receiver"+
            " join fetch n.sender"
    , countQuery = "select count(n) from Notice n"+
            " join n.receiver")
    Page<Notice> findByReceiver(@Param("receiver")Member receiver, Pageable pageable);
}
