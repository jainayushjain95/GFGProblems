package backtrackingprobs;

import java.util.ArrayList;

public class RatInAMaze2 {

	public static String path = "";
	public static ArrayList<String> paths;
	
	public static void main(String[] args) {
		int maze[][] = { 
				{ 1, 0, 0, 0 },
                { 1, 1, 0, 1 },
                { 0, 1, 0, 0 },
                { 0, 1, 1, 1 } 
               };	
		(new RatInAMaze2()).canRatEatCheeseDriver(maze);
		for(String s : paths) {
			System.out.println(s);
		}
	}

	public ArrayList<String> canRatEatCheeseDriver(int[][] maze) {
		int[][] pathMaze = new int[maze.length][maze[0].length];
		paths = new ArrayList<String>();
		canRatEatCheeseSolve(maze, pathMaze, 0, 0);
		return paths;
	}
	
	public void canRatEatCheeseSolve(int[][] maze, int[][] pathMaze, int currentCellRowIndex, int currentCellColumnIndex) {
		if(isBaseCase1Achieved(maze, pathMaze, currentCellRowIndex, currentCellColumnIndex)) {
			return;
		}
		
		if(isBaseCase2Achieved(maze, pathMaze, currentCellRowIndex, currentCellColumnIndex)) {
			paths.add(path);
			return;
		}
		
		pathMaze[currentCellRowIndex][currentCellColumnIndex] = 1;
		
		if(isValidMove(maze, pathMaze, currentCellRowIndex + 1, currentCellColumnIndex)) {
			path = path + "D";
			canRatEatCheeseSolve(maze, pathMaze, currentCellRowIndex + 1, currentCellColumnIndex);
			path = path.substring(0, path.length() - 1);
		}
		
		if(isValidMove(maze, pathMaze, currentCellRowIndex, currentCellColumnIndex - 1)) {
			path = path + "L";
			canRatEatCheeseSolve(maze, pathMaze, currentCellRowIndex, currentCellColumnIndex - 1);
			path = path.substring(0, path.length() - 1);
		}
		
		if(isValidMove(maze, pathMaze, currentCellRowIndex, currentCellColumnIndex + 1)) {
			path = path + "R";
			canRatEatCheeseSolve(maze, pathMaze, currentCellRowIndex, currentCellColumnIndex + 1);
			path = path.substring(0, path.length() - 1);
		}
		
		if(isValidMove(maze, pathMaze, currentCellRowIndex - 1, currentCellColumnIndex)) {
			path = path + "U";
			canRatEatCheeseSolve(maze, pathMaze, currentCellRowIndex - 1, currentCellColumnIndex);
			path = path.substring(0, path.length() - 1);
		}
		pathMaze[currentCellRowIndex][currentCellColumnIndex] = 0;
		return;
	}
	
	public static boolean isValidMove(int[][] maze, int[][] pathMaze, int currentCellRowIndex, int currentCellColumnIndex) {
		return currentCellRowIndex >= 0 
				&& currentCellRowIndex < maze.length
				&& currentCellColumnIndex >= 0
				&& currentCellColumnIndex < maze[currentCellRowIndex].length 
				&& maze[currentCellRowIndex][currentCellColumnIndex] == 1
				&& pathMaze[currentCellRowIndex][currentCellColumnIndex] == 0;
	}
	
	public static boolean isBaseCase1Achieved(int[][] maze, int[][] pathMaze, int currentCellRowIndex, int currentCellColumnIndex) {
		return currentCellRowIndex < 0 
				|| currentCellRowIndex >= maze.length 
				|| currentCellColumnIndex < 0 
				|| currentCellColumnIndex >= maze[currentCellRowIndex].length
				|| maze[currentCellRowIndex][currentCellColumnIndex] == 0;
	}
	
	public static boolean isBaseCase2Achieved(int[][] maze, int[][] pathMaze, int currentCellRowIndex, int currentCellColumnIndex) {
		return currentCellRowIndex == maze.length - 1 
				&& currentCellColumnIndex == maze[currentCellRowIndex].length - 1;
	}
	
}
