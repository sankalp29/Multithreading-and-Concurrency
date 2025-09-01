public class App {
    public static void main(String[] args) throws Exception {
        // Scenario 1 : Single threaded
        long start1 = System.currentTimeMillis();
        Calculator.getSum(1, 10000000);
        long end1 = System.currentTimeMillis();

        System.out.println("Time for single threaded: " + (end1 - start1));


        // Scenario 2 : Multi-threaded
        long start2 = System.currentTimeMillis();
        Thread t1 = new Thread(() -> Calculator.getSum(1, 5000000));
        Thread t2 = new Thread(() -> Calculator.getSum(5000001, 10000000));
        t1.start();
        t2.start();

        t1.join();
        t2.join();
        long end2 = System.currentTimeMillis();

        System.out.println("Time for multi-threaded : " + (end2 - start2));
    }
}
