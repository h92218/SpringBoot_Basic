package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutoWiredOption(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    static class TestBean{

        @Autowired(required = false)
        //자동주입할 대상이 없으면 수정자 메서드 자체가 호출이 안 됨
        public void setNoBean1(Member nobean1){
            System.out.println("nobean1 = " + nobean1);
        }

        @Autowired
        public void setnoBean2(@Nullable Member noBean2){
            System.out.println("noBean2 = " + noBean2);
            //noBean2 = null
        }
        
        @Autowired
        public void setNoBean3(Optional<Member> nobean3){
            System.out.println("nobean3 = " + nobean3);
            //nobean3 = Optional.empty
        }
    }

}
