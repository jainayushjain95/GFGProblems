package graph;

import java.util.*;
class PairMat {
    int i;
    int j;

    public PairMat(int i, int j) {
        this.i = i;
        this.j = j;
    }
}

class PairRO {
    int i;
    int j;
    public PairRO(int i, int j) {
        this.i = i;
        this.j = j;
    }
}

class Pair {
    int verticesCount;
    int edgesCount;

    public Pair(int verticesCount, int edgesCount) {
        this.verticesCount = verticesCount;
        this.edgesCount = edgesCount;
    }
}


class PairMaze {
    int node;
    int parent;

    public  PairMaze(int node, int parent) {
        this.node = node;
        this.parent = parent;
    }
}

public class Main {
    boolean[] detonated;
    boolean[][] visited2;
    int[][] directions = {
            {-1, 0},
            {0, -1}, {0, 1},
            {1, 0}
    };
    int[] colors;
    int NO_COLOR = 0;
    int COLOR_A = 1;
    int COLOR_B = 2;


    boolean[][] visitedFF;


    List<Integer>[] adjacencyList;
    List<Integer>[] adjacencyLists;
    boolean[] visited;


    boolean[][] cantBeSurrounded;
    int[][] dirsSR = {
            {0, 1}, {0, -1}, {1, 0}, {-1, 0}
    };

    List<Integer>[] adjacencyListMaze;
    boolean[] visitedMaze;
    boolean[][] visitedNOD;
    Set<String> islandsNOD;

    public static void main(String[] args) {
        int[][] data = {
                {3,4,6},
                {3,6},
                {3,6},
                {0,1,2,5},
                {0,7,8},
                {3},
                {0,1,2,7},
                {4,6},
                {4},
                {}
        };

        char[][] board = {{'X','X','X','X'},{'X','O','O','X'},{'X','X','O','X'},{'X','O','X','X'}};

        Main obj = new Main();
        System.out.println(obj.isBipartite(data));
    }

    public boolean isBipartite(int[][] graph) {
        prepareAdjacencyLists(graph);
        colors = new int[graph.length];
        return isBipartiteDFS(graph);
    }

