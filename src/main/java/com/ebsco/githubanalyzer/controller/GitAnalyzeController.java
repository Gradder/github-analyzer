package com.ebsco.githubanalyzer.controller;

import com.ebsco.githubanalyzer.model.ReadmeHealth;
import com.ebsco.githubanalyzer.service.GitAnalyzeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/analyze")
public class GitAnalyzeController {

    private final String CONCURRENT = "/concurrent";
    private final String README = "/readme";
    private final String CONTENTS_STATISTICS = "/contents_statistics";

    private final GitAnalyzeService analyzeService;

    @GetMapping(value = README)
    public ReadmeHealth getReadmeStatistics(String userName) {
        return analyzeService.getReadmeStatistics(userName);
    }

    @GetMapping(value = CONTENTS_STATISTICS)
    public ReadmeHealth getAllUsers(String userName) {
        return analyzeService.getContentsStatistics(userName);
    }

    @GetMapping(value = CONCURRENT + README)
    public ReadmeHealth getConcurrentReadmeStatistics(String userName) {
        return analyzeService.getReadmeStatisticsWithConcurrency(userName);
    }
}
