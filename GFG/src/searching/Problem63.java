package searching;

import java.util.Arrays;
import java.util.Collection;

public class Problem63 {

	public static void main(String[] args) {
		int n = 10;
		int[] a = new int[n];
		for(int i = 0;i < n; i++) {
			a[i] = (int)(Math.random()*5);
		}
		
		int m = 1;//(int)(Math.random()*5);
		
		Arrays.parallelSort(a);
		System.out.println(1 + rightMostOccurence(a, 0, n - 1, m) - leftMostOccurence(a, 0, n - 1, m));

		
	}

	public static int leftMostOccurence(int[] a, int beginIndex, int endIndex, int element) {
		if(beginIndex > endIndex) {
			return -1;
		}
		int mid = (endIndex - beginIndex)/2 + beginIndex;
		
		if(a[mid] == element && (mid == 0 || a[mid - 1] != element)) {
			return mid;
		}
		
		if(a[mid] < element) {
			return leftMostOccurence(a, mid + 1, endIndex, element);
		} else {
			return leftMostOccurence(a, beginIndex, mid - 1, element);
		} 
	}
	
	
	public static int rightMostOccurence(int[] a, int beginIndex, int endIndex, int element) {
		if(beginIndex > endIndex) {
			return -1;
		}
		int mid = (endIndex - beginIndex)/2 + beginIndex;
		
		if(a[mid] == element && (mid == a.length - 1 || a[mid + 1] != element)) {
			return mid;
		}
		
		if(a[mid] > element) {
			return rightMostOccurence(a, beginIndex, mid - 1, element);
		} else {
			return rightMostOccurence(a, mid + 1, endIndex, element);
		} 
	}
	
	
	public static int co(int[] a, int beginIndex, int endIndex, int element) {
		int count = 0;
		for(int x : a) {
			if(x == element) {
				count++;
			}
		}
		return count;
	}
	

}
