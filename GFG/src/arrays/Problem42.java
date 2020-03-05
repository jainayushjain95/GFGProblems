package arrays;

public class Problem42 {

	public static void main(String[] args) {
		int[] a = {0};
		System.out.println(getCountMaxConsecutiveOnes(a));
	}

	public static int getCountMaxConsecutiveOnes(int[] a) {
		int output = 0, n = a.length;
		int tempCount = 0;
		
		for(int i = 0; i < n; i++) {	
			if(a[i] == 0) {
				tempCount = 0;
			} else {
				tempCount++;
				output = Math.max(output, tempCount);
			}
		}
		return output;
	}
	
}
