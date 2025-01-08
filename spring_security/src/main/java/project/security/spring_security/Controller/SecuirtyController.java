package project.security.spring_security.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecuirtyController {

    @GetMapping("/hello")
    public String hell(){
        return "Hello Akshay";
    }
}
