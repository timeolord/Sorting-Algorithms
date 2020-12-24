public class MergeSort {
    public static int[] sort(int[] sorted) {
        int[] temp = new int[sorted.length];
        mergeSort(sorted,0, sorted.length-1, temp);
        return sorted;
    }
    private static void mergeSort(int[] array, int begin, int end, int[] temp){
        if (begin < end){
            int mid = (int) Math.floor((begin + end)/2);
            mergeSort(array,begin,mid, temp);
            mergeSort(array,mid+1,end, temp);
            merge(array,begin,end,temp);
        }
    }
    private static  void merge(int[] array, int begin, int end, int[] temp){
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
    }
}