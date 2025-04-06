package dynamic.programming.problems;
import java.util.*;

public class DPDP {
    int[][] dpLCS1;
    Set<String> set;
    boolean[] dp;

    int[] dpWB;
    List<String> output;
    int[] dpArrayCanPartition;
    boolean[][] dp2d_partition;
    int[][] dp_mp2;

    public static void main(String[] args) {
        DPDP obj = new DPDP();
//        System.out.println(obj.longestCommonSubsequence("abcde", "ace"));

        char c = '3';
        int a = c - '0';
        int[] nums = {7,1,5,3,6,4};
        List<String> wordDict = new ArrayList<>();
        wordDict.add("cat");
        wordDict.add("cats");
        wordDict.add("and");
        wordDict.add("sand");
        wordDict.add("dog");

        System.out.println(obj.maxProfit(nums));
    }

    public int maxProfit(int[] prices) {
        return maxProfitTabulation(prices);
    }

    private int maxProfitTabulation(int[] prices) {
        dp_mp2 = new int[prices.length + 1][2];
        dp_mp2[prices.length][0] = 0;
        dp_mp2[prices.length][1] = 0;
        for(int i = prices.length - 1; i >= 0; i--) {
            for(int buy = 0; buy <= 1; buy++) {
                int profit = 0;
                if(buy == 1) {
                    int profitIfIBuy = -1 * prices[i] + dp_mp2[i + 1][0];
                    int profitIfIDontBuy = dp_mp2[i + 1][1];
                    profit = Math.max(profitIfIBuy, profitIfIDontBuy);
                } else {
                    int profitIfISell = prices[i] + dp_mp2[i + 1][1];
                    int profitIfIDontSell = dp_mp2[i + 1][0];
                    profit = Math.max(profitIfISell, profitIfIDontSell);
                }
                dp_mp2[i][buy] = profit;
            }
        }
        return dp_mp2[0][1];
    }


    private int getSum(int[] nums) {
        int sum = 0;
        for(int x : nums) {
            sum += x;
        }
        StringBuilder asd = new StringBuilder();
//        asd.rev

        return sum;
    }

    public boolean canPartition(int[] nums) {
        int sum = getSum(nums);
        if(sum % 2 != 0) {
            return false;
        }
        return canPartitionBottomUpDP(nums, sum/2);
    }

    private boolean canPartitionBottomUpDP(int[] nums, int targetSum) {
        dp2d_partition = new boolean[nums.length][targetSum + 1];
        dp2d_partition[0][0] = true;
        for(int i = 0; i < nums.length; i++) {
            for(int j = 1; j <= targetSum; j++) {
                if(i == 0) {
                    dp2d_partition[i][j] = nums[i] == j;
                } else if(nums[i] > j) {
                    dp2d_partition[i][j] = dp2d_partition[i - 1][j];
                } else {
                    dp2d_partition[i][j] = dp2d_partition[i - 1][j] || dp2d_partition[i - 1][j - nums[i]];
                }
            }
        }
        return dp2d_partition[nums.length - 1][targetSum];
    }

    private boolean canPartitionRecursive(int[] nums, int index, int partitionASum, int partitionBSum) {
        System.out.println(index + " " + partitionASum + " " + partitionBSum);
        if(index == nums.length) {
            return partitionASum == partitionBSum;
        }
//        if(dpArrayCanPartition[index] > 0) {
//            return dpArrayCanPartition[index] == 1;
//        }
        boolean currElementInPartitionA = canPartitionRecursive(nums, index + 1, partitionASum + nums[index], partitionBSum);
        boolean currElementInPartitionB = canPartitionRecursive(nums, index + 1, partitionASum, partitionBSum + nums[index]);
//        if(currElementInPartitionA || currElementInPartitionB) {
//            dpArrayCanPartition[index] = 1;
//        } else {
//            dpArrayCanPartition[index] = 2;
//        }
        return currElementInPartitionA || currElementInPartitionB;
    }

    public List<String> wordBreak(String s, List<String> wordDict) {
        set = new HashSet<>(wordDict);
        output = new ArrayList<>();
        if(set.contains(s)) {
            output.add(s);
        } else {
            wordBreak1(s, 0, new StringBuilder());
        }
        return output;
    }

    private void wordBreak1(String s, int index, StringBuilder sb) {

        if(index == s.length()) {
            output.add(sb.toString().trim());
        }

        int originalLength = sb.length();

        for(int i = index; i < s.length(); i++) {
            String substring = s.substring(index, i + 1);
            if(set.contains(substring)) {
                sb.append(substring);
                sb.append(" ");
                wordBreak1(s, i + 1, sb);
                sb.setLength(originalLength);
            }
        }
    }

//    public boolean wordBreak(String s, List<String> wordDict) {
//        set = new HashSet<>(wordDict);
//        if(set.contains(s)) {
//            return true;
//        }
//        dpWB = new int[s.length()];
//        return backtrack2(s, 0);
//    }

