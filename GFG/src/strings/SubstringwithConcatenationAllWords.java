package strings;

import java.util.*;

public class SubstringwithConcatenationAllWords {

    public static void main(String[] args) {
        SubstringwithConcatenationAllWords obj = new SubstringwithConcatenationAllWords();
        String s = "barfoothefoobarman";
        String[] words = {"foo","bar"};
        List<Integer> output = obj.findSubstring(s, words);
        for(int x : output) {
            System.out.println(x);
        }
    }

    public List<Integer> findSubstring(String s, String[] words) {
        Map<String, Integer> frequencyMap = getFrequencyMap(words);
        List<Integer> result = new ArrayList<>();
        int wordLen = words[0].length();
        int numWords = words.length;

       for(int i = 0;i < wordLen; i++) {
           Map<String, Integer> windowMap = new HashMap<>();
           int left = i, right = i, count = 0;
           while(right + wordLen <= s.length()) {
               String word = s.substring(right, right + wordLen);
               right += wordLen;
               if(frequencyMap.containsKey(word)) {
                   windowMap.put(word, windowMap.getOrDefault(word, 0) + 1);
                   count++;
                   while(windowMap.get(word) > frequencyMap.get(word)) {
                       String leftWord = s.substring(left, left + wordLen);
                       windowMap.put(leftWord, windowMap.getOrDefault(leftWord, 0) - 1);
                       left = left + wordLen;
                       count--;
                   }
                   if(count == numWords) {
                       result.add(left);
                   }
               } else {
                   windowMap.clear();
                   count = 0;
                   left = right;
               }
           }
       }

        return result;
    }

    private Map<String, Integer> getFrequencyMap(String[] words) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            map.put(words[i], map.getOrDefault(words[i], 0) + 1);
        }
        return map;
    }
}
