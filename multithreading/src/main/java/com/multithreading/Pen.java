package com.multithreading;

public class Pen {
    public synchronized void writeWithPenAndPaper(Paper paper) {
        System.out.println("Writing with PEN and paper");
        paper.finishWriting();
    }

    public synchronized void finishWriting() {
        System.out.println("Finished writing with PEN");
    }
}
