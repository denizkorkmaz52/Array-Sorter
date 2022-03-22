import java.util.ArrayList;
import java.util.Random;

public class SortingClass {

	// BUCKET SORT
	
	//to complete bucket sort i used the instructions in the homework paper
	//and also learnd how to create array of lists in this link https://www.geeksforgeeks.org/array-of-arraylist-in-java/
	// and also searched the arraylist functions in this link https://www.javatpoint.com/java-arraylist 
	//to see if there are some usefull functions that i can use
	@SuppressWarnings("unchecked")
	public static void insertion_sort(ArrayList<Integer> arrayToSort) {
		int n = arrayToSort.size();
		for (int i = 1; i < n; i++) {
			int key = arrayToSort.get(i);
			int j = i - 1;

			while (j >= 0 && arrayToSort.get(j) > key) {// travel lower indexes to find out if
				arrayToSort.set(j + 1, arrayToSort.get(j));// there is a bigger number then the key
				j = j - 1; // if there is a bigger number then change indexes
			}
			arrayToSort.set(j + 1, key);// lastly place the key to correct index
		}
	}

	public static void BucketSort(int[] arrayToSort) {

		int bucketCount = (int)Math.sqrt(arrayToSort.length);//arrayToSort.length;
		ArrayList<Integer>[] buckets = new ArrayList[bucketCount];// deciding bucket count
		for (int i = 0; i < bucketCount; i++) {// creating buckets
			buckets[i] = new ArrayList<Integer>();
		}
		int bigger = 0;
		for (int i = 0; i < arrayToSort.length; i++) {// this for loop works to find the biggest
			if (arrayToSort[i] > bigger) // number in the array
				bigger = arrayToSort[i];
		}
		if (bigger % bucketCount != 0) { // if the biggest number is not a multiple of the bucket count
			for (int i = 0; i < bucketCount; i++) {// then find the closest number to the biggest number which
				bigger++; // is a multiple of the bucket amount
				if (bigger % bucketCount == 0)
					break;
			}
		}
		if(bigger < 0)
			bigger = Math.abs(bigger);
		int gap = bigger / bucketCount;// the interval size of each bucket
		for (int i = 0; i < arrayToSort.length; i++) {// this for loop works to place the numbers to the
			if (arrayToSort[i] != bigger) { // correct buckets
				int index = arrayToSort[i] / gap; // finding the right bucket of the number by dividing it by interval
													// size
				buckets[index].add(arrayToSort[i]);
			} else {
				buckets[buckets.length - 1].add(arrayToSort[i]);
			}
		}

		for (int i = 0; i < buckets.length; i++) { // this for loop works to sort the buckets by
			insertion_sort(buckets[i]); // using insertion sort
		}
		int x = 0;
		for (int i = 0; i < buckets.length; i++) {
			
			for (int j = 0; j < buckets[i].size(); j++) {
				arrayToSort[x] = buckets[i].get(j);
				x++;
			}
		}

	}

	//
	// HEAPSORT
	
	//to complete the heap sort i used the pseudo code in the course slides
	public static void HeapSort(int[] arrayToSort) {
		 int n = arrayToSort.length;
		 
	        // Build heap (rearrange array)
	        for (int i = n / 2 - 1; i >= 0; i--)
	            MaxHeapify(arrayToSort, i, n);
	 
	        // One by one extract an element from heap
	        for (int i = n - 1; i > 0; i--) {
	            // Move current root to end
	            int temp = arrayToSort[0];
	            arrayToSort[0] = arrayToSort[i];
	            arrayToSort[i] = temp;
	 
	            // call max heapify on the reduced heap to add the number to the array in increasing order
	            MaxHeapify(arrayToSort, 0, i);
	        }

	}

	public static void MaxHeapify(int[] arrayToSort, int index, int size) {
		int left = Left(index);//find left child's index
		int right = Right(index);//find right child's index
		int largest = index;
		if (left < size && arrayToSort[left] > arrayToSort[largest]) {
			//controlling left child if it is bigger then parent or not
			largest = left;
		}

		if (right < size && arrayToSort[right] > arrayToSort[largest]) {//comparing right child with 
			largest = right;							//parent and left child to see if it is bigger
		}

		if (largest != index) {//if the bigger number is not the parent then exchange
			int temp = arrayToSort[index];//and send the exchanged child to maxHeapify function again
			arrayToSort[index] = arrayToSort[largest];
			arrayToSort[largest] = temp;
		
			MaxHeapify(arrayToSort, largest , size);

		}
	}
	public void sort(int arr[])
    {
       
    }

