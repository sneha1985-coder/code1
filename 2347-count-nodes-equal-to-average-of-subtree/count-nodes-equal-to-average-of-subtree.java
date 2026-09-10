class Solution {
    private int count = 0;

    public int averageOfSubtree(TreeNode root) {
        calculateSubtree(root);
        return count;
    }

    private int[] calculateSubtree(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0};
        }

        int[] left = calculateSubtree(node.left);
        int[] right = calculateSubtree(node.right);

        int currentSum = left[0] + right[0] + node.val;
        int currentCount = left[1] + right[1] + 1;

        if (node.val == currentSum / currentCount) {
            count++;
        }

        return new int[]{currentSum, currentCount};
    }
}
