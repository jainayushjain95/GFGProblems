package arrays;

public class Problem38 {

	public static void main(String[] args) {
		int[] a = {7, 10, 4, 1, 6, 5, 2};
		printLeadersInAnArray(a);
	}
	
	//naive solution - O(n*n)
	//Efficient - O(n)
	public static void printLeadersInAnArray(int[] a) {
		int n = a.length - 1;
		int currentLeader = a[n];
		System.out.println(currentLeader);
		for(int i = n - 1; i >= 0; i--) {
			if(a[i] > currentLeader) {
				System.out.println(a[i]);
				currentLeader = a[i];
			}
		}
	}

}
