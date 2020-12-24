import java.util.ArrayList;
import java.util.List;

public class MergeInsertionSort {

    public static List<Integer> sort(List<Integer> sorted) {
        List<Integer> temp = new ArrayList<>();
        mergeSort(sorted,0, sorted.size()-1, temp);
        return sorted;
    }
    private static void mergeSort(List<Integer> array, int begin, int end, List<Integer> temp){
        if (end - begin <= 15){
            InsertionSort.sort(array,begin,end);
        }
        else{
            int mid = (int) Math.floor((begin + end)/2);
            mergeSort(array,begin,mid, temp);
            mergeSort(array,mid+1,end, temp);
            merge(array,begin,end,temp);
        }
    }
    private static  void merge(List<Integer> array, int begin, int end, List<Integer> temp){
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
            int right = array.get(rindex);
            int left = array.get(lindex);
            if (left > right){
                temp.set(tindex, array.get(lindex));
                lindex++;
            }
            else{
                temp.set(tindex, array.get(rindex));
                rindex++;
            }
            tindex++;
        }
        while (lindex <= lend){
            temp.set(tindex, array.get(lindex));
            tindex++;
            lindex++;
        }
        while (rindex <= rend){
            temp.set(tindex, array.get(rindex));
            tindex++;
            rindex++;
        }
        System.arraycopy(temp,begin,array,begin,size+1);
    }
}