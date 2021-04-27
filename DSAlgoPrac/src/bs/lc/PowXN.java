package bs.lc;

public class PowXN {

	public static void main(String[] args) {
		double x = 2.00000;
		int n = -3;
		System.out.println(myPowSolve(x, n));
	}

	public static double myPowSolve(double x, int n) {
		if(x == 0) {
			return 0;
		}
		
		if(n == 0) {
			return 1;
		}
		
		if(n == -1) {
			return 1/x;
		}
		
		if(x == 1) {
			return x;
		}
		
		if(n % 2 == 0) {
			double left = myPowSolve(x, n / 2);
			return left * left;
		} else {
			double left = myPowSolve(x, n / 2);
			return left * left * ((n < 0) ? 1/x : x);
		}
    }
}
