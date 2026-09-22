import java.util.*;

class Solution {
    static class Node {
        long prod;
        int[] remain;

        Node(int k) {
            this.prod = 1;
            this.remain = new int[k];
        }
    }

    private int k;
    private Node[] tree;
    private int n;

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.k = k;
        this.n = nums.length;
        this.tree = new Node[4 * n];
        
        for (int i = 0; i < n; i++) {
            nums[i] %= k;
        }

        build(0, 0, n - 1, nums);

        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int index = queries[i][0];
            int value = queries[i][1] % k;
            int start = queries[i][2];
            int x = queries[i][3];

            update(0, 0, n - 1, index, value);
            Node res = query(0, 0, n - 1, start, n - 1);
            ans[i] = res.remain[x];
        }

        return ans;
    }

    private void build(int nodeIdx, int start, int end, int[] nums) {
        tree[nodeIdx] = new Node(k);
        if (start == end) {
            tree[nodeIdx].prod = nums[start];
            tree[nodeIdx].remain[nums[start]] = 1;
            return;
        }
        int mid = start + (end - start) / 2;
        build(2 * nodeIdx + 1, start, mid, nums);
        build(2 * nodeIdx + 2, mid + 1, end, nums);
        tree[nodeIdx] = merge(tree[2 * nodeIdx + 1], tree[2 * nodeIdx + 2]);
    }

    private void update(int nodeIdx, int start, int end, int idx, int val) {
        if (start == end) {
            Arrays.fill(tree[nodeIdx].remain, 0);
            tree[nodeIdx].prod = val;
            tree[nodeIdx].remain[val] = 1;
            return;
        }
        int mid = start + (end - start) / 2;
        if (idx <= mid) {
            update(2 * nodeIdx + 1, start, mid, idx, val);
        } else {
            update(2 * nodeIdx + 2, mid + 1, end, idx, val);
        }
        tree[nodeIdx] = merge(tree[2 * nodeIdx + 1], tree[2 * nodeIdx + 2]);
    }

    private Node query(int nodeIdx, int start, int end, int l, int r) {
        if (l <= start && end <= r) {
            return tree[nodeIdx];
        }
        int mid = start + (end - start) / 2;
        if (r <= mid) {
            return query(2 * nodeIdx + 1, start, mid, l, r);
        }
        if (l > mid) {
            return query(2 * nodeIdx + 2, mid + 1, end, l, r);
        }
        return merge(query(2 * nodeIdx + 1, start, mid, l, r), query(2 * nodeIdx + 2, mid + 1, end, l, r));
    }

    private Node merge(Node left, Node right) {
        Node node = new Node(k);
        node.prod = (left.prod * right.prod) % k;
        for (int i = 0; i < k; i++) {
            node.remain[i] = left.remain[i];
        }
        for (int i = 0; i < k; i++) {
            int nextIdx = (int) ((i * left.prod) % k);
            node.remain[nextIdx] += right.remain[i];
        }
        return node;
    }
}
