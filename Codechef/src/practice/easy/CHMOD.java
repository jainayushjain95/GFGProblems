package practice.easy;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class CHMOD {

	public static int[] pnos = {
			2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97
	};

	public static void main(String[] args) throws IOException {
		ReaderCHMOD sc = new ReaderCHMOD();
		int N = sc.nextInt();

		long[] A = new long[N];
		for(int i = 0;i < N; i++) {
			A[i] = sc.nextLong();
		}
		int[][] map = new int[25][N];
		int t = sc.nextInt();
		int[] L = new int[t];
		int[] R = new int[t];
		int[] M = new int[t];

		L[0] = sc.nextInt() - 1;
		R[0] = sc.nextInt() - 1;
		M[0] = sc.nextInt();
		
		for(int i = 1;i < t; i++) {
			L[i] = sc.nextInt() - 1;
			R[i] = sc.nextInt() - 1;
			M[i] = sc.nextInt();
		}
		solve(N, A, t, L, R, M);
	}
	
	public static boolean isPrime(long number) {
		boolean isPrime = true;
		for(int i = 2; i <= Math.sqrt(number); i++) {
			if(number % i == 0) {
				isPrime = false;
				break;
			}
		}
		return isPrime;
	}

	public static void solve(int N, long[] A, int t, int[] L, int[] R, int[] M) {
		StringBuilder s = new StringBuilder();
		for(int i = 0;i < t; i++) {
			long output = 1;
			int left = L[i];
			int right = R[i];
			int mod = M[i];
			for(int j = left; j <= right; j++) {
				output = mul(A[j], output, mod);
			}
			s.append(output + "\n");
		}
		System.out.println(s.toString());
	}

	public static long mul(long a, long b, int mod) {
		long output = a * b;
		output = output % mod;
		return output;
	}

	public static long fastExponentiation(long mod, long number, long exponent) {
		if(number == 0) {
			return 0;
		}
		if(exponent == 0) {
			return 1;
		}
		if(exponent == 1) {
			return number;
		}
		if(exponent % 2 == 0) {
			long secondHalfResult = fastExponentiation(mod, number, exponent/2);
			long ans = secondHalfResult * secondHalfResult;
			if(ans >= mod) {
				ans = ans % mod;
			}
			return ans;
		} else {
			long secondHalfResult = fastExponentiation(mod, number, exponent/2);
			long ans = secondHalfResult * secondHalfResult;
			if(ans >= mod) {
				ans = ans % mod;
			}
			ans = ans * number;
			if(ans >= mod) {
				ans = ans % mod;
			}
			return ans;
		}
	}

}

class ReaderCHMOD { 
	final private int BUFFER_SIZE = 1 << 16; 
	private DataInputStream din; 
	private byte[] buffer; 
	private int bufferPointer, bytesRead; 

	public ReaderCHMOD() 
	{ 
		din = new DataInputStream(System.in); 
		buffer = new byte[BUFFER_SIZE]; 
		bufferPointer = bytesRead = 0; 
	} 

	public ReaderCHMOD(String file_name) throws IOException 
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
