package amazon;

import java.util.HashMap;
import java.util.Scanner;

public class SecondMostRepeatedStringInSequence {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = Integer.parseInt(sc.nextLine());
		while(t != 0) {
			int N = Integer.parseInt(sc.nextLine());
			String S = sc.nextLine();
			String[] strings = S.split(" ");
			System.out.println(solve(N, strings));
			t--;
		}
	}
	
	public static String solve(int N, String[] strings) {
		String max = "", solution = "";
		int maxFreq = 0, secMaxFreq = -1;
		HashMap<String, Integer> hm = new HashMap<String, Integer>();
		for(int i = 0;i < N; i++) {
			if(hm.containsKey(strings[i])) {
				hm.put(strings[i], hm.get(strings[i]) + 1);
			} else {
				hm.put(strings[i], 1);
			}
		}
		
		for(String s : hm.keySet()) {
			int freq = hm.get(s);
			if(freq > maxFreq) {
				solution = max;
				max = s;
				secMaxFreq = maxFreq;
				maxFreq = freq;
			} else if(freq > secMaxFreq) {
				secMaxFreq = freq;
				solution = s;
			}
		}
		
		return solution;
	}
	

}
