import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Multithreader {
    public static int[] multithread(int[] sorted, int threads, Function<int[],int[]> sort){
        ArrayList<ArrayList<Integer>> lists = split(sorted, threads);
        ArrayList<Worker> workers = new ArrayList<>();
        ArrayList<Thread> thread = new ArrayList<>();
        for (ArrayList<Integer> list : lists){
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
        for (int i = 1; i < workers.size();i++){
            int[] temp = merge(workers.get(i-1).getArray(), workers.get(i).getArray());
            merge = merge(temp, merge);
        }
        sorted = merge.clone();
        return merge;
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
        ArrayList<ArrayList<Integer>> partition = new ArrayList<ArrayList<Integer>>();
        for (int j = 0; j < threads; j++){
            partition.add(new ArrayList<Integer>());
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
    public static int[] merge(int[] first, int[] second) {
        if (first == null) {
            return second;
        }
        if (second == null) {
            return first;
        }
        int[] result = new int[first.length + second.length];
        int i=0;
        int j=0;
        int k=0;
        while (i < first.length && j < second.length) {
            if (first[i] <= second[j]) {
                result[k]=first[i];
                i++;
                k++;
            } else {
                result[k] = second[j];
                j++;
                k++;
            }
            if (i == first.length) {
                while (j < second.length) {
                    result[k]=second[j];
                    k++;
                    j++;
                }
            }
            if (j == second.length) {
                while (i < first.length) {
                    result[k]=first[i];
                    k++;
                    i++;
                }
            }
        }
        return result;
    }
}

