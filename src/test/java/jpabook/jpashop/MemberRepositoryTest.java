package jpabook.jpashop;

import jdk.jfr.Description;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
class MemberRepositoryTest {

    @Autowired MemberRepository memberRepository;

    @Test
    @Description("member entity save test")
    @Transactional
    @Rollback(true)
    public void testMember(){
        //given
        Member member = new Member();
        member.setUsername("kang");

        //when
        memberRepository.save(member);
        Member findMember = memberRepository.find(member.getId());

        //then
        Assertions.assertThat(findMember).isEqualTo(member);
    }

}