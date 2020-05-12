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
			//solveBF(X, Y, L, R);
			solve3(X, Y, L, R);
			t--;
		}

	}

	/*
	 * Brute Force
	 */
	public static void solveBF(long X, long Y, long L, long R) {
		long Z = -1, maxProduct = -1;
		for(long i = L; i <= R; i++) {
			long F = (X & i) * (Y & i);
			if(F > maxProduct) {
				maxProduct = F;
				Z = i;
			}
		}
		System.out.println(Z);
	}

	/*
	 * Targeted only for Subtask 1
	 */
	public static void solve(long X, long Y, long L, long R) {
		long Z = -1, maxProduct = -1;
		if(X == 0 || Y == 0) {
			Z = 0;
		} else {
			Z = X | Y;
		}
		System.out.println(Z);
	}

	/*
	 * Targeted only for Subtask 1 as well as Subtask 2
	 * 40: 10^12 max no: requires at max 40 bits
	 */
	public static long solve2(long X, long Y, long L, long R) {
		long Z = 0, maxProduct = 0, Z1 = 0;
		if(X == 0 || Y == 0 || R == 0) {
			Z = 0;
		} else {
			long lookup = X | Y;
			if(lookup <= R) {
				Z = lookup;
			} else {
				List<Integer> setBitsIndicesList = getSetBitsIndicesList(R);
				for(int i = 0;i < setBitsIndicesList.size(); i++) {
					Z1 = 0;
					int currentSetBitIndex = setBitsIndicesList.get(i);
					for(int j = 0; j < currentSetBitIndex; j++) {
						int k = j + 1;
						if(isKthBitSet(R, k)) {
							Z1 = getUpdatedZ(Z1, k);
						}
					}
					for(int j = currentSetBitIndex + 1; j < 40; j++) {
						int k = j + 1;
						if(isKthBitSet(lookup, k)) {
							Z1 = getUpdatedZ(Z1, k);
						}
					}
					if(Z1 < R) {
						long currProduct = F(X, Y, Z1);
						if(currProduct > maxProduct) {
							Z = Z1;
							maxProduct = currProduct;
						} else if(currProduct == maxProduct) {
							Z = Math.min(Z1, Z);
						}
					}
				}
				if(isMaxForRightBoundary(X, Y, Z, R) ) {
					Z = R;
				}
			}
		}
		return Z;
	}


	/*
	 * handles all the cases
	 * 40: 10^12 max no: requires at max 40 bits
	 */
	public static void solve3(long X, long Y, long L, long R) {
		long Z = 0, maxProduct = 0, Z1 = 0;
		if(X == 0 || Y == 0) {
			Z = 0;
		} else if(L == R) {
			Z = L;
		} else {
			long lookup = X | Y;
			if(lookup >= L && lookup <= R) {
				Z = lookup;
			} else {
				Z = solve2(X, Y, 0, R);
				int M = getLongestCommonPrefix(Z, R);
				int k = getLongestCommonPrefix(L, R);
			}
		}
	}


	/*
	 * Helpers
	 */

	//1st bit is MSB
	public static boolean isKthBitSet(long n, int k) {
		return (1l & (n >> (40 - k))) != 0;
	}

	public static int getLongestCommonPrefix(long A, long B) {
		int lcp = 0;
		for(int i = 0;i < 40; i++) {
			if(isKthBitSet(A, lcp + 1) == isKthBitSet(B, lcp + 1)) {
				lcp++;
			} else {
				break;
			}
		}
		return lcp;
	}

	public static long F(long X, long Y, long Z) {
		return (X & Z) * (Y & Z);
	}

	public static boolean isMaxForRightBoundary(long X, long Y, long Z, long R) {
		return F(X, Y, R) > F(X, Y, Z);
	}

	public static long getUpdatedZ(long Z, int k) {
		Z = Z | (1l << (40 - k));
		return Z;
	}

	public static int noOfBitsReqd(long n) {
		return 1 + (int)Math.floor(Math.log(n)/Math.log(2));
	}

	public static List<Integer> getSetBitsIndicesList(long n) {
		List<Integer> list = new ArrayList<Integer>();
		int index = 0;
		while(n > 0) {
			if((n & 1) != 0) {
				list.add(0, 40 - index - 1);
			}
			index++;
			n = n >> 1;
		}
		return list;
	}

}
