package com.sjboard.firstproject.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Member extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false, length = 30)
    private String loginId;

    @Column(nullable = false, length = 30)
    private String name;

    @Column(nullable = false)
    private String password;


    private String role;

    @OneToMany(mappedBy = "member")
    private List<Likes> likeList = new ArrayList<>();



    @OneToMany(mappedBy = "member",  cascade = CascadeType.REMOVE)
    private List<Board> boardList = new ArrayList<>();

    @OneToMany(mappedBy = "member",  cascade = CascadeType.REMOVE)
    private List<Comment> commentList = new ArrayList<>();

    @Builder
    public Member(String loginId, String name, String password, String role) {
        this.loginId = loginId;
        this.name = name;
        this.password = password;
        this.role = role;
    }
}
