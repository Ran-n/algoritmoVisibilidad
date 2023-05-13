package com.ran.algoritmovisibilidad.presenter.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DiagnosticController {
    
    @RequestMapping("/diagnostic")
    public String diagnostic() {
        return "OK";
    }

    @RequestMapping("/state")
    public String state() {
        return "Running";
    }
    
}
