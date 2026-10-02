import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack(result, "", 0, 0, n);
        return result;
    }

    private void backtrack(List<String> result, String currentString, int openCount, int closeCount, int maxPairs) {
        // Base case: If the current string reaches the maximum required length, add it to the list
        if (currentString.length() == maxPairs * 2) {
            result.add(currentString);
            return;
        }

        // Rule 1: You can always add an opening parenthesis if you haven't reached the limit 'n'
        if (openCount < maxPairs) {
            backtrack(result, currentString + "(", openCount + 1, closeCount, maxPairs);
        }

        // Rule 2: You can only add a closing parenthesis if it matches an existing, unmatched opening one
        if (closeCount < openCount) {
            backtrack(result, currentString + ")", openCount, closeCount + 1, maxPairs);
        }
    }
}
