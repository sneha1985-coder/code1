import java.util.*;

class Solution {
    static class Interval {
        int l, r, w, id;
        Interval(int l, int r, int w, int id) {
            this.l = l;
            this.r = r;
            this.w = w;
            this.id = id;
        }
    }

    static class State {
        long weight;
        List<Integer> indices;
        State(long weight, List<Integer> indices) {
            this.weight = weight;
            this.indices = indices;
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();
        Interval[] arr = new Interval[n];
        for (int i = 0; i < n; i++) {
            List<Integer> curr = intervals.get(i);
            arr[i] = new Interval(curr.get(0), curr.get(1), curr.get(2), i);
        }

        Arrays.sort(arr, (a, b) -> Integer.compare(a.l, b.l));

        State[][] dp = new State[n + 1][5];
        for (int i = 0; i <= n; i++) {
            for (int k = 0; k <= 4; k++) {
                dp[i][k] = new State(0, new ArrayList<>());
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            int low = i + 1, high = n - 1, nextIdx = n;
            while (low <= high) {
                int mid = (low + high) >>> 1;
                if (arr[mid].l > arr[i].r) {
                    nextIdx = mid;
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }

            for (int k = 1; k <= 4; k++) {
                State skip = dp[i + 1][k];

                long takeWeight = arr[i].w;
                List<Integer> takeIndices = new ArrayList<>();
                takeIndices.add(arr[i].id);
                if (nextIdx < n) {
                    State nextState = dp[nextIdx][k - 1];
                    takeWeight += nextState.weight;
                    takeIndices.addAll(nextState.indices);
                }
                
                List<Integer> sortedTake = new ArrayList<>(takeIndices);
                Collections.sort(sortedTake);

                if (takeWeight > skip.weight) {
                    dp[i][k] = new State(takeWeight, sortedTake);
                } else if (skip.weight > takeWeight) {
                    dp[i][k] = skip;
                } else {
                    if (compare(sortedTake, skip.indices) < 0) {
                        dp[i][k] = new State(takeWeight, sortedTake);
                    } else {
                        dp[i][k] = skip;
                    }
                }
            }
        }

        List<Integer> ansList = dp[0][4].indices;
        int[] ans = new int[ansList.size()];
        for (int i = 0; i < ansList.size(); i++) {
            ans[i] = ansList.get(i);
        }
        return ans;
    }

    private int compare(List<Integer> a, List<Integer> b) {
        int len = Math.min(a.size(), b.size());
        for (int i = 0; i < len; i++) {
            if (!a.get(i).equals(b.get(i))) {
                return Integer.compare(a.get(i), b.get(i));
            }
        }
        return Integer.compare(a.size(), b.size());
    }
}
