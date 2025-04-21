package design;

import java.util.*;

class Cell {
    int row;
    int col;
    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public String toString() {
        return this.row + "," + this.col;
    }
}

class SnakeGame {

    private final int width;
    private final int height;
    private final int[][] food;
    LinkedList<Cell> snake;
    private int score;
    private int foodIndex;
    private final Map<String, int[]> directionMap;
    private final Set<String> snakeCells;

    public SnakeGame(int width, int height, int[][] food) {
        this.width = width;
        this.height = height;
        this.food = food;
        this.snake = new LinkedList<>();
        Cell cell = new Cell(0, 0);
        this.snake.add(cell);
        this.score = 0;
        this.foodIndex = 0;
        this.directionMap = new HashMap<>();
        this.directionMap.put("U", new int[] {-1, 0});
        this.directionMap.put("D", new int[] {1, 0});
        this.directionMap.put("L", new int[] {0, -1});
        this.directionMap.put("R", new int[] {0, 1});
        this.snakeCells = new HashSet<>();
        snakeCells.add(cell.toString());
    }

    public int move(String direction) {
        int currentScore = -1;
        int[] dir = directionMap.get(direction);
        Cell currentHead = snake.peekLast();
        int newRowIndex = currentHead.row + dir[0];
        int newColIndex = currentHead.col + dir[1];
        if(!doesCollide(newRowIndex, newColIndex)) {
            Cell tail = snake.poll();
            snakeCells.remove(tail.toString());
            Cell newHead = new Cell(newRowIndex, newColIndex);
            if(!doesItSelfCollide(newHead)) {
                snake.add(newHead);
                snakeCells.add(newHead.toString());
                if(foodExists(newHead)) {
                    foodIndex++;
                    updateSnake(tail);
                    score++;
                }
                currentScore = score;
            }
        }
        return currentScore;
    }

    private void updateSnake(Cell tail) {
        snake.addFirst(tail);
        snakeCells.add(tail.toString());
    }

    private boolean foodExists(Cell newHead) {
        return foodIndex < food.length && food[foodIndex][0] == newHead.row && food[foodIndex][1] == newHead.col;
    }

    private boolean doesItSelfCollide(Cell newHead) {
        return snakeCells.contains(newHead.toString());
    }

    private boolean doesCollide(int row, int col) {
        return row < 0
                || col < 0
                || row >= height
                || col >= width;
    }
}
public class SnakeGameDesign {
}
