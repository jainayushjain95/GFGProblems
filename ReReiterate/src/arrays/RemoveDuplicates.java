package arrays;

public class RemoveDuplicates {

	public static void main(String[] args) {
		int[] array = {10, 20, 20, 30, 30, 30, 50, 50, 50};
		removeDuplicates(array);
		for(int x : array) {
			System.out.println(x);
		}
	}
	
	public static void removeDuplicates(int[] array) {
		int result = 0, n = array.length;
		for(int i = 1;i < n; i++) {
			if(array[i] != array[result]) {
				result++;
				array[result] = array[i];
			}
		}
		System.out.println("Size: " + result);
	}

}
