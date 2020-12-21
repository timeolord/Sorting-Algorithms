import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Tester {
    public static void main(String[] args){
        Random random = new Random();
        int size = 10000;
        int repeat = 10000;
        ArrayList<Long> avg = new ArrayList<Long>();

        int[] data = random.ints(size, 10 ,size*2).toArray();
        //System.out.println(Arrays.toString(data));

        for (int i = 0; i < repeat; i++) {
            data = random.ints(size, 10 ,size*2).toArray();
            long startTime = System.nanoTime();
            //Call sort method here
            MergeSort.sort(data);
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
    public static boolean testSorted(int[] array){
        for (int i = 1; i < array.length; i++){
            if (array[i-1] < array[i]){
                return false;
            }
        }
        return true;
    }
    public static BigDecimal average(ArrayList<Long> array){
        BigDecimal sum = BigDecimal.valueOf(0);
        for (long num : array){
            sum = sum.add(BigDecimal.valueOf(num));
        }
        return sum.divide(BigDecimal.valueOf(array.size()));
    }
}
