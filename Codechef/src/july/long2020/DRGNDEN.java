package july.long2020;

import java.io.*;
import java.util.*;



public class DRGNDEN {

	static Reader7 sc = new Reader7();

	static long[] prefixSumArrayLR;
	static int[] prevGreaterArrayLR;

	static long[] prefixSumArrayRL;
	static int[] prevGreaterArrayRL;

	static long[] maxSegmentTree;
	
	static long[] segmentTreeLR;
	static long[] segmentTreeRL;

	public static void prepareSegmentTreeLR(long[] h, int N, long[] a) {
		segmentTreeLR = new long[4 * N];
		buildSegmentTreeLR(h, a, 1, 0, N - 1);
	}
	
	public static void buildSegmentTreeLR(long[] h, long[] a, int segmentTreeVertex, int leftBoundaryOfCurrentSegment, int rightBoundaryOfCurrentSegment) {
		if(leftBoundaryOfCurrentSegment == rightBoundaryOfCurrentSegment) {
			segmentTreeLR[segmentTreeVertex] = getQuery(h, a, leftBoundaryOfCurrentSegment, rightBoundaryOfCurrentSegment);
		}
		int midIndex = (leftBoundaryOfCurrentSegment + rightBoundaryOfCurrentSegment) / 2;
		buildSegmentTreeLR(h, a, 2*segmentTreeVertex, leftBoundaryOfCurrentSegment, midIndex);
		buildSegmentTreeLR(h, a, 1 + 2*segmentTreeVertex, midIndex + 1, rightBoundaryOfCurrentSegment);
		segmentTreeLR[segmentTreeVertex] = getQuery(h, a, leftBoundaryOfCurrentSegment, rightBoundaryOfCurrentSegment);
	}
	
	public static long getQueryLR(long[] h, long[] a, int segmentTreeVertex, int leftBoundaryOfCurrentSegment, int rightBoundaryOfCurrentSegment, int left, int right) {
		if(leftBoundaryOfCurrentSegment > right || rightBoundaryOfCurrentSegment < left) {
			return 0;
		}
		if(leftBoundaryOfCurrentSegment >= left && rightBoundaryOfCurrentSegment <= right) {
			return segmentTreeLR[segmentTreeVertex];
		}
		int midIndex = (leftBoundaryOfCurrentSegment + rightBoundaryOfCurrentSegment) / 2;
		long leftOutput = getQueryLR(h, a, 2 * segmentTreeVertex, leftBoundaryOfCurrentSegment, midIndex, left, right);
		long rightOutput = getQueryLR(h, a, 1 + 2 * segmentTreeVertex, midIndex + 1, rightBoundaryOfCurrentSegment, left, right);
		return 1;
	}
	
	public static void prepareSegmentTreeRL(long[] h, int N, long[] a) {
		segmentTreeRL = new long[4 * N];
		buildSegmentTreeRL(h, a, 1, N - 1, 0);
	}
	
	public static void buildSegmentTreeRL(long[] h, long[] a, int segmentTreeVertex, int leftBoundaryOfCurrentSegment, int rightBoundaryOfCurrentSegment) {
		if(leftBoundaryOfCurrentSegment == rightBoundaryOfCurrentSegment) {
			segmentTreeRL[segmentTreeVertex] = getQuery(h, a, leftBoundaryOfCurrentSegment, rightBoundaryOfCurrentSegment);
		}
		int midIndex = (int)Math.ceil((double)(leftBoundaryOfCurrentSegment + rightBoundaryOfCurrentSegment) / 2);
		buildSegmentTreeRL(h, a, 2*segmentTreeVertex, leftBoundaryOfCurrentSegment, midIndex);
		buildSegmentTreeRL(h, a, 1 + 2*segmentTreeVertex, midIndex - 1, rightBoundaryOfCurrentSegment);
		segmentTreeRL[segmentTreeVertex] = getQuery(h, a, leftBoundaryOfCurrentSegment, rightBoundaryOfCurrentSegment);
	}
	
	
	
	
	
	
	
	
	
