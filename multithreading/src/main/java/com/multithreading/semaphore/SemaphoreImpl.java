package main.java.com.multithreading.semaphore;

public class SemaphoreImpl {
    private final int maxPermits;
    private int permitsGiven;

    public synchronized void acquire() throws InterruptedException {
        while (permitsGiven == maxPermits) 
            wait();
        
        System.out.println("Acquired by " + Thread.currentThread().getName());
        permitsGiven++;
        notifyAll();
    }

    public synchronized void release() throws InterruptedException {
        while (permitsGiven == 0) 
            wait();
        
        System.out.println("Released by " + Thread.currentThread().getName());
        permitsGiven--;
        notifyAll();
    }

    public SemaphoreImpl(int maxPermits) {
        this.maxPermits = maxPermits;
        this.permitsGiven = 0;
    }
}
