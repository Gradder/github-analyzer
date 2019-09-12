package com.ebsco.githubanalyzer.model;

import lombok.Getter;

import java.util.concurrent.atomic.AtomicInteger;

@Getter
public class ReadmeHealth {
    private AtomicInteger empty = new AtomicInteger();
    private AtomicInteger poor = new AtomicInteger();
    private AtomicInteger good = new AtomicInteger();
    private AtomicInteger large = new AtomicInteger();

    public void incrementEmpty() {
        empty.getAndIncrement();
    }

    public void incrementPoor() {
        poor.getAndIncrement();
    }

    public void incrementGood() {
        good.getAndIncrement();
    }

    public void incrementLarge() {
        large.getAndIncrement();
    }
}