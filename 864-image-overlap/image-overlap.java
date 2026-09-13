import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (img1[i][j] == 1) {
                    list1.add(i * 100 + j);
                }
                if (img2[i][j] == 1) {
                    list2.add(i * 100 + j);
                }
            }
        }
        
        Map<Integer, Integer> counts = new HashMap<>();
        int maxOverlap = 0;
        
        for (int p1 : list1) {
            int r1 = p1 / 100;
            int c1 = p1 % 100;
            for (int p2 : list2) {
                int r2 = p2 / 100;
                int c2 = p2 % 100;
                
                int key = (r2 - r1 + 30) * 100 + (c2 - c1 + 30);
                counts.put(key, counts.getOrDefault(key, 0) + 1);
                maxOverlap = Math.max(maxOverlap, counts.get(key));
            }
        }
        
        return maxOverlap;
    }
}
