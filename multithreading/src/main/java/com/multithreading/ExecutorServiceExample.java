package com.multithreading;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorServiceExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(1);
        Future<Integer> future = executor.submit(() -> {
            Thread.sleep(2000);
            return 42;
        });

        try {
            System.out.println("isDone : " + future.isDone());
            System.out.println("Result = " + future.get());
            System.out.println("isDone : " + future.isDone());
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        } catch (ExecutionException ex) {
            System.out.println(ex.getMessage());
        }

        executor.shutdown();
        System.out.println("Program done!");
    }
}