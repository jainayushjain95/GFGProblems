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
//			solveBF(X, Y, L, R);
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
		if(R == 0) {
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
	 * https://www.youtube.com/watch?v=-F7cHQ-gWS4
	 */
	public static void solve3(long X, long Y, long L, long R) {
		long Z = 0l;
		if(X == 0 || Y == 0) {
			Z = L;
		} else if(L == R) {
			Z = L;
		} else {
			long lookup = X | Y;
			if(lookup >= L && lookup <= R) {
				Z = lookup;
			} else if(L == 0) {
				Z = solve2(X, Y, 0, R);
			} else {
				int K = getLongestCommonPrefix(L, R);
				long Z1 = 0l, Z2 = 0l;
				
				for(int i = 0; i < K; i++) {
					int k = i + 1;
					if(isKthBitSet(L, k)) {
						Z1 = getUpdatedZ(Z1, k);
					}
				}
				
				Z2 = Z1;
				Z2 = getUpdatedZ(Z2, K + 1);
				
				Z1 = getZ1(X, Y, L, Z1, K, lookup);
				Z2 = getZ2(X, Y, R, Z2, K, lookup);
				
				long FZ1 = F(X, Y, Z1);
				long FZ2 = F(X, Y, Z2);
				
				if(FZ1 > FZ2) {
					Z = Z1;
				} else if(FZ1 < FZ2) {
					Z = Z2;
				} else {
					Z = Math.min(Z1, Z2);
				}
				
				long FZ = F(X, Y, Z);
				
				long FZL = F(X, Y, L);
				long FZR = F(X, Y, R);
				
				if(FZ < FZR) {
					Z = R;
					FZ = FZR;
				}
				
				if(FZ < FZL) {
					Z = L;
				} else if(FZ == FZL) {
					Z = L;
				}
			}
		}
		System.out.println(Z);
	}

	public static long getZ2(long X, long Y, long R, long Z2, int K, long lookup) {
		long maxProduct = 0l, Z = 0l, tempZ2 = Z2;
		List<Integer> setBitsList = getSetBitsIndicesListAfterIndexK(R, K);
		for(int i = 0;i < setBitsList.size(); i++) {
			tempZ2 = Z2;
			int currIndex = setBitsList.get(i);
			for(int j = K; j < currIndex; j++) {
				if(isKthBitSet(R, j + 1)) {
					tempZ2 = getUpdatedZ(tempZ2, j + 1);
				}
			}
			for(int j = currIndex + 1;j < 40; j++) {
				if(isKthBitSet(lookup, j + 1)) {
					tempZ2 = getUpdatedZ(tempZ2, j + 1);
				}
			}
			long currProduct = F(X, Y, tempZ2);
			if(currProduct > maxProduct) {
				Z = tempZ2;
				maxProduct = currProduct;
			} else if(currProduct == maxProduct) {
				Z = Math.min(tempZ2, Z);
			}
		}
		return Z;
	}

	public static long getZ1(long X, long Y, long L, long Z1, int K, long lookup) {
		List<Integer> unsetBits = getUnsetBitsIndicesListAfterIndexK(L, K);
		long maxProduct = 0l, Z = 0l, tempZ1 = Z1;;
		for(int i = 0;i < unsetBits.size(); i++) {
			tempZ1 = Z1;
			int currIndex = unsetBits.get(i);
			for(int j = K; j < currIndex; j++) {
				if(isKthBitSet(L, j + 1)) {
					tempZ1 = getUpdatedZ(tempZ1, j + 1);
				}
			}
			tempZ1 = getUpdatedZ(tempZ1, currIndex + 1);
			for(int j = currIndex + 1;j < 40; j++) {
				if(isKthBitSet(lookup, j + 1)) {
					tempZ1 = getUpdatedZ(tempZ1, j + 1);
				}
			}
			long currProduct = F(X, Y, tempZ1);
			if(currProduct > maxProduct) {
				Z = tempZ1;
				maxProduct = currProduct;
			} else if(currProduct == maxProduct) {
				Z = Math.min(tempZ1, Z);
			}
		}
		return Z;
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

	public static List<Integer> getUnsetBitsIndicesListAfterIndexK(long n, int k) {
		List<Integer> list = new ArrayList<Integer>();
		int offset = k;

		int index = offset + 1;
		List<Integer> setBitsList = getSetBitsIndicesList(n);
		for(int i = index;i < 40; i++) {
			if(!setBitsList.contains(i)) {
				list.add(i);
			}
		}
		return list;
	}
	
	public static List<Integer> getSetBitsIndicesListAfterIndexK(long n, int k) {
		List<Integer> list = new ArrayList<Integer>();
		int offset = k;

		int index = offset + 1;
		List<Integer> setBitsList = getSetBitsIndicesList(n);
		for(int i = index;i < 40; i++) {
			if(setBitsList.contains(i)) {
				list.add(i);
			}
		}
		return list;
	}
	
	public static List<Integer> getSetBitsIndicesList(long n) {
		List<Integer> list = new ArrayList<Integer>();
		int index = 0;
		while(n > 0) {
			if((n & 1l) != 0) {
				list.add(0, 40 - index - 1);
			}
			index++;
			n = n >> 1;
		}
		return list;
	}

}
