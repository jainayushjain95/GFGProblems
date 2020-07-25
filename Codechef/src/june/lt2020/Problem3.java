package june.lt2020;

import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class Problem3 {}

	/*static class ContriPair {
		int bitIndex;
		long contribution;
		public ContriPair(int bitIndex, long contribution) {
			this.bitIndex = bitIndex;
			this.contribution = contribution; 
		}
	}
	
	public static void main(String[] args) throws IOException {
		Reader sc = new Reader();
		int t = sc.nextInt();
		while(t != 0) {
			t--;
			int N = sc.nextInt();
			int K = sc.nextInt();
			long[] A = new long[N];
			long max = -1;
			long ored = 0;
			for(int i = 0;i < N; i++) {
				A[i] = sc.nextLong();
				max = Math.max(A[i], max);
				ored = ored | A[i];
			}
			solve(N, K, A, max, ored);
		}
	}

	public static void solve(int N, int K, long[] A, long max, long ored) {
		long X = ored;
		
		int noOfSetBits = noOfSetBits(X);
		
		if(noOfSetBits < K) {
			int i = 0;
			while(noOfSetBits != K) {
				if(!isKthBitSet(X, i)) {
					X += generateNumber(i);
					noOfSetBits++;
				}
				i++;
			}
		} else if(noOfSetBits > K) {
			ArrayList<ContriPair> contris = getEachBitsContri(A, X);
			int i = 0;
			while(noOfSetBits > K) {
				int bitIndex = contris.get(i).bitIndex;
				X = unsetKthBit(X, bitIndex);
				i++;
				noOfSetBits--;
			}
		}
		
		System.out.println(X);
	}
	
	
	public static long unsetKthBit(long X, int k) {
		return (X & ~(1 << (k)));
	}
	
	public static int noOfSetBits(long n) {
		int count = 0;
		if(n == 0) {
			count = 0;
		} else if(n == 1) {
			count = 1;
		} else {
			while(n != 0) {
				if(isLastBitSet(n)) {
					count++;
				}
				n = n >> 1;
			}
		}
		return count;
	}
	
	public static boolean isLastBitSet(long n) {
		return (n & 1) != 0;
	}
	
	public static int noOfBitsReqd(long n) {
		return 1 + (int)Math.floor(Math.log(n)/Math.log(2));
	}
	
	public static long generateNumber(int bitPosition) {
		return (long)(Math.pow(2, bitPosition));
	}
	
	public static boolean isKthBitSet(long number, int k) {
		return (number & (1 << k)) != 0;
	}
	
	public static ArrayList<ContriPair> getEachBitsContri(long[] A, long X) {
		ArrayList<ContriPair> contri = new ArrayList<ContriPair>();
		int bitIndex = 0;
		while(X != 0) {
			if(isKthBitSet(X, 0)) {
				int count = count(A, bitIndex);
				long contribution = count * generateNumber(bitIndex);
				contri.add(new ContriPair(bitIndex, contribution));
			}
			X = X >> 1;
			bitIndex++;
		}
		
		Collections.sort(contri, (pair1, pair2) -> {
			if(pair1.contribution < pair2.contribution) {
				return -1;
			} else if(pair1.contribution > pair2.contribution) {
				return 1;
			} else {
				if(pair1.bitIndex < pair2.bitIndex) {
					return 1;
				} else if(pair1.bitIndex > pair2.bitIndex) {
					return -1;
				} else {
					return 0;
				}
			}
		});
		
		return contri;
	}

	public static int count(long[] A, int k) {
		int count = 0;
		for(long x : A) {
			if(isKthBitSet(x, k)) {
				count++;
			}
		}
		return count;
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
}*/



