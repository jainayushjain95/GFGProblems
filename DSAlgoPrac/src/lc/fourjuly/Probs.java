package lc.fourjuly;

import java.util.PriorityQueue;

public class Probs {

	public static void main(String[] args) {
		System.out.println(new Probs().countGoodNumbers(4));
	}
	
	
	public int countGoodNumbers(long n) {
        long evens = (n >> 1) + n % 2;
        long odds = n >> 1;
        
        long count = getPow(5, evens);
        count = count * getPow(4, odds);
        if(count >= 1000000007) {
                count = count % 1000000007;
            }
        return (int)count;
    }
    
    public long getPow(long base, long pow) {
        if(base == 0) {
            return 0;
        }
        if(pow == 0) {
            return 1;
        }
        
        if(pow % 2 == 0) {
            long powCurr = getPow(base, pow >> 1);
            long sol = powCurr * powCurr;
            if(sol >= 1000000007) {
                sol = sol % 1000000007;
            }
            return sol;
        } else {
            long powCurr = getPow(base, pow >> 1);
            long sol = powCurr * powCurr * base;
            if(sol >= 1000000007) {
                sol = sol % 1000000007;
            }
            return sol;
        }
    }

}
