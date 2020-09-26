package amazon;

import java.util.HashMap;

public class MaximumMoney {

	public static void main(String[] args) {
		String[] list = {"Geeks", "For", "Geeks"};
		System.out.println(countWords(list, list.length));
	}


	public static int countWords(String list[], int n) {
        HashMap<String, Integer> hm = new HashMap<String, Integer>();
        for(int i = 0;i < n; i++) {
            String curr = list[i];
            if(hm.containsKey(curr)) {
                hm.put(curr, 1 + hm.get(curr));
            } else {
                hm.put(curr, 1);
            }
        }
        
        int count = 0;
        for(String curr : hm.keySet()) {
            if(hm.get(curr) == 2) {
                count++;
            }
        }
        return count; 
    }

}
