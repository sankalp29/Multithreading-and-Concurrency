package com.multithreading;

public class InterruptExample {
    public static void main(String[] args) throws InterruptedException {
        blockingInterruptExample();
        nonBlockingInterruptExample();
    }

    private static void blockingInterruptExample() throws InterruptedException {
        Thread sleepThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread().getName() + " is going to sleep now...");
                    Thread.sleep(1000000);
                } catch (InterruptedException ex) {
                    System.out.println(Thread.currentThread().getName() + " has been interrupted...");
                    System.out.println("Confirming isInterrupted = " + Thread.currentThread().isInterrupted());
                    Thread.currentThread().interrupt();
                    System.out.println("Manually interrupted");
                    System.out.println("Confirming isInterrupted = " + Thread.currentThread().isInterrupted());
                }
            }
        }, "Sleepy Thread");

        sleepThread.start();
        Thread.sleep(1000);
        System.out.println("Main thread will now interrupt " + sleepThread.getName());
        sleepThread.interrupt();
        sleepThread.join();

        System.out.println("Woken up " + sleepThread.getName());
    }

    private static void nonBlockingInterruptExample() throws InterruptedException {
        Thread worker = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) { 
                    for (int i = 0; i < 10000000; i++) System.out.println(".");

                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println(Thread.currentThread().getName() + " is interrupted now...");
                        break;
                    }
                }
            }
        }, "Worker Thread");

        worker.start();
        Thread.sleep(1000);
        System.out.println("Main thread will now interrupt " + worker.getName());
        worker.interrupt();
        worker.join();
    }
}