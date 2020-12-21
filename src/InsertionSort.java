import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class InsertionSort {
    public static <K, V extends Comparable> ArrayList<K> insertionSort(HashMap<K, V> results) {
        ArrayList<K> sorted = new ArrayList<>(results.keySet());
        K[] array = (K[]) sorted.toArray();
        insertionSort(array,0, array.length-1, results);
        sorted.clear();
        sorted.addAll(Arrays.asList(array));
        return sorted;
    }
    private static <K, V extends Comparable> void insertionSort(K[] array, int begin, int end, HashMap<K, V> results){
        for (int i = begin+1; i <= end; i++){
            K temp = array[i];
            V key = results.get(array[i]);
            int j = i-1;
            V compare = results.get(array[j]);
            while (j >= begin && key.compareTo(compare) > 0){
                array[j+1] = array[j];
                j--;
            }
            array[j+1] = temp;
        }
    }
}
