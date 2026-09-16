class Solution {
    public int numberOfSets(int n, int k) {
        int MOD = 1_000_000_007;
        int N = n + k - 1;
        int K = 2 * k;
        
        if (K > N) return 0;
        
        int[] dp = new int[K + 1];
        dp[0] = 1;
        
        for (int i = 1; i <= N; i++) {
            for (int j = Math.min(i, K); j > 0; j--) {
                dp[j] = (dp[j] + dp[j - 1]) % MOD;
            }
        }
        
        return dp[K];
    }
}
