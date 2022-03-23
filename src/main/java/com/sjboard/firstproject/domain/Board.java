package com.sjboard.firstproject.domain;

import lombok.Getter;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String title;

    private String Content;

    @OneToMany(mappedBy = "board")
    List<Comment> comments = new ArrayList<>();

    @CreatedDate
    private LocalDateTime createdDate;


}
