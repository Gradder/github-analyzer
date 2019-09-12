package com.ebsco.githubanalyzer.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.RepositoryContents;
import org.eclipse.egit.github.core.RepositoryId;
import org.eclipse.egit.github.core.service.ContentsService;
import org.eclipse.egit.github.core.service.RepositoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class GitRepositoryService {

    private final RepositoryService repositoryService;
    private final ContentsService contentsService;

    @SneakyThrows
    public List<Repository> getAllUserRepositories(String userName) {

            return repositoryService.getRepositories(userName);
    }

    @SneakyThrows
    public List<Repository> getAllRepositories() {

        return repositoryService.getRepositories();
    }

    @SneakyThrows
    public List<RepositoryContents> getRepositoryContents(String repositoryUrl) {

            return contentsService.getContents(RepositoryId.createFromUrl(repositoryUrl));
    }

    @SneakyThrows
    public RepositoryContents getRepositoryReadme(String repositoryUrl) {

            return contentsService.getReadme(RepositoryId.createFromUrl(repositoryUrl));
    }
}
