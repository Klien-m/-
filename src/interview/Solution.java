package interview;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

import static org.junit.Assert.assertEquals;

/**
 * @since 2020/8/16 18:19
 */
public class Solution {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    /**
     * date: 2020-09-01 09:13:00
     * 非递归中序遍历二叉树
     */
    public List<Integer> inorder(TreeNode head) {
        List<Integer> res = new ArrayList<>();
        if (head == null) return res;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = head;
        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            TreeNode popNode = stack.pop();
            res.add(popNode.val);
            node = popNode.right;
        }
        return res;
    }

    /**
     * date: 2020-09-01 09:30:10
     * 快速排序
     */
    public int[] quickSort(int[] nums, int left, int right) {
        if (left >= right) {
            return nums;
        }
        int i = left, j = right;
        int pivot = nums[left];
        while (i < j) {
            while (i < j && nums[j] > pivot) j--;
            while (i < j && nums[i] < pivot) i++;
            if (nums[i] == nums[j] && i < j) {
                i++;
            } else if (i < j) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }
        if (i - 1 > left) quickSort(nums, left, i - 1);
        if (j + 1 < right) quickSort(nums, j + 1, right);
        return nums;
    }

    /**
     * 二叉搜索树的插入
     */
    public TreeNode insert(TreeNode root, int value) {
        if (root == null) return new TreeNode(value);
        if (root.val > value) root.left = insert(root.left, value);
        else if (root.val < value) root.right = insert(root.right, value);
        else root.val = value;
        return root;
    }

    /**
     * 两大数相乘
     */
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0"))
            return "0";
        int m = num1.length(), n = num2.length();
        int[] ansArr = new int[m + n];
        for (int i = m - 1; i >= 0; i--) {
            int x = num1.charAt(i) - '0';
            for (int j = n - 1; j >= 0; j--) {
                int y = num2.charAt(j) - '0';
                ansArr[i + j + 1] += x * y;
            }
        }
        for (int i = m + n - 1; i > 0; i--) {
            ansArr[i - 1] += ansArr[i] / 10;
            ansArr[i] %= 10;
        }
        int index = ansArr[0] == 0 ? 1 : 0;
        StringBuilder sb = new StringBuilder();
        while (index < m + n)
            sb.append(ansArr[index++]);
        return sb.toString();
    }

    /**
     * 递归层序遍历
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> res = new ArrayList<>();
        levelOrder(root, 1, res);
        return res;
    }

    public void levelOrder(TreeNode root, int index, List<List<Integer>> res) {
        if (res.size() < index) {
            res.add(new ArrayList<>());
        }
        res.get(index - 1).add(root.val);
        if (root.left != null) {
            levelOrder(root.left, index + 1, res);
        }
        if (root.right != null) {
            levelOrder(root.right, index + 1, res);
        }
    }

    /**
     * 2020-09-05 10:39:25
     * 选择排序
     */
    public void selectSort(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            int index = i, min = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                if (min > nums[j]) {
                    index = j;
                    min = nums[j];
                }
            }
            if (index != i) {
                int temp = nums[i];
                nums[i] = nums[index];
                nums[index] = temp;
            }
        }
    }

    /**
     * 2020-09-06 09:07:19
     * 迪杰斯特拉算法
     */
    class Pair implements Comparable<Pair> {
        double probability;
        int node;

        public Pair(double probability, int node) {
            this.probability = probability;
            this.node = node;
        }

        @Override
        public int compareTo(Pair o) {
            if (this.probability == o.probability) {
                return this.node - o.node;
            } else {
                return this.probability - o.probability > 0 ? -1 : 1;
            }
        }
    }

    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        List<List<Pair>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < edges.length; i++) {
            int[] edge = edges[i];
            graph.get(edge[0]).add(new Pair(succProb[i], edge[1]));
            graph.get(edge[1]).add(new Pair(succProb[i], edge[0]));
        }
        PriorityQueue<Pair> queue = new PriorityQueue<>();
        double[] prob = new double[n];
        queue.offer(new Pair(1, start));
        prob[start] = 1;
        while (!queue.isEmpty()) {
            Pair pair = queue.poll();
            double pr = pair.probability;
            int node = pair.node;
            if (pr < prob[node]) {
                continue;
            }
            for (Pair pairNext : graph.get(node)) {
                double preNext = pairNext.probability;
                int nodeNext = pairNext.node;
                if (prob[nodeNext] < prob[node] * preNext) {
                    prob[nodeNext] = prob[node] * preNext;
                    queue.offer(new Pair(prob[nodeNext], nodeNext));
                }
            }
        }
        return prob[end];
    }

    @Test
    public void testSolve() {
        assertEquals(2, 2);
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(3);
        root.right.right = new TreeNode(6);
    }
}
