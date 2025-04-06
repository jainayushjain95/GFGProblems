package searching;

public class BS {
    public static void main(String[] args) {
        BS b = new BS();
        int[] nums = {2,4,8,2};
        System.out.println(b.minimumSize(nums, 4));
    }

    public int minimumSize(int[] nums, int maxOperations) {
        int end = getMax(nums), start = 1;
        int minPenalty = Integer.MAX_VALUE;
        while(start <= end) {
            int penalty = getMid(start, end);
            if(isPossible(nums, maxOperations, penalty)) {
                minPenalty = penalty;
                end = penalty - 1;
            } else {
                start = penalty + 1;
            }
        }
        return minPenalty;
    }

    private boolean isPossible(int[] nums, int maxOperations, int penalty) {
        boolean isPossible = true;
        for(int x : nums) {
            if(x <= penalty) {
                continue;
            }
            maxOperations -= x / penalty;
            if(x % penalty == 0) {
                maxOperations++;
            }
            if(maxOperations < 0) {
                isPossible = false;
                break;
            }
        }
        return isPossible;
    }

    private int getMid(int start, int end) {
        return start + (end - start)/2;
    }

    private int getMax(int[] nums) {
        int max = 0;
        for(int x : nums) {
            max = Math.max(max, x);
        }
        return max;
    }
}
