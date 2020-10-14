package amazon;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

//
//class Key { 
//    int freq;
//    char ch; 
//    Key(int val, char c)  { 
//        this.freq = val;  
//        this.ch = c; 
//    } 
//}
//
//class KeyComparator implements Comparator<Key> {
//
//	public int compare(Key o1, Key o2) {
//		if(o1.freq < o2.freq) {
//			return 1;
//		} else if(o1.freq > o2.freq) {
//			return -1;
//		}
//		return 0;
//	}
//	
//}

public class RearrangeCharacters {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = Integer.parseInt(sc.nextLine());
		while(t != 0) {
			System.out.println(solve(sc.nextLine()));
			t--;
		}
	}
	
	public static int solve(String S) {
		int ans = 0, N = S.length();
		int[] alpha = new int[26];
//		
//		PriorityQueue<Key> pq = new PriorityQueue<Key>();
//		
//		for(int i = 0;i < N; i++) {
//			alpha[S.charAt(i) - 'a']++;
//		}
//		
//		for(int i = 0;i < 26; i++) {
//			Key key = new Key(alpha[i], (char) (i + 97));
//			pq.add(key);
//		}
//		
		return ans;
	}

}