	public static int Left(int i) {
		return 2 * i + 1;
	}

	public static int Right(int i) {
		return 2 * i + 2;
	}

	//
	
	
	// QUICKSORT
	
	//I used the information and pseudo code in course slides and this video https://www.youtube.com/watch?v=Fiot5yuwPAg 
	//to understand and complete quicksort algorithm
	public static void QuickSort(int[] arrayToSort, int il, int ir) {
		//this is the main class that calls itself recursively. It calls itself with the 3 arrays
		//that is divided from the main array
		if (il < ir && il > -1 && ir > -1) {//checking the indexes if they are partitionable
			int[] ilr = Partition(arrayToSort, il, ir);//calling partition function with the pivots indexes
			if (ilr[0] > -1 && ilr[1] > -1) {//checking the turning indexes
				if (ilr[0] > 1)
					QuickSort(arrayToSort, il, ilr[0] - 1);//calling the class itself with left array
				if (ilr[1] - ilr[0] > 2)
					QuickSort(arrayToSort, ilr[0] + 1, ilr[1] - 1);//calling the class itself with middle array
				if (ilr[1] < ir - 1)
					QuickSort(arrayToSort, ilr[1] + 1, ir);//calling the class itself with right array
			}

		}

	}

	public static int[] Partition(int[] arr, int l, int r) {
		if (arr[l] > arr[r]) {//comparing left and right pivots if left is not smaller than right exchange
			int temp = arr[l];
			arr[l] = arr[r];
			arr[r] = temp;
		} else if (arr[l] == arr[r]) {//if the left and right pivots are equal then travel the
									//array to find an unequal value. If found exchange
			boolean flag = false;
			for (int i = l; i < r; i++) {
				if (arr[i] < arr[r]) {
					int temp = arr[i];
					arr[i] = arr[l];
					arr[l] = temp;
					flag = true;
					break;
				}
				
			}
			if(!flag) {
				int[] back = { -1, -1 };
				return back;//if not found send unpartitionable indexes and end algorithm
			}
			
		}
		int[] ilr = new int[2];
		if (r - l >= 2) {//controlling if there is a compareble elements between left and right pivots
			int il = l;
			int temp = 0;

			for (int j = l + 1; j < r; j++) {//controlling if there is smaller numbers than left pivot
										//if there is then apply the algorithm that is in pseudo code
				// it increments the pointer to where the small number should come by 1
				//and exchanges the number in this index with the smaller number
				if (arr[j] < arr[l]) {
					il++;
					temp = arr[j];
					arr[j] = arr[il];
					arr[il] = temp;
				}
			}
			//exchange the pivot with the last exchanged smaller number
			temp = arr[il];
			arr[il] = arr[l];
			arr[l] = temp;

			int ir = r;//apply the same algorithm but this time start with right pivots one index left
					//not the starting of the array and control towards to left pivot and find bigger numbers
				//than the right pivot
			for (int j = r - 1; j > il; j--) {
				if (arr[j] > arr[r]) {
					ir--;
					temp = arr[j];
					arr[j] = arr[ir];
					arr[ir] = temp;
				}
			}
			//exchange the right pivot with the last exchanged bigger number
			temp = arr[ir];
			arr[ir] = arr[r];
			arr[r] = temp;
			//keep left and right pivots indexes
			ilr[0] = il;
			ilr[1] = ir;
		} else {
			ilr[0] = l;
			ilr[1] = r;
		}

		return ilr;
	}

	public static void Print(int[] array) {
		for (int i = 0; i < array.length; i++) {
			System.out.println(array[i]);
		}
	}

	//
	public static void main(String[] args) {
		int[] arrayToSort = new int[10];
		Random rnd = new Random();
		int sameNumber = Math.abs(rnd.nextInt());
		for (int i = 0; i < arrayToSort.length; i++) {
			arrayToSort[i] = Math.abs(rnd.nextInt(100));
			//arrayToSort[i] = sameNumber;
		}
//		for (int i = 0; i < arrayToSort.length; i++) {
//			arrayToSort[i] = i;
//		}
//		int index = 0;
//		for (int i = arrayToSort.length-1; i > 0; i--) {
//			arrayToSort[index] = i;
//			index++;
//		}
		long startTime = System.currentTimeMillis();
		//BucketSort(arrayToSort);
		HeapSort(arrayToSort);
		//QuickSort(arrayToSort, 0, arrayToSort.length - 1);
		long estimatedTime = System.currentTimeMillis() - startTime;
		System.out.println(estimatedTime);
		Print(arrayToSort);
		System.out.println();
	}

}
