package sorting;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        String[] votes = {"BCA","CAB","CBA","ABC","ACB","BAC"};
        System.out.println(new Main().rankTeams(votes));
    }

    public String rankTeams(String[] votes) {
        int[][] data = new int[votes[0].length()][26];
        for(int i = 0;i < votes.length; i++) {
            for(int j = 0;j < votes[i].length(); j++) {
                data[j][votes[i].charAt(j) - 'A']++;
            }
        }

        StringBuilder rankTeams = new StringBuilder();
        Character[] candidates = new Character[votes[0].length()];

        for(int i = 0;i < votes[0].length(); i++) {
            candidates[i] = votes[0].charAt(i);
        }

        Arrays.sort(candidates, (a, b) -> {
            for(int i = 0;i < data.length; i++) {
                if(data[i][a - 'A'] != data[i][b - 'A']) {
                    return -data[i][a - 'A'] + data[i][b - 'A'];
                }
            }
            return -a + b;
        });
        for(char c : candidates) {
            rankTeams.append(c);
        }
        return rankTeams.toString();
    }

}
