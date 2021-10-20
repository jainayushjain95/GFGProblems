package lc.google;

public class CountNoOfTeams {

	public static void main(String[] args) {
		int[] rating = {2, 5, 3, 4, 1};
		System.out.println((new CountNoOfTeams().numTeams(rating)));
	}
	
	public int numTeams(int[] rating) {
		int count = 0;
		
		for(int i = 1;i < rating.length - 1; i++) {
			int ls = 0, rs = 0, lg = 0, rg = 0;
			int currentRating = rating[i];
			int j = i - 1;
			while(j >= 0) {
				if(currentRating > rating[j]) {
					ls++;
				}
				if(currentRating < rating[j]) {
					lg++;
				}
				j--;
			}
			j = i + 1;
			while(j < rating.length) {
				if(currentRating > rating[j]) {
					rs++;
				}
				if(currentRating < rating[j]) {
					rg++;
				}
				j++;
			}
			
			count += ls * rg + lg * rs;
		}
		
		return count;
    }
	

}
