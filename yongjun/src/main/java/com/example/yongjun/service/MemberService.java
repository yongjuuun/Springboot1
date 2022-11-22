package com.example.yongjun.service;

import com.example.yongjun.domain.Member;
import com.example.yongjun.repository.MemberRepository;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        checkPassword(member);
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

//    중복 회원 체크
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

    //    패스워드 길이 체크
    private void checkPassword(Member member) {
//        if (member.getPassword().length() < 8) {
//            throw new IllegalStateException("패스워드가 8자 미만입니다.");
//        }

        // 길이, 영어
        Pattern passPattern1 = Pattern.compile("^[a-zA-Z].{8,20}$");
//        Pattern passPattern1 = Pattern.compile("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\W).{8,20}$");
        Matcher passMatcher1 = passPattern1.matcher(member.getPassword());

        if(!passMatcher1.find()){
            throw new IllegalStateException("패스워드 영문,특수문자,숫자 포함 8자 이상");
        }

        if(member.getPassword().contains(member.getName())){
            throw new IllegalStateException("ID 포함 불가");
        }
    }


    //    비밀번호 재설정 기능
//    if (폰넘버 or 이름 존재, 일치하면) {
//        pw 재설정? 예외?
//    }

//    public String findByPassword(Member member) {
//        if ( memberRepository.findByName(member.getName()).ifPresent();
//                });
//    }


    public List<Member> findMembers() {
        // secret access 허용 하는 애가 요청 했는지?
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
