package com.multithreading.volatileKeyword;

class SharedObject {
    private volatile boolean flag = false;

    public void setFlagToTrue() {
        System.out.println("\nFlag is now set to TRUE\n");
        flag = true;
    }

    public void printWhileFlagFalse() {
        while (!flag) {
        }

        System.out.println("\n------------    TRUE NOW     ------------\n");
    }
}

public class VolatileExample {
    public static void main(String[] args) throws InterruptedException {
        SharedObject sharedObject = new SharedObject();

        Thread writer = new Thread(() -> sharedObject.setFlagToTrue(), "Writer Thread");
        Thread reader = new Thread(() -> sharedObject.printWhileFlagFalse(), "Reader Thread");

        reader.start();
        Thread.sleep(1000);
        writer.start();
        
        writer.join();
        reader.join();
    }
}

