package main.java.com.multithreading.semaphore;

import java.util.HashSet;
import java.util.Set;

public class SemaphoreDemo {
    public static void main(String[] args) throws InterruptedException {
        SemaphoreImpl semaphore = new SemaphoreImpl(5);
        Set<Thread> allThreads = new HashSet<>();
        
        for (int i = 0; i < 10; i++) {
            Thread acquireThread = new Thread(() -> {
                    try {
                        semaphore.acquire();
                    } catch (InterruptedException ex) {
                    }
                }
            , "Thread A - " + i);
            
            Thread releaseThread = new Thread(() -> {
                    try {
                        semaphore.release();
                    } catch (InterruptedException ex) {
                    }
                }
            , "Thread B - " + i);

            allThreads.add(releaseThread);
            allThreads.add(acquireThread);
        }

        for (Thread thread : allThreads) {
            thread.start();
        }

        for (Thread thread : allThreads) {
            thread.join();
        }

    }
}
