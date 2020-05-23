package april.long2020;

import java.util.Scanner;

public class UNITGCD {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		while(t != 0) {
			int N = sc.nextInt();
			t--;
			solve(N);
		}
	}
	
	public static void solve(int N) {
		StringBuilder str = new StringBuilder();
		if(N == 1) {
			str.append(1 + "\n" + 1 + " " + 1 + "\n");
		} else if(N % 2 == 0) {
			str.append(N/2 + "\n");
			for(int i = 1;i <= N; i = i + 2) {
				str.append(2 + " " + i + " " + (i + 1) + "\n");
			}
		} else {
			str.append(N/2 + "\n");
			str.append(3 + " " + 1 + " " + 2 + " " + N + "\n");
			for(int i = 3;i < N; i = i + 2) {
				str.append(2 + " " + i + " " + (i + 1) + "\n");
			}
		}
		System.out.println(str);
	}

}
