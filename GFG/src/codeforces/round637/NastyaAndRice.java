package codeforces.round637;

import java.util.Scanner;

public class NastyaAndRice {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		while(t != 0) {
			int n = sc.nextInt();
			int a = sc.nextInt();
			int b = sc.nextInt();
			int c = sc.nextInt();
			int d = sc.nextInt();
			
			ifPossible(n, a, b, c, d);
			t--;
		}

	}
	
	
	public static void ifPossible(int n, int a, int b, int c, int d) {
		int minSum = c - d;
		int maxSum = c + d;
		
		int minWeight = a - b;
		int maxWeight = a + b;
		
		
//		int tempWeight = (minWeight + maxWeight)/2;
//		
//		boolean flag = false;
//		
//		while(tempWeight >= minWeight && tempWeight <= maxWeight) {
//			int w = tempWeight * n;
//			if(w >= minSum && w <= maxSum) {
//				flag = true;
//				break;
//			} else if(w < minSum) {
//				minWeight = tempWeight + 1;
//				tempWeight = (minWeight + maxWeight)/2;
//			} else {
//				maxWeight = tempWeight - 1;
//				tempWeight = (minWeight + maxWeight)/2;
//			}
//		}
		boolean flag = false;
		
		int minPossibleWeight = minWeight * n;
		int maxPossibleWeight = maxWeight * n;
		
		if(minPossibleWeight <= maxSum && minPossibleWeight >= minSum) {
			flag = true;
		} else if(maxPossibleWeight >= minSum && maxPossibleWeight <= maxSum) {
			flag = true;
		}
		
		if(flag) {
			System.out.println("Yes");
		} else {
			System.out.println("No");
		}

	}

}
