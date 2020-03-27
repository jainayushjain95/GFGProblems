package matrices;

public class Problem87 {

	public static void main(String[] args) {
		int[][] a = {
				{10, 20, 30, 40} , 
				{15, 25, 35, 45} ,
				{27, 29, 37, 48} ,
				{32, 33, 39, 50}
		};
		search(a, 29);
	}

	public static void search(int[][] a, int x) {
		int m = a.length, n = a[0].length;
		int startIndexRow = 0, startIndexColumn = n - 1;
		while(startIndexColumn >= 0 && startIndexRow < m) {
			if(a[startIndexRow][startIndexColumn] == x) {
				System.out.println(startIndexRow + ", " + startIndexColumn);
				break;
			} else if(a[startIndexRow][startIndexColumn] < x) {
				startIndexRow++;
			} else {
				startIndexColumn--;
			}
		}
	}

}
