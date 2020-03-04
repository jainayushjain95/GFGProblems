package arrays;

public class Problem37 {

	public static void main(String[] args) {
		int[] input = {1, 2, 3, 4, 5};
		int d = 2;
		leftRotateByDElements2(input, d);
		for(int x : input) {
			System.out.println(x);
		}
	}
	
	//naive approach O(N*d)
	public static void leftRotateByDElements1(int[] input, int d) {
		for(int i = 0;i < d; i++) {
			leftRotatebyOne(input);
		}
	}
	
	//O(N)
	public static void leftRotateByDElements2(int[] input, int d) {
		int n = input.length ;
		reverse(input, 0, d - 1);
		reverse(input, d, n - 1);
		reverse(input, 0, n - 1);
	}
	
	
	
	
	//Helpers
	public static void leftRotatebyOne(int input[]) {
		int firstElement = input[0];
		for(int beginIndex = 1; beginIndex < input.length; beginIndex++) {
			input[beginIndex - 1] = input[beginIndex]; 
		}
		input[input.length - 1] = firstElement;
	}
	
	public static void reverse(int[] input, int i, int j) {
		if(i >= j) {
			return;
		}
		swap(input, i, j);
		reverse(input, i + 1, j - 1);
	}
	
	public static void swap(int[] input, int i, int j) {
		int temp = input[i];
		input[i] = input[j];
		input[j] = temp;
	}

}
