package strings;

import java.util.*;

class DataPoint {
    public String username;
    public String website;
    public int timestamp;

    public DataPoint(String username, String website, int timestamp) {
        this.username = username;
        this.website = website;
        this.timestamp = timestamp;
    }

}

class Pair_KM {
    int row;
    int col;

    public Pair_KM(int row, int col) {
        this.row = row;
        this.col = col;
    }
}



public class Main {
    int[][] dirs_km = {
            {-2, -1}, {-2, 1}, {-1, 2}, {1, 2},
            {2, 1}, {2, -1}, {1, -2}, {-1, -2}
    };

    public static void main(String[] args) {
        Main obj = new Main();
        String[] username = {"ua","ua","ua","ub","ub","ub"};
        int[] timestamp = {1,2,3,4,5,6};
        String[] website = {"a","b","c","a","b","a"};
        System.out.println(obj.minKnightMoves(5, 5));
    }

    public int minKnightMoves(int x, int y) {
        if(x == 0 && y == 0)
            return 0;

        x = Math.abs(x);
        y = Math.abs(y);

        int moves = 0;
        Set<String> visited = new HashSet<>();
        Queue<Pair_KM> queue = new LinkedList<>();
        queue.add(new Pair_KM(0, 0));

        while(!queue.isEmpty()) {
            moves++;
            int queueSize = queue.size();
            for(int i = 0;i < queueSize; i++) {
                Pair_KM pair = queue.poll();
                for(int[] direction : dirs_km) {
                    int row = pair.row + direction[0];
                    int col = pair.col + direction[1];
                    String pos = row + "," + col;
                    if(row == x && col == y) {
                        return moves;
                    }
                    if (row >= -1 && col >= -1 && !visited.contains(pos)) {
                        visited.add(pos);
                        queue.add(new Pair_KM(row, col));
                    }
                }
            }
        }

        return moves;
    }

    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        List<DataPoint> dataPoints = getSortedListOfDataPoints(username, timestamp, website);
        Map<String, List<String>> map = getMap(dataPoints);
        Map<String, Integer> patternMap = new HashMap<>();
        int maxFreq = 0;
        String maxPattern = "";

        for(String user : map.keySet()) {
            List<String> websites = map.get(user);
            for(int i = 0;i < websites.size(); i++) {
                for(int j = i + 1;j < websites.size(); j++) {
                    for(int k = j + 1;k < websites.size(); k++) {
                        String pattern = websites.get(i) + "," + websites.get(j) + "," + websites.get(k);
                        int freq = 1  + patternMap.getOrDefault(pattern, 0);
                        patternMap.put(pattern, freq);
                        if(freq > maxFreq) {
                            maxFreq = freq;
                            maxPattern =  pattern;
                        } else if(freq == maxFreq && pattern.compareTo(maxPattern) < 0) {
                            maxPattern = pattern;
                        }
                    }
                }
            }
        }

        return Arrays.asList(maxPattern.split(","));
    }

    private Map<String, List<String>> getMap(List<DataPoint> dataPoints) {
        Map<String, List<String>> map = new HashMap<>();
        for(DataPoint dataPoint : dataPoints) {
            if(!map.containsKey(dataPoint.username)) {
                map.put(dataPoint.username, new ArrayList<>());
            }
            map.get(dataPoint.username).add(dataPoint.website);
        }
        return map;
    }

    private List<DataPoint> getSortedListOfDataPoints(String[] username, int[] timestamp, String[] website) {
        List<DataPoint> dataPoints = new ArrayList<>();
        for(int i = 0;i < username.length; i++) {
            dataPoints.add(new DataPoint(username[i], website[i], timestamp[i]));
        }
        Collections.sort(dataPoints, Comparator.comparingInt(dataPoint -> dataPoint.timestamp));
        return dataPoints;
    }

    private String solve1(String s) {
        int[] frequenciesArray = new int[26];
        int maxFrequency = 0, indexWithFrequency = 0, n = s.length();

        for(int i = 0;i < n; i++) {
            int index = s.charAt(i) - 'a';
            frequenciesArray[index]++;
            if(frequenciesArray[index] > maxFrequency) {
                maxFrequency = frequenciesArray[index];
                indexWithFrequency = index;
            }
        }

        if(maxFrequency > (n + 1)/2) {
            return "";
        }

        char[] solutionArray = new char[n];
        int index = 0;

        while(frequenciesArray[indexWithFrequency] != 0) {
            solutionArray[index] = (char) (indexWithFrequency + 'a');
            frequenciesArray[indexWithFrequency]--;
            index += 2;
        }

        int i = 0;
        while(i < 26) {
            while(frequenciesArray[i] != 0) {
                if(index >= n) {
                    index = 1;
                }
                solutionArray[index] = (char) (i + 'a');
                frequenciesArray[i]--;
                index += 2;
            }
            i++;
        }


        return new String(solutionArray);
    }

    public int numberOfSubstrings(String s) {
        long n = s.length();
        long total = (n * (n + 1))/2, count = 0;
        int i = 0, j = 0;
        int[] countArray = new int[3];
        while(j < n) {
            countArray[s.charAt(j) - 'a']++;
            while(i <= j && countArray[0] >= 1 && countArray[1] >= 1 && countArray[2] >= 1) {
                countArray[s.charAt(i) - 'a']--;
                i++;
            }
            count += j - i + 1;
            j++;
        }
        return (int)(total - count);
    }
}
