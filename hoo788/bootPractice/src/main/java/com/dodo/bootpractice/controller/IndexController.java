package com.dodo.bootpractice.controller;

import groovy.util.logging.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "/views/index";
    }
}
