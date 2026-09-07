class Solution {
    public int distinctSubseqII(String s) {
        int MOD = 1_000_000_007;
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        
        int[] last = new int[26];
        java.util.Arrays.fill(last, -1);
        
        for (int i = 0; i < n; i++) {
            int x = s.charAt(i) - 'a';
            dp[i + 1] = (dp[i] * 2) % MOD;
            if (last[x] >= 0) {
                dp[i + 1] = (dp[i + 1] - dp[last[x]] + MOD) % MOD;
            }
            last[x] = i;
        }
        
        return (dp[n] - 1 + MOD) % MOD;
    }
}
