class Sorting {
	public static <T extends Comparable<T>> void print(T[] items) {
		int i = 0;
		while (i < items.length && items[i] != null) {
			System.out.print(items[i].toString()+" ");
			i++;
		}
		System.out.println();
	}
	public static <T extends Comparable<T>> void selectionSort(T[] data){
		int min;
		T temp;

		for (int index = 0; index < data.length - 1; index++)
		{
			min = index;
			for (int scan = index + 1; scan < data.length; scan++)
				if (data[scan].compareTo(data[min]) < 0)
					min = scan;

			swap(data, min, index);
			print(data);
		}
	}

	
	private static <T extends Comparable<T>> void swap(T[] data, int index1, int index2)
	{
		T temp = data[index1];
		data[index1] = data[index2];
		data[index2] = temp;
	}

	/**
	 * Sorts the specified array of objects using an insertion
	 * sort algorithm.
	 */
	public static <T extends Comparable<T>> void insertionSort(T[] data)
	{
		for (int index = 1; index < data.length; index++)
		{
			T key = data[index];
			int position = index;

			// shift larger values to the right 
			while (position > 0 && data[position-1].compareTo(key) > 0)
			{
				data[position] = data[position - 1];
				position--;
			}

			data[position] = key;
			System.out.println("Insertion Sort:");
			print(data);
		}
	}

	/**
	 * Sorts the specified array of objects using a bubble sort
	 * algorithm.
	 */
	public static <T extends Comparable<T>> void bubbleSort(T[] data)
	{
		int position, scan;

		for (position =  data.length - 1; position >= 0; position--)
		{
			for (scan = 0; scan <= position - 1; scan++)
			{
				if (data[scan].compareTo(data[scan + 1]) > 0)
					swap(data, scan, scan + 1);
			}
			print(data);
		}
	}

	/**
	 * Sorts the specified array of objects using the quick sort algorithm.
	 */
	public static <T extends Comparable<T>> void quickSort(T[] data)
	{
		quickSort(data, 0, data.length - 1);
	}

	/**
	 * Recursively sorts a range of objects in the specified array using the
	 * quick sort algorithm.
	 * min  the minimum index in the range to be sorted
	 * max  the maximum index in the range to be sorted
	 */
	private static <T extends Comparable<T>> void quickSort(T[] data, int min, int max)
	{
		if (min < max)
		{
			// create partitions
			int indexofpartition = partition(data, min, max);

			// sort the left partition (lower values)
			quickSort(data, min, indexofpartition - 1);

			// sort the right partition (higher values)
			quickSort(data, indexofpartition + 1, max);
			print(data);
		}
	}

	/**
	 * Used by the quick sort algorithm to find the partition.
	 * min  the minimum index in the range to be sorted
	 * max  the maximum index in the range to be sorted
	 */
	private static <T extends Comparable<T>> int partition(T[] data, int min, int max)
	{
		T partitionelement;
		int left, right;
		int middle = (min + max) / 2;

		// use the middle data value as the partition element
		partitionelement = data[middle];
		
		// move it out of the way for now
		swap(data, middle, min);

		left = min;
		right = max;

		while (left < right)
		{
			// search for an element that is > the partition element
			while (left < right && data[left].compareTo(partitionelement) <= 0)
				left++;

			// search for an element that is < the partition element
			while (data[right].compareTo(partitionelement) > 0)
				right--;

			// swap the elements
			if (left < right)
				swap(data, left, right);
		}

		// move the partition element into place
		swap(data, min, right);

		return right;
	}
	
	/**
	 * Sorts the specified array of objects using the merge sort
	 * algorithm.
	 */
	public static <T extends Comparable<T>>void mergeSort(T[] data)
	{
		mergeSort(data, 0, data.length - 1);
	}

	/**
	 * Recursively sorts a range of objects in the specified array using the
	 * merge sort algorithm.
	 * min  the index of the first element
	 * max  the index of the last element
	 */
	private static <T extends Comparable<T>>void mergeSort(T[] data, int min, int max)
	{
		if (min < max)
		{
			int mid = (min + max) / 2;
			mergeSort(data, min, mid);
			mergeSort(data, mid + 1, max);
			merge(data, min, mid, max);
			print(data);
		}
	}

