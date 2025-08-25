package com.multithreading;

public class SharedResource {
    private int data;
    private boolean hasData;

    public synchronized void produce(int value) {
        while (hasData) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        data = value;
        hasData = true;
        System.out.println("Produced {" + data + "}");
        notify();
    }

    public synchronized void consume() {
        while (!hasData) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("Consumed {" + data + "}");
        hasData = false;
        notify();
    }
}

class NotifyExample {
    SharedResource sharedResource = new SharedResource();
    Thread t1 = new Thread(new Runnable() {
        public void run() {
            for (int i = 0; i < 10; i++) {
                sharedResource.produce(i);   
            }
        }
    }, "Producer Thread");

    Thread t2 = new Thread(new Runnable() {
        public void run() {
            for (int i = 0; i < 10; i++) {
                sharedResource.consume();
            }
        }
    }, "Consumer Thread");

    public void demoNotifyFunction() {
        t1.start();
        t2.start();
    }
}
