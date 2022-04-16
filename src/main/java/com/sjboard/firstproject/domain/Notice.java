package com.sjboard.firstproject.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Getter
@NoArgsConstructor
public class Notice extends BaseTimeEntity{

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long Id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member Sender;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member Receiver;

    @Column(nullable = false)
    private String Content;

    private boolean isChecked;


    @Builder
    public Notice(Member sender, Member receiver, String content) {
        Sender = sender;
        Receiver = receiver;
        Content = content;
    }
}

