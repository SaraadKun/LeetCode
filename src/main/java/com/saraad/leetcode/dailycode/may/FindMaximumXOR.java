package com.saraad.leetcode.dailycode.may;

/**
 * 421. 数组中两个数的最大异或值
 * <p>
 * 使用前缀树
 */
public class FindMaximumXOR {

    private Trie root = new Trie();
    private int H = 6;
    public static void main(String[] args) {
        int[] nums = {3,10,5,25,2,8};
        FindMaximumXOR cla = new FindMaximumXOR();
        int xor = cla.findMaximumXOR(nums);
        System.out.println(xor);
    }

    public int findMaximumXOR(int[] nums) {
        int ans = -1;
        for (int i = 1; i < nums.length; i++) {
            add(nums[i - 1]);
            int cur = getVal(nums[i]);
            ans = Math.max(ans, cur ^ nums[i]);
        }
        return ans;
    }

    private void add(int n) {
        Trie t = root;
        for (int i = 31; i >= 0; i--) {
            int cur = (n >> i) & 1;
            if (cur == 0) {
                if (t.left == null) {
                    t.left = new Trie();
                }
                t = t.left;
            } else {
                if (t.right == null) {
                    t.right = new Trie();
                }
                t = t.right;
            }
        }
    }

    private int getVal(int n) {
        Trie t = root;
        int x = 0;
        for (int i = 31; i >= 0; i--) {
            int cur = (n >> i) & 1;
            if (cur == 0) {
                if (t.right != null) {
                    x = x * 2 + 1;
                    t = t.right;
                } else {
                    x = x * 2;
                    t = t.left;
                }
            } else {
                if (t.left != null) {
                    x = x * 2;
                    t = t.left;
                } else {
                    x = x * 2 + 1;
                    t = t.right;
                }
            }
        }
        return x;
    }

}

class Trie {
    // 左子树指向表示 0 的子节点
    Trie left = null;
    // 右子树指向表示 1 的子节点
    Trie right = null;
}

