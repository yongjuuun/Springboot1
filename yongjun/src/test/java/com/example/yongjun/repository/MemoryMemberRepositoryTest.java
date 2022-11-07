package com.example.yongjun.repository;

import com.example.yongjun.domain.Member;
//import org.junit.jupiter.api.Assertions;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @Test
    void save() {
        // given
        Member member = new Member();
        member.setName("yj");

        // when
        repository.save(member);

        // then
        Member result = repository.findById(member.getId()).get();
//        Assertions.assertEquals(member, result);
        assertThat(result).isEqualTo(member);
    }

    @Test
    void findById() {
    }

    @Test
    void findByName() {
        Member member1 = new Member();
        member1.setName("yj1");

        repository.save(member1);

        Member member2 = new Member();
        member2.setName("yj2");

        repository.save(member2);

        // when
        Member result = repository.findByName("yj1").get();

        // then
//        Assertions.assertEquals(member, result);
        assertThat(result).isEqualTo(member1);
    }

    @Test
    void findAll() {
        // given
        Member member1 = new Member();
        member1.setName("yj1");

        repository.save(member1);

        Member member2 = new Member();
        member2.setName("yj2");

        repository.save(member2);

        // when
        List<Member> result = repository.findAll();

        // then
//        Assertions.assertEquals(member, result);
        assertThat(result.size()).isEqualTo(2);
    }
}