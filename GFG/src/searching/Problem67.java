package searching;

public class Problem67 {

	public static void main(String[] args) {
		int[] a = {10, 20, 15, 5, 23, 90, 67};
		printOneOfThePeakElement(a, 0, a.length - 1);
	}

	public static void printOneOfThePeakElement(int[] a, int beginIndex, int endIndex) {
		if(beginIndex >= endIndex) {
			return;
		}
		int mid = (endIndex - beginIndex)/2 + beginIndex;
		
		if(mid == 0 && a[mid + 1] <= a[mid]) {
			System.out.println(a[mid]);
			return;
		}
		
		if(mid == a.length - 1 && a[mid - 1] <= a[mid]) {
			System.out.println(a[mid]);
			return;
		}
		
		if(a[mid] >= a[mid + 1] && a[mid] >= a[mid - 1]) {
			System.out.println(a[mid]);
			return;
		}
		
		if(a[mid] < a[mid + 1]) {
			printOneOfThePeakElement(a, mid + 1, endIndex);
		} else {
			printOneOfThePeakElement(a, beginIndex, mid - 1);
		}
	}
	
}
