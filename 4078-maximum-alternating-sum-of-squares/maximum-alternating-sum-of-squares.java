import java.util.Arrays;

class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        long[] squares = new long[n];
        for (int i = 0; i < n; ++i) {
            squares[i] = (long) nums[i] * nums[i];
        }
        Arrays.sort(squares);
        long ans = 0;
        int m = n >> 1;
        for (int i = 0; i < n; ++i) {
            ans += (i < m) ? -squares[i] : squares[i];
        }
        return ans;
    }
}
