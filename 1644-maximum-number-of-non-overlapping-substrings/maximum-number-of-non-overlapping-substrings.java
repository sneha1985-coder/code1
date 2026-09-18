import java.util.*;

class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        int[] first = new int[26];
        int[] last = new int[26];
        Arrays.fill(first, -1);
        Arrays.fill(last, -1);
        
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            if (first[c] == -1) {
                first[c] = i;
            }
            last[c] = i;
        }
        
        List<int[]> intervals = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            if (first[i] == -1) continue;
            int left = first[i];
            int right = last[i];
            int minLeft = left;
            int maxRight = right;
            
            for (int j = minLeft; j <= maxRight; j++) {
                int c = s.charAt(j) - 'a';
                minLeft = Math.min(minLeft, first[c]);
                maxRight = Math.max(maxRight, last[c]);
            }
            
            if (minLeft == left) {
                intervals.add(new int[]{minLeft, maxRight});
            }
        }
        
        Collections.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));
        
        List<String> ans = new ArrayList<>();
        int lastEnd = -1;
        for (int[] interval : intervals) {
            if (interval[0] > lastEnd) {
                ans.add(s.substring(interval[0], interval[1] + 1));
                lastEnd = interval[1];
            }
        }
        
        return ans;
    }
}
