import java.util.Random;

public class QuickSortRandomPivot {
    private static void quickSort(int[] sortedUrls, int start, int end) {
        if (sortedUrls.length <= 1) {
            return;
        }
        Random random = new Random();
        int pivot = sortedUrls[random.nextInt(end-start)+start];
        int i = start;
        int j = end;

        while (i <= j) {
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
            quickSort(sortedUrls, start, j);

        }
        if (end > i){
            quickSort(sortedUrls, i, end);
        }
    }

    public static int[] sort(int[] results) {
        quickSort(results,0, results.length-1);
        return results;
    }
}