import java.util.Arrays;
import java.util.Random;

public class Tester {
    public static void main(String[] args){
        Random random = new Random();
        int[] data = random.ints(10, 10 ,10000).toArray();

        System.out.println(Arrays.toString(data));
        InsertionSort.insertionSort(data);
        System.out.println(Arrays.toString(data));
        System.out.println(testSorted(data));
    }
    public static boolean testSorted(int[] array){
        for (int i = 1; i < array.length; i++){
            if (array[i-1] < array[i]){
                return false;
            }
        }
        return true;
    }
}
