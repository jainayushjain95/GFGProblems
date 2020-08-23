package arrays;

public class MajorityElement {

	public static void main(String[] args) {
		int[] a = {8, 8, 6, 6, 6, 4, 6};
		solve(a, a.length);
	}

	public static void solve(int[] a, int n) {
		int resultIndex = 0, count = 1;
		for(int i = 1; i < n; i++) {
			if(a[resultIndex] == a[i]) {
				count++;
			} else {
				count--;
			}
			if(count == 0) {
				count = 1;
				resultIndex = i;
			}
		}
		count = 0;
		for(int i = 0;i < n; i++) {
			if(a[i] == a[resultIndex]) {
				count++;
			}
		}
		if(count > n/2) {
			System.out.println(resultIndex);
		} else {
			System.out.println(-1);
		}
	}
}
