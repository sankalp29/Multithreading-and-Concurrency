package main.java.com.multithreading.readwritelock;

import java.util.LinkedList;
import java.util.Queue;

public class ReadWriteLockImpl {
    private boolean isWriteLockHeld;
    private int readLocksHeld;
    private final Queue<QueueNode> waitQueue = new LinkedList<>();

    private static class QueueNode {
        ThreadType threadType;
        Thread thread;

        public QueueNode(ThreadType threadType, Thread thread) {
            this.threadType = threadType;
            this.thread = thread;
        }
    }

    private enum ThreadType {
        READER,
        WRITER
    }

    public synchronized void acquireReadLock() throws InterruptedException {
        QueueNode node = new QueueNode(ThreadType.READER, Thread.currentThread());
        waitQueue.add(node);
        while(isWriteLockHeld || hasWriterThreadAhead(node)) {
            wait();
        }
        
        waitQueue.remove(node);
        System.out.println(Thread.currentThread().getName() + " acquired read lock");
        readLocksHeld++;
        notifyAll();
    }

    public synchronized void releaseReadLock() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " released read lock");
        readLocksHeld--;
        notifyAll();
    }

    public synchronized void acquireWriteLock() throws InterruptedException {
        QueueNode node = new QueueNode(ThreadType.WRITER, Thread.currentThread());
        waitQueue.add(node);
        while (isWriteLockHeld || readLocksHeld > 0 || waitQueue.peek().thread != Thread.currentThread()) {
            wait();
        }
        
        waitQueue.poll();
        System.out.println(Thread.currentThread().getName() + " acquired write lock");
        isWriteLockHeld = true;
    }

    public synchronized void releaseWriteLock() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " released write lock");
        isWriteLockHeld = false;
        notifyAll();
    }

    private boolean hasWriterThreadAhead(QueueNode node) {
        for (QueueNode queueNode : waitQueue) {
            if (queueNode == node) break;
            if (queueNode.threadType == ThreadType.WRITER) return true;

        }
        return false;
    }

    public ReadWriteLockImpl() {
        this.isWriteLockHeld = false;
        this.readLocksHeld = 0;
    }
}