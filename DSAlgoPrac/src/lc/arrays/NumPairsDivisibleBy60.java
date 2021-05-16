package lc.arrays;

public class NumPairsDivisibleBy60 {

	public static void main(String[] args) {
		int[] time = {30,20,150,100,40};
		System.out.println("Count: " + numPairsDivisibleBy60(time));
	}

	public static int numPairsDivisibleBy60(int[] time) {
		int solution = 0;
        int[] array = new int[61];
		for(int i = 0;i < time.length; i++) {
            array[(time[i]) % 60]++;    
        }
		
		for(int i = 1;i < 30; i++) {
			solution = solution + (array[i] * array[60 - i]);
		}
		if(array[30] != 0) {
			solution = solution + (array[30] * (array[30] - 1))/2;
		}
		if(array[0] != 0) {
			solution = solution + (array[0] * (array[0] - 1))/2;
		}
        return solution;
	}

}
