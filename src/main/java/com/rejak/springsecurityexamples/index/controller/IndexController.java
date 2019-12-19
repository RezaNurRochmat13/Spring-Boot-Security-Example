package com.rejak.springsecurityexamples.index.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1")
public class IndexController {
    @GetMapping("/")
    public String indexPage() {
        return "Index service user";
    }
}
