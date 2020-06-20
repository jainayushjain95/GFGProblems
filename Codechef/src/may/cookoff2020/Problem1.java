package may.cookoff2020;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

public class Problem1 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		StringBuilder str = new StringBuilder();
		while(t != 0) {
			int n = sc.nextInt();
			int[] a = new int[n];
			HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
			for(int i = 0;i < n; i++) {
				a[i] = sc.nextInt();
				if(hm.containsKey(a[i])) {
					hm.put(a[i], 1 + hm.get(a[i]));
				} else {
					hm.put(a[i], 1);
				}
			}
			str.append(solve(n, a, hm) + "\n");
			t--;
		}
		System.out.println(str.toString());
	}

	public static String solve(int n, int[] a, HashMap<Integer, Integer> hm) {
		StringBuilder str = new StringBuilder();
		HashMap<Integer, Integer> visited = new HashMap<Integer, Integer>();
		int i, j;
		boolean flag = true;
		for(i = 0;i < n && flag;) {
			for(j = i + 1; j < n && a[j] == a[j - 1]; j++) {
			}
			if(visited.containsKey(a[j - 1])) {
				flag = false;
				break;
			} else {
				visited.put(a[j - 1], 1);
			}
			i = j;
		}
		int count = 0;
		
		if(flag) {
			HashSet<Integer> hs = new HashSet<Integer>();
			
			for(Integer x : hm.keySet()) {
				if(!hs.contains(hm.get(x))) {
					hs.add(hm.get(x));
					count++;
				}
			}
			
		}
		
		if(!flag || count != hm.size()) {
			str.append("NO");
		} else {
			str.append("YES");
		}
		
		return str.toString();
	}

}
