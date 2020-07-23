package leetcode.简单;

import java.util.*;

/**
 * @since 2020/7/21 10:15
 */
public class Tree {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int x) {
            val = x;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // 687 2020-07-21 11:00:47
    int ans;

    public int longestUnivaluePath(TreeNode root) {
        ans = 0;
        arrowLength(root);
        return ans;
    }

    private int arrowLength(TreeNode node) {
        if (node == null) return 0;
        int left = arrowLength(node.left);
        int right = arrowLength(node.right);
        int arrowLeft = 0, arrowRight = 0;
        if (node.left != null && node.left.val == node.val) {
            arrowLeft += left + 1;
        }
        if (node.right != null && node.right.val == node.val) {
            arrowRight += right + 1;
        }
        ans = Math.max(ans, arrowLeft + arrowRight);
        return Math.max(arrowLeft, arrowRight);
    }

    // 111 2020-07-21 11:00:59
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        // 解决了[1, 2]的问题
        if (root.left == null || root.right == null) return left + right + 1;
        return Math.min(left, right) + 1;
    }

    // 501
    private TreeNode pre = null;
    private int[] ret;
    private int retCount = 0;
    private int maxCount = 0;
    private int currCount = 0;

    public int[] findMode(TreeNode root) {
        inOrder(root); // 一次中序，寻找众数数量
        pre = null;
        ret = new int[retCount];
        retCount = 0;
        currCount = 0;
        inOrder(root); // 二次中序，寻找众数
        return ret;
    }

    private void inOrder(TreeNode root) {
        if (root == null) return;
        inOrder(root.left);
        if (pre != null && pre.val == root.val) currCount++;
        else currCount = 1;
        if (currCount > maxCount) {
            maxCount = currCount;
            retCount = 1;
        } else if (currCount == maxCount) {
            if (ret != null) ret[retCount] = root.val;
            retCount++;
        }
        pre = root;
        inOrder(root.right);
    }

    // 671 2020-07-21 12:41:49
    public int findSecondMinimumValue(TreeNode root) {
        if (root == null || root.left == null || root.right == null) return -1;
        int left = root.left.val;
        int right = root.right.val;
        if (root.val == root.left.val) left = findSecondMinimumValue(root.left);
        if (root.val == root.right.val) right = findSecondMinimumValue(root.right);
        if (root.val == left && root.val == right) return -1;
        int min = Math.min(left, right);
        if (root.val < min) return min;
        else return Math.max(left, right);
    }

    // 572 2020-07-21 13:01:48
    private boolean isSameTree(TreeNode s, TreeNode t) {
        if (s == null && t == null) return true;
        if (s == null || t == null) return false;
        if (s.val != t.val) return false;
        return isSameTree(s.left, t.left) && isSameTree(s.right, t.right);
    }

    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (t == null) return true;
        if (s == null) return false;
        return isSubtree(s.left, t) || isSubtree(s.right, t) || isSameTree(s, t);
    }

    // 112 2020-07-21 13:18:00
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        if (root.left == null && root.right == null) {
            return sum == root.val;
        }
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }

    // 543 2020-07-21 13:33:22
    // 此解法求得的是根结点左分支深度加上右分支深度，其不一定为最长路径
//    private int depth(TreeNode node) {
//        if (node == null) return 0;
//        int left = depth(node.left);
//        int right = depth(node.right);
//        return Math.max(left, right) + 1;
//    }
//
//    public int diameterOfBinaryTree(TreeNode root) {
//        if (root == null) return 0;
//        return depth(root.left) + depth(root.right);
//    }
    private int depth1(TreeNode node) {
        if (node == null) return 0;
        int L = depth1(node.left);
        int R = depth1(node.right);
        ans = Math.max(ans, L + R + 1);
        return Math.max(L, R) + 1;
    }

    public int diameterOfBinaryTree(TreeNode root) {
        ans = 1;
        depth1(root);
        return ans - 1;
    }

    // 993 2020-07-21 16:14:48
    Map<Integer, Integer> depth;
    Map<Integer, TreeNode> parent;

    public boolean isCousins(TreeNode root, int x, int y) {
        depth = new HashMap<>();
        parent = new HashMap<>();
        dfs(root, null);
        return (depth.get(x) == depth.get(y) & parent.get(x) != parent.get(y));
    }

    private void dfs(TreeNode node, TreeNode par) {
        if (node != null) {
            depth.put(node.val, par != null ? 1 + depth.get(par.val) : 0);
            parent.put(node.val, par);
            dfs(node.left, node);
            dfs(node.right, node);
        }
    }

    // 101 2020-07-21 16:39:47
    private boolean check(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        return p.val == q.val && check(p.left, q.right) && check(p.right, q.left); // 此处为求对称
    }

