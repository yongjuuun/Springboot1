package com.example.yongjun.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.yongjun.domain.Member;
import com.example.yongjun.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    public void join() throws Exception {
        // Given -> Clinet
        Member member = new Member();
        member.setName("hello");

        // controller -> service

        // service -> repository

        // When
        Long saveId = memberService.join(member);

        // Then
        Member findMember = memberRepository.findById(saveId).get();
        assertEquals(member.getName(), findMember.getName());
    }

    @Test
    public void checkDuplicateMember() {
        Member member1 = new Member();
        member1.setName("java1");
        member1.setPhoneNum("01012345678");

        Member member2 = new Member();
        member2.setName("java2");
        member2.setPhoneNum("01012348");

        memberService.join(member1);

        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 등록된 번호입니다.");
    }

    @Test
    public void checkPassword() {
        Member member1 = new Member();
        member1.setPassword("javaJAVA");
        member1.setName("java");

        memberService.join(member1);

        IllegalStateException e = assertThrows((IllegalStateException.class),
                () -> memberService.join(member1));

        assertThat(e.getMessage()).isEqualTo("ID 포함 불가");
    }
}
