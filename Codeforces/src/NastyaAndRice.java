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
		int minGrainWeight = a - b;
		int maxGrainWeight = a + b;
		int minSum = c - d;
		int maxSum = c + d;
		
		boolean flag = true;
		if(n*minGrainWeight > maxSum || n*maxGrainWeight < minSum) {
			flag = false;
		}
		
		if(flag) {
			System.out.println("Yes");
		} else {
			System.out.println("No");
		}
		
	}

}
