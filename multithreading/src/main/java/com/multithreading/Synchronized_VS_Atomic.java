package com.multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Synchronized_VS_Atomic {
    private static final AtomicInteger counter = new AtomicInteger(0);
    private static Integer syncCounter = 0;
    private static final Integer NUM_THREADS = 10;

    public static void main(String[] args) {
        testAtomicInteger();
        testSynchronizedCounter();
    }

    public static void testAtomicInteger() {
        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);

        Long start = System.currentTimeMillis();
        try {
            for (int i = 0; i < NUM_THREADS; i++) {
                executor.submit(() -> {
                    for (int j = 0; j < 1000000; j++) {
                        counter.incrementAndGet();
                    }
                });
            }
        } finally {
            executor.shutdown();
        }
        

        Long end = System.currentTimeMillis();
        System.out.println("Time taken by AtomicInteger = " + (end - start));
    }

    public static void testSynchronizedCounter() {
        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);
        Long start = System.currentTimeMillis();

        try {
            for (int i = 0; i < NUM_THREADS; i++) {
                executor.submit(() -> {
                    for (int j = 0; j < 1000000; j++) {
                        increment();
                    }
                });
            }
        } finally {
            executor.shutdown();
        }

        Long end = System.currentTimeMillis();
        System.out.println("Time taken by Synchronized = " + (end - start));
    }

    private static synchronized void increment() {
        syncCounter++;
    }
}
