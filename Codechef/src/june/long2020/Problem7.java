package june.long2020;

import java.util.List;
import java.util.Scanner;


public class Problem7 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		while(t != 0) {
			solve(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt());
			t--;
		}
	}

	public static void solve(int p, int q, int r, int a, int b, int c) {
		int noOfOperations = 0;
		int noOfAnamolies = getNoOfAnamolies(p, q, r, a, b, c);

		while(noOfAnamolies != 0) {
			int diff1 = a - p, diff2 = b - q, diff3 = c - r;
			
			int pdiff1 = (p != 0) ? a / p : 0;
			int pdiff2 = (q != 0) ? b / q : 0; 
			int pdiff3 = (r != 0) ? c / r : 0;
			
			if(noOfAnamolies == 1) {
				noOfAnamolies = 0;;
				noOfOperations++;
				break;
			} else if(noOfAnamolies == 2) {
				if(diff1 == diff2 || diff1 == diff3 || diff2 == diff3) {
					noOfAnamolies = 0;
					noOfOperations++;
					break; 
				} else if(pdiff1 == pdiff2 && isValidProduct(pdiff1) && isValidProduct(pdiff2)) {
					noOfAnamolies = 0;
					noOfOperations++;
					break;
				} else if(pdiff2 == pdiff3 && isValidProduct(pdiff2) && isValidProduct(pdiff3)) {
					noOfAnamolies = 0;
					noOfOperations++;
					break;
				} else if(pdiff1 == pdiff3 && isValidProduct(pdiff3) && isValidProduct(pdiff1)) {
					noOfAnamolies = 0;
					noOfOperations++;
					break;
				} else {
					noOfAnamolies = 0;
					noOfOperations += 2;
					break;
				}
			} else {
				if(diff1 == diff2 && diff1 == diff3 && diff2 == diff3) {
					noOfAnamolies = 0;
					noOfOperations++;
					break; 
				} else if(isDivisible(c, r) && isDivisible(b, q) && isDivisible(a, p) && pdiff1 == pdiff2 && pdiff1 == pdiff3 && pdiff2 == pdiff3 && isValidProduct(pdiff1) && isValidProduct(pdiff2) && isValidProduct(pdiff3)) {
					noOfAnamolies = 0;
					noOfOperations++;
					break; 
				} else if(diff1 == diff2 || diff1 == diff3 || diff2 == diff3) {
					noOfAnamolies = 0;
					noOfOperations += 2;
					break; 
				} else if(isDivisible(b, q) && isDivisible(a, p) && pdiff1 == pdiff2 && isValidProduct(pdiff1) && isValidProduct(pdiff2)) {
					noOfAnamolies = 0;
					noOfOperations += 2;
					break;
				} else if(isDivisible(c, r) && isDivisible(b, q) && pdiff2 == pdiff3 && isValidProduct(pdiff2) && isValidProduct(pdiff3)) {
					noOfAnamolies = 0;
					noOfOperations += 2;
					break;
				} else if(isDivisible(a, p) && isDivisible(c, r) && pdiff1 == pdiff3 && isValidProduct(pdiff1) && isValidProduct(pdiff3)) {
					noOfAnamolies = 0;
					noOfOperations += 2;
					break;
				} else {
					
					int aa = q + diff1;
					int bb = r + diff1;
					int cc = p + diff2;
					int dd = r + diff2;
					int ee = p + diff3;
					int ff = q + diff3;
					
					
					
					if(aa != 0 && bb != 0 && (b / (q + diff1)) == (c / (r + diff1)) && isValidProduct(b / (q + diff1)) && isValidProduct(c / (r + diff1)) && isDivisible(b, q + diff1) && isDivisible(c, r + diff1)) {
						q += diff1;
						r += diff1;
						p += diff1;
						noOfAnamolies--;
						noOfOperations++;
					} else if(cc != 0 && dd != 0 && (a / (p + diff2)) == (c / (r + diff2)) && isValidProduct(a / (p + diff2)) && isValidProduct(c / (r + diff2)) && isDivisible(a, p + diff2) && isDivisible(c, r + diff2)) {
						q += diff2;
						r += diff2;
						p += diff2;
						noOfAnamolies--;
						noOfOperations++;
					} else if(ee != 0 && ff != 0 && (a / (p + diff3)) == (b / (q + diff3)) && isValidProduct(b / (q + diff3)) && isValidProduct(a / (p + diff3)) && isDivisible(b, q + diff3) && isDivisible(a, p + diff3)) {
						q += diff3;
						r += diff3;
						p += diff3;
						noOfAnamolies--;
						noOfOperations++;
					} else if(aa != 0 && r != 0 && (b / (q + diff1)) == (c / r) && isValidProduct(b / (q + diff1)) && isValidProduct(c / r) && isDivisible(b, q + diff1) && isDivisible(c, r)) {
						q += diff1;
						p += diff1;
						noOfAnamolies--;
						noOfOperations++;
					} else if(bb != 0 && q != 0 && (c / (r + diff1)) == (b / q) && isValidProduct(c / (r + diff1)) && isValidProduct(b / q) && isDivisible(b, q) && isDivisible(c, r + diff1)) {
						r += diff1;
						p += diff1;
						noOfAnamolies--;
						noOfOperations++;
					} else if(r != 0 && cc != 0 && (a / (p + diff2)) == (c / r) && isValidProduct(a / (p + diff2)) && isValidProduct(c / r) && isDivisible(a, p + diff2) && isDivisible(c, r)) {
						p += diff2;
						q += diff2;
						noOfAnamolies--;
						noOfOperations++;
					} else if(dd != 0 && p != 0 && (c / (r + diff2)) == (a / p) && isValidProduct(c / (r + diff2)) && isValidProduct(a / p) && isDivisible(a, p) && isDivisible(c, r + diff2)) {
						q += diff2;
						r += diff2;
						noOfAnamolies--;
						noOfOperations++;
					} else if(q != 0 && ee != 0 && (a / (p + diff3)) == (b / q) && isValidProduct(a / (p + diff3)) && isValidProduct(b / q) && isDivisible(b, q) && isDivisible(a, p + diff3)) {
						r += diff3;
						p += diff3;
						noOfAnamolies--;
						noOfOperations++;
					} else if(ff != 0 && p != 0 && (b / (q + diff3)) == (a / p) && isValidProduct(b / (q + diff3)) && isValidProduct(a / p) && isDivisible(b, q + diff3) && isDivisible(a, p)) {
						q += diff3;
						r += diff3;
						noOfAnamolies--;
						noOfOperations++;
					} else {
						noOfAnamolies--;
						noOfOperations++;
						p += diff1;
					}
				}
			}
		}

		System.out.println(noOfOperations);
	}

	public static boolean isValidProduct(int x) {
		return x < -1 || x > 1;
	}
	
	public static boolean isDivisible(int a, int b) {
		return b != 0 && a % b == 0;
	}
	
	public static int getMinPositive(List<Integer> positives) {
		if(positives.size() == 1) {
			return positives.get(0);
		} else if(positives.size() == 2) {
			return Math.min(positives.get(1), positives.get(0));
		} else if(positives.size() == 3) {
			return Math.min(positives.get(1), Math.min(positives.get(0), positives.get(2)));
		}
		return 0;
	}

	public static int getMaxNegative(List<Integer> negatives) {
		if(negatives.size() == 1) {
			return negatives.get(0);
		} else if(negatives.size() == 2) {
			return Math.max(negatives.get(1), negatives.get(0));
		} else if(negatives.size() == 3) {
			return Math.max(negatives.get(1), Math.max(negatives.get(0), negatives.get(2)));
		}
		return 0;
	}

	public static int getNoOfAnamolies(int p, int q, int r, int a, int b, int c) {
		int noOfAnamolies = 0;

		if(p != a) {
			noOfAnamolies++;
		}
		if(b != q) {
			noOfAnamolies++;
		}
		if(c != r) {
			noOfAnamolies++;
		}
		return noOfAnamolies;
	}
}
