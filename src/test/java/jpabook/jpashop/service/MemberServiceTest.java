package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    private final String TEST_NAME = "kang";

    @Test
    public void 회원가입() throws Exception{
        //given
        Member member = new Member();
        member.setName(TEST_NAME);

        //when
        memberService.join(member);

        //then
        Assertions.assertEquals(member, memberRepository.findOne(member.getId()));
    }

    @Test
    public void 중복_회원_예외() throws Exception {
        //given
        Member memberA = new Member();
        memberA.setName(TEST_NAME);
        Member memberB = new Member();
        memberB.setName(TEST_NAME);

        //when
        memberService.join(memberA);

        //then
        Assertions.assertThrows(IllegalStateException.class,
                () -> memberService.join(memberB)
        );
    }
}