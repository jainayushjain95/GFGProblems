package cc29aug;

import java.io.*;
import java.util.*;

public class Probs {

	
	static Reader1 sc = new Reader1();
	
	public static void main(String[] args) throws IOException {
		int t = sc.nextInt();
		StringBuilder str = new StringBuilder();
		while(t != 0) {
			t--;
			int N = sc.nextInt();
			long[] A = new long[N];
			long[] B = new long[N - 1];
			for(int i = 0;i < N; i++) {
				A[i] = sc.nextLong();
			}
			for(int i = 0;i < N - 1; i++) {
				B[i] = sc.nextLong();
			}
			str.append(solve(N, A, B));
			str.append("\n");
		}
		System.out.println(str.toString());
	}
	
	public static String solve(int N, long[] A, long[] B) {
		long sol = Long.MAX_VALUE;
		long sumA = 0, sumB = 0;
		for(long x : A) {
			sumA += x;
		}
		for(long x : B) {
			sumB += x;
		}
		
		for(long x : A) {
			long sum = sumB - sumA + x;
			if(sum % (N - 1) == 0) {
				long X = sum / (N - 1);
				if(X > 0) {
					sol = Math.min(sol, X);	
				}
			}
		}
		
		return sol + "";
	}

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
