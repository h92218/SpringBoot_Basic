package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;

    //private final ObjectProvider<MyLogger> myLoggerProvider;
    private final MyLogger myLogger;
    //mylogger의 스코프 : request 가 들어오고 나갈때 까지
    /* mylogger 의존관계 주입 시 scope가 request인데
      spring 시작 시 http request가 없어서 request 스코프를 가진 MyLogger 빈은 아직 생성이 안 됨
      => Scope 'request' is not active for the current thread;
      => ObjectProvider 로 스프링 컨테이너에 빈의 호출 시점을 지연하여 해결함
     */


    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request){
        String requestURL = request.getRequestURL().toString();
        //MyLogger myLogger = myLoggerProvider.getObject();

        System.out.println("myLogger = " + myLogger.getClass());
        //myLogger = class hello.core.common.MyLogger$$EnhancerBySpringCGLIB$$f4a6c885 <<- 가짜
        myLogger.setRequestURL(requestURL);

        myLogger.log("controller test");
        logDemoService.logic("testId");
        return "ok";
    }
}
