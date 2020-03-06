package arrays;

public class Problem49 {

	public static void main(String[] args) {
		int[] a = {8, 8, 6, 6, 6, 4, 6};
		System.out.println(findMajority(a));
	}

	//Moore's Voting Algo
	public static int findMajority(int[] a) {
		int candidate = getCandidate(a);
		int count = 0;
		for(int x : a) {
			if(a[candidate] == x) {
				count++;
			}
		}
		if(count > a.length/2) {
			return count;
		}
		return -1;
	}
	
	public static int getCandidate(int[] a) {
		int result = 0, n = a.length, count = 1;
		for(int i = 1; i < n; i++) {
			if(a[i] == a[result]) {
				count++;
			} else {
				count--;
			}
			if(count == 0) {
				result = i;
				count = 1;
			}
		}
		return result;
	}
	
}
