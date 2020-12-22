import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.function.Function;


public class Tester {
    public static void main(String[] args){
        int size = 2;
        int repeat = 1;

        test(InsertionSort::sort, size, repeat);

    }
    public static boolean testSorted(int[] array){
        for (int i = 1; i < array.length; i++){
            if (array[i-1] < array[i]){
                return false;
            }
        }
        return true;
    }
    public static boolean sameList(int[] first, int[] second){
        if (!testSorted(first)) {
            QuickInsertionSort.sort(first);
        }
        if (!testSorted(second)) {
            QuickInsertionSort.sort(second);
        }
        return Arrays.equals(first, second);
    }
    public static BigDecimal average(ArrayList<Long> array){
        BigDecimal sum = BigDecimal.valueOf(0);
        for (long num : array){
            sum = sum.add(BigDecimal.valueOf(num));
        }
        return sum.divide(BigDecimal.valueOf(array.size()));
    }
    public static void test(Function<int[], int[]> sort, int size, int repeat){
        Random random = new Random();
        ArrayList<Long> avg = new ArrayList<Long>();
        int[] data = {0};

        //test if the sort changes the list
        int[] numtest = random.ints(50, 1, 100).toArray();
        int[] numtest2 = numtest.clone();
        numtest2 = sort.apply(numtest2);
        System.out.println(Arrays.toString(numtest));
        System.out.println(Arrays.toString(numtest2));
        if (!sameList(numtest,numtest2)){
            System.out.println("The sort changed the list. Test Cancelled");
            return;
        }

        //main test for sort
        for (int i = 0; i < repeat; i++) {
            data = random.ints(size, 1, size * 2).toArray();
            //System.out.println(Arrays.toString(data));
            long startTime = System.nanoTime();
            data = sort.apply(data);
            long endTime = System.nanoTime();
            long duration = (endTime - startTime);
            avg.add(duration);
        }

        //System.out.println(Arrays.toString(data));
        BigDecimal time = average(avg);

        System.out.println("Did the sort work? " + testSorted(data));
        //divide by 1000000 to get milliseconds.
        System.out.println("Sort took " + time.divide(BigDecimal.valueOf(1000000)) + " ms on average of " + repeat + " attempts for a list of size " + size + ".");
    }
}
