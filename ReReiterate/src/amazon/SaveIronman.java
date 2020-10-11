package amazon;

import java.util.Scanner;

public class SaveIronman {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = 1;//sc.nextInt();
		while(t != 0) {
			String S = "X M!8 2!K e!l C!c R 1 F!3 7!5 1!h a!v 9 X y!s w!5 z!h Y!a k s s!t Y!p 7!A a!n o 7 V!v L!3 r!q f!t 6 N o!L m!8 u!6 p!d u p O!f T!c Q!y X!s y i I!0 S!U Y!n D!H U u o!3 H!9 O!Y e!S E K C!3 L!L l!D G!1 E H j!F q!v S!O 3!p r G 9!U 6!p 4!8 6!d 0 3 3!e n!S 0!5 D!J k i g!I K!m h!H i!G U 4 7!t s!C 1!Q G!u O B F!k x!t T!j f!M v L 5!R V!Q O!Z h!F 6 W e!j G!S Y!C F!g p P S!4 b!d B!Y W!7 l J 9!V v!A c!c K!3 e B a!H A!D N!Q f!P S u F!s K!o P!M f!D z f J!Z L!S d!L t!M e P P!0 m!X L!T p!h f 0 T!p k!i P!Z N!o H E n!r Q!n 1!e o!o r m 4!s b!f x!A F!Z 9 J v!L G!k w!G Y!8 2 8 y!D p!S q!g 1!f y V 6!4 7!v z!9 9!O O i u!T S!e K!k U!Y w v X!c v!P V!R Z!K q 5 p!E Z!k C!I O!3 I 9 4!V r!P 2!o U!8 k k I!3 T!w y!8 5!V A b y!F M!Q C!V S!A e B N!I l!E N!m h!y B G q!E 1!K Z!P S!m x k t!I R!s Q!N P!D r V Q!7 F!q j!S B!F 3 r z!L a!6 1!b b!P h 1 l!n f!P h!I 5!D B J H!R v!g l!W B!G l 9 0!9 T!C s!5 t!S j Y T!a D!E 3!z u!V h I 0!L c!B r!S r!D E o L!Q u!C T!d I!t j q 6!7 L!W B!2 T!w 3 2 E!d j!E 7!l P!9 4 S n!y 0!6 R!j 2!y v .................";
			S = S.toLowerCase();
			boolean isPalindrome = isPalindrome(S, 0, S.length() - 1);
			System.out.println((isPalindrome) ? "YES" : "NO");
			t--;
		}
	}

	public static boolean isPalindrome(String S, int beginIndex, int endIndex) {
		if(beginIndex >= endIndex) {
			return true;
		}
		if(!isAlphaNumeric(S.charAt(beginIndex))) {
			return isPalindrome(S, beginIndex + 1, endIndex);
		}
		if(!isAlphaNumeric(S.charAt(endIndex))) {
			return isPalindrome(S, beginIndex, endIndex - 1);
		}
		if(S.charAt(beginIndex) != S.charAt(endIndex)) {
			return false;
		}
		return isPalindrome(S, beginIndex + 1, endIndex - 1);
	}

	public static boolean isAlphaNumeric(char c) {
		int cc = (int) c;
		return (cc >= 48 && cc <= 57) || (cc >= 97 && cc <= 122) || (cc >= 65 && cc <= 90);
	}
}
