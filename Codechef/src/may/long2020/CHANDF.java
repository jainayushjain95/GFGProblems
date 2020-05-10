package may.long2020;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CHANDF {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		while(t != 0) {
			long X = sc.nextLong();
			long Y = sc.nextLong();
			long L = sc.nextLong();
			long R = sc.nextLong();
			solve(X, Y, L, R);
			t--;
		}

	}
	
	public static void solve(long X, long Y, long L, long R) {
		long Z = -1, maxProduct = -1;
		long xBits = getNoOfBits(X);
		long yBits = getNoOfBits(Y);
		long lBits = getNoOfBits(L);
		long rBits = getNoOfBits(R);
		
		Z = getMR(X, Y, xBits, yBits).get(1);
		if(Z <= R && Z >= L) {
			System.out.println(Z);
			return;
		} 
		System.out.println(-1);
	}

	public static List<Integer> getMR(long X, long Y, long xBits, long yBits) {
		long Z = 0;
		int i = 0;
		List<Integer> ZBitsList = new ArrayList<Integer>();
		while(xBits != 0 && yBits != 0) {
			long xParity = X & 1;
			long yParity = Y & 1;
			if(xParity != 0 || yParity != 0) {
				ZBitsList.add(1);
			} else {
				ZBitsList.add(0);
			}
			xBits --;
			yBits --;
			X = X >> 1;
			Y = Y >> 1;
			i++;
		}
		while(xBits != 0) {
			long xParity = X & 1;
			if(xParity != 0) {
				ZBitsList.add(1);
			} else {
				ZBitsList.add(0);
			}
			xBits--;
			X = X >> 1;
			i++;
		}
		while(yBits != 0) {
			long yParity = Y & 1;
			if(yParity != 0) {
				ZBitsList.add(1);
			} else {
				ZBitsList.add(0);
			}
			yBits--;
			Y = Y >> 1;
			i++;
		}
		return ZBitsList;
	}
	
	public static long getNoOfBits(long n) {
		return 1 + (int)Math.floor(Math.log(n)/Math.log(2));
	}
	
}
