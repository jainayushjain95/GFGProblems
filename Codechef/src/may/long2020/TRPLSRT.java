package may.long2020;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class TRPLSRT {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		while(t != 0) {
			Map<Integer, Integer> hm = new HashMap<Integer, Integer>();
			int N = sc.nextInt();
			int K = sc.nextInt();
			int[] a = new int[N + 1];
			for(int i = 1;i <= N; i++) {
				a[i] = sc.nextInt();
				if(i != a[i]) {
					hm.put(a[i], i);
				}
			}
			solve(a, N, K, hm);
			t--;
		}
	}

	public static void solve(int[] a, int n, int k, Map<Integer, Integer> hm) {
		int steps = 0;
		
		List<Integer> list = new ArrayList<Integer>();
		
			
		boolean isSorted = true;
		
		for(int i = 1; i < a.length; i++) {
			if(a[i] != i) {
				isSorted = false;
				break;
			}
		}
		if(!isSorted) {
			System.out.println(-1);
		} else {
			System.out.println(steps);
			for(int i = 0; i < list.size(); i = i + 3) {
				System.out.println(list.get(i) + " " + list.get(i + 1) + " " + list.get(i + 2));
			}
		}
		
	}
	
}
