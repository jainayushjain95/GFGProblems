package may.long2020;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CORUS {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		while(t != 0) {
			int n = sc.nextInt();
			int q = sc.nextInt();
			String s = sc.next();
			int[] c = new int[q];
			for(int i = 0;i < q; i++) {
				c[i] = sc.nextInt();
			}
			solve(n, q, s, c);
			t--;
		}

	}

	public static void solve(int n, int q, String s, int[] c) {
		Map<Character, Integer> hm = getFrequency(s);
		for(int i = 0; i < q; i++) {
			int noOfIsolationCenters = c[i];
			int queueSize = 0;
			for(Map.Entry<Character, Integer> entry : hm.entrySet()) {
				Character curr = entry.getKey();
				int left = hm.get(curr) - noOfIsolationCenters;
				queueSize += (left < 0) ? 0 : left;
			}
			
			System.out.println(queueSize);
		}
	}

	public static Map<Character, Integer> getFrequency(String s) {
		Map<Character, Integer> hm = new HashMap<Character, Integer>();
		for(int i = 0;i < s.length(); i++) {
			Character curr = s.charAt(i);
			if(hm.containsKey(curr)) {
				hm.put(curr, hm.get(curr) + 1);
			} else {
				hm.put(curr, 1);
			}
		}
		return hm;
	}

}
