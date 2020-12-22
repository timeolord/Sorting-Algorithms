
public class BogoSortMultithreaded {
    public static int[] sort(int[] sorted){
        int[] list1 = new int[sorted.length/2];
        int[] list2 = new int[sorted.length - sorted.length/2];
        System.arraycopy(sorted, 0, list1,0,(sorted.length/2));
        System.arraycopy(sorted, sorted.length/2, list2,0,(sorted.length - sorted.length/2));
        Worker work1 = new Worker(list1);
        Worker work2 = new Worker(list2);
        Thread worker1 = new Thread(work1);
        Thread worker2 = new Thread(work2);
        try {
            worker1.start();
            worker2.start();
            worker1.join();
            worker2.join();
        }catch (InterruptedException ignored){

        }
        return merge(work1.getArray(), work2.getArray());
    }
    private static class Worker implements Runnable{
        private int[] array;
        public int[] getArray() {return array;}
        public Worker(int[] array){
            this.array=array;
        }
        public void run(){
            BogoSort.sort(this.array);
        }
    }
    public static int[] merge(int[] first, int[] second) {
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