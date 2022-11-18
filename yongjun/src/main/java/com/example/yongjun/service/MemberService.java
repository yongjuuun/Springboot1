package com.example.yongjun.service;

import com.example.yongjun.domain.Member;
import com.example.yongjun.repository.MemberRepository;
import com.example.yongjun.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    //    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;

    // DI(Dependency Injection)
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원가입
    // 검증하는 비즈니스 로직 필요
    public Long join(Member member) {

        // 패스워드 검증 -> 짧다 출력( 몇자 이하다 )
        // 휴대폰 검증 -> 있는 휴대폰이면 에러 메세지
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        // Alert message
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });

        memberRepository.findByPhoneNum(member.getPhoneNum())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 등록된 번호입니다.");
                });
    }

    public List<Member> findMembers() {
        // secret access 허용 하는 애가 요청 했는지?
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
