package com.example.yongjun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloAPIController {

    @GetMapping("hello-api")
    @ResponseBody
    public String helloApi(@RequestParam("name") String name) {
        return "hello" + name;
    }
}
