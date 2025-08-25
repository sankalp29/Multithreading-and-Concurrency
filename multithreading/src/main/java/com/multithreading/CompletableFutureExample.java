package com.multithreading;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CompletableFutureExample {
    public static void main(String[] args) {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(
            () -> {
                try {
                    Thread.sleep(3000);
                    System.out.println("Worker thread running...");
                } catch (Exception e) {
                }
                return "Done ";
            }
        ).thenApply(result -> result.concat(result));

        try {
            String result = future1.get(2, TimeUnit.SECONDS);
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            System.out.println("This exception occurred : " + e);
        }
    }
}
