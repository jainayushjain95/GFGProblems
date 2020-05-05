package arrays.geeksforgeeks;

public class LeadersInAnArray {

	public static void main(String[] args) {
		int[] a = {7, 10, 4, 3, 6, 5, 2};
		printLeadersOfAnArray(a);
	}
	
	public static void printLeadersOfAnArray(int[] a) {
		int currLeader = a[a.length - 1];
		System.out.println(currLeader);
		for(int i = a.length - 2; i >= 0; i--) {
			if(a[i] > currLeader) {
				currLeader = a[i];
				System.out.println(currLeader);
			}
		}
	}

}
