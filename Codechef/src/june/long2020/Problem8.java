package june.long2020;

import java.util.*;
import java.io.*;

public class Problem8 {
	
	static class Point {
		Long x;
		Long y;
		public Point(Long x, Long y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws IOException {
		Reader sc = new Reader();
		int t = sc.nextInt();
		StringBuilder str = new StringBuilder();
		while(t != 0) {
			Long N = sc.nextLong();
			Long Q = sc.nextLong();
			List<Point> coordinates = new ArrayList<Point>();
			for(int i = 0;i < N; i++) {
				coordinates.add(new Point(sc.nextLong(), sc.nextLong()));
			}
			List<Point> queries = new ArrayList<Point>();
			for(int i = 0;i < Q; i++) {
				queries.add(new Point(sc.nextLong(), sc.nextLong()));
			}
			str.append(solve(N, Q, coordinates, queries) + "\n");
			t--;
		}
		System.out.println(str.toString());
	}
	
	public static String solve(Long N, Long Q, List<Point> coordinates, List<Point> queries) {
		StringBuilder str = new StringBuilder();
		List<List<Point>> convexHulls = new ArrayList<List<Point>>();
		while(coordinates.size() > 2) {
			List<Point> hull = convexHullGrahamScanAlgorithm(coordinates);
			hull.add(hull.get(0));
			coordinates.removeAll(hull);
			convexHulls.add(hull);
		}
		for(int i = 0;i < Q; i++) {
			if(convexHulls.size() == 0) {
				str.append(0 + "\n");
			} else if(convexHulls.size() == 1) {
				boolean isInside = isCandleStrictlyInside(convexHulls.get(0), queries.get(i));
				if(isInside) {
					str.append(1 + "\n");	
				} else {
					str.append(0 + "\n");
				}
			} else {
				long hullIndex = getHullInWHichCandleExists(convexHulls, queries.get(i), 0, convexHulls.size() - 1);
				str.append((hullIndex + 1)  + "\n");	
			}
		}
		return str.toString();
	}

	public static int getHullInWHichCandleExists(List<List<Point>> convexHulls, Point Q, int beginIndex, int endIndex) {
		if(beginIndex > endIndex) {
			return -1;
		}
		int midIndex = (endIndex - beginIndex)/2 + beginIndex;
		if(isCandleStrictlyInside(convexHulls.get(midIndex), Q)) {
			if(midIndex == convexHulls.size() - 1 || !isCandleStrictlyInside(convexHulls.get(midIndex + 1), Q)) {
				return midIndex;
			}
			return getHullInWHichCandleExists(convexHulls, Q, midIndex + 1, endIndex);
		}
		return getHullInWHichCandleExists(convexHulls, Q, beginIndex, midIndex - 1);
	}
	
	public static boolean isCandleStrictlyInside(List<Point> convexHull, Point Q) {
		for(int i = 0;i < convexHull.size() - 1; i++) {
			Point currPointA = convexHull.get(i);
			Point currPointB = convexHull.get(i + 1);
			if(getCrossProduct(currPointA, currPointB, Q) <= 0) {
				return false;
			}
		}
		return true;
	}
	
	public static List<Point> convexHullGrahamScanAlgorithm(List<Point> coordinates) {
		if(coordinates.size() <= 2) {
			return coordinates;
		}
		Point bottomMostPoint = getBottomMostPoint(coordinates);
		sortPointsHandlingCollinear(coordinates, bottomMostPoint);
		
		Stack<Point> stack = new Stack<Point>(); 
		
		stack.push(coordinates.get(0));
		stack.push(coordinates.get(1));
		stack.push(coordinates.get(2));
		
		for(int i = 2; i < coordinates.size(); i++) {
			long crossProduct = getCrossProduct(getTwoDownOfStackTop(stack), getOneDownOfStackTop(stack), getStackTop(stack));
			while(crossProduct < 0) {
				removeOneDownStack(stack);
				crossProduct = getCrossProduct(getTwoDownOfStackTop(stack), getOneDownOfStackTop(stack), getStackTop(stack));
			}
			if(i < coordinates.size() - 1) {
				stack.push(coordinates.get(i + 1));	
			}
		}
		
		return new ArrayList<Point>(stack);
	}
	
	public static Point getStackTop(Stack<Point> stack) {
		return stack.peek();
	}
	
	public static void removeOneDownStack(Stack<Point> stack) {
		Point top = stack.pop();
		stack.pop();
		stack.push(top);
	}
	
	public static Point getOneDownOfStackTop(Stack<Point> stack) {
		Point top = stack.pop();
		Point output = stack.pop();
		stack.push(output);
		stack.push(top);
		return output;
	}
	
	public static Point getTwoDownOfStackTop(Stack<Point> stack) {
		Point top = stack.pop();
		Point topOneDown = stack.pop();
		Point output = stack.pop();
		stack.push(output);
		stack.push(topOneDown);
		stack.push(top);
		return output;
	}
	
	public static void sortPointsHandlingCollinear(List<Point> coordinates, Point bottomMostPoint) {
		Collections.sort(coordinates, (p1, p2) -> {
			long crossProduct = getCrossProduct(bottomMostPoint, p1, p2);
			long dis1 = distance(bottomMostPoint, p1);
			long dis2 = distance(bottomMostPoint, p2);
			if(crossProduct == 0) {
				long disDiff = dis1 - dis2;
				if(disDiff > 0) {
					return 1;
				} else if(disDiff < 0) {
					return -1;
				} else {
					return 0;
				}
			} else {
				if(crossProduct < 0) {
					return 1;
				} else if(crossProduct > 0) {
					return -1;
				} else {
					return 0;
				}
			}
		});
		
		/*
		 * Handle Collienar Points
		 */
		
		Point p = coordinates.get(0);
		Point q = coordinates.get(coordinates.size() - 1);
		
		int i = coordinates.size() - 2;
		while(i >= 0 && getCrossProduct(p, q, coordinates.get(i)) == 0) {
			i--;
		}
		int k = i + 1, l = coordinates.size() - 1;
		while(k < l) {
			swap(k, l, coordinates);
			k++;
			l--;
		}
	}
	
	public static long distance(Point origin, Point one) {
		return (origin.x - one.x) * (origin.x - one.x) + (origin.y - one.y) * (origin.y - one.y); 
	}
	
	public static void swap(int i, int j, List<Point> coordinates) {
		Point temp = coordinates.get(j);
		coordinates.set(j, coordinates.get(i));
		coordinates.set(i, temp);
	}
	
	/*
	 * a --> origin
	 * ab, ac
	 * if returns > 0, c is on left of ab
	 * if returns < 0, c is on right of ab
	 * else -> a, b, c --> collinear
	 */
	public static long getCrossProduct(Point a, Point b, Point c) {
		return ((c.y - a.y) * (b.x - a.x)) - ((b.y - a.y) * (c.x - a.x));
	}
	
	public static Point getBottomMostPoint(List<Point> coordinates) {
		Point bottomPoint = coordinates.get(0);
		for(Point p : coordinates) {
			if(p.y < bottomPoint.y) {
				bottomPoint = p;
			} else if(p.y == bottomPoint.y) {
				if(p.x < bottomPoint.x) {
					bottomPoint = p;
				}
			}
		}
		return bottomPoint;
	}
	
}


class Reader{ 
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
}
