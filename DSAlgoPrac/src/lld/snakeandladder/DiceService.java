package lld.snakeandladder;

import java.util.Random;

public class DiceService {

	public static int rollDice() {
		return 1 + new Random().nextInt(6);
	}
	
}
