package segment.trees;

public class SegmentTree {

	public static void main(String[] args) {
		int n = 6;
		int[] a = {10, 20, 30, 40, 50, 60};
		int[] segmentTree = new int[4 * n];
		buildSegmentTreeForRangeSumQueries(a, 0, n - 1, segmentTree, 0);
		System.out.println(getSum(segmentTree, 2, 4, 0, 0, 5));

	}
	
	public static void buildSegmentTreeForRangeSumQueries(int[] a, int beginIndex, int endIndex, int[] segmentTree, int treeIndex) {
		if(beginIndex > endIndex) {
			return;
		}
		
		if(beginIndex == endIndex) {
			segmentTree[treeIndex] = a[beginIndex];
			return;
		}
		int midIndex = (endIndex + beginIndex) / 2;
		buildSegmentTreeForRangeSumQueries(a, beginIndex, midIndex, segmentTree, 2 * treeIndex + 1);
		buildSegmentTreeForRangeSumQueries(a, midIndex + 1, endIndex, segmentTree, 2 * treeIndex + 2);
		segmentTree[treeIndex] = segmentTree[2 * treeIndex + 1] + segmentTree[2 * treeIndex + 2];
		
	}
	
	public static void update(int[] segmentTree, int index, int treeIndex, int left, int right, int newValue) {
		if(left == right) {
			segmentTree[treeIndex] = segmentTree[treeIndex] + (newValue);
			return;
		}
		int mid = (left + right) / 2;
		if(index > mid) {
			update(segmentTree, index, 2 * treeIndex + 2, mid + 1, right, newValue);
		} else {
			update(segmentTree, index, 2 * treeIndex + 1, left, mid, newValue);
		}
		segmentTree[treeIndex] = segmentTree[2 * treeIndex + 1] + segmentTree[2 * treeIndex + 2];
	}
	
	public static int getSum(int[] segmentTree, int rangeLeft, int rangeRight, int treeIndex, int left, int right) {
		if(rangeLeft > right || rangeRight < left) {
			return 0;
		}
		if(left >= rangeLeft && right <= rangeRight) {
			return segmentTree[treeIndex];
		}
		int midIndex = (left + right) / 2;
		return getSum(segmentTree, rangeLeft, rangeRight, 2 * treeIndex + 1, left, midIndex) + getSum(segmentTree, rangeLeft, rangeRight, 2 * treeIndex + 2, midIndex + 1, right);
	}
}