    private boolean isBipartiteDFS(int[][] graph) {
        for(int i = 0;i < graph.length; i++) {
            if(colors[i] == NO_COLOR) {
                if(!isBipartiteDFSHelper(i, COLOR_A)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isBipartiteDFSHelper(int vertex, int color) {
        colors[vertex] = color;
        for(int neighbour : adjacencyLists[vertex]) {
            if(colors[neighbour] == NO_COLOR) {
                 isBipartiteDFSHelper(neighbour, (colors[vertex] == COLOR_A) ? COLOR_B : COLOR_A);
            } else if(colors[neighbour] == colors[vertex]) {
                return false;
            }
        }
        return true;
    }

    private void prepareAdjacencyLists(int[][] graph) {
        adjacencyLists = new List[graph.length];
        for(int i = 0;i < graph.length; i++) {
            adjacencyLists[i] = new ArrayList<>();
            for(int neighbour : graph[i]) {
                adjacencyLists[i].add(neighbour);
            }
        }
    }

    private boolean isBipartiteBFS(int[][] graph) {
        for(int i = 0;i < graph.length;i++) {
            if(colors[i] == NO_COLOR) {
                Queue<Integer> queue = new LinkedList<>();
                queue.add(i);
                colors[i] = COLOR_A;
                while(!queue.isEmpty()) {
                    int queueSize = queue.size();
                    while(queueSize > 0) {
                        int vertex = queue.poll();
                        for(int neighbour : adjacencyLists[vertex]) {
                            if(colors[neighbour] == NO_COLOR) {
                                colors[neighbour] = (colors[vertex] == COLOR_A) ? COLOR_B : COLOR_A;
                                queue.add(neighbour);
                            } else if(colors[neighbour] == colors[vertex]) {
                                return false;
                            }
                        }
                        queueSize--;
                    }
                }
            }
        }

        return true;
    }

    public int numDistinctIslands(int[][] grid) {
        visitedNOD = new boolean[grid.length][grid[0].length];
        islandsNOD = new HashSet<>();
        for(int i = 0;i < grid.length; i++) {
            for(int j = 0;j < grid[i].length; j++) {
                if(!visitedNOD[i][j] && grid[i][j] == 1) {
                    List<Integer> island = new ArrayList<>();
                    dfsNOD(grid, i, j, island);
                    islandsNOD.add(getIsland(island));
                }
            }
        }
        return islandsNOD.size();
    }

    private void dfsNOD(int[][] grid, int row, int col, List<Integer> island) {
        visitedNOD[row][col] = true;
        island.add(row);
        island.add(col);
        int minRow = row, minCol = col;

        for(int[] direction : directions) {
            int r = row + direction[0], c = col + direction[1];
            if(isValidNod(grid, r, c)) {
                island.add(r);
                island.add(c);
                minRow = Math.min(r, minRow);
                minCol = Math.min(c, minCol);
                dfsNOD(grid, r, c, island);
            }
        }
    }

    private String getIsland(List<Integer> island) {
        StringBuilder sb = new StringBuilder();
        int minRow = Integer.MAX_VALUE;
        int minCol = Integer.MAX_VALUE;

        for(int i = 0;i < island.size() - 1; i = i + 2) {
            minRow = Math.min(minRow, island.get(i));
            minCol = Math.min(minCol, island.get(i + 1));
        }

        for(int i = 0;i < island.size() - 1; i = i + 2) {
            sb.append(island.get(i) - minRow);
            sb.append(island.get(i + 1) - minCol);
        }
        return sb.toString();
    }

    private boolean isValidNod(int[][] grid, int row, int col) {
        return row >= 0
                && col >= 0
                && row < grid.length
                && col < grid[0].length
                && grid[row][col] == 1
                && !visitedNOD[row][col];
    }

    public int numberOfPaths(int n, int[][] corridors) {
        prepareAdjacencyListMaze(n, corridors);
        visitedMaze = new boolean[n];
        int score = 0;
        for(int i = 0;i < n; i++) {
            if(!visitedMaze[i]) {
                int cycleSize = bfs(i, -1);
                if(cycleSize == 3) {
                    score++;
                }
            }
        }
        return score;
    }

    private int bfs(int vertex, int parent) {
        visitedMaze[vertex] = true;
        int cycleSize = 0;
        boolean cycleFound = false;
        Queue<PairMaze> queue = new LinkedList<>();
        queue.add(new PairMaze(vertex, parent));

        while(!queue.isEmpty()) {
            int queueSize = queue.size();
            while(queueSize > 0) {
                PairMaze pair = queue.poll();
                for(int i = 0;i < adjacencyListMaze[pair.node].size(); i++) {
                    int adjacentVertex = adjacencyListMaze[pair.node].get(i);
                    if(!visitedMaze[adjacentVertex]) {
                        queue.add(new PairMaze(adjacentVertex, pair.node));
                        visitedMaze[adjacentVertex] = true;
                        cycleSize++;
                    } else if(adjacentVertex != pair.parent) {
                        cycleFound = true;
                    }
                }
                queueSize--;
            }
        }
        return (cycleFound) ? cycleSize : 0;
    }

    private void prepareAdjacencyListMaze(int n, int[][] corridors) {
        adjacencyListMaze = new ArrayList[n];
        for(int i = 0; i < n; i++) {
            adjacencyListMaze[i] = new ArrayList<>();
        }
        for(int i = 0; i < corridors.length; i++) {
            int v1 = corridors[i][0] - 1;
            int v2 = corridors[i][1] - 1;
            adjacencyListMaze[v1].add(v2);
            adjacencyListMaze[v2].add(v1);
        }
    }


    public void solve(char[][] board) {
        cantBeSurrounded = new boolean[board.length][board[0].length];
        int row = 0;
        for(int col = 0;col < board[0].length; col++) {
            if(board[0][col] == 'O') {
                dfsSR(board, 0, col);
            }
        }

        for(row = 1;row < board.length; row++) {
            if(board[row][board[0].length - 1] == 'O') {
                dfsSR(board, row, board[0].length - 1);
            }
        }

        row = board.length - 1;
        for(int col = board[0].length - 2; col >= 0; col--) {
            if(board[row][col] == 'O') {
                dfsSR(board, row, col);
            }
        }

        for(row = board.length - 2;row > 0; row--) {
            if(board[row][0] == 'O') {
                dfsSR(board, row, 0);
            }
        }

        for(int i = 0;i < board.length; i++) {
            for(int j = 0;j < board[0].length; j++) {
                if(board[i][j] == 'O' && !cantBeSurrounded[i][j]) {
                    board[i][j] = 'X';
                }
            }
        }
    }

    private void dfsSR(char[][] board, int row, int col) {
        cantBeSurrounded[row][col] = true;
        for(int[] direction : dirsSR) {
            if(isValidSR(board, row + direction[0], col + direction[1])) {
                dfsSR(board, row + direction[0], col + direction[1]);
            }
        }
    }

    private boolean isValidSR(char[][] board, int row, int col) {
        return row >= 0
                && col >= 0
                && row < board.length
                && col < board[0].length
                && !cantBeSurrounded[row][col]
                && board[row][col] == 'O';
    }

    public int[][] updateMatrix(int[][] mat) {
        int[][] solution = new int[mat.length][mat[0].length];
        for(int i = 0;i < mat.length; i++) {
            for(int j = 0;j < mat[0].length; j++) {
                if(mat[i][j] != 0) {
                    int min = Integer.MAX_VALUE;
                    for(int[] direction : directions) {
                        if(isValidMoveTemp(mat, i + direction[0], j + direction[1])) {
                            if(mat[i + direction[0]][j + direction[1]] == 0) {
                                solution[i][j] = 1;
                                break;
                            } else if (solution[i + direction[0]][j + direction[1]] != 0){
                                min = Math.min(min, solution[i + direction[0]][j + direction[1]]);
                            }
                        }
                    }
                    if(solution[i][j] != 1) {
                        if(min != Integer.MAX_VALUE) {
                            solution[i][j] = 1 + min;
                        } else {
                            solution[i][j] = bfs(mat, i, j);
                        }
                    }
                }
            }
        }
        return solution;
    }

    private int bfs(int[][] mat, int i, int j) {
        Queue<PairMat> queue = new LinkedList<>();
        boolean[][] visited = new boolean[mat.length][mat[0].length];
        int count = 0;
        queue.add(new PairMat(i, j));
        while(!queue.isEmpty()) {
            count++;
            int queueSize = queue.size();
            while(queueSize > 0) {
                PairMat pair = queue.poll();
                for(int[] direction : directions) {
                    if(isValidMove(mat, pair.i + direction[0], pair.j + direction[1], visited)) {
                        if(mat[pair.i + direction[0]][pair.j + direction[1]] == 0) {
                            return count;
                        }
                        visited[pair.i + direction[0]][pair.j + direction[1]] = true;
                        queue.add(new PairMat(pair.i + direction[0], pair.j + direction[1]));
                    }
                }
                queueSize--;
            }
        }
        return 0;
    }

    private boolean isValidMove(int[][] mat, int i, int j, boolean[][] visited) {
        return i >= 0
                && j >= 0
                && i < mat.length
                && j < mat[0].length
                && !visited[i][j];
    }

    private boolean isValidMoveTemp(int[][] mat, int i, int j) {
        return i >= 0
                && j >= 0
                && i < mat.length
                && j < mat[0].length;
    }


    public int maximumDetonation(int[][] bombs) {
        int maxCount = 1;
        for(int i = 0;i < bombs.length; i++) {
            detonated = new boolean[bombs.length];
            maxCount = Math.max(maxCount, dfs(bombs, i));
        }
        return maxCount;
    }

    private int dfs(int[][] bombs, int bombIndex) {
        detonated[bombIndex] = true;
        int count = 1;
        for(int i = 0;i < bombs.length; i++) {
            if(i != bombIndex && !detonated[i] && isInRange(bombs, bombIndex, i)) {
                count += dfs(bombs, i);
            }
        }
        return count;
    }

    private boolean isInRange(int[][] bombs, int sourceBombIndex, int destinationBombIndex) {
        return getDis(bombs, sourceBombIndex, destinationBombIndex) <= bombs[sourceBombIndex][2];
    }

    private double getDis(int[][] bombs, int sourceBombIndex, int destinationBombIndex) {
        int x1x2 = bombs[sourceBombIndex][0] - bombs[destinationBombIndex][0];
        int y1y2 = bombs[sourceBombIndex][1] - bombs[destinationBombIndex][1];
        return Math.sqrt(x1x2*x1x2 + y1y2*y1y2);
    }

    public int orangesRotting(int[][] grid) {
        int count = 0;
        visited2 = new boolean[grid.length][grid[0].length];
        Queue<PairRO> queue = new LinkedList<>();
        for(int i = 0;i < grid.length; i++) {
            for(int j = 0;j < grid[i].length; j++) {
                if(isValid(grid, i, j)) {
                    queue.add(new PairRO(i, j));
                }
            }
        }
        while(!queue.isEmpty()) {
            int queueSize = queue.size();
            boolean anyValidMove = false;
            while(queueSize > 0) {
                PairRO pair = queue.poll();
                for(int[] direction : directions) {
                    if(isValidMove(grid, pair.i + direction[0], pair.j + direction[1])) {
                        anyValidMove = true;
                        grid[pair.i + direction[0]][pair.j + direction[1]] = 2;
                        visited2[pair.i + direction[0]][pair.j + direction[1]] = true;
                        queue.add(new PairRO(pair.i + direction[0], pair.j + direction[1]));
                    }
                }
                queueSize--;
            }
            if(anyValidMove) {
                count++;
            }
        }

        return anyFreshOrangeLeft(grid) ? -1 : count;
    }

    private boolean anyFreshOrangeLeft(int[][] grid) {
        for(int i = 0;i < grid.length; i++) {
            for(int j = 0;j < grid[i].length; j++) {
                if(grid[i][j] == 1) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isValid(int[][] grid, int i, int j) {
        return !visited2[i][j] && grid[i][j] == 2;
    }

    private boolean isValidMove(int[][] grid, int i, int j) {
        return i < grid.length
                && i >= 0
                && j < grid[i].length
                && j >= 0
                && !visited2[i][j]
                && grid[i][j] == 1;
    }

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        return floodFillDFS(image, sr, sc, color);
    }

    private int[][] floodFillDFS(int[][] image, int sr, int sc, int color) {
        visitedFF = new boolean[image.length][image[0].length];
        if(image[sr][sc] != color) {
            floodFillDFSSolve(image, sr, sc, image[sr][sc], color);
        }
        return image;
    }

    private void floodFillDFSSolve(int[][] image, int sr, int sc, int originalColor, int color) {
        visitedFF[sr][sc] = true;
        image[sr][sc] = color;
        for(int[] direction : directions) {
            if(isValidMove(image, sr + direction[0], sc + direction[1], originalColor, color)) {
                floodFillDFSSolve(image, sr + direction[0], sc + direction[1], originalColor, color);
            }
        }
    }

    private boolean isValidMove(int[][] image, int sr, int sc, int originalColor, int color) {
        return sr >= 0 && sc >= 0
                && sr < image.length
                && sc < image[sr].length
                && !visitedFF[sr][sc]
                && image[sr][sc] != color
                && image[sr][sc] == originalColor;
    }

    public int numIslands(char[][] grid) {
        return numIslandsDFS(grid);
    }

    private int numIslandsDFS(char[][] grid) {
        visited2 = new boolean[grid.length][grid[0].length];
        int count = 0;
        for(int i = 0;i < grid.length; i++) {
            for(int j = 0;j < grid[i].length; j++) {
                if(isValid(grid, i, j)) {
                    count++;
                    numIslandsDFSHelper(grid, i, j);
                }
            }
        }
        return count;
    }

    private void numIslandsDFSHelper(char[][] grid, int i, int j) {
        visited2[i][j] = true;
        for(int[] direction : directions) {
            if(isValidBoundary(grid, i + direction[0], j + direction[1])) {
                numIslandsDFSHelper(grid, i + direction[0], j + direction[1]);
            }
        }
    }

    private boolean isValid(char[][] grid, int i, int j) {
        return grid[i][j] == '1' && !visited2[i][j];
    }

    private boolean isValidBoundary(char[][] grid, int i, int j) {
        return i >= 0 && j >= 0 && i < grid.length && j < grid[i].length && isValid(grid, i, j);
    }


    public int countCompleteComponents(int n, int[][] edges) {
        prepareAdjacencyList(n, edges);
        visited = new boolean[n];
        int count = 0;
        for(int i = 0;i < n; i++) {
            if(!visited[i]) {
                List<Integer> verticesConnected = new ArrayList<>();
                verticesConnected.add(i);
                countCompleteComponentsSolve(verticesConnected, i);
                if(isCompletelyConnected(verticesConnected)) {
                    count++;
                }
            }
        }
        return count;
    }

    private Pair countCompleteComponentsSolve(List<Integer> verticesConnected, int vertex) {
        visited[vertex] = true;
        for(int i = 0;i < adjacencyList[vertex].size(); i++) {
            if(!visited[adjacencyList[vertex].get(i)]) {
                verticesConnected.add(adjacencyList[vertex].get(i));
                countCompleteComponentsSolve(verticesConnected, adjacencyList[vertex].get(i));
            }
        }
        return null;
    }

    private boolean isCompletelyConnected(List<Integer> verticesConnected) {
        int edges = adjacencyList[verticesConnected.get(0)].size();
        for(int i = 0;i < verticesConnected.size(); i++) {
            if(edges != adjacencyList[verticesConnected.get(i)].size()) {
                return false;
            }
        }
        return true;
    }

    private void prepareAdjacencyList(int n, int[][] edges) {
        adjacencyList = new ArrayList[n];
        for(int i = 0;i < n; i++) {
            adjacencyList[i] = new ArrayList<>();
        }
        for(int[] edge : edges) {
            adjacencyList[edge[0]].add(edge[1]);
            adjacencyList[edge[1]].add(edge[0]);
        }
    }

    public int countComponents(int n, int[][] edges) {
        prepareAdjacencyList(n, edges);
        return countComponentsBFS(n);
    }

    private int countComponentsBFS(int n) {
        return countComponentsBFSSolve(n);
    }

    private int countComponentsBFSSolve(int n) {
        int count = 0;
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if(!visited[i]) {
                Queue<Integer> queue = new LinkedList<>();
                queue.add(i);
                visited[i] = true;
                count++;
                while(!queue.isEmpty()) {
                    int vertex = queue.poll();
                    for(int adjacentVertex : adjacencyList[vertex]) {
                        if(!visited[adjacentVertex]) {
                            visited[adjacentVertex] = true;
                            queue.add(adjacentVertex);
                        }
                    }
                }
            }
        }

        return count;
    }
//
//    private void prepareAdjacencyList(int n, int[][] edges) {
//        adjacencyList = new ArrayList[n];
//        for(int i = 0;i < n; i++) {
//            adjacencyList[i] = new ArrayList<>();
//        }
//        for(int[] edge : edges) {
//            adjacencyList[edge[0]].add(edge[1]);
//            adjacencyList[edge[1]].add(edge[0]);
//        }
//    }
}
