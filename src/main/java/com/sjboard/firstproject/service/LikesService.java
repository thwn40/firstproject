package com.sjboard.firstproject.service;

import com.sjboard.firstproject.domain.Board;
import com.sjboard.firstproject.domain.Likes;
import com.sjboard.firstproject.domain.Member;
import com.sjboard.firstproject.dto.LikesRequestDto;
import com.sjboard.firstproject.repository.BoardRepository;
import com.sjboard.firstproject.repository.LikesRepository;
import com.sjboard.firstproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LikesService {

    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    private final LikesRepository likesRepository;

    @Transactional
    public Long likeUp(Long boardId,Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(()->{return new IllegalArgumentException("회원이 없습니다");});

        Board board = boardRepository.findById(boardId).orElseThrow(()->{return new IllegalArgumentException("게시글이 없습니다");});

        if (likesRepository.findByMemberIdAndBoardId(boardId, memberId) == null) {
            Likes likes = Likes.builder().member(member).board(board).build();
            board.LikesCountUp(likes);
            return likesRepository.save(likes).getId();
        }
        else{
            throw new IllegalArgumentException("권한이 없습니다");
        }




    }


}
