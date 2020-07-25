package july.long2020;

import java.io.*;
import java.util.*;


class InfectedPeopleIndiceComparator implements Comparator<InfectedPeopleIndice>{

	public int compare(InfectedPeopleIndice o1, InfectedPeopleIndice o2) {
		if(o1.count > o2.count) {
			return 1;
		} else if (o1.count < o2.count) {
			return -1;
		}
		return 0;
	}
}

class InfectedPeopleIndice {
	int index;
	long count;

	public InfectedPeopleIndice(int index, long count) {
		super();
		this.index = index;
		this.count = count;
	}

}

public class DRCHEF {


	public static void main(String[] args)  throws IOException {
		Reader6 sc = new Reader6();
		int t = sc.nextInt();
		StringBuilder str = new StringBuilder();
		while(t != 0) {
			int N = sc.nextInt();
			long x = sc.nextLong();
			long[] population = new long[N];
			long[] infected = new long[N];
			for(int i = 0; i < N; i++) {
				population[i] = sc.nextLong();
				infected[i] = population[i];
			}
			str.append(solve(N, x, population, infected) + "\n");
			t--;
		}
		System.out.println(str.toString());
	}


	public static long solve(int N, long x, long[] population, long[] infected) {
		long minimumNoOfDays = 0;
		
		int noOfInfectedCountries = N;
		
		while(noOfInfectedCountries != 0) {
			int justLesserThanEqualsIndex = getJustLesserThanEqualsIndex(N, infected, x);
			if(justLesserThanEqualsIndex != -1) {
				if(infected[justLesserThanEqualsIndex] == x) {
					noOfInfectedCountries--;
					infected[justLesserThanEqualsIndex] = 0;
					x = x * 2;
				} else {
					long cured = infected[justLesserThanEqualsIndex];
					if(2*cured > x) {
						noOfInfectedCountries--;
						infected[justLesserThanEqualsIndex] = 0;
						x = cured * 2;
					} else {
						long max = getMax(infected);
						if(max > x) {
							x = x * 2;
						} else {
							minimumNoOfDays += noOfInfectedCountries - 1;
							noOfInfectedCountries = 0;	
						}
					}
				}
			} else {
				x = x * 2;
			}
			minimumNoOfDays++;
		}
		
		return minimumNoOfDays;
	}

	public static long getMax(long[] infected) {
		long max = -2;
		for(long x : infected) {
			max = Math.max(x, max);
		}
		return max;
	}
	
	public static int getJustLesserThanEqualsIndex(int N, long[] infected, long x) {
		int index = -1;
		for(int i = 0;i < N; i++) {
			if(infected[i] == x) {
				index = i;
				break;
			} else if(infected[i] < x) {
				if(index == -1) {
					index = i;
				} else if(infected[i] > infected[index]) {
					index = i;
				}
			}
		}
		return index;
	}
	
}













class Reader6 { 
	final private int BUFFER_SIZE = 1 << 16; 
	private DataInputStream din; 
	private byte[] buffer; 
	private int bufferPointer, bytesRead; 

	public Reader6() 
	{ 
		din = new DataInputStream(System.in); 
		buffer = new byte[BUFFER_SIZE]; 
		bufferPointer = bytesRead = 0; 
	} 

	public Reader6(String file_name) throws IOException 
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
