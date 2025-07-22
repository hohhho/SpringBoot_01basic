package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // **회원 가입**
    public Long join(Member member){

        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    // 중복 회원 검증 메서드
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {   // ifPresent : 값이 있으면
                    throw new IllegalStateException("이미 존재하는 이름입니다."); // IllegalStateException : 표준 예외. 대상 객체의 상태가 호출된 메서드를 수행하기 적절하지 않을 때 발생시킬 수 있음
                });
    }


    // **전체 회원 조회**
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    // **단일 회원 조회**
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }



}
