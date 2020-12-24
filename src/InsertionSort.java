import java.util.List;

public class InsertionSort {
    public static List<Integer> sort(List<Integer> array){
        for (int i = 1; i < array.size(); i++){
            int key = array.get(i);
            int j = i-1;
            while (j >= 0 && array.get(j) < key){
                array.set(j+1, array.get(j));
                j--;
            }
            array.set(j+1, key);
        }
        return array;
    }
    public static List<Integer> sort(List<Integer> array, int begin, int end){
        for (int i = begin+1; i <= end; i++){
            int key = array.get(i);
            int j = i-1;
            while (j >= begin && array.get(j) < key){
                array.set(j+1, array.get(j));
                j--;
            }
            array.set(j+1, key);
        }
        return array;
    }
}