	public static void prepareMaxSegmentTree(long[] h, int N) {
		maxSegmentTree = new long[4*N];
		buildMaxSegmentTree(h, 1, 0, N - 1);
	}

	public static void buildMaxSegmentTree(long[] a, int segmentTreeVertex, int leftBoundaryOfCurrentSegment, int rightBoundaryOfCurrentSegment) {
		if(leftBoundaryOfCurrentSegment == rightBoundaryOfCurrentSegment) {
			maxSegmentTree[segmentTreeVertex] = a[leftBoundaryOfCurrentSegment];
		} else {
			int midIndex = (leftBoundaryOfCurrentSegment + rightBoundaryOfCurrentSegment) / 2;
			buildMaxSegmentTree(a, 2 * segmentTreeVertex, leftBoundaryOfCurrentSegment, midIndex);
			buildMaxSegmentTree(a, 1 + 2 * segmentTreeVertex, 1 + midIndex, rightBoundaryOfCurrentSegment);
			maxSegmentTree[segmentTreeVertex] = Math.max(maxSegmentTree[2 * segmentTreeVertex], maxSegmentTree[1 + 2 * segmentTreeVertex]);
		}
	}
	
	public static long maxQuery(int segmentTreeVertex, int leftBoundaryOfCurrentSegment, int rightBoundaryOfCurrentSegment, int left, int right) {
		if(leftBoundaryOfCurrentSegment > right || rightBoundaryOfCurrentSegment < left) {
			return 0;
		}
		if(leftBoundaryOfCurrentSegment >= left && rightBoundaryOfCurrentSegment <= right) {
			return maxSegmentTree[segmentTreeVertex];
		}
		int midIndex = (leftBoundaryOfCurrentSegment + rightBoundaryOfCurrentSegment) / 2;
		return Math.max(maxQuery(2 * segmentTreeVertex, leftBoundaryOfCurrentSegment, midIndex, left, right), maxQuery(1 + 2 * segmentTreeVertex, midIndex + 1, rightBoundaryOfCurrentSegment, left, right));
	}


	public static void main(String[] args)  throws IOException {
		StringBuilder str = new StringBuilder();
		int N = sc.nextInt();
		int Q = sc.nextInt();
		long[] h = new long[N];
		long[] a = new long[N];
		for(int i = 0;i < N; i++) {
			h[i] = sc.nextLong();
		}
		for(int i = 0;i < N; i++) {
			a[i] = sc.nextLong();
		}
		str.append(solve(N, Q, h, a) + "\n");
		System.out.println(str.toString());
	}

	public static void preparePrefixSumArrayLR(int N, long[] h, long[] a) {
		preparePrevGreaterArrayLR(N, h);
		prefixSumArrayLR = new long[N];
		prefixSumArrayLR[0] = a[0];
		for(int i = 1;i < N; i++) {
			if(h[i] < h[i - 1]) {
				prefixSumArrayLR[i] = a[i] + prefixSumArrayLR[i - 1];
			} else if(h[i] >= h[i - 1]) {
				int prevGreaterIndex = prevGreaterArrayLR[i];
				if(prevGreaterIndex == -1) {
					prefixSumArrayLR[i] = a[i];	
				} else {
					prefixSumArrayLR[i] = a[i] + prefixSumArrayLR[prevGreaterIndex];
				}
			}
		}
	}

