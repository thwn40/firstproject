package com.sjboard.firstproject.domain;

import lombok.Builder;
import lombok.Getter;

import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.Transactional;

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

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(columnDefinition = "integer default 0", nullable = false)
    private int view;

    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
    List<Likes> likeCount = new ArrayList<>();


    @OneToMany(mappedBy = "board", fetch =FetchType.EAGER, cascade = CascadeType.REMOVE)
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

    public void LikesCountUp(Likes like){
        likeCount.add(like);
    }



}
