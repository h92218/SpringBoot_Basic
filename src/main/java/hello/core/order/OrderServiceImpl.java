package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
//@RequiredArgsConstructor : final이 붙은 필수 필드를 파라미터로 받는 생성자를 만들어줌.
public class OrderServiceImpl implements OrderService{

    /* 필드 주입
    @Autowired private MemberRepository memberRepository;
    @Autowired private DiscountPolicy discountPolicy;
    */
   

    /*생성자 주입*/
    //final 키워드를 사용하여 생성자에서만 값을 넣어줄 수 있음
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    /* @RequiredArgsConstructor 붙였으므로 생략
    @Autowired
    //생성자가 한개만 있으면 @Autowired를 생략해도 됨
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
    */
     

    
    
    /* 수정자 주입
    private MemberRepository memberRepository;
    private DiscountPolicy discountPolicy;
   
    @Autowired(required = false)
    public void setMemberRepository(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    @Autowired(required = false)
    public void setDiscountPolicy(DiscountPolicy discountPolicy){
        this.discountPolicy = discountPolicy;
    }
    */


    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member,itemPrice);

        return new Order(memberId,itemName,itemPrice,discountPrice);
    }

    //테스트용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }

}
