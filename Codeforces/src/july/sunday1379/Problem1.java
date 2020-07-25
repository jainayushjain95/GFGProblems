package july.sunday1379;
import java.io.*;
import java.util.*;


public class Problem1 {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		StringBuilder s = new StringBuilder();
		while(t != 0) {
			int n = sc.nextInt();
			String st = sc.next();
			s.append(solve(n, st));
			t--;
		}
		System.out.println(s.toString());
	}

	public static String solve(int n, String s) {
		String solution = "";
		String search = "abacaba";
		HashMap<Integer, Character> hm = new HashMap<Integer, Character>();
		int count = 0, icount = 0;
		int i = 0, j = 0;
		
		for(i = 0;i < n - search.length() + 1; i++) {
			for(j = 0; j < search.length(); j++) {
				char a = search.charAt(j);
				char b = s.charAt(j + i);
				if(a != b) {
					break;
				}
			}
			if(j == search.length()) {
				icount++;
			}
			if(icount > 1) {
				break;
			}
		}
		if(icount > 1) {
			solution = "No\n";
		} else if(icount == 1) {
			solution = "YES\n";
			for(int m = 0;m < n; m++) {
				if(s.charAt(m) == '?') {
					solution += "q";
				} else {
					solution += s.charAt(m);
				}
			}
			solution += "\n";
		} else {
			i = 0;
			j = 0;
			for(i = 0;i < n - search.length() + 1; i++) {
				if(count == 0) {
					hm = new HashMap<Integer, Character>();	
				}
				for(j = 0; j < search.length(); j++) {
					char a = search.charAt(j);
					char b = s.charAt(j + i);
					if(a != b && b != '?') {
						break;
					} else if(a != b && b == '?') {
						hm.put(i + j, a);
					}
				}
				if(j == search.length()) {
					count++;
				}
				if(count > 1) {
					break;
				}
			}
			if(count != 1) {
				solution = "No\n";
			} else {
				solution = "YES\n";
				for(Integer index : hm.keySet()) {
					if(index == 0) {
						s = hm.get(index) + s.substring(index + 1);
					} else if(index == n - 1) {
						s = s.substring(0, index) + hm.get(index);
					} else {
						s = s.substring(0, index) + hm.get(index) + s.substring(index + 1);
					}
				}
				for(int m = 0;m < n; m++) {
					if(s.charAt(m) == '?') {
						solution += "q";
					} else {
						solution += s.charAt(m);
					}
				}
				solution += "\n";
			}	
		}
		return solution;
	}
}



class Reader1 { 
	final private int BUFFER_SIZE = 1 << 16; 
	private DataInputStream din; 
	private byte[] buffer; 
	private int bufferPointer, bytesRead; 

	public Reader1() 
	{ 
		din = new DataInputStream(System.in); 
		buffer = new byte[BUFFER_SIZE]; 
		bufferPointer = bytesRead = 0; 
	} 

	public Reader1(String file_name) throws IOException 
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
