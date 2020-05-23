package segment.trees.problems;

/*
 * Segment Tree Starts at index 1
 */
public class BasicSTs {

	private static int[] segmentTree;
	
	public static void main(String[] args) {
		int n = 5;
		int[] a = {1, 3, -2, 8, -7};
		segmentTree = new int[4 * n];
		build(a, 1, 0, n - 1);
		updateQuery(1, 0, n - 1, 3, 0);
		System.out.println(sumQuery(1, 0, n - 1, 1, 3));
	}

	public static void build(int[] a, int segmentTreeVertex, int leftBoundaryOfCurrentSegment, int rightBoundaryOfCurrentSegment) {
		if(leftBoundaryOfCurrentSegment == rightBoundaryOfCurrentSegment) {
			segmentTree[segmentTreeVertex] = a[leftBoundaryOfCurrentSegment];
		} else {
			int midIndex = (leftBoundaryOfCurrentSegment + rightBoundaryOfCurrentSegment) / 2;
			build(a, 2 * segmentTreeVertex, leftBoundaryOfCurrentSegment, midIndex);
			build(a, 1 + 2 * segmentTreeVertex, 1 + midIndex, rightBoundaryOfCurrentSegment);
			segmentTree[segmentTreeVertex] = segmentTree[2 * segmentTreeVertex] + segmentTree[1 + 2 * segmentTreeVertex];
		}
	}
	
	public static int sumQuery(int segmentTreeVertex, int leftBoundaryOfCurrentSegment, int rightBoundaryOfCurrentSegment, int left, int right) {
		if(leftBoundaryOfCurrentSegment > right || rightBoundaryOfCurrentSegment < left) {
			return 0;
		}
		if(leftBoundaryOfCurrentSegment >= left && rightBoundaryOfCurrentSegment <= right) {
			return segmentTree[segmentTreeVertex];
		}
		int midIndex = (leftBoundaryOfCurrentSegment + rightBoundaryOfCurrentSegment) / 2;
		return sumQuery(2 * segmentTreeVertex, leftBoundaryOfCurrentSegment, midIndex, left, right) + sumQuery(1 + 2 * segmentTreeVertex, midIndex + 1, rightBoundaryOfCurrentSegment, left, right);
	}
	
	public static void updateQuery(int segmentTreeVertex, int leftBoundaryOfCurrentSegment, int rightBoundaryOfCurrentSegment, int index, int newValue) {
		if(leftBoundaryOfCurrentSegment == rightBoundaryOfCurrentSegment) {
			segmentTree[segmentTreeVertex] = newValue;
			return;
		}
		int midIndex = (leftBoundaryOfCurrentSegment + rightBoundaryOfCurrentSegment) / 2;
		if(midIndex < index) {
			updateQuery(1 + 2 * segmentTreeVertex, midIndex + 1, rightBoundaryOfCurrentSegment, index, newValue);
		} else {
			updateQuery(2 * segmentTreeVertex, leftBoundaryOfCurrentSegment, midIndex, index, newValue);
		}
		segmentTree[segmentTreeVertex] = segmentTree[2 * segmentTreeVertex] + segmentTree[1 + 2 * segmentTreeVertex];
		
	}
	
}
