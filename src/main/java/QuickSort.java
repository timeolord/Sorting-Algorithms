import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry; // You may need it to implement fastSort

public class QuickSort {
    private static void quickSort(List<Integer> sortedUrls, int start, int end) {
        if (sortedUrls.size() <= 1) {
            return;
        }
        int pivot = sortedUrls.get(start + (end-start)/2);
        int i = start;
        int j = end;

        while (i<=j) {
            while (sortedUrls.get(i) > pivot) {
                i++;
            }
            while (sortedUrls.get(j) < pivot) {
                j--;
            }
            if (i <= j) { // swap i and j
                Integer tmp = sortedUrls.get(i);
                sortedUrls.set(i, sortedUrls.get(j));
                sortedUrls.set(j, tmp);
                i++;
                j--;
            }
        }

        if (start < j){
            quickSort(sortedUrls, start, j);

        }
        if (end > i){
            quickSort(sortedUrls, i, end);
        }
    }

    public static List<Integer> sort(List<Integer> results) {
        quickSort(results,0, results.size()-1);
        return results;
    }
}