package may022020;

public class CycleSort {

	public static void main(String[] args) {
		int[] a = {20, 40, 50, 10, 30, 30};
		cycleSort(a);
		for(int x : a) {
			System.out.println(x);
		}
//		System.out.println(noOfSwapsNeeded);
	}
	
	//Sorts and returns swaps count with duplicated elements allowed
	public static void cycleSort(int[] a) {
		int n = a.length;
		for(int cycle = 0; cycle < n - 1; cycle++) {
			int item = a[cycle];
			int pos = cycle + getNoOfElementsLesserThanX(item, a, cycle);
			if(pos == cycle) {
				continue;
			}
			 
			int temp = item;
			item = a[pos];
			a[pos] = temp;
			while(pos != cycle) {
				pos = cycle + getNoOfElementsLesserThanX(item, a, cycle);
				temp = item;
				item = a[pos];
				a[pos] = temp;
			}
			
		}
	}

	/*
	 * helpers
	 */
	
	public static int getNoOfElementsLesserThanX(int x, int[] a, int index) {
		int count = 0;
		for(int m = 1 + index; m < a.length; m++) {
			if(a[m] < x) {
				count++;
			}
		}
		return count;
	} 
	
}
