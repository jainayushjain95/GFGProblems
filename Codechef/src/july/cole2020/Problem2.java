package july.cole2020;

import java.io.*;
import java.util.*;

public class Problem2 {
	static Reader2 sc = new Reader2();
	public static void main(String[] args)  throws IOException {
		StringBuilder str = new StringBuilder();
		int t = sc.nextInt();
		while(t != 0) {
			t--;
			int N = sc.nextInt();
			long[] a = new long[N];
			
			HashSet<Long> hs = new HashSet<Long>();
			for(int i = 0;i < N; i++) {
				a[i] = sc.nextLong();
				hs.add(a[i]);
 			}
			int Q = sc.nextInt();
			
			long[] x = new long[Q];
			long[] y = new long[Q];
			for(int i = 0;i < Q; i++) {
				x[i] = sc.nextLong();
				y[i] = sc.nextLong();
			}
			
			
			
			str.append(solve(Q, N, a, x, y, hs) + "\n");
		}
		System.out.println(str.toString());
	}
	
	
	public static String solve(int Q, int N, long[] a, long[] x, long[] y, HashSet<Long> hs) {
		StringBuilder output = new StringBuilder();
		int i = 0;
		while(Q != 0) {
			long xc = x[i];
			long yc = y[i];
			if(xc == 0 && yc == 0) {
				output.append("0\n");
			} else if(hs.contains(xc + yc)) {
				output.append("-1\n");
			} else {
				int index = getIndex(x[i], y[i], N, 0, N - 1, a);
				output.append(index + "\n"); 
			}
			Q--;
			i++;
		}
		return output.toString();
	}
	
	public static long getCP(long a, long x, long y) {
		long p = a * (a - x - y);
		return p;
	}
	
	public static int getIndex(long x, long y, int N, int beginIndex, int endIndex, long[] a) {
		if(beginIndex > endIndex) {
			return a.length;
		}
		int mid = (endIndex - beginIndex)/2 + beginIndex;
		long cp = getCP(a[mid], x, y);
		if(cp > 0) {
			if(mid == 0) {
				return 0;
			} else if(getCP(a[mid - 1], x, y) < 0) {
				return mid;
			}
			return getIndex(x, y, N, beginIndex, mid - 1, a);
		} else {
			return getIndex(x, y, N, mid + 1, endIndex, a);
		}
	}
	
}




class Reader2 { 
	final private int BUFFER_SIZE = 1 << 16; 
	private DataInputStream din; 
	private byte[] buffer; 
	private int bufferPointer, bytesRead; 

	public Reader2() 
	{ 
		din = new DataInputStream(System.in); 
		buffer = new byte[BUFFER_SIZE]; 
		bufferPointer = bytesRead = 0; 
	} 

	public Reader2(String file_name) throws IOException 
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