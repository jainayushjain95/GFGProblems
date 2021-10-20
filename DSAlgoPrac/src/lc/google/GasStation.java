package lc.google;

public class GasStation {

	public static void main(String[] args) {
		
	}
	
	public int canCompleteCircuit(int[] gas, int[] cost) {
		int index = 0, tank = 0;
		for(int i = 0;i < gas.length; i++) {
			tank += gas[i] - cost[i];
		}
		if(tank < 0) {
			return -1;
		}
		tank = 0;
		for(int i = 0;i < gas.length; i++) {
			tank += gas[i] - cost[i];
			if(tank < 0) {
				index = i + 1;
				tank = 0;
			}
		}
		
		return index;
    }

}
