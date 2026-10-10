class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        int[] buckets = new int[100001];
        long k = (long) k1 + k2;
        
        for (int i = 0; i < n; i++) {
            int diff = Math.abs(nums1[i] - nums2[i]);
            buckets[diff]++;
        }
        
        for (int i = 100000; i > 0; i--) {
            if (buckets[i] == 0) {
                continue;
            }
            if (k >= buckets[i]) {
                k -= buckets[i];
                buckets[i - 1] += buckets[i];
                buckets[i] = 0;
            } else {
                buckets[i - 1] += k;
                buckets[i] -= k;
                k = 0;
                break;
            }
        }
        
        long ans = 0;
        for (int i = 1; i <= 100000; i++) {
            if (buckets[i] > 0) {
                ans += (long) buckets[i] * i * i;
            }
        }
        return ans;
    }
}
