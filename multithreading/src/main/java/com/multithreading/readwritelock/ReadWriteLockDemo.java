package main.java.com.multithreading.readwritelock;

import java.util.HashSet;
import java.util.Set;

public class ReadWriteLockDemo {
    public static void main(String[] args) throws InterruptedException {
        ReadWriteLockImpl readWriteLock = new ReadWriteLockImpl();
        Set<Thread> allThreads = new HashSet<>();
        
        for (int i = 0; i < 3; i++) {
            Thread writeLockThread = new Thread(() -> {
                try {
                    readWriteLock.acquireWriteLock();
                    Thread.sleep(3000);
                    readWriteLock.releaseWriteLock();
                } catch (InterruptedException ex) {}
            }, "Thread W - " + i);

            allThreads.add(writeLockThread);
        }

        for (int i = 0; i < 100; i++) {
            Thread readLockThread = new Thread(() -> {
                    try {
                        readWriteLock.acquireReadLock();
                        Thread.sleep(1000);
                        readWriteLock.releaseReadLock();
                    } catch (InterruptedException ex) {
                    }
                }
            , "Thread R - " + i);

            allThreads.add(readLockThread);
        }

        for (Thread thread : allThreads) {
            thread.start();
        }

        for (Thread thread : allThreads) {
            thread.join();
        }
    }
}
