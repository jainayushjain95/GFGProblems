package arrays;

import java.util.Arrays;
import java.util.Scanner;

public class MaximumAreaPieceCake {
    public static int mod = 1000000007;
    public static void main(String[] args) {
        int h = 5, w = 4;
        int[] horizontalCuts = {1, 2, 4};
        int[] verticalCuts = {1, 3};
        System.out.println(getMaxArea(h, w, horizontalCuts, verticalCuts));
    }

    public static int getMaxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        int maxArea = 0;
        Arrays.sort(horizontalCuts);
        Arrays.sort(verticalCuts);
        h = horizontalCuts.length;
        w = verticalCuts.length;

        int maxDiffH = horizontalCuts[0], maxDiffW = verticalCuts[0];

        int i = 0;

        while(i < h - 1) {
            if((horizontalCuts[i + 1] - horizontalCuts[i]) > maxDiffH) {
                maxDiffH = horizontalCuts[i + 1] - horizontalCuts[i];
            }
            i++;
        }
        maxDiffH = Math.max(maxDiffH, horizontalCuts[horizontalCuts.length - 1]);
        i = 0;
        while(i < w - 1) {
            if((verticalCuts[i + 1] - verticalCuts[i]) > maxDiffW) {
                maxDiffW = verticalCuts[i + 1] - verticalCuts[i];
            }
            i++;
        }
        maxDiffW = Math.max(maxDiffW, verticalCuts[verticalCuts.length - 1]);
        maxArea = maxDiffH * maxDiffW;
        if(maxArea >= mod) {
            maxArea = maxArea % mod;
        }
        return maxArea;
    }
}
