package com.ebsco.githubanalyzer.controller;

import com.ebsco.githubanalyzer.service.GitUserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class GitUserController {

    private final String ALL_USERS = "/all_users";

    private final GitUserService userService;

    @GetMapping(value = ALL_USERS)
    public String getAllUsers() {
        return userService.getAllUsers();
    }
}
