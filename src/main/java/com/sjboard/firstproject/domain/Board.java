package com.sjboard.firstproject.domain;

import lombok.Builder;
import lombok.Getter;

import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Board extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String title;

    private String author;

    private String content;

    @OneToMany(mappedBy = "board")
    List<Comment> comments = new ArrayList<>();

    @Builder
    public Board(String author,String title, String content, Member member) {
        this.author = author;
        this.title = title;
        this.content = content;
        this.member = member;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }
    @Column(columnDefinition = "integer default 0", nullable = false)
    private int view;

}
