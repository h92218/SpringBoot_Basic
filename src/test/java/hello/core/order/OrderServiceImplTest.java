package hello.core.order;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.Test;


class OrderServiceImplTest {

    @Test
    void createOrder(){
        /*생성자 주입 시 순수 JAVA 코드에서 조작가능
            수정자 주입 사용시 createOrder 메소드만 테스트 하고 싶어도
            OrderServiceImpl orderService = new OrderServiceImpl(); 에서
            MemoryMemberRepository(), FixDiscountPolicy 도 만들어 주어야 함.
        */

        OrderServiceImpl orderService = new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
        //orderService.createOrder(1L,"itemA",1000);
    }
}