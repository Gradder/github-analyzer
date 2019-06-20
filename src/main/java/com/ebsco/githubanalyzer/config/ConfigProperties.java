package com.ebsco.githubanalyzer.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("github")
@Data
public class ConfigProperties {

    private String oauth2Token;

}
