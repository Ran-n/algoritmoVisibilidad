package com.ran.algoritmovisibilidad;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerTest {
    
    @RequestMapping("/test")
    public String test() {
        return "Test";
    }
}
