package toyproject.commu.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import toyproject.commu.domain.Member;
import toyproject.commu.domain.Post;
import toyproject.commu.repository.MemberRepository;
import toyproject.commu.repository.PostRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PostServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    PostService postService;

    @Test
    void post() {
        Member member = createMember();
        memberService.join(member);
        String title = "hi";
        String content = "test text" ;
        Long saveId = postService.post(member.getId(), title, content);

        Assertions.assertThat(postService.findPost(saveId).getContent()).isEqualTo("test text");
    }

    Member createMember(){
        Member member = new Member();
        member.setName("kim");

        return member;
    }
}