	public static void updatePrefixSumArrayLR(int N, long[] h, long[] a) {
		prefixSumArrayLR = new long[N];
		prefixSumArrayLR[0] = a[0];
		for(int i = 1;i < N; i++) {
			if(h[i] < h[i - 1]) {
				prefixSumArrayLR[i] = a[i] + prefixSumArrayLR[i - 1];
			} else if(h[i] >= h[i - 1]) {
				int prevGreaterIndex = prevGreaterArrayLR[i];
				if(prevGreaterIndex == -1) {
					prefixSumArrayLR[i] = a[i];	
				} else {
					prefixSumArrayLR[i] = a[i] + prefixSumArrayLR[prevGreaterIndex];
				}
			}
		}
	}
	
	
	public static void preparePrefixSumArrayRL(int N, long[] h, long[] a) {
		preparePrevGreaterArrayRL(N, h);
		prefixSumArrayRL = new long[N];
		prefixSumArrayRL[N - 1] = a[N - 1];
		for(int i = N - 2;i >= 0; i--) {
			if(h[i] < h[i + 1]) {
				prefixSumArrayRL[i] = a[i] + prefixSumArrayRL[i + 1];
			} else if(h[i] >= h[i + 1]) {
				int prevGreaterIndex = prevGreaterArrayRL[i];
				if(prevGreaterIndex == -1) {
					prefixSumArrayRL[i] = a[i];	
				} else {
					prefixSumArrayRL[i] = a[i] + prefixSumArrayRL[prevGreaterIndex];
				}
			}
		}
	}
	
	public static void updatePrefixSumArrayRL(int N, long[] h, long[] a) {
		prefixSumArrayRL = new long[N];
		prefixSumArrayRL[N - 1] = a[N - 1];
		for(int i = N - 2;i >= 0; i--) {
			if(h[i] < h[i + 1]) {
				prefixSumArrayRL[i] = a[i] + prefixSumArrayRL[i + 1];
			} else if(h[i] >= h[i + 1]) {
				int prevGreaterIndex = prevGreaterArrayRL[i];
				if(prevGreaterIndex == -1) {
					prefixSumArrayRL[i] = a[i];	
				} else {
					prefixSumArrayRL[i] = a[i] + prefixSumArrayRL[prevGreaterIndex];
				}
			}
		}
	}

	public static void preparePrevGreaterArrayLR(int N, long[] h) {
		prevGreaterArrayLR = new int[N];
		prevGreaterArrayLR[0] = -1;
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(0);
		for(int i = 1; i < N; i++) {
			while(!stack.isEmpty() && h[stack.peek()] <= h[i]) {
				stack.pop();
			}
			if(stack.isEmpty()) {
				prevGreaterArrayLR[i] = -1;
			} else {
				prevGreaterArrayLR[i] = stack.peek();
			}
			stack.push(i);
		}
	}

	public static void preparePrevGreaterArrayRL(int N, long[] h) {
		prevGreaterArrayRL = new int[N];
		prevGreaterArrayRL[N - 1] = -1;
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(N - 1);
		for(int i = N - 2; i >= 0; i--) {
			while(!stack.isEmpty() && h[stack.peek()] <= h[i]) {
				stack.pop();
			}
			if(stack.isEmpty()) {
				prevGreaterArrayRL[i] = -1;
			} else {
				prevGreaterArrayRL[i] = stack.peek();
			}
			stack.push(i);
		}
	}


	public static String solve(int N, int Q, long[] h, long[] a) throws IOException {
		preparePrefixSumArrayLR(N, h, a);
		preparePrefixSumArrayRL(N, h, a);
		prepareMaxSegmentTree(h, N);
		StringBuilder str = new StringBuilder();
		while(Q != 0) {
			int queryType = sc.nextInt();
			if(queryType == 1) {
				int index = sc.nextInt() - 1;
				long newValue = sc.nextLong();
				a[index] = newValue;
				updatePrefixSumArrayLR(N, h, a);
				updatePrefixSumArrayRL(N, h, a);
			} else {
				int beginIndex = sc.nextInt() - 1;
				int endIndex = sc.nextInt() - 1;
				long solution = getQuery(h, a, beginIndex, endIndex);
				str.append(solution + "\n");	
			}
			Q--;
		}
		return str.toString();
	}

