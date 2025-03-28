package arrays;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Main obj = new Main();
        List<List<Integer>> nums = new ArrayList<>();
        nums.add(new ArrayList<>(Arrays.asList(1, 2, 3)));
        nums.add(new ArrayList<>(Arrays.asList(1, 2, 3)));
        nums.add(new ArrayList<>(Arrays.asList(1, 2, 3)));
        obj.smallestRangeTwoPointerBruteForce(nums);
    }
    public int[] smallestRange(List<List<Integer>> nums) {
        return smallestRangeTwoPointerBruteForce(nums);
    }

    private int[] smallestRangeTwoPointerBruteForce(List<List<Integer>> nums) {
        int[] output = new int[2];
        int[] pointers = new int[nums.size()];
        int range = Integer.MAX_VALUE;

        while(true) {
            int minValue = Integer.MAX_VALUE;
            int maxValue = Integer.MIN_VALUE;
            int minIndex = -1;

            for (int i = 0; i < nums.size(); i++) {
                int currentValue = nums.get(i).get(pointers[i]);
                if (currentValue < minValue) {
                    minValue = currentValue;
                    minIndex = i;
                }
                maxValue = Math.max(maxValue, currentValue);
            }

            if (maxValue - minValue < range) {
                range = maxValue - minValue;
                output[0] = minValue;
                output[1] = maxValue;
            }

            pointers[minIndex]++;

            if (pointers[minIndex] == nums.get(minIndex).size()) {
                break;
            }
        }
        return output;
    }

    private int[] getNextMinMaxIndices(List<List<Integer>> nums, int[] pointers) {
        int minIndex = 0;
        int maxIndex = 0;
        int min = nums.get(0).get(pointers[0]);
        int max = min;

        int[] output = new int[2];
        for(int i = 1;i < nums.size(); i++) {
            int element = nums.get(i).get(pointers[i]);
            if(element < min) {
                min = element;
                minIndex = i;
            }
            if(element > max) {
                max = element;
                maxIndex = i;
            }
        }
        output[0] = minIndex;
        output[1] = maxIndex;
        return output;
    }

}
