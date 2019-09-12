package com.ebsco.githubanalyzer.controller;

import com.ebsco.githubanalyzer.service.GitRepositoryService;
import lombok.RequiredArgsConstructor;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.RepositoryContents;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/repository")
@RequiredArgsConstructor
public class GitExplorerController {

    private final String USER = "/user";
    private final String CONTENTS = "/contents";

    private final GitRepositoryService service;

    @GetMapping
    public List<Repository> getAllRepositories() {
        return service.getAllRepositories();
    }

    @GetMapping(value = USER)
    public List<Repository> getAllUserRepositories(String userName) {
        return service.getAllUserRepositories(userName);
    }

    @GetMapping(value = CONTENTS)
    public List<RepositoryContents> getRepositoryContents(String repositoryUrl) {
        return service.getRepositoryContents(repositoryUrl);
    }
}
