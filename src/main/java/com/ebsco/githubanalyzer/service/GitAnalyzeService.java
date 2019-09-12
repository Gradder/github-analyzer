package com.ebsco.githubanalyzer.service;

import com.ebsco.githubanalyzer.model.ReadmeHealth;
import com.ebsco.githubanalyzer.util.ConcurrencyUtils;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.RepositoryContents;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@RequiredArgsConstructor
public class GitAnalyzeService {

    private final GitRepositoryService repositoryService;
    private ReadmeHealth readmeHealth;


    public ReadmeHealth getReadmeStatisticsWithConcurrency(String userName) {

        readmeHealth = new ReadmeHealth();

        ExecutorService executor = Executors.newCachedThreadPool();

        List<Repository> allUserRepositories = repositoryService.getAllUserRepositories(userName);

        for (Repository repo : allUserRepositories) {

            ReadmeRequest readmeRequest = new ReadmeRequest(repo.getHtmlUrl(), readmeHealth);
            executor.submit(readmeRequest);
        }

        ConcurrencyUtils.awaitForExecutorTermination(executor);

        return readmeHealth;
    }


    @AllArgsConstructor
    public class ReadmeRequest implements Runnable {

        String repoUrl;
        ReadmeHealth readmeHealth;

        @Override
        public void run() {
            RepositoryContents repositoryContent;
            try {
                repositoryContent = repositoryService.getRepositoryReadme(repoUrl);
            } catch (Exception e) {
                readmeHealth.incrementEmpty();
                return;
            }

            long size = repositoryContent.getSize();

            if (size == 0) {
                readmeHealth.incrementEmpty();
            } else {
                if (size < 700) {
                    readmeHealth.incrementPoor();
                } else {
                    if (size < 30000) {
                        readmeHealth.incrementGood();
                    } else {
                        readmeHealth.incrementLarge();
                    }
                }
            }
        }
    }

    public void getReadmeStatistic(String repoUrl, ReadmeHealth readmeHealth) {

        RepositoryContents repositoryContent;
        try {
            repositoryContent = repositoryService.getRepositoryReadme(repoUrl);
        } catch (Exception e) {
            readmeHealth.incrementEmpty();
            return;
        }

        long size = repositoryContent.getSize();

        if (size == 0) {
            readmeHealth.incrementEmpty();
        } else {
            if (size < 700) {
                readmeHealth.incrementPoor();
            } else {
                if (size < 30000) {
                    readmeHealth.incrementGood();
                } else {
                    readmeHealth.incrementLarge();
                }
            }
        }
    }


    public ReadmeHealth getContentsStatistics(String userName) {

        readmeHealth = new ReadmeHealth();
        List<Repository> allUserRepositories = repositoryService.getAllUserRepositories(userName);

        for (Repository repo : allUserRepositories) {

            List<RepositoryContents> repositoryContent = repositoryService.getRepositoryContents(repo.getHtmlUrl());

            boolean isReadmeExists = repositoryContent.stream()
                    .anyMatch(content -> content.getName().equals("README.md"));

            if (!isReadmeExists) {
                readmeHealth.incrementEmpty();
                continue;
            }

            for (RepositoryContents contents : repositoryContent) {
                String content = contents.getName();
                if (content.equals("README.md")) {


                    long size = contents.getSize();

                    if (size == 0) {
                        readmeHealth.incrementEmpty();
                    } else {
                        if (size < 700) {
                            readmeHealth.incrementPoor();
                        } else {
                            if (size < 30000) {
                                readmeHealth.incrementGood();
                            } else {
                                readmeHealth.incrementLarge();
                            }
                        }
                    }
                }
            }
        }
        return readmeHealth;
    }

    public ReadmeHealth getReadmeStatistics(String userName) {
        long startProcessingTime = System.currentTimeMillis();

        readmeHealth = new ReadmeHealth();

        List<Repository> allUserRepositories = repositoryService.getAllUserRepositories(userName);

        for (Repository repo : allUserRepositories) {

            RepositoryContents repositoryContent;
            try {
                repositoryContent = repositoryService.getRepositoryReadme(repo.getHtmlUrl());
            } catch (Exception e) {
                readmeHealth.incrementEmpty();
                continue;
            }
            long size = repositoryContent.getSize();

            if (size == 0) {
                readmeHealth.incrementEmpty();
            } else {
                if (size < 700) {
                    readmeHealth.incrementPoor();
                } else {
                    if (size < 30000) {
                        readmeHealth.incrementGood();
                    } else {
                        readmeHealth.incrementLarge();
                    }
                }
            }
        }
        long totalProcessingTime = System.currentTimeMillis() - startProcessingTime;
        System.out.println("Without concurrency spent: " + totalProcessingTime);
        return readmeHealth;
    }


}
