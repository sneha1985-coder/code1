import java.util.HashMap;
import java.util.Map;

class Solution {
    private static final int MOD = 1000000007;

    private static class SegmentTree {
        int n;
        long[] sum;
        long[] sqSum;
        long[] lazy;

        SegmentTree(int n) {
            this.n = n;
            sum = new long[4 * n];
            sqSum = new long[4 * n];
            lazy = new long[4 * n];
        }

        void push(int node, int start, int end) {
            if (lazy[node] == 0) return;
            long val = lazy[node];
            lazy[node] = 0;

            int mid = (start + end) / 2;
            int left = 2 * node;
            int right = 2 * node + 1;

            apply(left, start, mid, val);
            apply(right, mid + 1, end, val);
        }

        void apply(int node, int start, int end, long val) {
            long len = end - start + 1;
            sqSum[node] = (sqSum[node] + 2 * val % MOD * sum[node] % MOD + val * val % MOD * len % MOD) % MOD;
            sum[node] = (sum[node] + val * len) % MOD;
            lazy[node] = (lazy[node] + val) % MOD;
        }

        void update(int node, int start, int end, int l, int r, long val) {
            if (r < start || end < l) return;
            if (l <= start && end <= r) {
                apply(node, start, end, val);
                return;
            }
            push(node, start, end);
            int mid = (start + end) / 2;
            update(2 * node, start, mid, l, r, val);
            update(2 * node + 1, mid + 1, end, l, r, val);
            sum[node] = (sum[2 * node] + sum[2 * node + 1]) % MOD;
            sqSum[node] = (sqSum[2 * node] + sqSum[2 * node + 1]) % MOD;
        }
    }

    public int sumCounts(int[] nums) {
        int n = nums.length;
        SegmentTree tree = new SegmentTree(n);
        Map<Integer, Integer> lastPos = new HashMap<>();
        long totalSum = 0;

        for (int j = 0; j < n; j++) {
            int last = lastPos.getOrDefault(nums[j], -1);
            tree.update(1, 0, n - 1, last + 1, j, 1);
            totalSum = (totalSum + tree.sqSum[1]) % MOD;
            lastPos.put(nums[j], j);
        }

        return (int) ((totalSum + MOD) % MOD);
    }
}
