package arrays.geeksforgeeks;

public class MajorityElement {

	public static void main(String[] args) {
		int[] a = {8, 3, 4, 8};
		solve(a);
	}
	
	/*
	 * Boyer Moore vote algorithm
	 */
	public static void solve(int[] a) {
		int candidateIndex = getCandidateIndex(a);
		System.out.println(candidateIndex);
		System.out.println(isValid(a, candidateIndex));
	}
	
	/*
	 * Step 1: Guarantees that if there exists an majority element, it would give that
	 */
	public static int getCandidateIndex(int[] a) {
		int candidateIndex = 0;
		int count = 0;
		for(int i = 0;i < a.length; i++) {
			if(count == 0) {
				candidateIndex = i;
				count = 1;
			} else if(a[candidateIndex] == a[i]) {
				count ++; 
			} else {
				count--;
			}
		}
		return candidateIndex;
	}
	
	/*
	 * Step 2: verifies if candidate is actually a majority element
	 * Not reqd if it is guaranteed that there exists a majority element for sure
	 */
	public static boolean isValid(int[] a, int index) {
		int count = 0;
		for(int x : a) {
			if(x == a[index]) {
				count++;
			}
		}
		return count > a.length/2;
	}

}
