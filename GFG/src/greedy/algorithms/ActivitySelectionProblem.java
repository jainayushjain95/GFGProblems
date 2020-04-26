package greedy.algorithms;

public class ActivitySelectionProblem {

	static class ActivityTimesPair {
		int startTime;
		int endTime;

		public ActivityTimesPair(int startTime, int endTime) {
			this.startTime = startTime;
			this.endTime = endTime;
		}
	} 


	public static void main(String[] args) {
		ActivityTimesPair[] activityTimesPairs = new ActivityTimesPair[6];
		activityTimesPairs[0] = new ActivityTimesPair(1, 2);
		activityTimesPairs[1] = new ActivityTimesPair(3, 4);
		activityTimesPairs[2] = new ActivityTimesPair(0, 6);
		activityTimesPairs[3] = new ActivityTimesPair(5, 7);
		activityTimesPairs[4] = new ActivityTimesPair(5, 9);
		activityTimesPairs[5] = new ActivityTimesPair(8, 9);
		printMaxNoOfActivitiesCanBeCompleted(activityTimesPairs);
	}

	/*
	 * activityTimesPairs have to be sorted in increasing order of end times
	 * TC-> O(NlogN) + O(N) = O(NlogN) ---> If not sorted
	 * otherwise, O(N) (below code assumes it is sorted)
	 */
	public static void printMaxNoOfActivitiesCanBeCompleted(ActivityTimesPair[] activityTimesPairs) {
		ActivityTimesPair lastPickedActivity = activityTimesPairs[0];
		System.out.println(lastPickedActivity.startTime + ", " + lastPickedActivity.endTime);

		for(int i = 1;i < activityTimesPairs.length; i++) {
			ActivityTimesPair currentPickedActivity = activityTimesPairs[i];
			if(!isTwoActivitiesOverlapping(lastPickedActivity, currentPickedActivity)) {
				System.out.println(currentPickedActivity.startTime + ", " + currentPickedActivity.endTime);
				lastPickedActivity = currentPickedActivity;
			}
		}

	}

	public static boolean isTwoActivitiesOverlapping(ActivityTimesPair activityTimesPairOne, ActivityTimesPair activityTimesPairTwo) {
		return activityTimesPairOne.endTime > activityTimesPairTwo.startTime;
	}

}
