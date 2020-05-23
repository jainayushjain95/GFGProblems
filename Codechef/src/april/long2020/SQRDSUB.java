package april.long2020;

import java.util.Scanner;

public class SQRDSUB {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		StringBuilder output = new StringBuilder();
		while(t != 0) {
			int N = sc.nextInt();
			int[] a = new int[N];
			for(int i = 0;i < N; i++) {
				a[i] = sc.nextInt();
				if(a[i] % 2 != 0) {
					a[i] = 0;
				} else if(a[i] % 4 == 0) {
					a[i] = 2;
				} else {
					a[i] = 1;
				}
			}
			output.append(solve(a, N) + "\n");
			t--;
		}
		System.out.println(output);
	}

	public static long solve(int[] a, int N) {
		/*
		 * Now the array has only 3 possible values: 0, 1, 2
		 */
		long x = getNoOfSubArraysWithOddProduct(a);
		long y = getNoOfSubarraysWithProductDivisibleByFour(a);
		return (x + y);
	}

	public static long getNoOfSubArraysWithOddProduct(int[] a) {
		long count = 0, temp = 0;
		for(int i = 0;i < a.length; i++) {
			if(a[i] == 0) {
				temp++;
			} else {
				count += (temp) * (temp + 1)/2;
				temp = 0;
			}
		}
		if(temp != 0) {
			count += (temp) * (temp + 1)/2;
		}
		return count;
	}


	/*
	 * Calculates no of subarrays with sum < 2
	 */
	public static long getNoOfSubarraysWithSumLessThanTwo(int[] a) {
		long count = 0, sum = a[0], n = a.length;
		int i = 0, j = 0;
		
		while(i < n && j < n) {
			if(sum < 2) {
				j++;
				if(j >= i) {
					count += j - i;
				}
				if(j < n) {
					sum += a[j];
				}
			} else {
				sum -= a[i];
				i++;
			}
		}
		return count;
	}



	public static long getNoOfSubarraysWithProductDivisibleByFour(int[] a) {
		int n = a.length;
		long ln = (long)n;
		long x = ln * (ln + 1)/2;
		long y = getNoOfSubarraysWithSumLessThanTwo(a);
		return x - y;
	}

}
