package hello.core.discount;

import hello.core.AppConfig;
import hello.core.member.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {
    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();
    MemberService memberService;


    @BeforeEach
    public void beforeEach(){
        AppConfig appconfig = new AppConfig();
        memberService = appconfig.memberService();
    }

    @Test
    @DisplayName("VIP는 10퍼 할인 적용")
    void vip_o(){
        Member member = new Member(1L,"memberVIP", Grade.VIP);

        int discount = discountPolicy.discount(member,10000);

        assertThat(discount).isEqualTo(1000);

    }

    @Test
    @DisplayName("VIP 아니면 할인 X")
    void vip_x(){
        Member member = new Member(1L,"memberBASIC", Grade.BASIC);

        int discount = discountPolicy.discount(member,10000);

        assertThat(discount).isEqualTo(0);

    }
}