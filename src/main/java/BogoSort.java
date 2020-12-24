import java.util.Arrays;
import java.util.Random;

public class BogoSort {
    public static int[] sort(int[] list){
        int[] temp = list.clone();
        Random random = new Random();
        while (!testSorted(temp)){
            temp = new int[list.length];
            for (int nums : list){
                int rand = random.nextInt(list.length);
                while (temp[rand] != 0){
                    rand = random.nextInt(list.length);
                }
                temp[rand] = nums;
            }
        }
        return temp;
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
