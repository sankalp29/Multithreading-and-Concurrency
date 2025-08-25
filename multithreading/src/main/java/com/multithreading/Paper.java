package com.multithreading;

public class Paper {
    public synchronized void writeWithPaperAndPen(Pen pen) {
        System.out.println("Writing with PAPER and pen");
        pen.finishWriting();
    }

    public synchronized void finishWriting() {
        System.out.println("Finished writing with PAPER");
    }
}
