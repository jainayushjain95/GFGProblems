package arrays;

public class Problem34 {

	public static void main(String[] args) {
		int[] input = {1, 2, 3, 4, 8};
		reverseAnArray(input, 0, input.length - 1);
		for(int x : input) {
			System.out.println(x);
		}
	}
	
	public static void reverseAnArray(int[] input, int beginIndex, int endIndex) {
		if(beginIndex >= endIndex) 
			return;
		swap(input, beginIndex, endIndex);
		reverseAnArray(input, beginIndex + 1, endIndex - 1);
	}
	
	public static void swap(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

}
