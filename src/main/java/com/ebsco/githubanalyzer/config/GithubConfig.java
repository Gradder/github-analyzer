package com.ebsco.githubanalyzer.config;

import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.ContentsService;
import org.eclipse.egit.github.core.service.RepositoryService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GithubConfig {

    @Bean
    public RepositoryService repositoryService() {
        return new RepositoryService();
    }

    @Bean
    public ContentsService contentsService() {
        return new ContentsService();
    }

    @Bean
    public GitHubClient gitHubClient(ConfigProperties configProperties) {
        GitHubClient client = new GitHubClient();
        client.setOAuth2Token(configProperties.getOauth2Token());
        return client;
    }
}
