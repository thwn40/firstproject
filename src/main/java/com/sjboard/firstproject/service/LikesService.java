package com.sjboard.firstproject.service;

import com.sjboard.firstproject.domain.Board;
import com.sjboard.firstproject.domain.Likes;
import com.sjboard.firstproject.domain.Member;
import com.sjboard.firstproject.dto.LikesRequestDto;
import com.sjboard.firstproject.repository.BoardRepository;
import com.sjboard.firstproject.repository.LikesRepository;
import com.sjboard.firstproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class LikesService {

    private final NoticeService noticeService;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    private final LikesRepository likesRepository;

    @Transactional
    public Long likeUp(Long boardId,Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(()->{return new IllegalArgumentException("회원이 없습니다");});

        Board board = boardRepository.findById(boardId).orElseThrow(()->{return new IllegalArgumentException("게시글이 없습니다");});




        if (likesRepository.findByBoardIdAndMemberId(boardId, memberId).isEmpty()) {
            Likes likes = Likes.builder().member(member).board(board).build();
            log.info("좋아요 1 증가");
            Long id = likesRepository.save(likes).getId();
            if(member!=board.getMember()){
                noticeService.makeNotice(member,board.getMember(),"내 게시글에 "+member.getName()+"님이 좋아요를 눌렀습니다!");
            }
            return id;
        }
        else{
            return 1L;


        }




    }


}
