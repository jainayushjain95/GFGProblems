package june.long2020;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class GUESSGQ {
	
	public static Scanner sc = new Scanner(System.in);
	public static int N = -1;
	public static HashMap<Integer, Integer> bRanges = new HashMap<Integer, Integer>();
	public static HashMap<Integer, Integer> aR = new HashMap<Integer, Integer>();
	public static HashMap<Integer, Integer> dis = new HashMap<Integer, Integer>();
	
	
	static class Ranges {
		int left;
		int right;
		public Ranges() {}
		public Ranges(int left, int right) {
			this.left = left;
			this.right = right;
		}
	}

	/*
	 * L -- -1
	 * E -- 0
	 * G -- 1
	 */
	public static int getGraderResponse(int x) {
		if(!isValidInteger(x, N)) {
			return -2;
		}
		System.out.println(x);
		String a = sc.next();
		if(a.equals("E")) {
			System.exit(0);
		}
		//dis.put(x, 1);
		if(a.equals("L")) {
			return -1;
		}
		return 1;
	}
	
	public static void main(String[] args) {
		
		N = sc.nextInt();
		LinkedList<Ranges> queue = new LinkedList<Ranges>();
		queue.add(new Ranges(1, N));
		
		while(!queue.isEmpty()) {
			System.out.println("***************************************");
			for(Ranges r : queue) {
				System.out.println(r.left + ", " + r.right);
			}
			System.out.println("***************************************");
			if(queue.size() >= 3) {
				removeOneRangeFromQueue(queue);
			} else {
				solve(queue);
			}
		}
	}

	public static void solve(LinkedList<Ranges> queue) {
		
		Ranges range = queue.poll();
		int left = range.left;
		int right = range.right;
		
		if(left > right) {
			return;
		} else if(right - left <= 3) {
			while(left <= right) {
				getGraderResponse(left);
				left++;
			}
			return;
		}
		
		int size = (int)Math.ceil(((double)right - (double)left)/3);
		
		int al = left, ar = al + size - 1;
		int bl = ar + 1, br = bl + size - 1;
		int cl = br + 1, cr = right;
		
		
		int A = -2, B = -2, C = -2;

		if(bl != br) {
			A = getGraderResponse(bl);
			bRanges.put(bl, A);
		
			B = -2;
			if(A == 1) {
				B = getGraderResponse(ar);	
				aR.put(ar, B);
				if(B == 1) {
					addRangeToQueue(bl, cr, queue);
					return;
				}
			}
			C = getGraderResponse(br);	
			bRanges.put(br, C);	
		}
		
				
		if(A == -1 && C == -1) {
			addRangeToQueue(al, br - 1, queue);
		} else if(A == -1 && C == 1) {
			addRangeToQueue(al, ar, queue);
			addRangeToQueue(cl, cr, queue);
		} else {
			if(B == -1 && C == -1) {
				addRangeToQueue(al, br - 1, queue);
			} else if(B == -1 && C == 1){
				addRangeToQueue(cl, cr, queue);
				addRangeToQueue(al, ar - 1, queue);
			}	
		}
		
	}
	
	public static void removeOneRangeFromQueue(LinkedList<Ranges> queue) {
		Ranges midRange = queue.get(1);
		int A = getGraderResponse(midRange.left);
		int B = -2;
		
		Ranges a = queue.get(0);
		Ranges b = queue.get(1);
		Ranges c = queue.get(2);
	
		
		if(A == 1) {
			B = getGraderResponse(queue.get(0).right);	
			if(B == 1) {
				queue.remove(a);
				return;
			}
		}

		int C = getGraderResponse(midRange.right);
			
		if(A == -1 && C == -1) {
			queue.remove(c);
		} else if(A == -1 && C == 1) {
			queue.remove(b);
		} else {
			if(B == -1 && C == -1) {
				queue.remove(c);
			} else if(B == -1 && C == 1){
				queue.remove(b);
			}		
		}
	}

	public static int getMid(int a, int b) {
		return (b - a)/2 + a;
	}
	
	public static void addRangeToQueue(int left, int right, LinkedList<Ranges> queue) {
		if(left > right) {
			return;
		}
		if(!isValidInteger(left, right) || !isValidInteger(right, right)) {
			return;
		}
		
		if(right - left <= 3) {
			while(left <= right) {
				if(!aR.containsKey(left) && !bRanges.containsKey(left) && !dis.containsKey(left)) {
					getGraderResponse(left);	
				}
				left++;
			}
			return;
		}
		
		queue.add(getQueueInsertionIndex(left, queue), new Ranges(left, right));
	}
	
	public static int getQueueInsertionIndex(int element, LinkedList<Ranges> queue) {
		int index = 0;
		for(Ranges range : queue) {
			if(range.left > element) {
				break;
			}
			index++;
		}
		return index;
	}
	
	public static boolean isValidInteger(int x, int max) {
		return x >= 1 && x <= max;
	}
}

