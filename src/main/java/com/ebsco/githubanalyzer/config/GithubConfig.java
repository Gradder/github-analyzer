package com.ebsco.githubanalyzer.config;

import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.ContentsService;
import org.eclipse.egit.github.core.service.RepositoryService;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class GithubConfig {

    @Bean
    public RepositoryService repositoryService(GitHubClient gitHubClient) {
        return new RepositoryService(gitHubClient);
    }

    @Bean
    public ContentsService contentsService(GitHubClient client) {
        return new ContentsService(client);
    }

    @Bean
    public GitHubClient gitHubClient(ConfigProperties configProperties) {
        GitHubClient client = new GitHubClient();
        client.setOAuth2Token(configProperties.getOauth2Token());
        return client;
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        // Do any additional configuration here
        return builder.build();
    }
}
