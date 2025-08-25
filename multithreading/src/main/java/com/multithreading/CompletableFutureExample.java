package com.multithreading;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CompletableFutureExample {
    public static void main(String[] args) {
        ExecutorService executorService =  Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            CompletableFuture<String> future = CompletableFuture.supplyAsync(
            () -> {
                try {
                    Thread.sleep(3000);
                    System.out.println("Worker thread running...");
                } catch (Exception e) {
                }
                return "Done ";
            }
        , executorService).thenApply(result -> result.concat(result));

            try {
                String result = future.get(i, TimeUnit.SECONDS);
                System.out.println(result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                System.out.println("This exception occurred : " + e);
            }   
        }

        executorService.shutdown();
    }
}
