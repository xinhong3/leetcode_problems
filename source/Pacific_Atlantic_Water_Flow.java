import java.util.*;

class Solution {
    private final static int[][] directions = new int[][] {{0,1}, {1,0}, {-1,0}, {0,-1}};
    private final static int[][] downAndRight = new int[][] {{0,1}, {1,0}};
    private final static int[][] upAndLeft = new int[][] {{-1,0}, {0,-1}};
    private int row;
    private int col;


    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        this.row = heights.length;
        if (this.row < 1) {
            return new ArrayList<>();
        }
        this.col = heights[0].length;

        ArrayDeque<int[]> pacificReachable = new ArrayDeque<>();
        ArrayDeque<int[]> atlanticReachable = new ArrayDeque<>();

        int[][] reachable = new int[row][col];
        boolean[][] pacificVisited = new boolean[row][col];
        boolean[][] atlanticVisited = new boolean[row][col];

        for (int i = 0; i < row; i++) {
            pacificReachable.offer(new int[]{i, 0});
            reachable[i][0]++;
            pacificVisited[i][0] = true;

            atlanticReachable.offer(new int[]{i, col-1});
            reachable[i][col-1]++;
            atlanticVisited[i][col-1] = true;
        }

        for (int j = 0; j < col; j++) {
            pacificReachable.offer(new int[]{0, j});
            reachable[0][j]++;
            pacificVisited[0][j] = true;

            atlanticReachable.offer(new int[]{row-1, j});
            reachable[row-1][j]++;
            atlanticVisited[row-1][j] = true;
        }


        bfs(heights, pacificReachable, downAndRight, reachable, pacificVisited);
        bfs(heights, atlanticReachable, upAndLeft, reachable, atlanticVisited);

        List<List<Integer>> res = new ArrayList<>();

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (reachable[i][j] >= 2) {
                    res.add(Arrays.asList(i, j));
                }
            }
        }

        return res;
        // dp[i][j] = if (i,j) can reach the both oceans

        //

        // for a node in the middle
        // dp[i][j] = ()

        // dp[i][j] = dp[i-1][j] || dp[i][j-1] ||
    }

    public void bfs(int[][] heights, Queue<int[]> queue, int[][] availableDirections, int[][] reachable, boolean[][] visited) {
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            for (int[] direction: availableDirections) {
                int[] next = new int[]{current[0] + direction[0], current[1] + direction[1]};
                if (!isValid(next)) continue;

                int next_row = next[0];
                int next_col = next[1];
                int curr_row = current[0];
                int curr_col = current[1];
                if (heights[next_row][next_col] >= heights[curr_row][curr_col] && !visited[next[0]][next[1]]) {
                    queue.offer(next);
                    visited[next_row][next_col] = true;
                    reachable[next_row][next_col]++;
                }
            }
        }
    }

    private boolean isValid(int[] next) {
        return next[0] >= 0 && next[0] < row && next[1] >= 0 && next[1] < col;
    }
}