package lc.bs;

public class RandomPickWithWeight {

	int[] prefixSums;
	int totalSum;

	public static void main(String[] args) {
		int[] w = {3, 14, 1, 7};
		RandomPickWithWeight obj = new RandomPickWithWeight(w);
		int a = 0, b = 0, c = 0, d = 0;
		int n = 10000;
		for(int i = 0;i <= n; i++) {
			int index = obj.pickIndex();
			if(index == 0) {
				a++;
			} else if(index == 1) {
				b++;
			} else if(index == 2) {
				c++;
			} else if(index == 3) {
				d++;
			}  
		}
		System.out.println((double)a/n*100);
		System.out.println((double)b/n*100);
		System.out.println((double)c/n*100);
		System.out.println((double)d/n*100);
	}

	public RandomPickWithWeight(int[] w) {
		prefixSums = new int[w.length];
		prefixSums[0] = w[0];
		for(int i = 1; i < w.length; i++) {
			prefixSums[i] = prefixSums[i - 1] + w[i];
		}
		totalSum = prefixSums[w.length - 1];
	}

	public int pickIndex() {
		int index = -1;
		int random = (int)(1 + Math.random()*totalSum);
		index = search(0, prefixSums.length - 1, random);
		return index;
	}

	public int brute(int random) {
		int index = -1;
		for(int i = 0;i < prefixSums.length; i++) {
			if(prefixSums[i] > random) {
				index = i;
				break;
			}
		}
		return index;
	}

	public int search(int beginIndex, int endIndex, int search) {
		if(beginIndex <= endIndex) {
			int midIndex = getMidIndex(beginIndex, endIndex);
			if(prefixSums[midIndex] == search) {
				return midIndex;
			} else if(prefixSums[midIndex] > search) {
				if(midIndex == 0 || search > prefixSums[midIndex - 1]) {
					return midIndex;
				} else {
					return search(beginIndex, midIndex - 1, search);	
				}
			} else {
				return search(midIndex + 1, endIndex, search);
			}
		}
		return -1;
	}

	public static int getMidIndex(int beginIndex, int endIndex) {
		return (endIndex - beginIndex)/2 + beginIndex;
	}
}
