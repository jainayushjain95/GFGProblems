package algos;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/*
 * https://leetcode.com/problems/erect-the-fence/discuss/103302/Java-Graham-scan-with-adapted-sorting-to-deal-with-collinear-Points/138612/
 */

public class ConvexHullGS {	
	
	static class Point {
		int x;
		int y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) {
		List<Point> coordinates = new ArrayList<Point>();
		coordinates.add(new Point(3, 0));
		coordinates.add(new Point(1, 2));
//		coordinates.add(new Point(0, 0));
//		coordinates.add(new Point(2, 0));
//		coordinates.add(new Point(3, 2));
//		coordinates.add(new Point(2, 4));
//		coordinates.add(new Point(0, 2));
//		coordinates.add(new Point(0, 1));
//		coordinates.add(new Point(3, 1));
//		coordinates.add(new Point(4, 2));
//		coordinates.add(new Point(2, 2));
		List<Point> polygon = convexHullGrahamScanAlgorithm(coordinates);
		for(Point p : polygon) {
			System.out.println(p.x + ", " + p.y);	
		}
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
			int crossProduct = getCrossProduct(getTwoDownOfStackTop(stack), getOneDownOfStackTop(stack), getStackTop(stack));
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
			int crossProduct = getCrossProduct(bottomMostPoint, p1, p2);
			int dis1 = distance(bottomMostPoint, p1);
			int dis2 = distance(bottomMostPoint, p2);
			return (crossProduct != 0) ? -crossProduct : (dis1 - dis2);
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
	
	public static int distance(Point origin, Point one) {
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
	public static int getCrossProduct(Point a, Point b, Point c) {
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
