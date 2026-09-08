class Solution {
    public int countCommas(int n) {
        long totalCommas = 0;
        for (long i = 1000; i <= n; i *= 1000) {
            totalCommas += (n - i + 1);
        }
        return (int) totalCommas;
    }
}
