package july.long2020;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class CRDGAME {
	public static void main(String[] args) throws IOException {
		Reader2 sc = new Reader2();
		int t = sc.nextInt();
		StringBuilder str = new StringBuilder();
		while(t != 0) {
			int N = sc.nextInt();
			int[] A = new int[N];
			int[] B = new int[N];
			
			for(int i = 0;i < N; i++) {
				A[i] = sc.nextInt();
				B[i] = sc.nextInt();
			}
			
			
			str.append(solve(N, A, B) + "\n");
			t--;
		}
		System.out.println(str.toString());
	}
	
	
	public static String solve(int N, int[] A, int[] B) {
		String output = "";
		
		long aCount = 0, bCount = 0;
		
		for(int i = 0; i < N; i++) {
			if(A[i] == B[i]) {
				aCount++;
				bCount++;
			} else {
				long a = getSumOfDigits(A[i]);
				long b = getSumOfDigits(B[i]);
				if(a > b) {
					aCount++;
				} else if (b > a){
					bCount++;
				} else {
					aCount++;
					bCount++;
				}
			}
		}
		
		if(aCount == bCount) {
			output = "2 " + aCount; 
		} else if(aCount > bCount) {
			output = "0 " + aCount;
		} else {
			output = "1 " + bCount;
		}
		
		return output;
	}
	
	
	public static long getSumOfDigits(int N) {
		if(N < 10) {
			return N;
		}
		return (N % 10) + getSumOfDigits(N/10);
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
