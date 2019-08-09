package spittr.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/hello")
public class HelloController {
    @ResponseBody()
    @RequestMapping(method = GET)
    public String hello(){
        return "hello World";
    }
}