	/**
	 * Merges two sorted subarrays of the specified array.
	 * first the beginning index of the first subarray
	 * mid the ending index fo the first subarray
	 * last the ending index of the second subarray
	 */
	@SuppressWarnings("unchecked")
	private static <T extends Comparable<T>>void merge(T[] data, int first, int mid, int last)
	{
		T[] temp = (T[])(new Comparable[data.length]);

		int first1 = first, last1 = mid;  // endpoints of first subarray
		int first2 = mid + 1, last2 = last;  // endpoints of second subarray
		int index = first1;  // next index open in temp array

		//  Copy smaller item from each subarray into temp until one
		//  of the subarrays is exhausted
		while (first1 <= last1 && first2 <= last2)
		{
			if (data[first1].compareTo(data[first2]) < 0)
			{
				temp[index] = data[first1];
				first1++;
			}
			else
			{
				temp[index] = data[first2];
				first2++;
			}
			index++;
		}

		//  Copy remaining elements from first subarray, if any
		while (first1 <= last1)
		{
			temp[index] = data[first1];
			first1++;
			index++;
		}

		//  Copy remaining elements from second subarray, if any
		while (first2 <= last2)
		{
			temp[index] = data[first2];
			first2++;
			index++;
		}

		//  Copy merged data into original array
		for (index = first; index <= last; index++)
			data[index] = temp[index];
	}

}
class Inventory implements Comparable<Inventory>{
	private String name;
	private double cost;
	private int amount;
	
	public Inventory(String name, double cost, int amount) {
		super();
		this.name = name;
		this.cost = cost;
		this.amount = amount;
	}
	public String receiveName() {
		return name;
	}
	public double receiveCost() {
		return cost;
	}
	public int receiveAmount() {
		return amount;
	}
	
	@Override
	public String toString() {
		return name+ ", " + cost+ ", " + amount;
	}
	@Override
	public int compareTo(Inventory inv) {
		return name.compareTo(inv.name);
	}
}
public class SortItemList{
	public static void main(String[] args) {

		Inventory items[] = new Inventory[5];
		items[0] = new Inventory("CPU", 120.99, 20);
		items[1] = new Inventory("Motherboard", 140.99, 40);
		items[2] = new Inventory("RAM", 52.99, 50);
		items[3] = new Inventory("Power Supply", 130.99, 65);
		items[4] = new Inventory("Case", 190.99, 70);
		
		Inventory[] item1 = new Inventory[5];
		Inventory[] item2 = new Inventory[5];
		Inventory[] item3 = new Inventory[5];
		Inventory[] item4 = new Inventory[5];
		Inventory[] item5 = new Inventory[5];
		
		for(int i = 0; i<items.length;i++) {
			item1[i]=items[i];
			item2[i]=items[i];
			item3[i]=items[i];
			item4[i]=items[i];
			item5[i]=items[i];
		}
		
		System.out.println("Before:");
		printInventory(items);
		
		System.out.println("===================================================================");
		System.out.println("Selection Sort: ");
        Sorting.selectionSort(item1);
   	
		System.out.println("===================================================================");
		System.out.println("Bubble Sort: ");
        Sorting.bubbleSort(item2);
      
		System.out.println("===================================================================");
		System.out.println("Quick Sort: ");
        Sorting.quickSort(item3);
 
		System.out.println("===================================================================");
		System.out.println("Merge Sort: ");
        Sorting.mergeSort(item4);

		System.out.println("===================================================================");
		System.out.println("Insertion Sort: ");
        Sorting.insertionSort(item5);			
        
	}

	private static void printInventory(Inventory[] items) {
		int i = 0;
		while(i < items.length && items[i] != null) {
			System.out.println(items[i].toString() + " ");
			i++;
		}
		System.out.println();
		
	}
}

