package com.example.yongjun.repository;

import com.example.yongjun.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    Member save(Member member);

    Optional<Member> findById(Long id);

    Optional<Member> findByName(String name);

    Optional<Member> findByEmail(String email);

    Optional<Member> findByPhoneNum(String phoneNum);

    List<Member> findAll();

    void clearStore();
}
