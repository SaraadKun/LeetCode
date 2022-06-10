package com.saraad.structure;

import lombok.Data;

import java.util.ArrayDeque;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * Red-Black tree implementation.
 * 实现方法: put, get, delete, contains, size, isEmpty methods
 *
 */
public class RedBlackBST<Key extends Comparable<Key>, Value> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node root;     // root of the BST

    /**
     * Node class
     */
    private class Node {
        private Key key;
        private Value val;
        private Node left, right;
        private boolean color;
        private int size;

        public Node(Key key, Value val, boolean color, int size) {
            this.key = key;
            this.val = val;
            this.color = color;
            this.size = size;
        }
    }

    public RedBlackBST() {
    }

    public int size() {
        return size(root);
    }

    public boolean isEmpty() {
        return root == null;
    }

    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0)
                x = x.left;
            else if (cmp > 0)
                x = x.right;
            else return x.val;
        }
        return null;
    }

    public int height() {
        return height(root);
    }
    private int height(Node x) {
        if (x == null) return -1;
        return 1 + Math.max(height(x.left), height(x.right));
    }

    public Key min() {
        if (isEmpty()) throw new NoSuchElementException("calls min() with empty symbol table");
        return min(root).key;
    }

    private Node min(Node h) {
        return h.left == null ? h : min(h.left);
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public void put(Key key, Value val) {
        if (key == null) throw new IllegalArgumentException("first argument to put() is null");
        if (val == null) {
            delete(key);
            return;
        }
        root = put(root, key, val);
        root.color = BLACK;
    }

    private Node put(Node h, Key key, Value val) {
        //递归查找key的位置,并创建一个红色节点(代表指向该节点的链接为红链接)加入树中
        if (h == null) {
            return new Node(key, val, RED, 1);
        }
        int cmp = key.compareTo(h.key);
        if (cmp < 0)
            h.left = put(h.left, key, val);
        else if (cmp > 0)
            h.right = put(h.right, key, val);
        else h.val = val;
        //添加动作结束后对红黑树做检查,并进行相应的旋转操作(自底向上)
        return balance(h);
    }

    public void deleteMin() {
        if (isEmpty()) throw new NoSuchElementException("BST underflow");
        //确保根节点不是2-节点(2-节点其实代表root是一个4-节点)  方式:将root置红
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;
        root = deleteMin(root);
        if (!isEmpty()) root.color = BLACK;
    }

    private Node deleteMin(Node h) {
        //删除最小值操作  当前节点h一定不是2-节点,所以直接删除
        if (h.left == null)
            return null;
        //自顶向下的保证当前节点不是2-节点的操作
        //即在进行下一轮递归时,需要保证当前节点的左子节点不是2-节点(最小值向左查找)
        //若当前左子节点不是2-节点 (即 isRed(h.left) || isRed(h.left.left)),无需操作,继续向下,否则执行操作以保证h.left不为2-及诶按
        if (!isRed(h.left) && !isRed(h.left.left)) {
            h = moveRedLeft(h);
        }
        h.left = deleteMin(h.left);
        //最后自底向上进行节点平衡处理(与put相同)
        return balance(h);
    }

    public void deleteMax() {
        if (isEmpty()) throw new NoSuchElementException("BST underflow");
        //确保根节点不是2-节点(2-节点其实代表root是一个4-节点)  方式:将root置红
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;
        root = deleteMax(root);
        if (!isEmpty()) root.color = BLACK;
    }

    //所有的变换操作都是为了保证在执行删除时,当前节点不是一个2-节点,即最终移除一个color == RED的节点
    private Node deleteMax(Node h) {
        //若左子节点为红(自上而下操作时,右子节点不可能为红),右旋,将红色链接移到右子树
        if (isRed(h.left))
            rotateRight(h);
        //删除操作
        if (h.right == null)
            return null;
        //若h.right是一个2-节点,执行操作将h.right变红或者将h.right的子节点之一变红,以确保h.right不是一个2-节点
        if (!isRed(h.right) && !isRed(h.right.left))
            h = moveRedRight(h);
        h.right = deleteMax(h.right);
        return balance(h);
    }

    /*
       2.若当前左子节点是2-节点
        2.1 若当前右子节点也是2-节点,将父节点中的最小值(即h),左子节点,右子节点合并为一个4-节点,同时父节点-1(3-变为2- or 4-变为3-)
        2.2 若当前右子节点不是2-节点(即isRed(h.right.left), 将左子节点兄弟节点中的一个键移动到左子节点中
     */
    private Node moveRedLeft(Node h) {
        //若h左右均为2-节点,则flipColor操作就足够了  因为h一定不为2-节点,所以flip之后,左右链接都为红
        flipColors(h);
        //若h的右子节点不为2-节点,则此处操作相当于从h的右子节点"借"一个键移动到h的左子节点中
        if (isRed(h.right.left)) {
            //将h右子树的左节点(红键)右旋,将红键移到右侧 此时 h.right.right.color == RED
            h.right = rotateRight(h.right);
            //将h右子树左旋  此时h的位置为原来的h.right, h和h.left的红链接下移,相当于给原h位置的左子节点添加了一个红键
            h = rotateLeft(h);
            //h颜色翻转后 h左右链接变为BLACK,h的BLACK链接恢复为RED
            flipColors(h);
        }
        return h;
    }

    //h必为红色节点,h.left必为黑色(红色在之前步骤处理过了)  将h.right 或者h.right的一个子节点变红
    private Node moveRedRight(Node h) {
        //此步骤会将h的左右子节点都变为红色,相当于从父节点中(3- or 4-)拆分出h,并与左右子节点共同构成一个4-节点
        flipColors(h);
        //若左子节点不为2-节点,将左子节点的红键转移到右子节点中
        if (isRed(h.left.left)) {
            h = rotateRight(h);
            flipColors(h);
        }
        return h;
    }

    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("first argument to delete() is null");
        if (!contains(key))
            return;

        // if both children of root are black, set root to red
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;
        root = delete(root, key);

        if (!isEmpty())
            root.color = BLACK;
    }

    /*
        分为三种情况:
        1.要删除的节点在左子树 : 参考deleteMin的向下操作,向左递归调用delete
        2.要删除的节点在右子树 : 向右递归调用
        3.要删除的节点是当前节点 : 若无右子树,直接删除,否则寻找右子树中最小的节点,替换掉当前节点,并将原来的右子树最小节点删除
     */
    private Node delete(Node h, Key key) {
        if (key.compareTo(h.key) < 0) {
            //向左查找,参考deleteMin向下的判断条件
            if (!isRed(h.left) && !isRed(h.left.left))
                h = moveRedLeft(h);
            h.left = delete(h.left, key);
        } else {
            //向右删除(删除当前节点本质也是需要向右删除)需要确保右子节点不为2-节点,而
            if (isRed(h.left))
                h = rotateRight(h);
            if (key.compareTo(h.key) == 0 && (h.right == null))
                return null; //右节点为空,直接删除
            if (!isRed(h.right) && !isRed(h.right.left))
                h = moveRedRight(h);
            //右子树不为空时删除当前节点
            if (key.compareTo(h.key) == 0) {
                Node x = min(h.right);
                h.key = x.key;
                h.val = x.val;
                h.right = deleteMin(h.right);
            } else {
                h.right = delete(h.right, key);
            }
        }
        return balance(h);
    }

    private Node balance(Node h) {
        // 1.左黑右红, 左旋转 (3-节点) (完成or连同父红节点转为2)   // 也可以不判断左子树 1.右红, 左旋转 (3-节点) (完成or转为2 or 3)
        // 2.左红且子节点左也为红, 右旋转 (转为3)
        // 3.左右都为红 代表当前节点为4-节点,需提取中间节点"进位"(相当于在父节点添加中间节点),即将左右置为BLACK,当前置为RED(相当于新增)
//        if (!isRed(h.left) && isRed(h.right))
        if (!isRed(h.left) && isRed(h.right))
            h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left))
            h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right))
            flipColors(h);
        //重新计算h.size  flipColors时可能会忽略掉size的改变
        h.size = size(h.left) + size(h.right) + 1;
        return h;
    }

    private Node rotateRight(Node h) {
        Node l = h.left;
        h.left = l.right;
        l.right = h;
        l.color = h.color;
        h.color = RED;
        l.size = h.size;
        h.size = size(h.left) + size(h.right) + 1;
        return l;
    }

    //改变颜色,改变size
    private Node rotateLeft(Node h) {
        Node r = h.right;
        h.right = r.left;
        r.left = h;
        r.color = h.color;
        h.color = RED;
        r.size = h.size;
        h.size = size(h.left) + size(h.right) + 1;
        return r;
    }

    private int size(Node x) {
        return x == null ? 0 : x.size;
    }

    private void flipColors(Node h) {
        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
    }

    private boolean isRed(Node h) {
        return h != null && h.color == RED;
    }


    public String toString() {
        StringBuilder sb = new StringBuilder();
        Queue<Pair<Node, Integer>> queue = new ArrayDeque<>();
        if (root != null)
            queue.offer(Pair.of(root, 1));
        int level = 1;
        while (!queue.isEmpty()) {
            Pair<Node, Integer> p = queue.poll();
            Integer cl = p.getValue();
            Node cur = p.getKey();
            if (cl != level) {
                sb.append("\n");
                level = cl;
            }
            sb.append(cur.key).append(", ");
            if (cur.left != null)
                queue.offer(Pair.of(cur.left, cl + 1));
            if (cur.right != null)
                queue.offer(Pair.of(cur.right, cl + 1));
        }
        return sb.toString();
    }

    @Data
    static class Pair<K, V> {
        K key;
        V value;

        Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public static <K, V> Pair of(K key, V value){
            return new Pair(key, value);
        }
    }


}
