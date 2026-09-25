import java.util.*;

public class Solution {
    public List<String> braceExpansionII(String expression) {
        Queue<String> queue = new LinkedList<>();
        queue.offer(expression);
        Set<String> res = new HashSet<>();
        
        while (!queue.isEmpty()) {
            String str = queue.poll();
            if (str.indexOf('{') == -1) {
                res.add(str);
                continue;
            }
            
            int i = 0;
            while (str.charAt(i) != '}') {
                i++;
            }
            
            int j = i;
            while (str.charAt(j) != '{') {
                j--;
            }
            
            String before = str.substring(0, j);
            String after = str.substring(i + 1);
            String[] inside = str.substring(j + 1, i).split(",");
            
            for (String s : inside) {
                queue.offer(before + s + after);
            }
        }
        
        List<String> ans = new ArrayList<>(res);
        Collections.sort(ans);
        return ans;
    }
}
