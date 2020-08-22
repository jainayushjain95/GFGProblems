package arrays;

public class LeadersInArray {

	public static void main(String[] args) {
		int[] a = {30, 20, 10};
		printLeaders(a.length, a);
	}
	
	public static void printLeaders(int n, int[] a) {
		int currentLeader = a[n - 1];
		System.out.println(currentLeader);
		
		for(int i = n - 1; i>= 0; i--) {
			if(a[i] > currentLeader) {
				currentLeader = a[i];
				System.out.println(currentLeader);
			}
		}
		
	}

}
