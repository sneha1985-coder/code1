import java.util.HashMap;
import java.util.Map;

class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int[] minLen = new int[n];
        int ans = Integer.MAX_VALUE;
        
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        
        int sum = 0;
        int currentMin = Integer.MAX_VALUE;
        
        for (int i = 0; i < n; i++) {
            sum += arr[i];
            
            if (map.containsKey(sum - target)) {
                int prevIdx = map.get(sum - target);
                int currLen = i - prevIdx;
                
                if (prevIdx >= 0 && minLen[prevIdx] != Integer.MAX_VALUE) {
                    ans = Math.min(ans, currLen + minLen[prevIdx]);
                }
                currentMin = Math.min(currentMin, currLen);
            }
            
            minLen[i] = currentMin;
            map.put(sum, i);
        }
        
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
