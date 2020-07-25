package july.long2020;

import java.io.*;
import java.util.*;

public class PTMSSNG {

	static class Point {
		int x;
		int y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static class Pair {
		Point i;
		Point j;
		public Pair(Point i, Point j) {
			this.i = i;
			this.j = j;
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		Reader4 sc = new Reader4();
		
		int t = sc.nextInt();
		StringBuilder str = new StringBuilder();
		while(t != 0) {
			t--;
			int N = sc.nextInt();
			int[] X = new int[4 * N];
			int[] Y = new int[4 * N];
			HashMap<Integer, Integer> mx = new HashMap<Integer, Integer>();
			HashMap<Integer, Integer> my = new HashMap<Integer, Integer>();
			for(int i = 0;i < 4*N - 1; i++) {
				X[i] = sc.nextInt();
				Y[i] = sc.nextInt();
				if(mx.containsKey(X[i])) {
					mx.put(X[i], 1 + mx.get(X[i]));
				} else {
					mx.put(X[i], 1);
				}
				if(my.containsKey(Y[i])) {
					my.put(Y[i], 1 + my.get(Y[i]));
				} else {
					my.put(Y[i], 1);
				}
			}
			str.append(solve(N, X, Y, mx, my) + "\n");
		}
		System.out.println(str.toString());
	}
	
	
	public static String solve(int N, int[] X, int[] Y, HashMap<Integer, Integer> mx, HashMap<Integer, Integer> my) {
		int missingX = -1, missingY = -1;
		
		for(Integer x : mx.keySet()) {
			if(mx.get(x) % 2 != 0) {
				missingX = x;
				break;
			}
		}
		
		for(Integer y : my.keySet()) {
			if(my.get(y) % 2 != 0) {
				missingY = y;
				break;
			}
		}
		
		return missingX + " " + missingY;
		
	}
	
}


class Reader4 { 
	final private int BUFFER_SIZE = 1 << 16; 
	private DataInputStream din; 
	private byte[] buffer; 
	private int bufferPointer, bytesRead; 

	public Reader4() 
	{ 
		din = new DataInputStream(System.in); 
		buffer = new byte[BUFFER_SIZE]; 
		bufferPointer = bytesRead = 0; 
	} 

	public Reader4(String file_name) throws IOException 
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
