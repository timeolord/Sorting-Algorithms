import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Function;


public class Tester {
    public static void main(String[] args){
        int size = 10_000;
        int repeat = 10_000;
        boolean print = false;
        boolean multithread = true;

        test(InsertionSort::sort, size, repeat, print, multithread);

    }
    public static boolean testSorted(int[] array){
        for (int i = 1; i < array.length; i++){
            if (array[i-1] < array[i]){
                return false;
            }
        }
        return true;
    }
    static BiFunction<int[],int[],Boolean> sameList =
            (first, second) -> Arrays.equals(QuickInsertionSort.sort(first.clone()), QuickInsertionSort.sort(second.clone()));

    public static BigDecimal average(ArrayList<Long> array){
        BigDecimal sum = BigDecimal.valueOf(0);
        for (long num : array){
            sum = sum.add(BigDecimal.valueOf(num));
        }
        return sum.divide(BigDecimal.valueOf(array.size()));
    }
    public static void test(Function<int[], int[]> sort, int size, int repeat, boolean print, boolean multithread){
        Random random = new Random();
        ArrayList<Long> avg = new ArrayList<Long>();
        int threads = 2;
        int[] data = {0};

        //tests to see if the sort works
        int[] numtest = random.ints(10, 1, 10).toArray();
        int[] numtest2 = numtest.clone();
        if (multithread) {
            numtest2 = Multithreader.multithread(numtest2, threads, sort);
        }
        else{
            numtest2 = sort.apply(numtest2);
        }
        if (print) {
            System.out.println("Test 1 to see if the sort changes the elements: " + Arrays.toString(numtest));
            System.out.println("Test 2 to see if the sort changes the elements: " + Arrays.toString(numtest2));
        }
        if (!sameList.apply(numtest,numtest2)){
            System.out.println("The sort changed the list. Test Cancelled");
            return;
        }
        if (print){
            System.out.println("Input of the sort: " + Arrays.toString(numtest));
            System.out.println("Output of the sort: " + Arrays.toString(numtest2));
        }
        if (!testSorted(numtest2)){
            System.out.println("The sort didn't correct sort in descending order. Test Cancelled");
            System.out.println("Input of the sort: " + Arrays.toString(numtest));
            System.out.println("Output of the sort: " + Arrays.toString(numtest2));
            return;
        }

        //main test for sort
        int j = 1000;
        for (int i = 1; i <= repeat; i++) {
            if (i % 1000 == 0){
                System.out.println(j + " tests completed.");
                j += 1000;
            }
            long startTime;
            data = random.ints(size, 1, size * 2).toArray();
            if (multithread) {
                startTime = System.nanoTime();
                data = Multithreader.multithread(data, threads, sort);
            }
            else{
                startTime = System.nanoTime();
                data = sort.apply(data);
            }
            long endTime = System.nanoTime();
            long duration = (endTime - startTime);
            avg.add(duration);
        }

        BigDecimal time = average(avg);
        //divide by 1000000 to get milliseconds.
        System.out.println("Sort took " + time.divide(BigDecimal.valueOf(1000000)) + " ms on average of " + repeat + " attempts for a list of size " + size + ".");
    }
}
