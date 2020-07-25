
package june.lt2020;

import java.io.*;


public class Problem5 {
	
	private static long[] segmentTree;
	
	public static void main(String[] args) throws IOException {
		Reader sc = new Reader();
		int t = sc.nextInt();
		while(t != 0) {
			t--;
			int N = sc.nextInt();
			long X = sc.nextLong();
			int oneSizedCount = 0;
			long[] A = new long[N];
			for(int i = 0; i < N; i++) {
				A[i] = sc.nextInt();
				if(X == A[i]) {
					oneSizedCount++;
				}
			}
			solve(N, X, A, oneSizedCount);
		}
	}
	
	
	public static void solve(int N, long X, long[] A, int count) {
//		segmentTree = new long[4 * N];
//		build(A, 1, 0, N - 1);
//		for(int size = 2;size <= N; size++) {
//			long noOfBoxes = size * size;
//			for(int i = 0; i < N - size + 1; i++) {
//				for(int j = 0; j < N - size + 1; j++) {
//					long totalFreq = 2 * noOfBoxes;
//					long factor = totalFreq / size;
//					long currSum = factor * sumQuery(1, 0, N - 1, i, i + size - 1);
//					if(currSum == X) {
//						count++;
//					}
//				}	
//			}
//		}
//		
//		System.out.println(count)
		int[][] M = new int[N][N]; 
		for(int i = 0;i < N; i++) {
			for(int j = 0;j < N; j++) {
				M[i][j] = (int) (A[i] + A[j]);
			}
		}
		for(int i = 0;i < N; i++) {
			for(int j = 0;j < N; j++) {
				System.out.print(M[i][j] + " ");
			}
			System.out.println();
		}
		
	}
	
	
	public static void build(long[] a, int segmentTreeVertex, int leftBoundaryOfCurrentSegment, int rightBoundaryOfCurrentSegment) {
		if(leftBoundaryOfCurrentSegment == rightBoundaryOfCurrentSegment) {
			segmentTree[segmentTreeVertex] = a[leftBoundaryOfCurrentSegment];
		} else {
			int midIndex = (leftBoundaryOfCurrentSegment + rightBoundaryOfCurrentSegment) / 2;
			build(a, 2 * segmentTreeVertex, leftBoundaryOfCurrentSegment, midIndex);
			build(a, 1 + 2 * segmentTreeVertex, 1 + midIndex, rightBoundaryOfCurrentSegment);
			segmentTree[segmentTreeVertex] = segmentTree[2 * segmentTreeVertex] + segmentTree[1 + 2 * segmentTreeVertex];
		}
	}
	
	public static long sumQuery(int segmentTreeVertex, int leftBoundaryOfCurrentSegment, int rightBoundaryOfCurrentSegment, int left, int right) {
		if(leftBoundaryOfCurrentSegment > right || rightBoundaryOfCurrentSegment < left) {
			return 0;
		}
		if(leftBoundaryOfCurrentSegment >= left && rightBoundaryOfCurrentSegment <= right) {
			return segmentTree[segmentTreeVertex];
		}
		int midIndex = (leftBoundaryOfCurrentSegment + rightBoundaryOfCurrentSegment) / 2;
		return sumQuery(2 * segmentTreeVertex, leftBoundaryOfCurrentSegment, midIndex, left, right) + sumQuery(1 + 2 * segmentTreeVertex, midIndex + 1, rightBoundaryOfCurrentSegment, left, right);
	}
	
	
}


class Reader { 
	final private int BUFFER_SIZE = 1 << 16; 
	private DataInputStream din; 
	private byte[] buffer; 
	private int bufferPointer, bytesRead; 
	
	public Reader() 
	{ 
		din = new DataInputStream(System.in); 
		buffer = new byte[BUFFER_SIZE]; 
		bufferPointer = bytesRead = 0; 
	} 
	
	public Reader(String file_name) throws IOException 
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