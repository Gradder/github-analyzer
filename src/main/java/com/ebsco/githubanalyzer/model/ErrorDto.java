package com.ebsco.githubanalyzer.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDto {

    private ZonedDateTime timestamp;
    private String path;
    private String message;

    public ErrorDto(String path, String message) {
        this.path = path;
        this.message = message;
        this.timestamp = ZonedDateTime.now();
    }
}
