package com.example.yongjun.repository;

import com.example.yongjun.domain.Member;
//import org.junit.jupiter.api.Assertions;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    void save() {
        // given
        Member member = new Member();
        member.setName("nameTest1");
        member.setPassword("1234");
//        member.setEmail("spring@boot.com");
        member.setPhoneNum("01012345678");

        // when
        repository.save(member);

        // then
        Member result = repository.findById(member.getId()).get();
        assertThat(result).isEqualTo(member);
//        Assertions.assertEquals(member, result);
    }

    @Test
    void findById() {
        Member member1 = new Member();
        member1.setName("nameTest1");
        repository.save(member1);

        Member result = repository.findById(1L).get();
        assertThat(result).isEqualTo(member1);
    }

    @Test
    void findByName() {
        Member member1 = new Member();
        member1.setName("nameTest1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("nameTest2");
        repository.save(member2);

        // when
        Member result = repository.findByName("nameTest2").get();

        // then
//        Assertions.assertEquals(member, result);
        assertThat(result).isEqualTo(member2);
    }

    @Test
    void findAll() {
        // given
        Member member1 = new Member();
        member1.setName("nameTest1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("nameTest2");
        repository.save(member2);

        // when
        List<Member> result = repository.findAll();

        // then
//        Assertions.assertEquals(member, result);
        assertThat(result.size()).isEqualTo(2);
    }

    @Test
    void findByEmail() {
        // given
        Member member1 = new Member();
        member1.setName("nameTest1");
        member1.setEmail("spring1@boot.com");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("nameTest2");
        member2.setEmail("spring2@boot.com");
        repository.save(member2);

        // when
        Member result = repository.findByEmail("spring2@boot.com").get();

        // then
        assertThat(result).isEqualTo(member2);
    }

    @Test
    void findByPhoneNum() {
        // given
        Member member1 = new Member();
        member1.setPhoneNum("01012345678");
        repository.save(member1);

        Member result = repository.findByPhoneNum("01012345678").get();

        // then
        assertThat(result).isEqualTo(member1);
    }
}