import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry; // You may need it to implement fastSort

public class QuickInsertionSort {
	private static void quickSort (int[] sortedUrls, int start, int end) {
		if (sortedUrls.length <= 1) {
			return;
		}
		int pivot = sortedUrls[start + (end-start)/2];
		int i = start;
		int j = end;

		while (i<=j) {
			while (sortedUrls[i] > pivot) {
				i++;
			}
			while (sortedUrls[j] < pivot) {
				j--;
			}
			if (i <= j) { // swap i and j
				int tmp = sortedUrls[i];
				sortedUrls[i] = sortedUrls[j];
				sortedUrls[j] = tmp;
				i++;
				j--;
			}
		}

		if (start < j){
			if ((j-start) <= 10) {
				InsertionSort.sort(sortedUrls,start,j);
			} else {
				quickSort(sortedUrls, start, j);
			}
		}
		if (end > i){
			if ((end-i) <= 10) {
				InsertionSort.sort(sortedUrls,i,end);
			} else {
				quickSort(sortedUrls, i, end);
			}
		}
	}

	public static int[] sort(int[] results) {
	    quickSort(results,0, results.length-1);
	    return results;
    }
}