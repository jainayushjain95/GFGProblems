package searching;

public class Candies {
    public static void main(String[] args) {
        int[] candies = {1,2,6,8,6,7,3,5,2,5};
        Candies obj = new Candies();
        System.out.println(obj.maximumCandies(candies, 3));
    }

    public int maximumCandies(int[] candies, long k) {
        int max = getMax(candies), min = 1, output = 0;
        while(min <= max) {
            int mid = min + (max - min)/2;
            if(canAllocate(mid, candies, k)) {
                output = mid;
                min = mid + 1;
            } else {
                max = mid - 1;
            }
        }
        return output;
    }


    private boolean canAllocate(int candyCount, int[] candies, long k) {
        long maxNoOfChildrenCanBeServed = 0;
        for(int candy : candies) {
            maxNoOfChildrenCanBeServed += candy/candyCount;
            if(maxNoOfChildrenCanBeServed >= k)
                return true;
        }
        return maxNoOfChildrenCanBeServed >= k;
    }

    private int getMax(int[] candies) {
        int max = 0;
        for(int candy : candies) {
            max = Math.max(max, candy);
        }
        return max;
    }

}
