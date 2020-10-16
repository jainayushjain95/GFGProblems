package amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class GroupAnagramsTogether {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(sc.nextLine());
		while(t != 0) {
			t--;
			int N = Integer.parseInt(sc.nextLine());
			sb.append(solve(N, sc.nextLine()) + "\n");
		}
		System.out.println(sb.toString());
	}
	
	public static String solve(int N, String S) {
		StringBuilder solution = new StringBuilder();
		String[] strings = S.split(" ");
		
		HashMap<HashMap<Character, Integer>, Integer> hm = new HashMap<HashMap<Character,Integer>, Integer>();
		for(int i = 0;i < N; i++) {
			String s = strings[i];
			HashMap<Character, Integer> h = new HashMap<Character, Integer>();
			for(int j = 0;j < s.length(); j++) {
				if(h.containsKey(s.charAt(j))) {
					h.put(s.charAt(j), 1 + h.get(s.charAt(j)));
				} else {
					h.put(s.charAt(j), 1);
				}
			}
			if(hm.containsKey(h)) {
				hm.put(h, hm.get(h) + 1);
			} else {
				hm.put(h, 1);
			}
		}
		
		List<Integer> list = new ArrayList<Integer>();
		for(Integer x : hm.values()) {
			list.add(x);
		}
		Collections.sort(list);
		for(int x : list) {
			solution.append(x);
			solution.append(" ");
		}
		return solution.toString();
	}
	
}
