package may.lt2020;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Problem3 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		StringBuilder str = new StringBuilder();
		while(t != 0) {
			int n = sc.nextInt();
			String a = sc.next();
			String b = sc.next();
			str.append(solve(a, b, n) + "\n");
			t--;
		}
		System.out.println(str.toString());
	}

	public static String solve(String a, String b, int n) {
		StringBuilder str = new StringBuilder();
		StringBuilder str1 = new StringBuilder();
		int[] pa = new int[256];
		
		int noOfAnamolies = 0;
		
		for(int i = 0; i < a.length(); i++) {
			char ca = a.charAt(i);
			pa[ca]++;
			char cb = b.charAt(i);
			if(ca != cb) {
				noOfAnamolies++;
			}
		}
		
		if(noOfAnamolies != 0) {
			int noOfOperations = 0;
			for(int i = 122; i >= 97 && noOfAnamolies != 0; i--) {
				List<Integer> lsa = new ArrayList<Integer>();
				String temp = "";
				for(int k = 0;k < n; k++) {
					if(a.charAt(k) > i && b.charAt(k) == i) {
						lsa.add(k);
						temp += k + " ";
					}
				}
				
				if(pa[i] != 0 && lsa.size() != 0) {
					noOfOperations++;
					str1.append((1 + lsa.size()) + " " + a.indexOf(i) + " " + temp);
					noOfAnamolies -= lsa.size();
					if(noOfAnamolies != 0) {
						str1.append("\n");
					}
				}
				
			}
			
			if(noOfAnamolies == 0) {
				str.append(noOfOperations + "\n");
				str.append(str1);
			} else {
				str.append("-1");
			}
		} else {
			str.append("0");
		}
		
		
		return str.toString();
	}

}
