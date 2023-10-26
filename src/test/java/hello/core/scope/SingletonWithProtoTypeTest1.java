package hello.core.scope;

import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class SingletonWithProtoTypeTest1 {

    @Test
    void prototypeFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        Assertions.assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        Assertions.assertThat(prototypeBean2.getCount()).isEqualTo(1);

    }

    @Test
    void singletonClientUsePrototype(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class,PrototypeBean.class);
        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int client1 = clientBean1.logic();
        Assertions.assertThat(client1).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int client2 = clientBean2.logic();
        Assertions.assertThat(client2).isEqualTo(1);

    }

    @Scope("singleton") //생략가능
    //@RequiredArgsConstructor
    static class ClientBean{
        //생성시점에 주입 => 계속 같은걸 씀..
       // private final PrototypeBean prototypeBean;

        // JSR-330 javax.inject.Inject annotation 로 대체가능
        @Autowired
        private ObjectProvider<PrototypeBean> prototypeBeanProvider;


        /*@Autowired
        public ClientBean(PrototypeBean prototypeBean){
            this.prototypeBean = prototypeBean;
        }*/


        public int logic(){
            //getObject 호출 시점에 스프링 컨테이너에서 해당 빈을 찾아서 반환함(Dependency Lookup)
            PrototypeBean prototypeBean = prototypeBeanProvider.getObject();
            //항상 새로운 프로토타입 빈이 생성됨
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }

    }

    @Scope("prototype")
    static class PrototypeBean{
        private int count = 0;

        public void addCount(){
            count ++;
        }

        public int getCount(){
            return count;
        }

        @PostConstruct
        public void init(){
            System.out.println("PrototypeBean.init " + this);
        }

        @PreDestroy
        public void destroy(){
            System.out.println("PrototypeBean.destroy " + this);
        }
    }
}
