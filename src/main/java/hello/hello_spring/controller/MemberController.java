package hello.hello_spring.controller;

import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired  // 의존성 주입(DI) 중에서 생성자 주입 => 의존 관계가 실행중에 동적으로 변하는 경우는 거의 없으므로 생성자 주입을 권장
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
