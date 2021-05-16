package lc.strings;

import java.util.ArrayList;
import java.util.List;

public class IntegerToEnglishWords {

	
	public static void main(String[] args) {
		System.out.println((new IntegerToEnglishWords()).numberToWords(1000000));
	}
	
	public String numberToWords(int num) {
		String numStr = num + "";
		return numberToWordsSolve(numStr);
    }
	
	public static String numberToWordsSolve(String numStr) {
		String solution = "";
		int length = numStr.length();
		if(numStr.equals("0")) {
			solution = "Zero";
		} else if(length <= 3) {
			solution = getThreeWordsString(numStr);
		} else {
			int i = length - 1;
			List<String> words = new ArrayList<String>();
			for(;i >= 2; i = i - 3) {
				String word = numStr.substring(i - 2, i + 1);
				words.add(getThreeWordsString(word));
			}
			int residualLength = length % 3;
			if(residualLength > 0) {
				words.add(getThreeWordsString(numStr.substring(0, residualLength)));
			}
			StringBuilder stringBuilder = new StringBuilder();
			int count = words.size();
			for(int k = words.size() - 1; k >= 0; k--) {
				String word = words.get(k);
				stringBuilder.append(word);
				if(word.length() > 0 && k > 0) {
					stringBuilder.append(" ");
					stringBuilder.append(getIntermediateString(count--));
					stringBuilder.append(" ");
				}
			}
			solution = stringBuilder.toString();
		}
		return solution.trim();
	}
	
	public static String getIntermediateString(int count) {
		String solution = "";
		if(count == 2) {
			solution = "Thousand";
		} else if(count == 3) {
			solution = "Million";
		} else if(count == 4) {
			solution = "Billion";
		}
		return solution;
	}

	public static String getThreeWordsString(String numStr) {
		int num = Integer.parseInt(numStr);
		int c = num % 10;
		int b = (num/10) % 10;
		int a = (num/100) % 10;
		
		StringBuilder stringBuilder = new StringBuilder();
		
		if(a > 0) {
			stringBuilder.append(getSingleWord(a));
			stringBuilder.append(" Hundred ");
		}
		if(b > 0) {
			if(b == 1) {
				stringBuilder.append(getOneDoubleWord(b*10 + c));
				stringBuilder.append(" ");
			} else {
				stringBuilder.append(getDoubleWord(b));
				stringBuilder.append(" ");
				if(c > 0) {
					stringBuilder.append(getSingleWord(c));
					stringBuilder.append(" ");
				}
			}
		} else if(c > 0) {
			stringBuilder.append(getSingleWord(c));
			stringBuilder.append(" ");
		}
		return stringBuilder.toString().trim();
	}
	
	
	public static String getSingleWord(int n) {
		String word = "";
		switch(n) {
		case 0: 
			word = "Zero";
			break;
		case 1: 
			word = "One";
			break;
		case 2: 
			word = "Two";
			break;
		case 3: 
			word = "Three";
			break;
		case 4: 
			word = "Four";
			break;
		case 5: 
			word = "Five";
			break;
		case 6: 
			word = "Six";
			break;
		case 7: 
			word = "Seven";
			break;
		case 8: 
			word = "Eight";
			break;
		case 9: 
			word = "Nine";
			break;
		}
		return word;
	}
	
	public static String getDoubleWord(int n) {
		String word = "";
		switch(n) {
		case 2: 
			word = "Twenty";
			break;
		case 3: 
			word = "Thirty";
			break;
		case 4: 
			word = "Forty";
			break;
		case 5: 
			word = "Fifty";
			break;
		case 6: 
			word = "Sixty";
			break;
		case 7: 
			word = "Seventy";
			break;
		case 8: 
			word = "Eighty";
			break;
		case 9: 
			word = "Ninety";
			break;
		}
		return word;
	}
	
	public static String getOneDoubleWord(int n) {
		String word = "";
		switch(n) {
		case 10: 
			word = "Ten";
			break;
		case 11: 
			word = "Eleven";
			break;
		case 12: 
			word = "Twelve";
			break;
		case 13: 
			word = "Thirteen";
			break;
		case 14: 
			word = "Fourteen";
			break;
		case 15: 
			word = "Fifteen";
			break;
		case 16: 
			word = "Sixteen";
			break;
		case 17: 
			word = "Seventeen";
			break;
		case 18: 
			word = "Eighteen";
			break;
		case 19: 
			word = "Nineteen";
			break;
		}
		return word;
	}
}
