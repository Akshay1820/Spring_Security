package Spring.Security.Project.project_security.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/project/security")
public class DemoController {

    @GetMapping("/hello")
    public String demoApi(){
        return "Hello World";
    }
}
