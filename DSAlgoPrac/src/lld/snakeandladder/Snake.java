package lld.snakeandladder;

public class Snake {

	private int startPosition;
	private int endPosition;
	
	public Snake(int startPosition, int endPosition) {
		super();
		this.startPosition = startPosition;
		this.endPosition = endPosition;
	}
	
	public int getStartPosition() {
		return startPosition;
	}
	
	public int getEndPosition() {
		return endPosition;
	}
	
}