	public static long getQuery(long[] h, long[] a, int beginIndex, int endIndex) {
		long output = -1;
		if(beginIndex == endIndex) {
			output = a[beginIndex];
		} else if(h[endIndex] >= h[beginIndex]) {
			output = -1;
		} else if(beginIndex + 1 == endIndex) {
			output = a[beginIndex] + a[endIndex];
		} else if(endIndex + 1 == beginIndex) {
			output = a[beginIndex] + a[endIndex];
		} else {
			int startPoint = -1, endPoint = -1;

			startPoint = beginIndex;
			endPoint = endIndex;

			output = 0;

			if(startPoint > endPoint) {
				long maxBetween = maxQuery(1, 0, h.length - 1, endPoint + 1, startPoint - 1);
				if(maxBetween >= h[startPoint]) {
					output = -1;
				} else {
					output = prefixSumArrayRL[endPoint] - prefixSumArrayRL[startPoint] + a[startPoint];
				}
			} else {
				long maxBetween = maxQuery(1, 0, h.length - 1, startPoint + 1, endPoint - 1);
				if(maxBetween >= h[startPoint]) {
					output = -1;
				} else {
					output = prefixSumArrayLR[endPoint] - prefixSumArrayLR[startPoint] + a[startPoint];
				}
			}

		}

		return output;
	}

}













class Reader7 { 
	final private int BUFFER_SIZE = 1 << 16; 
	private DataInputStream din; 
	private byte[] buffer; 
	private int bufferPointer, bytesRead; 

	public Reader7() 
	{ 
		din = new DataInputStream(System.in); 
		buffer = new byte[BUFFER_SIZE]; 
		bufferPointer = bytesRead = 0; 
	} 

	public Reader7(String file_name) throws IOException 
	{ 
		din = new DataInputStream(new FileInputStream(file_name)); 
		buffer = new byte[BUFFER_SIZE]; 
		bufferPointer = bytesRead = 0; 
	} 

	public String readLine() throws IOException 
	{ 
		byte[] buf = new byte[64]; // line length 
		int cnt = 0, c; 
		while ((c = read()) != -1) 
		{ 
			if (c == '\n') 
				break; 
			buf[cnt++] = (byte) c; 
		} 
		return new String(buf, 0, cnt); 
	} 

	public int nextInt() throws IOException 
	{ 
		int ret = 0; 
		byte c = read(); 
		while (c <= ' ') 
			c = read(); 
		boolean neg = (c == '-'); 
		if (neg) 
			c = read(); 
		do
		{ 
			ret = ret * 10 + c - '0'; 
		}  while ((c = read()) >= '0' && c <= '9'); 

		if (neg) 
			return -ret; 
		return ret; 
	} 

	public long nextLong() throws IOException 
	{ 
		long ret = 0; 
		byte c = read(); 
		while (c <= ' ') 
			c = read(); 
		boolean neg = (c == '-'); 
		if (neg) 
			c = read(); 
		do { 
			ret = ret * 10 + c - '0'; 
		} 
		while ((c = read()) >= '0' && c <= '9'); 
		if (neg) 
			return -ret; 
		return ret; 
	} 

	public double nextDouble() throws IOException 
	{ 
		double ret = 0, div = 1; 
		byte c = read(); 
		while (c <= ' ') 
			c = read(); 
		boolean neg = (c == '-'); 
		if (neg) 
			c = read(); 

		do { 
			ret = ret * 10 + c - '0'; 
		} 
		while ((c = read()) >= '0' && c <= '9'); 

		if (c == '.') 
		{ 
			while ((c = read()) >= '0' && c <= '9') 
			{ 
				ret += (c - '0') / (div *= 10); 
			} 
		} 

		if (neg) 
			return -ret; 
		return ret; 
	} 

	private void fillBuffer() throws IOException 
	{ 
		bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE); 
		if (bytesRead == -1) 
			buffer[0] = -1; 
	} 

	private byte read() throws IOException 
	{ 
		if (bufferPointer == bytesRead) 
			fillBuffer(); 
		return buffer[bufferPointer++]; 
	} 

	public void close() throws IOException 
	{ 
		if (din == null) 
			return; 
		din.close(); 
	} 
}