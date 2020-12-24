public class MergeInsertionSort {

    public static int[] sort(int[] sorted) {
        int[] temp = new int[sorted.length];
        mergeSort(sorted,0, sorted.length-1, temp);
        return sorted;
    }
    private static  void mergeSort(int[] array, int begin, int end, int[] temp){
        if (end - begin <= 15){
            InsertionSort.sort(array,begin,end);
        }
        else {
            int mid = (int) Math.floor((begin + end) / 2);
            mergeSort(array, begin, mid, temp);
            mergeSort(array, mid + 1, end, temp);
            merge(array, begin, end, temp);
        }
    }
    private static  void merge(int[] array, int begin, int end, int[] temp){
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