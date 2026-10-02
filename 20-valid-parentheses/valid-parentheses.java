import java.util.Stack;

class Solution {
    public boolean isValid(String s) {
        // Create a stack to keep track of the open brackets
        Stack<Character> stack = new Stack<>();
        
        // Loop through each character in the string
        for (char c : s.toCharArray()) {
            // If it's an open bracket, push it onto the stack
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } 
            // If it's a closing bracket, check for matching open bracket
            else {
                // If the stack is empty, there's no matching open bracket
                if (stack.isEmpty()) {
                    return false;
                }
                
                char openBracket = stack.pop();
                
                // Check if the popped bracket matches the current closing bracket
                if (c == ')' && openBracket != '(') return false;
                if (c == '}' && openBracket != '{') return false;
                if (c == ']' && openBracket != '[') return false;
            }
        }
        
        // If the stack is empty, all brackets were successfully matched
        return stack.isEmpty();
    }
}
