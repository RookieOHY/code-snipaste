package com.rookieohy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/demo")
public class TestController {
    @GetMapping("/value")
    @ResponseBody
    public String getValue(){
        return "app is running";
    }
}
