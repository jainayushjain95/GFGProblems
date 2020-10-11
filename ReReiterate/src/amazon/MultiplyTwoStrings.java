package amazon;

public class MultiplyTwoStrings {

	public static void main(String[] args) {
		System.out.println(multiply("120", "13"));
	}

	public static String multiply(String a, String b) {
		String solution = "";

		boolean positiveResult = true;
		if(a.contains("-")) {
			a = a.substring(1);
			positiveResult = !positiveResult;
		}

		if(b.contains("-")) {
			b = b.substring(1);
			positiveResult = !positiveResult;
		}

		int n = a.length(), m = b.length(), index = m + n;
		int[] sol = new int[n + m];

		for(int i = m - 1;i >= 0; i--) {
			int currIndex = index - 1;
			int carry = 0;
			int currB = (int)b.charAt(i) - 48;
			for(int j = n - 1;j >= 0; j--) {
				int currA = (int)a.charAt(j) - 48;
				int prod = sol[currIndex] + carry + currA * currB;
				sol[currIndex] = prod % 10;

				currIndex--;
				carry = prod / 10;
			}
			sol[currIndex] += carry;
			index--;
		}


		int i = 0;
		while(i < m + n && sol[i] == 0) {
			i++;
		}

		StringBuilder sb = new StringBuilder();

		if(i < m + n) {
			for(;i < m + n; i++) {
				sb.append(sol[i]);
			}
			solution = sb.toString();
			if(!positiveResult) {
				solution = "-" + solution;
			}	
		} else {
			solution = "0";
		}

		return solution;
	}
}
