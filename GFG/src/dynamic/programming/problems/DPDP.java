package dynamic.programming.problems;

public class DPDP {
    int[][] dpLCS1;

    public static void main(String[] args) {
        DPDP obj = new DPDP();
//        System.out.println(obj.longestCommonSubsequence("abcde", "ace"));

        char c = '3';
        int a = c - '0';
        int[] nums = {0,2};
        System.out.println(obj.maxProduct(nums));
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
