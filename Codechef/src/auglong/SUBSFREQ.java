package auglong;

import java.io.*;
import java.util.*;

public class SUBSFREQ {
	public static int mod = 1000000007;
	static Reader2 sc = new Reader2();

	public static void main(String[] args) throws IOException {
		int t = sc.nextInt();
		StringBuilder str = new StringBuilder();
		while(t != 0) {
			t--;
			str.append(solveOptimized());
			str.append("\n");
		}
		System.out.println(str.toString());
	}

	public static String solveOptimized() throws IOException {
		StringBuilder str = new StringBuilder();
		int N = sc.nextInt();
		int max = -1;
		int[] A = new int[N];
		for(int i = 0;i < N; i++) {
			A[i] = sc.nextInt();
			max = Math.max(A[i], max);
		}

		int[] frequencyArray = new int[max + 1];
		long[] solutionArray = new long[N + 1];
		List<Integer> list = new ArrayList<Integer>();


		int maxF = -1;

		for(int i = 0;i < N; i++) {
			if(frequencyArray[A[i]] == 0) {
				list.add(A[i]);
			} 
			frequencyArray[A[i]]++;
			maxF = Math.max(maxF, frequencyArray[A[i]]);
		}
		
		sortList(frequencyArray, list);
		
		if(list.size() == 1) {
			for(int i = 1;i <= N; i++) {
				long output = 0;
				if(i == list.get(0)) {
					output = fastExponentiation(2, N) - 1;
				}
				str.append(output);
				str.append(" ");
			}
		} else if(list.size() == N) {
			for(int i = 0;i < N; i++) {
				long output = fastExponentiation(2, N - i - 1);
				str.append(output);
				str.append(" ");
			}
		} else {
			List<List<Long>> ncrDPList = new ArrayList<List<Long>>();
			List<List<Long>> ncrDPPreList = new ArrayList<List<Long>>();

			getNCR(maxF, maxF, ncrDPList, ncrDPPreList);

			
			for(int i = 0;i < list.size(); i++) {
				long solution = 0;
				int frequency = frequencyArray[list.get(i)];
				int noOfTotalBits = frequency;
				List<Long> a = ncrDPList.get(noOfTotalBits);
				for(int p = noOfTotalBits; p > 0; p--) {
					long currSoution = 1;
//					System.out.println("0" + " <--> " + (i-1) + " : " + (p - 1) +", " + (i + 1) + " <--> " + list.size() + " : " + p);
					for(int j = 0;j < list.size(); j++) {
						if(j != i) {
							int maxSetBitsCount = -1;
							if(list.get(j) < list.get(i)) {
								maxSetBitsCount = p - 1;
							} else {
								maxSetBitsCount = p;
							}
							int currentFrequency = frequencyArray[list.get(j)];
							long sol = getCount(ncrDPPreList, currentFrequency, maxSetBitsCount);
							currSoution = mul(currSoution, sol);		
						}
					}
					currSoution = mul(currSoution, a.get(p));

					solution += currSoution;
					if(solution >= mod) {
						solution = solution % mod;
					}
				}
				solutionArray[list.get(i)] = solution;
			}

			for(int i = 1;i < solutionArray.length; i++) {
				str.append(solutionArray[i]);
				str.append(" ");
			}	
		}

		return str.toString();
	}

	
	public static void buildDPList() {
		
	}
	
	public static long getCount(List<List<Long>> ncrDPPreList, int currentFrequency, int maxSetBitsCount) {
		long ans = 0;
		int noOfBits = Math.min(maxSetBitsCount, currentFrequency);
		ans = ncrDPPreList.get(currentFrequency).get(noOfBits);
		return ans;
	}	

	
	public static void sortList(int[] frequencyArray, List<Integer> list) {
		Collections.sort(list, (a, b) -> {
			if(frequencyArray[a] > frequencyArray[b]) {
				return -1;
			} else if(frequencyArray[a] < frequencyArray[b]) {
				return 1;
			} else if(a > b) {
				return 1;
			} else if(a < b) {
				return -1;
			}
			return 0;
		});
	}
	
	public static void getNCR(int n, int r, List<List<Long>> ncrDPList, List<List<Long>> ncrDPPreList) {
		for(int i = 0;i <= n; i++) {
			List<Long> a = new ArrayList<Long>();
			ncrDPList.add(a);
			
			List<Long> apre = new ArrayList<Long>();
			ncrDPPreList.add(apre);
			
			List<Long> prev = null;
			if(i > 0) {
				prev = ncrDPList.get(i - 1);
			}
			
			for(int j = 0;j <= Math.min(r, i); j++) {
				if(j == 0 || i == j) {
					a.add(j, 1l);
				} else if(r > n) {
					a.add(j, 0l);
				} else {
					a.add(j, prev.get(j - 1) + prev.get(j));
					if(a.get(j) >= mod) {
						a.set(j, a.get(j) % mod);
					}
				}
				if(j == 0) {
					apre.add(j, 1l);
				} else {
					apre.add(j, apre.get(j - 1) + a.get(j));
					if(apre.get(j) >= mod) {
						apre.set(j, apre.get(j) % mod);
					}
				}
			}
		}
	}

	public static long fastExponentiation(long number, long exponent) {
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
			long secondHalfResult = fastExponentiation(number, exponent/2);
			long ans = secondHalfResult * secondHalfResult;
			if(ans >= mod) {
				ans = ans % mod;
			}
			return ans;
		} else {
			long secondHalfResult = fastExponentiation(number, exponent/2);
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

	public static long mul(long a, long b) {
		long output = a * b;
		if(output >= mod) {
			output = output % mod;
		}
		return output;
	}

	public static int getNoOfSetBits(long n) {
		int setBitsCount = 0;
		while(n != 0) {
			if(isLastBitSet(n)) {
				setBitsCount++;
			}
			n = n >> 1;
		}	
		return setBitsCount;
	}

	public static boolean isLastBitSet(long n) {
		return (n & 1) != 0; 
	}

	public static int noOfBitsReqd(long n) {
		return 1 + (int)Math.floor(Math.log(n)/Math.log(2));
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