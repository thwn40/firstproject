package com.sjboard.firstproject.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String loginId;

    private String password;

    @OneToMany
    private List<Board> boardList = new ArrayList<>();

    @OneToMany
    private List<Comment> commentList = new ArrayList<>();





}
