class Solution {
    private Boolean[][][] memo;

    public boolean hasValidPath(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        if ((m + n - 1) % 2 != 0) {
            return false;
        }
        memo = new Boolean[m][n][(m + n + 1) / 2];
        return dfs(grid, 0, 0, 0);
    }

    private boolean dfs(char[][] grid, int r, int c, int bal) {
        if (grid[r][c] == '(') {
            bal++;
        } else {
            bal--;
        }
        if (bal < 0 || bal >= memo[0][0].length) {
            return false;
        }
        if (r == grid.length - 1 && c == grid[0].length - 1) {
            return bal == 0;
        }
        if (memo[r][c][bal] != null) {
            return memo[r][c][bal];
        }
        boolean res = false;
        if (r + 1 < grid.length) {
            res = dfs(grid, r + 1, c, bal);
        }
        if (!res && c + 1 < grid[0].length) {
            res = dfs(grid, r, c + 1, bal);
        }
        return memo[r][c][bal] = res;
    }
}
