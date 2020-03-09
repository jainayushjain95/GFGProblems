package arrays;

import java.util.ArrayList;

public class Problem60 {

	public static void main(String[] args) {
		ArrayList<Integer> a = new ArrayList<Integer>();
		for(int i = 1; i <= 8; i++) {
			a.add(i);
		}
		reverseInGroups(3, a, 0);
	}
	
	public static ArrayList<Integer> reverseInGroups(int k, ArrayList<Integer> a, int beginIndex) {
		if(beginIndex < a.size()) {
			reverse(a, beginIndex, Math.min(a.size() - 1, beginIndex + k - 1));
			reverseInGroups(k, a, k + beginIndex);
		}
		return a;
	}
	
	public static void reverse(ArrayList<Integer> a, int i, int j) {
		if(i >= j) {
			return;
		}
		swap(a, i, j);
		reverse(a, ++i, --j);
	}
	
	public static void swap(ArrayList<Integer> a, int i, int j) {
		int x = a.get(i);
		int y = a.get(j);
		a.set(i, y);
		a.set(j, x);
	}

}
