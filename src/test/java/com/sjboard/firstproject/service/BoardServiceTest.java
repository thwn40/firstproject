package com.sjboard.firstproject.service;

import com.sjboard.firstproject.domain.Board;
import com.sjboard.firstproject.domain.Member;
import com.sjboard.firstproject.domain.Role;
import com.sjboard.firstproject.dto.BoardSaveDto;
import com.sjboard.firstproject.dto.BoardUpdateDto;
import com.sjboard.firstproject.repository.BoardRepository;
import com.sjboard.firstproject.repository.MemberRepository;

import org.junit.Before;
import org.junit.Test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import org.springframework.security.test.context.support.WithUserDetails;

import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BoardServiceTest {
    //member 필드
    private static Long memberId = 1L;
    private static final String loginId = "test@test.com";
    private static final String name = "테스트";
    private static final Role role = Role.USER;


    @LocalServerPort
    private int port;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private BoardService boardService;

    @Autowired
    private MemberRepository memberRepository;


    @Autowired
    private WebApplicationContext context;
    Member member;
    Long save;
    @AfterEach
    void tearDown() throws Exception {
       memberRepository.delete(member);
       boardRepository.deleteById(save);
    }

    @Test
    public void Board_로그인을_해야_등록된다() throws Exception {
         Member member = Member.builder()
                .name(name)
                .loginId(loginId)
                .password("password")
                .role(role)
                .build();
        member = memberRepository.save(member);

        String title = "title";
        String content = "content";
        BoardSaveDto requestDto = BoardSaveDto.builder()
                .title(title)
                .content(content)
                .build();


        //given


        save = boardService.save(requestDto, member);

        //then
        List<Board> all = boardRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);
    }

    @Test
    public void Board_수정된다() throws Exception {
        Member member = Member.builder()
                .name(name)
                .loginId(loginId)
                .password("password")
                .role(role)
                .build();
        member = memberRepository.save(member);

        Board saveBoard = boardRepository.save(Board.builder().title("asdf").content("asdf").author(member.getName()).member(member).build());

        //given
        String expectedTitle = "title";
        String expectedContent = "content";
        BoardUpdateDto boardUpdateDto = BoardUpdateDto.builder().
        title(expectedTitle)
                .content(expectedContent).build();

        save = boardService.update(saveBoard.getId(), boardUpdateDto);

        //then
        List<Board> all = boardRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
        assertThat(all.get(0).getContent()).isEqualTo(expectedContent);
    }


}