package practice.easy;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class MATBREAK {
	
	public static int mod = 1000000007;
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		StringBuilder s = new StringBuilder();
		while(t != 0) {
			int N = sc.nextInt();
			long A = sc.nextLong();
			s.append(solve(N, A) + "\n");
			t--;
		}
		System.out.println(s.toString());
	}
	
	
	public static long solve(int N, long A) {
		long totalSum = 0, temp = 1;
		for(int i = 1; i <= N; i++) {
			temp = fastExponentiation(A, 2*i - 1);
			A = A * temp;
			if(A >= mod) {
				A = A % mod;
			}
			totalSum += temp;
			if(totalSum >= mod) {
				totalSum = totalSum % mod;
			}
		}
		return totalSum;
	}
	
	public static long fastExponentiation(long number, long exponent) {
		if(number == 0) {
			return 0;
		}
		if(exponent == 0) {
			return 1;
		}
		if(exponent == 1) {
			return number;
		}
		if(exponent % 2 == 0) {
			long secondHalfResult = fastExponentiation(number, exponent/2);
			long ans = secondHalfResult * secondHalfResult;
			if(ans >= mod) {
				ans = ans % mod;
			}
			return ans;
		} else {
			long secondHalfResult = fastExponentiation(number, exponent/2);
			long ans = secondHalfResult * secondHalfResult;
			if(ans >= mod) {
				ans = ans % mod;
			}
			ans = ans * number;
			if(ans >= mod) {
				ans = ans % mod;
			}
			return ans;
		}
	}
	
}


