package lc.google;

import java.util.*;

public class BestMeetingPoint {

	public static void main(String[] args) {
		int[][] grid = {
				{1,0,0,0,1},
				{0,0,0,0,0},
				{0,0,1,0,0}
		};
		System.out.println(new BestMeetingPoint().minTotalDistance(grid));
	}


	public int minTotalDistance(int[][] grid) {
		int x = getMedianX(grid);
		int y = getMedianY(grid);

		int dis = 0;
		for(int i = 0;i < grid.length; i++) {
			for(int j = 0;j < grid[i].length; j++) {
				if(grid[i][j] == 1) 
					dis += Math.abs(x - i) + Math.abs(y - j);      
			}
		}
		return dis;
	}

	public int getMedianX(int[][] grid) {
		List<Integer> list = new ArrayList<>();
		for(int i = 0;i < grid.length; i++) {
			for(int j = 0;j < grid[i].length; j++) {
				if(grid[i][j] == 1) {
					list.add(i);
				}
			}    
		}
		return list.get(list.size()/2);
	}

	public int getMedianY(int[][] grid) {
		List<Integer> list = new ArrayList<>();
		for(int i = 0;i < grid[0].length; i++) {
			for(int j = 0;j < grid.length; j++) {
				if(grid[j][i] == 1) {
					list.add(i);
				}
			}    
		}
		return list.get(list.size()/2);
	}
}
