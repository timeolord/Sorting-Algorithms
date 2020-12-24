import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeSort {
    public static List<Integer> sort(List<Integer> sorted) {
        List<Integer> temp =  new ArrayList<>();
        mergeSort(sorted,0, sorted.size()-1, temp);
        return sorted;
    }
    private static void mergeSort(List<Integer> array, int begin, int end, List<Integer> temp){
        if (begin < end){
            int mid = (int) Math.floor((begin + end)/2);
            mergeSort(array,begin,mid, temp);
            mergeSort(array,mid+1,end, temp);
            merge(array,begin,end,temp);
        }
    }
    private static void merge(List<Integer> numArray, int begin, int end, List<Integer> tempAr){
        Integer[] array = numArray.toArray(new Integer[0]);
        Integer[] temp = tempAr.toArray(new Integer[0]);
        int size = end - begin;
        int mid = (begin + end)/2;
        int rstart = mid+1;
        int lindex = begin;
        int rindex = rstart;
        int tindex = begin;

        while(lindex <= mid && rindex <= end){
            int right = array[rindex];
            int left = array[lindex];
            if (left > right){
                temp[tindex] = array[lindex];
                lindex++;
            }
            else{
                temp[tindex] = array[rindex];
                rindex++;
            }
            tindex++;
        }
        while (lindex <= mid){
            temp[tindex] = array[lindex];
            tindex++;
            lindex++;
        }
        while (rindex <= end){
            temp[tindex] = array[rindex];
            tindex++;
            rindex++;
        }
        System.arraycopy(temp,begin,array,begin,size+1);
        numArray = new ArrayList<>(Arrays.asList(array));
    }
}