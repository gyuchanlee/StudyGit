package com.dodo.bootpractice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthTestController {

    @GetMapping("/user")
    public String userTest() {
        return "Auth User";
    }


    @GetMapping("/admin")
    public String adminTest() {
        return "Auth Admin";
    }


    @GetMapping("/user2")
    public String user2Test() {
        return "Auth User2";
    }
}
