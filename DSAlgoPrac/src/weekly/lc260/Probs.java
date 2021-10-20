package weekly.lc260;

public class Probs {
//	Contest Rating
//	1,696pt
//	Ranking
//	22,879
//	Attended
//	7
//	76568
	

	
	public static void main(String[] args) {
		int[][] grid = {
				{20, 3, 20, 17, 2, 12, 15, 17, 4, 15}, 
				{20, 10, 13, 14, 15, 5, 2, 3, 14, 3}
		};	
		System.out.println((new Probs().gridGame(grid)));
	}

	public long gridGame(int[][] grid) {
		long points = 0l;
		int[][] newgrid = new int[2][grid[0].length];
		
		for(int i = 0;i < 2; i++) {
			for(int j = 0;j < grid[i].length; j++) {
				newgrid[i][j] = grid[i][j];
			}
		}
		
		for(int i = newgrid[0].length - 2;i >= 0; i--) {
			newgrid[1][i] += newgrid[1][i + 1];
		}
		
		newgrid[0][grid[0].length - 1] += newgrid[1][newgrid[1].length - 1]; 
		
		for(int i = newgrid[0].length - 2; i >= 0; i--) {
			newgrid[0][i] += Math.max(newgrid[0][i + 1], newgrid[1][i]); 
		}
		
		grid[0][0] = 0;
		grid[1][grid[0].length - 1] = 0;
		
		int i = 0,j = 0;
		while(true) {
			long right = (j < grid[0].length - 1) ? newgrid[i][j + 1] : Long.MIN_VALUE;
			long down = (i < 1) ? newgrid[i + 1][j] : Long.MIN_VALUE;
		
			if(right > down) {
				grid[i][j + 1] = 0;
				j++;
			} else if(right <= down && right != Long.MIN_VALUE) {
				grid[i + 1][j] = 0;
				i++;
			} else {
				i++;
				j++;
			}
			if(i > 1 && j > grid[0].length - 1) {
				break;
			}
		}
		
		for(i = 1;i >= 0; i--) {
			for(j = grid[0].length - 1;j >= 0; j--) {
				if(i == 1 && j == grid[0].length - 1) {
					continue;
				}
				if(i == 1) {
					grid[i][j] += grid[i][j + 1];
				} else if(j == grid[0].length - 1) {
					grid[i][j] += grid[i + 1][j];
				} else {
					grid[i][j] += Math.max(grid[i][j + 1], grid[i + 1][j]);
				}
				
			}
		}
		
		return grid[0][0];
    }
	
	
}
