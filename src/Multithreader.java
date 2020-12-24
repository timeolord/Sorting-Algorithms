import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Multithreader {
    public static List<Integer> multithread(List<Integer> sorted, int threads, Function<List<Integer>,List<Integer>> sort){
        List<List<Integer>> lists = split(sorted, threads);
        ArrayList<Worker> workers = new ArrayList<>();
        ArrayList<Thread> thread = new ArrayList<>();
        for (List<Integer> list : lists){
            Worker worker = createWorker.apply(list, sort);
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
        List<Integer> merge = Arrays.asList(new Integer[sorted.size()]);
        boolean first = true;
        for (int i = 1; i < workers.size();i++){
            List<Integer> temp;
            if(workers.get(i-1).getArray().size() == 0) {
                temp = workers.get(i).getArray();
            }
            else if (workers.get(i).getArray().size() == 0){
                continue;
            }
            else {
                temp = merge(workers.get(i - 1).getArray(), workers.get(i).getArray());
            }
            if (!first){
                merge = merge(temp, merge);
            }
            else{
                merge = temp;
            }
            first = false;
        }
        sorted = new ArrayList<>(merge);
        return merge;
    }
    private static class Worker implements Runnable{
        private final List<Integer> array;
        private final Function<List<Integer>,List<Integer>> sort;
        public List<Integer> getArray() {return array;}
        public Worker(List<Integer> array, Function<List<Integer>, List<Integer>> sort){
            this.array=array;
            this.sort=sort;
        }
        public void run(){
            sort.apply(this.array);
        }
    }

    static BiFunction<List<Integer>, Function<List<Integer>,List<Integer>>, Worker> createWorker = Worker::new;
    static Function<Worker,Thread> createThread = Thread::new;

    private static List<List<Integer>> split(List<Integer> split, int threads){
        int division = split.size()/threads+1;
        List<List<Integer>> partition = new ArrayList<>();
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
    public static List<Integer> merge(List<Integer> first,List<Integer> second) {
        if (first == null) {
            return second;
        }
        if (second == null) {
            return first;
        }
        List<Integer> result = Arrays.asList(new Integer[first.size()+second.size()]);
        int i=0;
        int j=0;
        int k=0;
        while (i < first.size() && j < second.size()) {
            if (first.get(i) <= second.get(j)) {
                result.set(k, first.get(i));
                i++;
                k++;
            } else {
                result.set(k, second.get(j));
                j++;
                k++;
            }
            if (i == first.size()) {
                while (j < second.size()) {
                    result.set(k, second.get(j));
                    k++;
                    j++;
                }
            }
            if (j == second.size()) {
                while (i < first.size()) {
                    result.set(k, first.get(i));
                    k++;
                    i++;
                }
            }
        }
        return result;
    }
}

