package sorting;

public class Problem80 {

	public static void main(String[] args) {
		int[] a = {8, 4, 7, 9, 3, 10, 5, 1, 2, 3};
		quickSort(a, 0, a.length - 1);
		for(int x : a) {
			System.out.println(x);
		}
	}
	
	public static void quickSort(int[] a, int beginIndex, int endIndex) {
		if(beginIndex >= endIndex) {
			return;
		}
		int partitionIndex = lomutoPartition(a, beginIndex, endIndex);
		quickSort(a, beginIndex, partitionIndex - 1);
		quickSort(a, partitionIndex + 1, endIndex);
	}
	
	public static int lomutoPartition(int[] a, int beginIndex, int endIndex) {
		int pivot = a[endIndex];
		int i = beginIndex - 1, j = beginIndex;
		while(j < endIndex) {
			if(a[j] < pivot) {
				swap(a, ++i, j);
			}
			j++;
		}
		swap(a, ++i, endIndex);
		return i;
	}
	
	public static void swap(int[] a, int i, int j) {
		int temp = a[i];
		a[i]= a[j];
		a[j] = temp;
	}

}
