package july.long2020;


import java.io.*;
import java.math.*;
import java.util.*;

public class CHFNSWPS {
	
	static class Ups {
		int number;
		int frequency;
		public Ups(int number, int frequency) {
			super();
			this.number = number;
			this.frequency = frequency;
		}
	}
	
	public static void main(String[] args) throws IOException {
		Reader5 sc = new Reader5();
		int t = sc.nextInt();
		StringBuilder str = new StringBuilder();
		while(t != 0) {
			t--;
			int N = sc.nextInt();
			int[] A = new int[N];
			int[] B = new int[N];
			
			HashMap<Integer, Integer> hmA = new HashMap<Integer, Integer>();
			HashMap<Integer, Integer> hmB = new HashMap<Integer, Integer>();
			
			for(int i = 0;i < N; i++) {
				A[i] = sc.nextInt();
				if(hmA.containsKey(A[i])) {
					hmA.put(A[i], 1 + hmA.get(A[i]));
				} else {
					hmA.put(A[i], 1);
				}
			}
			for(int i = 0;i < N; i++) {
				B[i] = sc.nextInt();
				if(hmB.containsKey(B[i])) {
					hmB.put(B[i], 1 + hmB.get(B[i]));
				} else {
					hmB.put(B[i], 1);
				}
			}
			str.append(solve(N, A, B, hmA, hmB) + "\n");
		}
		System.out.println(str.toString());
	}
	
	
	public static String solve(int N, int[] A, int[] B, HashMap<Integer, Integer> hmA, HashMap<Integer, Integer> hmB) {
		List<Ups> upsList = new ArrayList<Ups>();
		List<Ups> downsList = new ArrayList<Ups>();
		
		HashSet<Integer> visited = new HashSet<Integer>();
		
		boolean flag = false;
		
		int smallest = Integer.MAX_VALUE;
		
		for(Integer x : hmA.keySet()) {
			visited.add(x);
			int aFreq = hmA.get(x);
			
			int bFreq = (hmB.containsKey(x)) ? hmB.get(x) : 0;
			if((aFreq + bFreq) % 2 != 0) {
				flag = true;
				break;
			}
			if(aFreq > bFreq) {
				int diff = (aFreq - bFreq)/2;
				downsList.add(new Ups(x, diff));
			} else if(aFreq < bFreq) {
				int diff = (bFreq - aFreq)/2;
				upsList.add(new Ups(x, diff));
			} else {
				smallest = Math.min(smallest, x);
			}
		}
		
		if(!flag) {
			for(Integer x : hmB.keySet()) {
				if(!visited.contains(x)) {
					int bFreq = hmB.get(x);
					if(bFreq % 2 != 0) {
						flag = true;
						break;
					}
					upsList.add(new Ups(x, (bFreq)/2));	
				}	
			}	
		}
		
		if(flag) {
			return "-1";
		}
		
		Collections.sort(upsList, (ups1, ups2) -> {
			if(ups1.number > ups2.number) {
				return 1;
			}
			return -1;
		});
		
		Collections.sort(downsList, (downs1, downs2) -> {
			if(downs1.number > downs2.number) {
				return 1;
			}
			return -1;
		});
		
		BigInteger cost = new BigInteger("0");
		
		if(upsList.size() > 0) {
			if(upsList.get(0).number > downsList.get(0).number) {
				List<Ups> temp = upsList;
				upsList = downsList;
				downsList = temp;
			}
			int aiOuter = 0, aiInner = 0;
			int biOuter = downsList.size() - 1, biInner = 0;
			while(aiOuter < upsList.size()) {
				int tempCost = Math.min(upsList.get(aiOuter).number, downsList.get(biOuter).number);
				if(smallest <= tempCost/2) {
					cost = cost.add(BigInteger.valueOf(smallest));
					cost = cost.add(BigInteger.valueOf(smallest));
				} else {
					cost = cost.add(BigInteger.valueOf(tempCost));
				}
				
				aiInner++;
				biInner++;
				if(aiInner == upsList.get(aiOuter).frequency) {
					smallest = Math.min(smallest, upsList.get(aiOuter).number);
					aiInner = 0;
					aiOuter++;
				}
				if(biInner == downsList.get(biOuter).frequency) {
					smallest = Math.min(smallest, downsList.get(biOuter).number);
					biInner = 0;
					biOuter--;
				}
			}
		}
		
		
		
		return cost + "";
	}
	
}





class Reader5 { 
	final private int BUFFER_SIZE = 1 << 16; 
	private DataInputStream din; 
	private byte[] buffer; 
	private int bufferPointer, bytesRead; 

	public Reader5() 
	{ 
		din = new DataInputStream(System.in); 
		buffer = new byte[BUFFER_SIZE]; 
		bufferPointer = bytesRead = 0; 
	} 

	public Reader5(String file_name) throws IOException 
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
