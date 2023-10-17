package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
//이름 직접 붙이기 가능 @Component("memberServiceImpl")
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Autowired
    //Component 어노테이션사용시 사용
    //의존관계 자동 주입
    //기본적으로는 타입이 같은 빈을 찾아서 주입한다.
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //테스트 용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
