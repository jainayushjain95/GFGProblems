package auglong;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class CHEFWED {
	static Reader1 sc = new Reader1();

	public static void main(String[] args) throws IOException  {
		System.out.println("s");
		int t = sc.nextInt();
		StringBuilder str = new StringBuilder();
		while(t != 0) {
			t--;
			str.append(solve() + "\n");
		}
		System.out.println(str.toString());
	}

	public static long solveOptimized(long currentCost, int i, int N, int K, int[] F, int j, int[] seenWindow, long[][] dpArray) {
		if(i > N - 1 || j > N - 1) {
			return currentCost;
		}
		if(seenWindow[F[j]] == 0) {
			seenWindow[F[j]] = 1;
			dpArray[i][j] = currentCost;
			return solveOptimized(currentCost, i, N, K, F, j + 1, seenWindow, dpArray);
		} else {
			if(dpArray[i][j] != 0) {
				return dpArray[i][j];
			}
			long includeCost = currentCost;
			if(seenWindow[F[j]] > 1) {
				includeCost++;
			} else {
				includeCost += 2;
			}

			long excludeCost = currentCost + K;
			
			if(excludeCost < includeCost) {
				long cc = currentCost + solveOptimized(K, j, N, K, F, j, new int[seenWindow.length], dpArray);
				dpArray[i][j] = cc;
				return cc;
			} else {
				seenWindow[F[j]]++; 
				long a = solveOptimized(includeCost, i, N, K, F, j + 1, seenWindow, dpArray);
				long b = currentCost + solveOptimized(K, j, N, K, F, j, new int[seenWindow.length], dpArray);
				dpArray[i][j] = Math.min(a, b);
				return Math.min(a, b);
			}

		}
	}
	
	public static long solve() throws IOException {
		int N = sc.nextInt();
		int K = sc.nextInt();
		int[] F = new int[N];
		int max = -1;
		for(int i = 0;i < N; i++) {
			F[i] = sc.nextInt();
			max = Math.max(max, F[i]);
		}
		long[][] dpArray = new long[N][N];
		int[] A = new int[max + 1];
		return solveOptimized(K, 0, N, K, F, 0, A, dpArray);
	}


	
	public static long getOverlappingCost(int[] a, int[] b) {
		long cost = 0;
		for(int i = 0; i < a.length; i++) {
			if((a[i] + b[i]) > 1) {
				cost += a[i] + b[i];
			}
		}
		
		return cost;
	}

	//	public static long solve() {
	//		HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
	//		
	//		long minEfficiency = 0;
	//		int N = sc.nextInt();
	//		int K = sc.nextInt();
	//		
	//		int[] F = new int[N];
	//		for(int i = 0;i < N; i++) {
	//			F[i] = sc.nextInt();
	//		}
	//		
	//		for(int i = 0;i < N;) {
	//			int j = i;
	//			long currentCost = K;
	//			HashMap<Integer, Integer> seenWindow = new HashMap<Integer, Integer>();
	//			while(j < N) {
	//				if(!seenWindow.containsKey(F[j])) {
	//					seenWindow.put(F[j], 1);
	//				} else {
	//					long includeCost = currentCost;
	//					if(seenWindow.get(F[j]) > 1) {
	//						includeCost++;
	//					} else {
	//						includeCost += 2;
	//					}
	//					long excludeCost = currentCost + K;
	//					
	//					if(includeCost >= excludeCost) {
	////						currentCost = excludeCost;
	//						break;
	//					} else {
	//						currentCost = includeCost;
	//						seenWindow.put(F[j], 1 + seenWindow.get(F[j]));
	//					}
	//				}
	//				j++;
	//			}
	//			minEfficiency += currentCost;
	//			i = j;
	//		}
	//		
	//		return minEfficiency;
	//	}
}


















class Reader1 { 
	final private int BUFFER_SIZE = 1 << 16; 
	private DataInputStream din; 
	private byte[] buffer; 
	private int bufferPointer, bytesRead; 

	public Reader1() 
	{ 
		din = new DataInputStream(System.in); 
		buffer = new byte[BUFFER_SIZE]; 
		bufferPointer = bytesRead = 0; 
	} 

	public Reader1(String file_name) throws IOException 
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