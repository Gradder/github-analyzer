package com.ebsco.githubanalyzer.service;

import lombok.RequiredArgsConstructor;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.RepositoryContents;
import org.eclipse.egit.github.core.RepositoryId;
import org.eclipse.egit.github.core.service.ContentsService;
import org.eclipse.egit.github.core.service.RepositoryService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GitRepositoryService {

    private final RepositoryService repositoryService;
    private final ContentsService contentsService;

    public List<Repository> getAllUserRepositories(String userName) throws IOException {

        return repositoryService.getRepositories(userName);
    }

    public List<RepositoryContents> getRepositoryContent(String repositoryUrl) throws IOException {
        return contentsService.getContents(RepositoryId.createFromUrl(repositoryUrl));
    }
}
