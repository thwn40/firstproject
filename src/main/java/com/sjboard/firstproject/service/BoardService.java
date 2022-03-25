package com.sjboard.firstproject.service;


import com.sjboard.firstproject.domain.Board;
import com.sjboard.firstproject.dto.BoardResponseDto;
import com.sjboard.firstproject.dto.BoardSaveDto;
import com.sjboard.firstproject.dto.BoardUpdateDto;
import com.sjboard.firstproject.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    //게시글 저장
    @Transactional
    public Long save(BoardSaveDto boardSaveDto) {
        return boardRepository.save(boardSaveDto.toEntity()).getId();
    }

    //게시글 수정
    @Transactional
    public Long update(Long id, BoardUpdateDto boardUpdateDto) {

        Optional<Board> byId = boardRepository.findById(id);
        if (byId.isEmpty()) {
            throw new IllegalStateException("게시글이 없습니다");
        } else {
            byId.get().update(boardUpdateDto.getTitle(), boardUpdateDto.getContent());
            return id;
        }
    }
    @Transactional(readOnly = true)
    public List<BoardResponseDto> findAllDesc() {
        return boardRepository.findAll(Sort.by(Sort.Direction.DESC, "id")).stream().map(BoardResponseDto :: new).collect(Collectors.toList());
    }

    //게시글 조회
    public BoardResponseDto findById(Long id) {
        Optional<Board> byId = boardRepository.findById(id);

        if (byId.isEmpty()) {
            throw new IllegalStateException("게시글이 없습니다");
        } else {
            return new BoardResponseDto(byId.get());
        }
    }

    //게시글 삭제
    @Transactional
    public void deleteById(Long id) {
        Optional<Board> byId = boardRepository.findById(id);

        if (byId.isEmpty()) {
            throw new IllegalStateException("게시글이 없습니다");
        } else {
            boardRepository.delete(byId.get());
        }
    }

    @Transactional
    public int updateView(Long id) { return boardRepository.updateView(id); }

}