//    public boolean isSymmetric(TreeNode root) {
//        return check(root, root);
//    }

    // 110 2020-07-21 16:57:52
    int recur(TreeNode root) {
        if (root == null) return 0;
        int left = recur(root.left);
        if (left == -1) return -1;
        int right = recur(root.right);
        if (right == -1) return -1;
        return Math.abs(left - right) < 2 ? Math.max(left, right) + 1 : -1;
    }

//    public boolean isBalanced(TreeNode root) {
//        return recur(root) != -1;
//    }

    // 783 2020-07-21 17:59:05
    List<Integer> list;

    // 先序遍历
    private void inorder(TreeNode node) {
        if (node == null) return;
        list.add(node.val);
        inorder(node.left);
        inorder(node.right);
    }

    public int minDiffInBST(TreeNode root) {
        list = new ArrayList<>();
        inorder(root);
        list.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        int min = list.get(1) - list.get(0);
        for (int i = 2; i < list.size(); i++) {
            int dis = list.get(i) - list.get(i - 1);
            if (dis < min) {
                min = dis;
            }
        }
        return min;
    }

    // 中序遍历
    class MinDiffInBST {
        Integer prev, ans;

        public int minDiffInBST(TreeNode root) {
            prev = null;
            ans = Integer.MAX_VALUE;
            dfs(root);
            return ans;
        }

        private void dfs(TreeNode node) {
            if (node == null) return;
            dfs(node.left);
            if (prev != null)
                ans = Math.min(ans, node.val - prev);
            prev = node.val;
            dfs(node.right);
        }
    }

    // 606 2020-07-21 20:13:34
    public String tree2str(TreeNode t) {
        if (t == null) return "";
        if (t.left == null && t.right == null) return t.val + "";
        if (t.right == null) return t.val + "(" + tree2str(t.left) + ")";
        return t.val + "(" + tree2str(t.left) + ")(" + tree2str(t.right) + ")";
    }

    // 404 2020-07-21 21:36:53
    public int sumOfLeftLeaves(TreeNode root) {
        return sumOfLeftLeaves(root, false);
    }

    public int sumOfLeftLeaves(TreeNode node, boolean flag) {
        if (node == null) {
            return 0;
        }
        if (flag && node.left == null && node.right == null) {
            return node.val;
        }
        int leftSum = sumOfLeftLeaves(node.left, true);
        int rightSum = sumOfLeftLeaves(node.right, false);
        return leftSum + rightSum;
    }

    // 2020-07-22 09:23:41
    // 437 2020-07-21 21:53:45
    private int dfs(TreeNode root, HashMap<Integer, Integer> map, int sum, int i) {
        if (root == null) return 0;
        i += root.val; // i 为当前所到节点至根节点的值的和，i-sum 若存在，表明舍弃一部分节点之后，可得到一段和为 sum 的节点
        int cnt = map.getOrDefault(i - sum, 0);
        map.put(i, map.getOrDefault(i, 0) + 1);
        cnt += dfs(root.left, map, sum, i);
        cnt += dfs(root.right, map, sum, i);
        map.put(i, map.get(i) - 1);
        return cnt;
    }

    public int pathSum(TreeNode root, int sum) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        return dfs(root, map, sum, 0);
    }

    // 563 2020-07-22 09:34:32
    int tile = 0;

    public int findTilt(TreeNode root) {
        traverse(root);
        return tile;
    }

    private int traverse(TreeNode node) {
        if (node == null) return 0;
        int left = traverse(node.left);
        int right = traverse(node.right);
        tile += Math.abs(left - right);
        return left + right + node.val;
    }

    // 04.04 同 110 2020-07-22 11:20:35
    public boolean isBalanced2(TreeNode root) {
        return recur(root) != -1;
    }

    // 653 2020-07-22 11:24:55
    // 方法一：使用HashSet
    public boolean findTarget(TreeNode root, int k) {
        Set<Integer> set = new HashSet<>();
        return find(root, k, set);
    }

    private boolean find(TreeNode root, int k, Set<Integer> set) {
        if (root == null) return false;
        if (set.contains(k - root.val)) return true;
        set.add(root.val);
        return find(root.left, k, set) || find(root.right, k, set);
    }

    // 方法二：使用 BFS 和 HashSet
    public boolean findTarget2(TreeNode root, int k) {
        Set<Integer> set = new HashSet<>();
        Queue<TreeNode> queue = new LinkedList<>();
        while (!queue.isEmpty()) {
            if (queue.peek() != null) {
                TreeNode node = queue.remove();
                if (set.contains(k - node.val))
                    return true;
                set.add(node.val);
                queue.add(node.left);
                queue.add(node.right);
            } else {
                queue.remove();
            }
        }
        return false;
    }

    // 方法三：BST
    private void inorder(TreeNode root, List<Integer> list) {
        if (root == null) return;
        inorder(root.left, list);
        list.add(root.val);
        inorder(root.right, list);
    }

    public boolean findTarget3(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        int l = 0, r = list.size() - 1;
        while (l < r) {
            int sum = list.get(l) + list.get(r);
            if (sum == k) return true;
            else if (sum < k) l++;
            else r++;
        }
        return false;
    }

    // 530 同 783 中序 2020-07-22 14:06:33

    // 剑指 Offer 28 2020-07-22 14:14:55
    private boolean isSame(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left == null || right == null) return false;
        return left.val == right.val && isSame(left.left, right.right) && isSame(left.right, right.left);
    }

    public boolean isSymmetric(TreeNode root) {
        return isSame(root, root);
    }

    // 剑指 Offer 55 Ⅱ 2020-07-22 14:19:25
    class Balance {
        private int recur(TreeNode node) {
            if (node == null) return 0;
            int left = recur(node.left);
            if (left == -1) return -1;
            int right = recur(node.right);
            if (right == -1) return -1;
            return Math.abs(left - right) < 2 ? Math.max(left, right) + 1 : -1;
        }

        public boolean isBalanced(TreeNode root) {
            return recur(root) != -1;
        }
    }

    // 100 2020-07-22 14:48:15
    public boolean isSameTree2(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        if (p.val != q.val) return false;
        return isSameTree2(p.left, q.left) && isSameTree2(p.right, q.right);
    }

    // 面试题 17.12 2020-07-22 14:51:38
    // 递归将左节点的最右边下一个指向左节点的父节点
    public TreeNode convertBiNode(TreeNode root) {
        if (root == null) return null;
        TreeNode subRoot = convertBiNode(root.left);
        if (subRoot == null) {
            subRoot = root;
        } else {
            TreeNode subRootTmp = subRoot;
            while (subRoot.right != null) {
                subRoot = subRoot.right;
            }
            subRoot.right = root;
            subRoot = subRootTmp;
        }
        root.left = null;
        root.right = convertBiNode(root.right);
        return subRoot;
    }

    // 538 2020-07-22 15:16:07
    int num = 0;

    public TreeNode convertBST(TreeNode root) {
        if (root == null) return null;
        convertBST(root.right);
        root.val += num;
        num = root.val;
        convertBST(root.left);
        return root;
    }

    // 872 2020-07-22 15:39:39

    private void leafs(TreeNode root, List<Integer> list) {
        if (root == null) return;
        if (root.left == null && root.right == null) list.add(root.val);
        leafs(root.left, list);
        leafs(root.right, list);
    }

    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        leafs(root1, list1);
        leafs(root2, list2);
        if (list1.size() != list2.size()) return false;
        for (int i = 0; i < list1.size(); i++) {
            if (list1.get(i) != list2.get(i))
                return false;
        }
        return true;
    }

    // 1022 2020-07-22 16:12:2
    private int sumRootToLeaf(TreeNode root, int num) {
        int sum = 0;
        num = (num << 1) + root.val;
        if (root.left == null && root.right == null) return num;
        if (root.left != null) sum += sumRootToLeaf(root.left, num);
        if (root.right != null) sum += sumRootToLeaf(root.right, num);
        return sum % 1000000007;
    }

    public int sumRootToLeaf(TreeNode root) {
        return root != null ? sumRootToLeaf(root, 0) : 0;
    }

    // 257 2020-07-22 19:10:16
    List<String> pathList;

    public void binaryTreePaths(TreeNode root, String str) {
        str += root.val;
        String str1 = str; // 若不加此句,若左右子树皆存在则右子树的字符会多一些 ->
        if (root.left == null && root.right == null) pathList.add(str);
        if (root.left != null) binaryTreePaths(root.left, str += "->");
        if (root.right != null) binaryTreePaths(root.right, str1 += "->");
    }

    public List<String> binaryTreePaths(TreeNode root) {
        pathList = new ArrayList<>();
        if (root == null) return pathList;
        binaryTreePaths(root, "");
        return pathList;
    }

    // 235 2020-07-22 20:39:42
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        int parentVal = root.val;
        int pVal = p.val;
        int qVal = q.val;
        // 以下判断源于 二叉搜索树中父节点的左子树的值小于等于父节点的值,右子树的值大于等于父节点的值
        if (pVal > parentVal && qVal > parentVal) {
            return lowestCommonAncestor(root.right, p, q);
        } else if (pVal < parentVal && qVal < parentVal) {
            return lowestCommonAncestor(root.left, p, q);
        } else {
            return root;
        }
    }

    // 637 2020-07-22 20:49:31
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> ans = new ArrayList<>();
        if (root == null) return ans;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            double sum = 0d;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                sum += node.val;
                // 记得判空
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            ans.add(sum / (double) size);
        }
        return ans;
    }

    // 107 2020-07-22 22:00:39
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        LinkedList<List<Integer>> ans = new LinkedList<>();
        if (root == null) return ans;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            // 使用 LinkedList 每次向首位添加
            ans.addFirst(list);
        }
        return ans;
    }

    // 669 2020-07-23 09:07:42
    // 这种树的递归，还是有点搞不懂
    public TreeNode trimBST(TreeNode root, int L, int R) {
        if (root == null) return null;
        if (root.val < L) return trimBST(root.right, L, R);
        if (root.val > R) return trimBST(root.left, L, R);
        root.left = trimBST(root.left, L, R);
        root.right = trimBST(root.right, L, R);
        return root;
    }

    // 965 2020-07-23 09:13:06
    public boolean isUnivalTree(TreeNode root) {
        boolean leftCorrect = (root.left == null || (root.val == root.left.val && isUnivalTree(root.left)));
        boolean rightCorrect = (root.right == null || (root.val == root.right.val && isUnivalTree(root.right)));
        return leftCorrect && rightCorrect;
    }

    // 面试题 68 -Ⅰ 同 235 2020-07-23 09:35:43
    class LCA {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            int parentVal = root.val;
            int pVal = p.val;
            int qVal = q.val;
            if (pVal > parentVal && qVal > parentVal) {
                return lowestCommonAncestor(root.right, p, q);
            } else if (pVal < parentVal && qVal < parentVal) {
                return lowestCommonAncestor(root.left, p, q);
            } else {
                return root;
            }
        }
    }

    // 剑指 Offer 32 - Ⅱ 同 107 2020-07-23 09:40:34

    // 面试题 68 - Ⅱ 2020-07-23 09:43:09
    // 递归找到 p、q，将其公共父节点返回出去
    class LCAN {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if (root == null || root == p || root == q) return root;
            TreeNode left = lowestCommonAncestor(root.left, p, q);
            TreeNode right = lowestCommonAncestor(root.right, p, q);
            if (left == null && right == null) return null;
            if (left == null) return right;
            if (right == null) return left;
            return root;
        }
    }

    class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    // 559 2020-07-23 10:44:29
    public int maxDepth(Node root) {
        if (root == null) return 0;
        int max = -1;
        if (root.children.isEmpty()) return 1; // 判空 == null 和 .isEmpty() 为何结果不一样
        for (Node node : root.children) {
            max = Math.max(max, maxDepth(node));
        }
        return max + 1;
    }

    // 897 2020-07-23 10:58:52
    class IBST {
        TreeNode cur;

        public TreeNode increasingBST(TreeNode root) {
            TreeNode ans = new TreeNode(0);
            cur = ans;
            inorder(root);
            return ans.right;
        }

        private void inorder(TreeNode node) {
            if (node == null) return;
            inorder(node.left);
            node.left = null;
            cur.right = node;
            cur = node;
            inorder(node.right);
        }
    }


    // 108 2020-07-23 11:36:57
    private TreeNode dfs(int[] nums, int lo, int hi) {
        if (lo > hi) return null;
        int mid = lo + (hi - lo) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = dfs(nums, lo, mid - 1);
        root.right = dfs(nums, mid + 1, hi);
        return root;
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        return dfs(nums, 0, nums.length - 1);
    }

    // 589 2020-07-23 11:49:29
    class Preorder {
        List<Integer> ans;

        private void inorder(Node root) {
            if (root == null) return;
            ans.add(root.val);
            if (root.children.isEmpty()) return;
            for (Node node : root.children) {
                inorder(node);
            }
        }

        public List<Integer> preorder(Node root) {
            ans = new ArrayList<>();
            inorder(root);
            return ans;
        }
    }

    // 剑指 Offer 54 2020-07-23 12:00:58
    class KL {
        int res, k;

        public int kthLargest(TreeNode root, int k) {
            this.k = k;
            dfs(root);
            return res;
        }

        private void dfs(TreeNode root) {
            if (root == null) return;
            dfs(root.right);
            if (k == 0) return;
            if (--k == 0) res = root.val;
            dfs(root.left);
        }
    }

    // 104 2020-07-23 12:10:10
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return Math.max(left, right) + 1;
    }

    // 590 2020-07-23 12:12:21
    class PostOrder {
        List<Integer> ans;

        private void dfs(Node root) {
            if (root != null) {
                for (Node node : root.children) {
                    if (node != null)
                        dfs(node);
                }
                ans.add(root.val);
            }
//            if (root == null) return;
//            if (root.children.isEmpty()) ans.add(root.val); // 这里的数会重复添加，why
//            for (Node node : root.children) {
//                dfs(node);
//            }
//            ans.add(root.val);
        }

        public List<Integer> postorder(Node root) {
            ans = new ArrayList<>();
            dfs(root);
            return ans;
        }
    }

    // 700 2020-07-23 12:36:20
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null || root.val == val) return root;
        return root.val > val ? searchBST(root.left, val) : searchBST(root.right, val);
    }

    // 迭代版本 2020-07-23 12:42:05
    public TreeNode searchBST2(TreeNode root, int val) {
        while (root != null && val != root.val)
            root = val < root.val ? root.left : root.right;
        return root;
    }

    // 226 2020-07-23 12:43:31
    // 翻转二叉树
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        TreeNode right = invertTree(root.right);
        TreeNode left = invertTree(root.left);
        root.left = right;
        root.right = left;
        return root;
    }

    // 617 2020-07-23 12:52:42
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null) return t2;
        if (t2 == null) return t1;
        t1.val += t2.val;
        t1.left = mergeTrees(t1.left, t2.left);
        t1.right = mergeTrees(t1.right, t2.right);
        return t1;
    }

    // 938 2020-07-23 15:31:50
    public int rangeSumBST(TreeNode root, int L, int R) {
        if (root == null) return 0;
        if (root.val > R)
            return rangeSumBST(root.left, L, R);
        if (root.val < L)
            return rangeSumBST(root.right, L, R);
        else return root.val + rangeSumBST(root.left, L, R) + rangeSumBST(root.right, L, R);
    }

    // 剑指 Offer 27 2020-07-23 16:32:17
    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) return null;
        TreeNode node = new TreeNode(root.val);
        node.left = mirrorTree(root.right);
        node.right = mirrorTree(root.left);
        return node;
    }
    // public TreeNode mirrorTree(TreeNode root) {
    //     if (root == null) return null;
    //     TreeNode tmp = root.left;
    //     root.left = mirrorTree(root.right);
    //     root.right = mirrorTree(tmp);
    //     return root;
    // }

    // 剑指 Offer 55 -Ⅰ2020-07-23 16:39:57
    class MD {
        public int maxDepth(TreeNode root) {
            if (root == null) return 0;
            int left = maxDepth(root.left);
            int right = maxDepth(root.right);
            return Math.max(left, right) + 1;
        }
    }

    // 面试题 04.02 2020-07-23 16:42:19
//    private TreeNode dfs(int[] nums, int lo, int hi) {
//        if (lo > hi) return null;
//        int mid = lo + (hi - lo) / 2;
//        TreeNode root = new TreeNode(nums[mid]);
//        root.left = dfs(nums, lo, mid - 1);
//        root.right = dfs(nums, mid + 1, hi);
//        return root;
//    }
//
//    public TreeNode sortedArrayToBST(int[] nums) {
//        return dfs(nums, 0, nums.length - 1);
//    }
}
