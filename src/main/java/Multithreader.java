import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;

public class Multithreader {
    public static void main(String[] args) {
        int[] ar1 = {5,4,3,2,1};
        int[] ar2 = {9,7,6,4,1};
        System.out.println(Arrays.toString(merge(ar1, ar2)));
    }
    public static int[] multithread(int[] sorted, int threads, Function<int[],int[]> sort){
        List<List<Integer>> lists = Lists.partition(Arrays.stream(sorted).boxed().collect(Collectors.toList()), sorted.length/threads+1);
        ArrayList<Worker> workers = new ArrayList<>();
        ArrayList<Thread> thread = new ArrayList<>();
        for (List<Integer> list : lists){
            int[] intList = Arrays.stream(list.toArray()).mapToInt(i -> (int) i).toArray();
            Worker worker = createWorker.apply(intList, sort);
            workers.add(worker);
            Thread thread1 = createThread.apply(worker);
            thread.add(thread1);
            thread1.start();
        }
        for (Thread thr : thread){
            try {
                thr.join();
            } catch (InterruptedException ignored) {
            }
        }
        int[] merge = null;
        boolean initial = true;
        for (int i = 1; i < workers.size();i++){
            if (initial){
                //System.out.println(Arrays.toString(workers.get(0).getArray()) + " " + Arrays.toString(workers.get(i).getArray()));
                merge = merge(workers.get(0).getArray(), workers.get(i).getArray());
                initial = false;
            }
            else{
                //System.out.println(Arrays.toString(merge) + " " + Arrays.toString(workers.get(i).getArray()));
                merge = merge(merge, workers.get(i).getArray());
            }
            //System.out.println(Arrays.toString(merge));
            sorted = merge.clone();
        }
        return sorted;
    }
    private static class Worker implements Runnable{
        private final int[] array;
        private Function<int[],int[]> sort;
        public int[] getArray() {return array;}
        public Worker(int[] array, Function<int[], int[]> sort){
            this.array=array;
            this.sort=sort;
        }
        public void run(){
            sort.apply(this.array);
        }
    }

    static BiFunction<int[], Function<int[],int[]>, Worker> createWorker = Worker::new;
    static Function<Worker,Thread> createThread = Thread::new;

    private static ArrayList<ArrayList<Integer>> split(int[] split, int threads){
        int division = split.length/threads+1;
        ArrayList<ArrayList<Integer>> partition = new ArrayList<>();
        for (int j = 0; j < threads; j++){
            partition.add(new ArrayList<>());
        }
        int j = 0;
        int k = 0;
        for (int value : split) {
            if (k == division) {
                j++;
                k = 0;
            }
            partition.get(j).add(value);
            k++;
        }
        return partition;
    }
    public static int[] merge(int[] arr1, int[] arr2) {
        if (arr1 == null){
            return arr2;
        }
        else if (arr2 == null){
            return arr1;
        }
        int[] arr3 = new int[arr1.length+ arr2.length];
        int i = 0, j = 0, k = 0;

        while (i< arr1.length && j<arr2.length)
        {
            if (arr1[i] > arr2[j])
                arr3[k++] = arr1[i++];
            else
                arr3[k++] = arr2[j++];
        }

        while (i < arr1.length)
            arr3[k++] = arr1[i++];

        while (j < arr2.length)
            arr3[k++] = arr2[j++];

        return arr3;
    }
}


