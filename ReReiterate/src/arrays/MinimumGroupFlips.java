package arrays;

public class MinimumGroupFlips {

	public static void main(String[] args) {
		int[] a = {0, 1};
		solve(a, a.length);
	}

	public static void solve(int[] a, int n) {
		boolean flipZeroes = (a[0] == 1), flipOnes = (a[0] == 0);
		
		for(int i = 0;i < n;) {
			int j = i;
			if(flipOnes && a[j] == 1) {
				while(j < n && a[j] == 1) {
					j++;
				}
				System.out.println("From " + i + " to " + (j - 1));
			} else if(flipZeroes && a[j] == 0) {
				while(j < n && a[j] == 0) {
					j++;
				}
				System.out.println("From " + i + " to " + (j - 1));
			}
			i = j + 1;
		}
	}
	
}
