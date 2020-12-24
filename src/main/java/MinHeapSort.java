public class MinHeapSort {
	public static int[] sort(int[] array)
	{

		// array -> min heap
		for (int i = (array.length/2) - 1; i >= 0; i--)
			buildMinHeap(array, array.length, i);

		for (int i = array.length - 1; i > 0; i--) {
			// move root to end
			swap(i,0,array);
			buildMinHeap(array, i, 0);
		}
		return array;
	}


	private static void buildMinHeap(int[] array, int n, int i)
	{
		int root = i;
		int right = 2*i + 2;
		int left = 2*i + 1;

		if (right < n && array[right] < array[root])
			root = right;

		if (left < n && array[left] < array[root])
			root = left;

		// if min isn't the root of the heap, swap them
		if (root != i) {
			swap(i,root,array);
			buildMinHeap(array, n, root);
		}
	}

	private static void swap(int a, int b, int array[])  {
		int temp = array[a];
		array[a] = array[b];
		array[b] = temp;
	}
}