package com.ebsco.githubanalyzer.controller;

import com.ebsco.githubanalyzer.service.GitRepositoryService;
import lombok.RequiredArgsConstructor;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.RepositoryContents;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/mainpoint")
@RequiredArgsConstructor
public class GitExplorerController {

    private final GitRepositoryService service;

    @GetMapping("/repo")
    public List<Repository> getAllUserRepositories(String userName) throws IOException {
        return service.getAllUserRepositories(userName);
    }

    @GetMapping("/contents")
    public List<RepositoryContents> getRepositoryContents(String repositoryUrl) throws IOException {
        return service.getRepositoryContent(repositoryUrl);
    }

}
