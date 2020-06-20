package june.long2020;

import java.util.Scanner;

public class Problem5 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		StringBuilder str = new StringBuilder();
		while(t != 0) {
			str.append(solve(sc.nextInt()) + "\n");
			t--;
		}
		System.out.println(str.toString());
	}

	public static String solve(int N) {
		StringBuilder str = new StringBuilder();
		if(N == 1) {
			str.append("1");
		} else {
			int even = 2, odd = 1;

			for(int i = 0;i < N; i++) {
				if(i % 2 == 0) {
					for(int j = 0;j < N; j++) {
						if(j % 2 == 0) {
							str.append(odd + " ");
							odd = odd + 2;
						} else {
							str.append(even + " ");
							even = even + 2;
						}
					}
				} else {
					for(int j = 0;j < N; j++) {
						if(j % 2 != 0) {
							str.append(odd + " ");
							odd = odd + 2;
						} else {
							str.append(even + " ");
							even = even + 2;
						}
					}
				}
				str.append("\n");
			}
		}


		return str.toString();
	}
}
