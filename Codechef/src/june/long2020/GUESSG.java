package june.long2020;

import java.util.Scanner;

public class GUESSG {

	public static String finalR = "a";
	public static boolean flag = false;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int left = 1, right = N;
		solve(N, left, right, sc);
	}

	public static void solve(int N, int left, int right, Scanner sc) {
		if(left > right) {
			return;
		}

		if(finalR.equals("E")) {
			return;
		}
		
		int size = (int)Math.ceil(((double)right - (double)left)/3);
		
		int al = left, ar = al + size - 1;
		int bl = ar + 1, br = bl + size - 1;
		int cl = br + 1, cr = right;
		
		System.out.println(bl);
		String A = sc.next();
		
		if(A.equals("E")) {
			finalR = "E";
			return;
		}
		
		String C = "";
		String B = "";
		
		if(A.equals("G")) {
			System.out.println(ar);
			B = sc.next();	
		}
		
		if(B.equals("E")) {
			finalR = "E";
			return;
		}
		
		System.out.println(br);
		C = sc.next();
		
		if(C.equals("E")) {
			finalR = "E";
			return;
		}
		
		if(A.equals("L") && C.equals("L")) {
			solve(N, al, ar, sc);
			if(!finalR.equals("E")) {
				solve(N, bl, br, sc);
			}
		} else if(A.equals("L") && C.equals("G")) {
			solve(N, al, ar, sc);
			if(!finalR.equals("E")) {
				solve(N, cl, cr, sc);
			}
		} else {
			if(B.equals("G") && C.equals("G")) {
				solve(N, bl, br, sc);
				if(!finalR.equals("E")) {
					solve(N, cl, cr, sc);
				}
			} else if(B.equals("L") && C.equals("L")) {
				solve(N, al, ar, sc);
				if(!finalR.equals("E")) {
					solve(N, bl, br, sc);
				}
			} else if(B.equals("G") && C.equals("L")) {
				solve(N, bl, br, sc);
				if(!finalR.equals("E")) {
					solve(N, cl, cr, sc);
				}
			} else {
				solve(N, al, ar, sc);
				if(!finalR.equals("E")) {
					solve(N, cl, cr, sc);
				}
			}
		}
		
	}

}