package june.lt2020;

import java.util.*;
import java.io.*;

public class Problem2 {

	/*
	 * public static void main(String[] args) throws IOException { Reader sc = new
	 * Reader(); int t = sc.nextInt(); while(t != 0) { t--; int N = sc.nextInt();
	 * int[] A = new int[N];
	 * 
	 * for(int i = 0;i < N; i++) { A[i] = sc.nextInt(); } solve(N, A); } }
	 * 
	 * public static void solve(int N, int[] A) { int[] A1 = new int[200001]; int[]
	 * A2 = new int[200001];
	 * 
	 * int i = 0;
	 * 
	 * HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
	 * 
	 * int max1 = -1, max2 = -1;
	 * 
	 * for(i = 0;i < N; i++) { if(!hm.containsKey(A[i])) { A1[A[i]] = A[i];
	 * hm.put(A[i], 1); max1 = Math.max(max1, A[i]); } else if(hm.get(A[i]) == 1) {
	 * A2[A[i]] = A[i]; hm.put(A[i], 2); max2 = Math.max(max2, A[i]); } else {
	 * break; } }
	 * 
	 * if(i < N) { System.out.println("NO"); } else { if(max1 <= max2) {
	 * System.out.println("NO"); } else { System.out.println("YES"); for(i = 0;i <
	 * 200001; i++) { if(A1[i] != 0) { System.out.print(i + " "); } } for(i =
	 * 200000;i >= 0; i--) { if(A2[i] != 0) { System.out.print(i + " "); } }
	 * System.out.println(); } } } }
	 * 
	 * 
	 * class Reader { final private int BUFFER_SIZE = 1 << 16; private
	 * DataInputStream din; private byte[] buffer; private int bufferPointer,
	 * bytesRead;
	 * 
	 * public Reader() { din = new DataInputStream(System.in); buffer = new
	 * byte[BUFFER_SIZE]; bufferPointer = bytesRead = 0; }
	 * 
	 * public Reader(String file_name) throws IOException { din = new
	 * DataInputStream(new FileInputStream(file_name)); buffer = new
	 * byte[BUFFER_SIZE]; bufferPointer = bytesRead = 0; }
	 * 
	 * public String readLine() throws IOException { byte[] buf = new byte[64]; //
	 * line length int cnt = 0, c; while ((c = read()) != -1) { if (c == '\n')
	 * break; buf[cnt++] = (byte) c; } return new String(buf, 0, cnt); }
	 * 
	 * public int nextInt() throws IOException { int ret = 0; byte c = read(); while
	 * (c <= ' ') c = read(); boolean neg = (c == '-'); if (neg) c = read(); do {
	 * ret = ret * 10 + c - '0'; } while ((c = read()) >= '0' && c <= '9');
	 * 
	 * if (neg) return -ret; return ret; }
	 * 
	 * public long nextLong() throws IOException { long ret = 0; byte c = read();
	 * while (c <= ' ') c = read(); boolean neg = (c == '-'); if (neg) c = read();
	 * do { ret = ret * 10 + c - '0'; } while ((c = read()) >= '0' && c <= '9'); if
	 * (neg) return -ret; return ret; }
	 * 
	 * public double nextDouble() throws IOException { double ret = 0, div = 1; byte
	 * c = read(); while (c <= ' ') c = read(); boolean neg = (c == '-'); if (neg) c
	 * = read();
	 * 
	 * do { ret = ret * 10 + c - '0'; } while ((c = read()) >= '0' && c <= '9');
	 * 
	 * if (c == '.') { while ((c = read()) >= '0' && c <= '9') { ret += (c - '0') /
	 * (div *= 10); } }
	 * 
	 * if (neg) return -ret; return ret; }
	 * 
	 * private void fillBuffer() throws IOException { bytesRead = din.read(buffer,
	 * bufferPointer = 0, BUFFER_SIZE); if (bytesRead == -1) buffer[0] = -1; }
	 * 
	 * private byte read() throws IOException { if (bufferPointer == bytesRead)
	 * fillBuffer(); return buffer[bufferPointer++]; }
	 * 
	 * public void close() throws IOException { if (din == null) return;
	 * din.close(); }
	 */
}
