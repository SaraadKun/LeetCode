package com.saraad.leetcode;

public class Code220504 {

    public static void main(String[] args) {
        Code220504 instance = new Code220504();
        int[] nums = {10,9,10,4,3,8,3,3,6,2,10,10,9,3};
        int k = 19;
        int ans = instance.numSubarrayProductLessThanK(nums, k);
        System.out.println(ans);



    }


    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int ans = 0;
        int m = 1;
        int l = 0;
        for (int r = 0; r < nums.length; r++) {
            m *= nums[r];
            while (m >= k && l <= r) {
                m /= nums[l++];
            }
            ans += r - l + 1;
        }
        return ans;
    }
    public int findTheWinner(int n, int k) {
        if (n <= 1) {
            return n;
        }
        int ans = (findTheWinner(n - 1, k) + k) % n;
        return ans == 0 ? n : ans;
    }

    public int findTheWinner2(int n, int k) {
        int cnt = n;
        boolean[] visited = new boolean[n + 1];
        int cur = 1;
        while(cnt > 1) {
            int step = (k - 1) % cnt + 1;
            for (int i = 0; i < step - 1; i++) {
                cur = increace(cur, n);
                while (visited[cur]) {
                    cur = increace(cur, n);
                }
            }
            visited[cur] = true;
            cnt--;
            while(visited[cur]) {
                cur = increace(cur, n);
            }
        }
        return cur;
    }

    private int increace(int cur, int n) {
        cur++;
        return cur > n ? 1 : cur;
    }
}
