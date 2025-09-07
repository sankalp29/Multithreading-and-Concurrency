package com.multithreading.volatileKeyword;

class SharedResource {
    private boolean flag = false;

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

public class VolatileNeedExample {
    public static void main(String[] args) throws InterruptedException {
        SharedResource sharedResource = new SharedResource();

        Thread writer = new Thread(() -> sharedResource.setFlagToTrue(), "Writer Thread");
        Thread reader = new Thread(() -> sharedResource.printWhileFlagFalse(), "Reader Thread");

        reader.start();
        Thread.sleep(1000);
        writer.start();
        
        writer.join();
        reader.join();

        /**
         * Program runs forever, because the reader thread is never able to see the updated value of FLAG
         */
    }
}
