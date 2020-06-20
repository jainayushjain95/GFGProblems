package strings.geeksforgeeks;

public class LeftmostCharacterThatRepeats {

	public static void main(String[] args) {
		String input = "gtfeksoreelswveer";
		leftMostCharacterThatRepeats(input);
	}
	
	public static void leftMostCharacterThatRepeats(String input) {
		int[] p = new int[256];
		for(int i = 0;i < 256; i++) {
			p[i] = -1;
		}
		
		int leftMostIndex = Integer.MAX_VALUE;
		
		for(int i = 0;i < input.length(); i++) {
			char a  = input.charAt(i);
			if(p[a] == -1) {
				p[a] = i;
			} else {
				leftMostIndex = Math.min(p[a], leftMostIndex);
			}
		}
		System.out.println(leftMostIndex);
	}

}
