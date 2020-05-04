package recursion.geeksforgeeks;

public class JosephusProblem {

	public static void main(String[] args) {
		System.out.println(jp(5, 3));
	}
	
	public static int jp(int n, int k) {
		if(n == 1) {
			return 0;
		}
		return (jp(n - 1, k) + k) % n;
	}

}
