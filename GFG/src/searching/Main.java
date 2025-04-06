package searching;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Main obj = new Main();
        long ans = obj.validSubstringCount("abcabc", "abc");
        System.out.println(ans);
    }

    public long validSubstringCount(String word1, String word2) {
        return validSubstringCountSLidingWindow(word1, word2);
    }

    private static long validSubstringCountSLidingWindow(String word1, String word2) {
        int[] alpha1 = new int[26];
        int[] alpha2 = getAlphaArray(word2);
        int m = word1.length();

        int left = 0, right = 0;
        long count = 0;
        while(right < m) {
            alpha1[word1.charAt(right) - 'a']++;
            if(needToMoveAhead(alpha1, alpha2)) {
                count++;
            }

            while(left <= right && !needToMoveAhead(alpha1, alpha2)) {
                alpha1[word1.charAt(left) - 'a']--;
                left++;
            }
            right++;
        }

        return (long) (m * (m + 1)/2) - count;
    }

    private static long validSubstringCountBruteForce(String word1, String word2) {
        int[] alpha2 = getAlphaArray(word2);
        int[] alpha1 = new int[26];

        long count = 0;
        for(int i = 0;i < word1.length(); i++) {
            for(int j = i; j < word1.length(); j++) {
                alpha1[word1.charAt(j) - 'a']++;
                if(!needToMoveAhead(alpha1, alpha2)) {
                    count += word1.length() - j;
                    break;
                }
            }
            alpha1[word1.charAt(i) - 'a']--;
        }
        return count;
    }

    private static boolean needToMoveAhead(int[] alpha1, int[] alpha2) {
        boolean flag = false;
        for(int i = 0;i < 26; i++) {
            if(alpha2[i] > alpha1[i]) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    private static int[] getAlphaArray(String word) {
        int[] alpha = new int[26];
        for(int i = 0;i < word.length(); i++) {
            alpha[word.charAt(i) - 'a']++;
        }
        return alpha;
    }
}
