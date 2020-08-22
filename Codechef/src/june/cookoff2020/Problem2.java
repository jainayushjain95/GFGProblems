package june.cookoff2020;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;


public class Problem2 {
	public static void main(String[] args) throws IOException {
		Reader1 sc = new Reader1();
		int t = sc.nextInt();
		StringBuilder str = new StringBuilder();
		while(t != 0) {
			int N = sc.nextInt();
			int M = sc.nextInt();
			int[] A = new int[N];
			int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
			int count = 0;
			HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
			for(int i = 0;i < N; i++) {
				A[i] = sc.nextInt();
				min = Math.min(A[i], min);
				max = Math.max(A[i], max);
				if(A[i] > M) {
					count++;
				}
				if(!hm.containsKey(A[i])) {
					hm.put(A[i], 1);
				} else {
					hm.put(A[i], 1 + hm.get(A[i]));
				}
			}
			str.append(solve(N, M, A, hm, min, max, count) + "\n");
			t--;
		}
		System.out.println(str.toString());
	}

	public static String solve(int N, int M, int[] A, HashMap<Integer, Integer> hm, int min, int max, int count) {
		StringBuilder str = new StringBuilder();
		int output = 0;

		int lesserCount = 0;

		int i = 1;
		
		for(i = 1;i < M; i++) {
			if(hm.containsKey(i)) {
				lesserCount += hm.get(i);
			} else {
				break;
			}
		}

		
		
		if(i != M) {
			output = -1;
		} else {
			int greaterCount = 0;

			greaterCount = count;

			output = lesserCount + greaterCount;	
	
		}
		

		str.append(output);
		return str.toString();
	}

}



class Reader1{
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
