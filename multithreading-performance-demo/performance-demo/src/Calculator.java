public class Calculator {
    public static long getSum(int start, int end) {
        long sum = 0;
        for (int i = start; i <= end; i++) {
            sum+=i;
        }
        return sum;
    }
}