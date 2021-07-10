package lc.stacks;

import java.util.Stack;

import javax.swing.text.StyleContext.SmallAttributeSet;

class Pair {
    int price;
    int freq;
    
    public Pair(int price, int freq) {
        this.price = price;
        this.freq = freq;
    }
}

class StockSpanner {

    Stack<Pair> stack;
    
    public StockSpanner() {
        stack = new Stack<Pair>();
    }
    
    public int next(int price) {
        if(stack.isEmpty()) {
            stack.push(new Pair(price, 1));
            return 1;
        } 
        
        if(stack.peek().price > price) {
            stack.push(new Pair(price, 1));
            return 1;
        }
        
        int freq = 1;
        while(!stack.isEmpty() && stack.peek().price <= price) {
            Pair pair = stack.pop();
            freq = freq + pair.freq;
        }
        stack.push(new Pair(price, freq));
        return freq;
    }
}

public class Probs2 {

	int beginIndex = 0;

	public static void main(String[] args) {
		System.out.println((new Probs2().decodeString("3[a]2[bc]")));
	}
	
	public String decodeString(String s) {
		StringBuilder result = new StringBuilder();
		while(beginIndex < s.length() && s.charAt(beginIndex) != ']') {
			if(Character.isDigit(s.charAt(beginIndex))) {
				int freq = 0;
				while(beginIndex < s.length() && Character.isDigit(s.charAt(beginIndex))) {
					freq = freq * 10 + (s.charAt(beginIndex++) - '0');
				}
				beginIndex++;
				String smaller = decodeString(s);
				while(freq != 0) {
					result.append(smaller);
					freq--;
				}
				beginIndex++;
			} else {
				result.append(s.charAt(beginIndex++));
			}
		}
        return result.toString();
    }
	

	public boolean isDigit(String s) {
		int a = s.charAt(0) - '0';
		return s.length() == 1 && a >= 0 && a <= 9 ;
	}
    
  

}
