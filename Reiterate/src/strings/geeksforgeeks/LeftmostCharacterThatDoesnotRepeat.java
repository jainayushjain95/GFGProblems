package strings.geeksforgeeks;

public class LeftmostCharacterThatDoesnotRepeat {
	public static void main(String[] args) {
		String input = "geeksforgeeks";
		leftMostCharacterThatDoesNotRepeats(input);
	}

	public static void leftMostCharacterThatDoesNotRepeats(String input) {
		int[] p = new int[256];
		for(int i = 0;i < 256; i++) {
			p[i] = -1;
		}

		for(int i = 0;i < input.length(); i++) {
			char a = input.charAt(i);
			if(p[a] == -1) {
				p[a] = i;
			} else {
				p[a] = -2;
			}
		}

		for(int i = 0;i < 256; i++) {
			if(p[i] > -1) {
				System.out.println(p[i]);
				break;
			}
		}

	}
}
