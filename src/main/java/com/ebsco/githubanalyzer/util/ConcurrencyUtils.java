package com.ebsco.githubanalyzer.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class ConcurrencyUtils {

    public static void awaitForExecutorTermination(ExecutorService executorService){
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException ex) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
