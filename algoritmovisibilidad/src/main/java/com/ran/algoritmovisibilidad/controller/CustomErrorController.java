package com.ran.algoritmovisibilidad.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomErrorController implements ErrorController {

    @RequestMapping(value = "/error")
    public String error() {
        return "Error 404";
    }

}
