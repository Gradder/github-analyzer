package com.ebsco.githubanalyzer.service;

import lombok.AllArgsConstructor;
import org.eclipse.egit.github.core.User;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import static org.eclipse.egit.github.core.client.IGitHubConstants.SEGMENT_USERS;
import static org.eclipse.egit.github.core.client.IGitHubConstants.URL_API;

@Service
@AllArgsConstructor
public class GitUserService {

    protected final GitHubClient client;
    private final RestTemplate restTemplate;

    public String getAllUsers()  {

        RequestEntity<Void> requestEntity = RequestEntity.get(URI.create(URL_API + SEGMENT_USERS + "?since=" + Math.random()*100000))
                .build();

        List<User> body = restTemplate.exchange(requestEntity, new ParameterizedTypeReference<List<User>>(){}).getBody();
        return body.get(0).getLogin();
    }
}
