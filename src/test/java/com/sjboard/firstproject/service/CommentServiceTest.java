//package com.sjboard.firstproject.service;
//
//import com.sjboard.firstproject.domain.Board;
//import com.sjboard.firstproject.domain.Comment;
//import com.sjboard.firstproject.domain.Member;
//import com.sjboard.firstproject.domain.Role;
//import com.sjboard.firstproject.repository.BoardRepository;
//import com.sjboard.firstproject.repository.CommentRepository;
//import com.sjboard.firstproject.repository.MemberRepository;
//import org.junit.Test;
//import org.junit.jupiter.api.Assertions;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.test.context.support.WithUserDetails;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.context.transaction.BeforeTransaction;
//
//import javax.transaction.Transactional;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.assertj.core.api.Assertions.fail;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@Transactional
//public class CommentServiceTest {
//    @Autowired
//    CommentService commentService;
//    @Autowired
//    MemberRepository memberRepository;
//    @Autowired
//    BoardRepository boardRepository;
//    @Autowired
//    CommentRepository commentRepository;
//
//    //member 필드
//    private static Long memberId = 1L;
//    private static final String loginId = "test@test.com";
//    private static final String name = "테스트";
//    private static final Role role = Role.USER;
//    private Long id;
//
//
//
//    @Test
//    public void 부모댓글_저장(){
//        //given
//        id = memberRepository.save(Member.builder()
//                .name(name)
//                .loginId(loginId)
//                .password("password")
//                .role(role)
//                .build()).getId();
//
//        Member member =  memberRepository.getById(id);
//        Board saveBoard = boardRepository.save(Board.builder().title("asdf").content("asdf").author(member.getName()).member(member).build());
//        String content = "asdf";
//
//        //when
//        commentService.commentParentSave(content,id,saveBoard.getId());
//
//        //then
//        List<Comment> all = commentRepository.findAll();
//        assertThat(all.get(0).getMember().getName()).isEqualTo(member);
//        assertThat(all.get(0).getContent()).isEqualTo(content);
//    }
//
//    @Test
//    public void 자식댓글_저장(){
//        //given
//        id = memberRepository.save(Member.builder()
//                .name(name)
//                .loginId(loginId)
//                .password("password")
//                .role(role)
//                .build()).getId();
//        Member member =  memberRepository.getById(id);
//        Board saveBoard = boardRepository.save(Board.builder().title("asdf").content("asdf").author(member.getName()).member(member).build());
//        String content = "asdf";
//
//        //when
//        Long parentId = commentService.commentParentSave(content, id, saveBoard.getId());
//        commentService.commentChildrenSave("asdf",id, saveBoard.getId(), parentId);
//
//        //then
//        List<Comment> children = commentService.findById(parentId).getChildren();
//        assertThat(children.get(0).getContent()).isEqualTo("asdf");
//    }
//
//    @Test
//    public void 부모없이_자식댓글_저장하면_안된다(){
//        Board saveBoard = boardRepository.save(Board.builder().title("asdf").content("asdf").author("Asdf").member(new Member()).build());
//        Assertions.assertThrows(IllegalArgumentException.class, () ->commentService.commentChildrenSave("asdf", 1L, 1L, 560L));
//
//    }
//
//}
