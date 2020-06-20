package may642;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Problem4 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		while(t != 0) {
			int n = sc.nextInt();
			solve(n);
			t--;
		}
	}

	public static void solve(int n) {
		int[] a = new int[n + 1];

		PriorityQueue<Pair> queue = new PriorityQueue<Pair>(n, new PairComparator());
		queue.add(new Pair(1, n));

		int action = 1;

		while(!queue.isEmpty()) {
			Pair currPair = queue.poll();
			int length = currPair.b - currPair.a + 1;

			int left = currPair.a, right = currPair.b;
			int index = 0;


			if(length % 2 != 0) {
				index = (left + right) / 2;
			} else {
				index = (left + right - 1) / 2;
			}
			a[index] = action++;

			if(currPair.a <= (index - 1)) {
				queue.add(new Pair(currPair.a, index - 1));
			}

			if(currPair.b >= (index + 1)) {
				queue.add(new Pair(index + 1, currPair.b));
			}

		}

		for(int i = 1; i <= n;  i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println();
	}

}

class Pair {
	public int a;
	public int b;
	public Pair(int a, int b) {
		this.a = a;
		this.b = b;
	}
}

class PairComparator implements Comparator<Pair> {

	public int compare(Pair o1, Pair o2) {
		int l1 = o1.b - o1.a;
		int l2 = o2.b - o2.a;
		if(l2 > l1) {
			return 1;
		} else if(l1 > l2) {
			return -1;
		} else {
			if(o1.a < o2.a) {
				return -1;
			} else {
				return 1;
			}
		}
	}

}
