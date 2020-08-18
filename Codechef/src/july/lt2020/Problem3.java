package july.lt2020;

import java.io.*;
import java.util.*;

public class Problem3 {

	static Reader3 sc = new Reader3();

	static HashMap<Integer, Long> hm = new HashMap<Integer, Long>();

	public static void main(String[] args)  throws IOException {
		StringBuilder str = new StringBuilder();
		int t = sc.nextInt();
		while(t != 0) {
			t--;
			int N = sc.nextInt();
			long[] A = new long[N];
			long max = -1;
			for(int i = 0;i < N; i++) {
				A[i] = sc.nextLong();
				max = Math.max(max, A[i]);
			}
			str.append(solve(N, A, max) + "\n");
		}
				System.out.println(str.toString());
	}

	public static long solve(int N, long[] A, long max) {
		long solution = 0;
		if(N > 100) {
			for(int i = 0;i < N; i++) {
				solution = Math.max(solution, Math.abs(binaryConcatenation(max, A[i])));
			}	
		} else {
			solution = solve2(N, A);
		}

		return solution;
	}

	public static long solve2(int N, long[] A) {
		long solution = 0;
		for(int i = 0;i < N; i++) {
			for(int j = 0;j < N; j++) {
				if(i != j) {
					long currSOlution = binaryConcatenation(A[i], A[j]);
					System.out.println(A[i] + ", " + A[j] + " : " + currSOlution);
					solution = Math.max(solution, currSOlution);
				}
			}
		}
		return solution;
	}


	public static boolean ifPowerOf2(long n) {
		if(n == 0)
			return false;
		return ((n & (n-1)) == 0);
	}

	public static long binaryConcatenation(long X, long Y) {
		int bitsCountX = noOfBitsReqd(X);
		int bitsCountY = noOfBitsReqd(Y);
		int[] XBits = new int[bitsCountX];
		int[] YBits = new int[bitsCountY];
		getBinaryRepresentation(XBits, X);
		getBinaryRepresentation(YBits, Y);

		long XplusY = 0, YplusX = 0;

		for(int i = bitsCountY - 1;i >= 0; i--) {
			if(YBits[i] == 1) {
				XplusY += getPow(bitsCountY - i - 1);
			}
		}

		for(int i = bitsCountX - 1;i >= 0; i--) {
			if(XBits[i] == 1) {
				XplusY += getPow(bitsCountY + bitsCountX - i - 1);
			}
		}

		for(int i = bitsCountX - 1;i >= 0; i--) {
			if(XBits[i] == 1) {
				YplusX += getPow(bitsCountX - i - 1);
			}
		}

		for(int i = bitsCountY - 1;i >= 0; i--) {
			if(YBits[i] == 1) {
				YplusX += getPow(bitsCountX + bitsCountY - i - 1);	
			}
		} 

		return XplusY - YplusX;
	}

	public static long fastExponentiation(long exponent) {

		if(exponent == 0) {
			return 1;
		}
		if(exponent == 1) {
			return 2;
		}
		if(exponent % 2 == 0) {
			long secondHalfResult = fastExponentiation(exponent/2);
			long ans = secondHalfResult * secondHalfResult;
			return ans;
		} else {
			long secondHalfResult = fastExponentiation(exponent/2);
			long ans = secondHalfResult * secondHalfResult;
			ans = ans * 2;
			return ans;
		}
	}

	public static long getPow(int number) {
		long a = 0;
		if(!hm.containsKey(number)) {
			a = fastExponentiation(number);
			hm.put(number, a);
		} else {
			a = hm.get(number);
		}
		return a;
	}

	public static int noOfBitsReqd(long n) {
		return 1 + (int)Math.floor(Math.log(n)/Math.log(2));
	}

	public static void getBinaryRepresentation(int[] array, long n) {
		int i = array.length - 1;
		while(n != 0) {
			if(isLastBitSet(n)) {
				array[i] = 1;
			}
			n = n >> 1;
			i--;
		}
	}

	public static boolean isLastBitSet(long n) {
		return (n & 1) != 0;
	}

}



class Reader3 { 
	final private int BUFFER_SIZE = 1 << 16; 
	private DataInputStream din; 
	private byte[] buffer; 
	private int bufferPointer, bytesRead; 

	public Reader3() 
	{ 
		din = new DataInputStream(System.in); 
		buffer = new byte[BUFFER_SIZE]; 
		bufferPointer = bytesRead = 0; 
	} 

	public Reader3(String file_name) throws IOException 
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
