package toyproject.commu.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import toyproject.commu.domain.Member;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {
    @Autowired
    MemberService memberService;

    @Test
    @Transactional
    void join() {
        System.out.println("hi, test!");
        // given
        Member m1 = createMemberForDupTest();
        Member m2 = createMemberForDupTest();

        Long saveId = memberService.join(m1);
        Member findMember = memberService.findMember(saveId);
        // when
        // 1) Duplicate Validate
        IllegalStateException e = assertThrows(IllegalStateException.class,
                ()->memberService.join(m2));
        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        // then
        Assertions.assertThat(findMember).isEqualTo(m1);
        Assertions.assertThat(findMember.getId()).isEqualTo(m1.getId());
        Assertions.assertThat(findMember.getName()).isEqualTo(m1.getName());
    }

    Member createMemberForDupTest(){
        Member m = new Member();
        m.setName("kim");
        return m;
    }


}