package com.rejak.springsecurityexamples.index.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    @RequestMapping("/")
    public String indexPage() {
        return "Index service user";
    }
}
