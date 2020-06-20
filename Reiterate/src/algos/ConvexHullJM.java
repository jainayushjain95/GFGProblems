package algos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ConvexHullJM {

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
		coordinates.add(new Point(0, 3)); 
		coordinates.add(new Point(2, 3)); 
		coordinates.add(new Point(1, 1)); 
		coordinates.add(new Point(2, 1)); 
		coordinates.add(new Point(3, 0)); 
		coordinates.add(new Point(0, 0)); 
		coordinates.add(new Point(3, 3));
		List<Point> polygon = jarvisMarchAlgorithm(coordinates);
		for(Point p : polygon) {
			System.out.println(p.x + ", " + p.y);	
		}
	}
	
	public static List<Point> jarvisMarchAlgorithm(List<Point> coordinates) {
		List<Point> polygonCoordinates = new ArrayList<Point>();
		Point firstPoint = getLeftMostPoint(coordinates);
		Point current = firstPoint;
		polygonCoordinates.add(firstPoint);
		List<Point> colinearPoints = new ArrayList<Point>();
		while(true) {
			Point nextPoint = coordinates.get(0);
			for(Point currPoint : coordinates) {
				if(currPoint.equals(firstPoint)) {
					continue;
				}
				int crossProduct = getCrossProduct(firstPoint, nextPoint, currPoint);
				if(crossProduct > 0) {
					nextPoint = currPoint;
					colinearPoints = new ArrayList<Point>();
				} else if(crossProduct == 0) {
					double distanceBetweenFirstPointAndNextPoint = distance(firstPoint, nextPoint);
					double distanceBetweenFirstPointAndCurrPoint = distance(firstPoint, currPoint);
					if(distanceBetweenFirstPointAndNextPoint - distanceBetweenFirstPointAndCurrPoint < 0) {
						if(distanceBetweenFirstPointAndNextPoint > 0) {
							colinearPoints.add(nextPoint);	
						}
						nextPoint = currPoint;
					} else {
						colinearPoints.add(currPoint);
					}
				}
			}
			for(Point p : colinearPoints) {
				polygonCoordinates.add(p);
			}
			if(nextPoint.equals(current)) {
				break;
			}
			polygonCoordinates.add(nextPoint);
			firstPoint = nextPoint;
		}
		return polygonCoordinates;
	} 

	public static double distance(Point origin, Point one) {
		return Math.sqrt((Math.pow(origin.x - one.x, 2)) + (Math.pow(origin.y - one.y, 2))); 
	}
	
	public static int getCrossProduct(Point origin, Point one, Point two) {
		int y2 = origin.y - two.y;
		int y1 = origin.y - one.y;
		int x2 = origin.x - two.x;
		int x1 = origin.x - one.x;
		return (y2 * x1) - (y1 * x2);
	}
	
	public static Point getLeftMostPoint(List<Point> coordinates) {
		Point leftMost = coordinates.get(0);
		for(Point p : coordinates) {
			if(p.x < leftMost.x) {
				leftMost = p;
			}
		}
		return leftMost;
	}
	
}
