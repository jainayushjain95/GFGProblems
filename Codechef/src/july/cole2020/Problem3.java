package july.cole2020;

import java.io.*;
import java.util.*;

public class Problem3 {
	static Reader3 sc = new Reader3();
	public static void main(String[] args)  throws IOException {
		StringBuilder str = new StringBuilder();
		int t = sc.nextInt();
		while(t != 0) {
			t--;
			String S = sc.readLine();
			int Q = sc.nextInt();
			int[] q = new int[Q];
			for(int i = 0;i < Q; i++) {
				q[i] = sc.nextInt();
			}
			str.append(solve(S, S.length(), Q, q) + "\n");
		}
		System.out.println(str.toString());
	}
	
	
	public static String solve(String S, int N, int Q, int[] q) {
		StringBuilder str = new StringBuilder();
		int qi = 0;
		while(Q != 0) {
			String s = S.substring(q[qi] - 1);
			int ans = balanced(s);
			if(ans != -1) {
				ans = q[qi] + balanced(s);
			}
			str.append(ans + "\n");
			Q--;
			qi++;
		}
		return str.toString();
	}
	
	
	public static int balanced(String s) {
		int i = 0;
		boolean flag = false;
		long acount = 0, ccount = 0;
		if(s.length() == 0) {
			flag = true;
		} else {
			for(i = 0;i < s.length(); i++) {
				char curr = s.charAt(i);
				if(curr == '(') {
					acount++;
				} else {
					ccount++;
				}
				if(acount == ccount) {
					flag = true;
					break;
				} else if(acount < ccount && acount != 0) {
					flag = true;
					break;
				}
			}
		}
		if(!flag) {
			i = -1;
		}
		return i;
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