    private boolean backtrack2(String s, int index) {
        if(index == s.length()) {
            return true;
        }
        for(String word : set) {
            if(
                    word.length() + index <= s.length()
                            && s.substring(index, index + word.length()).equals(word)
                            && backtrack2(s, index + word.length())
            ) {
                return true;
            }
        }
        return false;
    }

    private boolean tabulation(String s) {
        boolean possible = false;
        boolean[][] dp = new boolean[s.length() + 1][s.length() + 1];
        for(int i = 1; i <= s.length(); i++) {
            dp[i][i] = set.contains(s.charAt(i - 1) + "");
        }

        for(int len = 2; len <= s.length(); len++) {
            for(int row = 1; row <= s.length() - len + 1; row++) {
                int col = row + 1;
                System.out.println(row + ", " + col);
//                if(set.contains(s.substring(row - 1, col))) {
//                    dp[row][col] = true;
//                } else {
//                    for(int i = row; i <= col; i++) {
//
//                    }
//                }
            }
        }

        return false;
    }

    private boolean backtrack(String s, int index) {
        if(index == s.length()) {
            return true;
        }
        if(dpWB[index] > 0) {
            return dpWB[index] == 1;
        }
        dpWB[index] = 2;
        for(int i = index;i < s.length(); i++) {
            String sub = s.substring(index, i + 1);
            if(set.contains(sub) && backtrack(s, i + 1)) {
                dpWB[index] = 1;
                break;
            }
        }
        return dpWB[index] == 1;
    }

    private Set<String> getWordsSet(List<String> wordDict) {
        Set<String> set = new HashSet<>();
        for(String word : wordDict) {
            set.add(word);
        }
        return set;
    }

    public int maxProduct(int[] nums) {
        int[] prefixProds = prefixProds(nums);
        int maxProd = prefixProds[nums.length - 1];

        for(int i = 0;i < nums.length; i++) {
            if(nums[i] < 0) {
                int prefix = (i == 0) ? 0 : prefixProds[i - 1];
                int denominator = prefixProds[i] == 0 ? 1 : prefixProds[i];
                int suffix = prefixProds[nums.length - 1] / denominator;
                maxProd = Math.max(maxProd, Math.max(prefix, suffix));
            }
        }

        return maxProd ;
    }

    private int[] prefixProds(int[] nums) {
        int[] prefixProds = new int[nums.length];
        prefixProds[0] = nums[0];

        for(int i = 1;i < nums.length; i++) {
            prefixProds[i] = prefixProds[i - 1] * nums[i];
        }

        return prefixProds;
    }

    public int longestCommonSubsequence(String text1, String text2) {
        return longestCommonSubsequenceDPSpaceOptimized(text1, text2);
    }

    private int longestCommonSubsequenceDPSpaceOptimized(String text1, String text2) {
        int[] curr = new int[text2.length() + 1];
        int[] prev = new int[text2.length() + 1];
        for (int i = 1; i <= text1.length(); i++) {
            for (int j = 1; j <= text2.length(); j++) {
                if(text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    curr[j] = 1 + prev[j - 1];
                } else {
                    curr[j] = Math.max(prev[j], curr[j - 1]);
                }
            }
            int[] temp = curr;
            curr = prev;
            prev = temp;
        }

        return curr[text2.length()];
    }

    private int longestCommonSubsequenceDP(String text1, String text2) {
        dpLCS1 = new int[text1.length()][text2.length()];
        for(int i = 0; i < text1.length(); i++) {
            for(int j = 0; j < text2.length(); j++) {
                if(text1.charAt(i) == text2.charAt(j)) {
                    if(i > 0 && j > 0) {
                        dpLCS1[i][j] = 1 + dpLCS1[i - 1][j - 1];
                    } else {
                        dpLCS1[i][j] = 1;
                    }
                } else {
                    int a = (i > 0) ? dpLCS1[i - 1][j] : 0;
                    int b = (j > 0) ? dpLCS1[i][j - 1] : 0;
                    dpLCS1[i][j] = Math.max(a, b);
                }
            }
        }
        return dpLCS1[text1.length() - 1][text2.length() - 1];
    }


    public String longestPalindrome(String s) {
        int n = s.length();
        boolean[][] dpArray = new boolean[n][n];
        int leftIndex = 0, rightIndex = 0, maxLength = -1;

        for (int i = 0; i < n; i++) {
            dpArray[i][i] = true;
        }

        for (int i = 0; i < n - 1; i++) {
            if(s.charAt(i) == s.charAt(i + 1)) {
                dpArray[i][i + 1] = s.charAt(i) == s.charAt(i + 1);
                leftIndex = i;
                rightIndex = i + 1;
            }
        }

        int colsLeft = n - 2, colBase = 2;
        while (colsLeft > 0) {
            int row = 0;
            for(int col = colBase; col < n; col++) {
                if(s.charAt(row) == s.charAt(col) && dpArray[row + 1][col - 1]) {
                    dpArray[row][col] = true;
                    leftIndex = row;
                    rightIndex = col;
                }
                row++;
            }
            colBase++;
            colsLeft--;
        }

        return s.substring(leftIndex, rightIndex + 1);
    }


}
