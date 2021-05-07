package backtrackingprobs;

public class RatInAMazeProblem {

	public static void main(String[] args) {
		int[][] maze = {
				{1, 0, 0, 0},
				{1, 1, 0, 1},
				{0, 1, 0, 0}, 
				{1, 1, 1, 1}
		};
		
		int[][] pathMaze = new int[maze.length][maze[0].length];
		boolean canRatEatCheese = canRatEatCheese(maze, pathMaze, 0, 0);
		System.out.println(canRatEatCheese);
	}
	
	public static boolean canRatEatCheese(int[][] maze, int[][] pathMaze, int currentCellRowIndex, int currentCellColumnIndex) {
		if(currentCellRowIndex == maze.length - 1 && currentCellColumnIndex == maze[currentCellRowIndex].length - 1) {
			pathMaze[currentCellRowIndex][currentCellColumnIndex] = 1;
			return true;
		}
		
		if(isValidCell(maze, currentCellRowIndex, currentCellColumnIndex)) {
			pathMaze[currentCellRowIndex][currentCellColumnIndex] = 1;
			if(canRatEatCheese(maze, pathMaze, currentCellRowIndex, currentCellColumnIndex + 1)) {
				return true;
			}
			
			if(canRatEatCheese(maze, pathMaze, currentCellRowIndex + 1, currentCellColumnIndex)) {
				return true;
			}
			
			pathMaze[currentCellRowIndex][currentCellColumnIndex] = 0;
			return false;
		}
		
		return false;
	}

	public static boolean isValidCell(int[][] maze, int currentCellRowIndex, int currentCellColumnIndex) {
		return currentCellRowIndex < maze.length 
				&& currentCellColumnIndex < maze[currentCellRowIndex].length
				&& maze[currentCellRowIndex][currentCellColumnIndex] == 1;
	}
}
