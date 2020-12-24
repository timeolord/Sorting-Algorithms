import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;


public class Tester {
    //TODO Fix Multithreading, Fix Merge Sort
    public static void main(String[] args){
        int size = 1_000_000_000;
        int repeat = 1;
        boolean print = false;
        boolean multithread = false;

        test(QuickSort::sort, size, repeat, print, multithread);

    }
    public static boolean testSorted(List<Integer> array){
        for (int i = 1; i < array.size(); i++){
            if (array.get(i-1).compareTo(array.get(i)) < 0){
                return false;
            }
        }
        return true;
    }
    public static boolean sameList(List<Integer> first, List<Integer> second){
        return QuickInsertionSort.sort(deepClone(first)).equals(QuickInsertionSort.sort(deepClone(second)));
    }
    public static BigDecimal average(List<Long> array){
        BigDecimal sum = BigDecimal.valueOf(0);
        for (long num : array){
            sum = sum.add(BigDecimal.valueOf(num));
        }
        return sum.divide(BigDecimal.valueOf(array.size()));
    }
    public static void test(Function<List<Integer>, List<Integer>> sort, int size, int repeat, boolean print, boolean multithread){
        Random random = new Random();
        List<Long> avg = new ArrayList<Long>();
        int threads = 2;
        List<Integer> data = new ArrayList<>();

        //tests to see if the sort works
        List<Integer> numtest = random.ints(10, 1, 10).boxed().collect(Collectors.toList());
        List<Integer> numtest2 = deepClone(numtest);
        if (multithread) {
            numtest2 = Multithreader.multithread(numtest2, threads, sort);
        }
        else{
            numtest2 = sort.apply(numtest2);
        }
        if (print) {
            System.out.println("Test 1 to see if the sort changes the elements: " + (numtest));
            System.out.println("Test 2 to see if the sort changes the elements: " + (numtest2));
        }
        if (!sameList(numtest,numtest2)){
            System.out.println("The sort changed the list. Test Cancelled");
            return;
        }
        if (print){
            System.out.println("Input of the sort: " + (numtest));
            System.out.println("Output of the sort: " + (numtest2));
        }
        if (!testSorted(numtest2)){
            System.out.println("The sort didn't correct sort in descending order. Test Cancelled");
            System.out.println("Input of the sort: " + (numtest));
            System.out.println("Output of the sort: " + (numtest2));
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
            data = random.ints(10, 1, 10).boxed().collect(Collectors.toList());
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
    private static List<Integer> deepClone(List<Integer> list){
        Integer[] numArray1 = list.toArray(new Integer[0]);
        List<Integer> copy = new ArrayList<>(Arrays.asList(numArray1));
        return copy;
    }
}
