package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data","Hello!!");
        return "hello";
    }

    // 스프링 컨테이너에서 viewResolver가 동작
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody   // http의 body, viewResolver 대신 HttpMessageConverter 동작
    public String helloString(@RequestParam("name") String name){
        // 리턴값이 문자: StringHttpMessageConverter 동작
        return "hello " + name;
    }


    // json 형식으로 전달
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        // 리턴값이 객체: MappingJackson2HttpMessageConverter 동작
        // (Jackson = 객체를 json으로 바꿔주는 라이브러리)
        return hello;
    }

    static class Hello{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
