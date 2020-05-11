package arrays.leetcode;


public class QPWK1005 {

	public static void main(String[] args) {
		int[] queries = {4, 1, 2, 2};
		int m = 4;
		queries = processQueriesSolved(queries, m);
		for(int x : queries) {
			System.out.println(x);
		}
	}

	public static int[] processQueriesSolved(int[] queries, int m) {
		int[] P = getPermArray(m);
		for(int i = 0;i < queries.length; i++) {
			int index = getIndex(P, queries[i]);
			queries[i] = index;
			updatePermArray(P, index);
		}
		return queries;
	}
	
	public static void updatePermArray(int[] P, int index) {
		int element = P[index];
		for(int i = index; i > 0; i--) {
			P[i] = P[i - 1];
		}
		P[0] = element;
	}
	
	public static int[] getPermArray(int m) {
		int[] P = new int[m];
		for(int i = 0;i < m; i++) {
			P[i] = i + 1;
		}
		return P;
	}
	
	public static int getIndex(int[] P, int element) {
		int index = -1;
		for(int i = 0;i < P.length; i++) {
			if(P[i] == element) {
				index = i;
				break;
			}
		}
		return index;
	}

}
