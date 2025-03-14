import java.util.*;

public class UniqueLength3PalindromicSubsequences {

    public static void main(String[] args) {
        String s = "bbcbaba";
        UniqueLength3PalindromicSubsequences obj = new UniqueLength3PalindromicSubsequences();
        System.out.println(obj.countPalindromicSubsequence(s));
    }

    public int countPalindromicSubsequence(String s) {
        int count = 0;
        Map<Character, int[]> map = prepareMap(s);

        for(int[] minMax : map.values()) {
            count += getNoOfUniqueCharacters(s, minMax);
        }

        return count;
    }

    private int getNoOfUniqueCharacters(String s, int[] minMax) {
        int count = 0;
        if(minMax[1] != -1 && minMax[1] - minMax[0] >= 2) {
            int leftIndex = minMax[0] + 1;
            int rightIndex = minMax[1] - 1;
            Set<Character> set = new HashSet<>();
            while(leftIndex <= rightIndex) {
                if(!set.contains(s.charAt(leftIndex))) {
                    count++;
                    set.add(s.charAt(leftIndex));
                }
                leftIndex++;
            }
        }
        return count;
    }

    private Map<Character, int[]> prepareMap(String s) {
        Map<Character, int[]> map = new HashMap<>();
        for(int i = 0;i < s.length(); i++) {
            char c = s.charAt(i);
            if(!map.containsKey(c)) {
                int[] minMax = new int[2];
                minMax[0] = i;
                minMax[1] = -1;
                map.put(c, minMax);
            } else {
                int[] minMax = map.get(c);
                minMax[1] = i;
                map.put(c, minMax);
            }
        }
        return map;
    }


}
