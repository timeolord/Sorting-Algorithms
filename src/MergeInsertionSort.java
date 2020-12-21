import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class MergeInsertionSort {

    public static <K, V extends Comparable> ArrayList<K> mergeSort(HashMap<K, V> results) {
        ArrayList<K> sorted = new ArrayList<>(results.keySet());
        K[] array = (K[]) sorted.toArray();
        Object[] tempObj = new Object[array.length];
        K[] temp = (K[]) tempObj;
        mergeSort(array,0, array.length-1, results, temp);
        sorted.clear();
        sorted.addAll(Arrays.asList(array));
        return sorted;
    }

    private static <K, V extends Comparable> void mergeSort(K[] array, int begin, int end, HashMap<K, V> results, K[] temp){
        if (end - begin <= 10){
            insertionSort(array,begin,end,results);
            return;
        }
        int mid = (int) Math.floor((begin + end)/2);
        mergeSort(array,begin,mid,results, temp);
        mergeSort(array,mid+1,end,results, temp);
        merge(array,begin,end,results,temp);
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
    private static <K, V extends Comparable> void merge(K[] array, int begin, int end, HashMap<K, V> results, K[] temp){
        int size = end - begin;
        int lstart = begin;
        int mid = (begin + end)/2;
        int rstart = mid+1;
        int lend = mid;
        int rend = end;
        int lindex = lstart;
        int rindex = rstart;
        int tindex = begin;

        while(lindex <= lend && rindex <= rend){
            V right = results.get(array[rindex]);
            V left = results.get(array[lindex]);
            if (left.compareTo(right) > 0){
                temp[tindex] = array[lindex];
                lindex++;
            }
            else{
                temp[tindex] = array[rindex];
                rindex++;
            }
            tindex++;
        }
        while (lindex <= lend){
            temp[tindex] = array[lindex];
            tindex++;
            lindex++;
        }
        while (rindex <= rend){
            temp[tindex] = array[rindex];
            tindex++;
            rindex++;
        }
        System.arraycopy(temp,begin,array,begin,size+1);
    }
}