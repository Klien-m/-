package offer_java;

import java.util.*;
import java.util.regex.Pattern;

/**
 * @since 2020/7/15 18:25
 */

public class KthNode {

    class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    class TreeLinkNode {
        int val = 0;
        TreeLinkNode left = null;
        TreeLinkNode right = null;
        TreeLinkNode next = null;

        public TreeLinkNode(int val) {
            this.val = val;
        }
    }

    public class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * (1)给定一棵二叉搜索树，请找出其中的第k小的结点。例如，（5，3，7，2，4，6，8）中，按结点数值大小顺序第三小结点的值为4。
     */
    TreeNode treeNode = null;
    int count = 0;

    void dfs(TreeNode pRoot, int k) {
        if (count < k && pRoot.left != null) {
            dfs(pRoot.left, k);
        }
        if (++count == k) {
            treeNode = pRoot;
        }
        if (count < k && pRoot.right != null) {
            dfs(pRoot.right, k);
        }
    }

    /**
     * 寻找二叉搜索树的第k小的节点。
     */
    TreeNode kthNode(TreeNode pRoot, int k) {
        if (pRoot == null || k <= 0) {
            return null;
        }
        dfs(pRoot, k);
        return treeNode;
    }

    /**
     * (2)序列化和反序列化二叉树。
     */
    String Serialize(TreeNode root) { // 先序遍历
        if (root == null) {
            return "#";
        } else {
            return root.val + "," + Serialize(root.left) + "," + Serialize(root.right);
        }
    }

    int index = -1;

    TreeNode Deserialize(String str) {
        String[] s = str.split(",");
        index++;
        int len = s.length;
        if (index > len) {
            return null;
        }
        TreeNode treeNode = null;
        if (!s[index].equals("#")) {
            treeNode = new TreeNode(Integer.parseInt(s[index]));
            treeNode.left = Deserialize(str);
            treeNode.right = Deserialize(str);
        }
        return treeNode;
    }

    /**
     * (3)从上到下按层打印二叉树，同一层结点从左至右输出。每一层输出一行。
     */
    ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
        if (pRoot == null) return ret;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(pRoot);
        while (!queue.isEmpty()) {
            int size = queue.size(); // 求得当前队列长度，以便确定是否进入下一层。
            ArrayList<Integer> ans = new ArrayList<>();
            while (size-- != 0) {
                TreeNode treeNode = queue.remove();
                ans.add(treeNode.val);
                if (treeNode.left != null) queue.add(treeNode.left);
                if (treeNode.right != null) queue.add(treeNode.right);
            }
            ret.add(ans);
        }
        return ret;
    }

    /**
     * (4)请实现一个函数按照之字形打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右至左的顺序打印，第三行按照从左到右的顺序打印，其他行以此类推。
     */
    ArrayList<ArrayList<Integer>> PrintS(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
        if (pRoot == null) return ret;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(pRoot);
        int layer = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            ArrayList<Integer> ans = new ArrayList<>();
            while (size-- != 0) {
                TreeNode node = queue.remove();
                ans.add(node.val);
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);

            }
            if (layer++ % 2 == 0) { // 根据层数的奇偶性，对ans进行反转。
                int length = ans.size();
                for (int i = 0; i < length / 2; ++i) {
                    int tmp = ans.get(i);
                    ans.set(i, ans.get(length - i - 1));
                    ans.set(length - i - 1, tmp);
                }
            }
            ret.add(ans);
        }
        return ret;
    }

    /**
     * (5)请实现一个函数，用来判断一棵二叉树是不是对称的。注意，如果一个二叉树同此二叉树的镜像是同样的，定义其为对称的。
     */
    boolean isSame(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) return true;
        if (node1 == null || node2 == null) return false;
        return node1.val == node2.val && isSame(node1.left, node2.right) && isSame(node1.right, node2.left);
    }

    boolean isSymmetrical(TreeNode pRoot) {
        return isSame(pRoot, pRoot);
    }

    /**
     * (6)给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针。
     */
    public TreeLinkNode GetNext(TreeLinkNode pNode) {
        if (pNode == null) {
            return pNode;
        }
        if (pNode.right != null) {
            pNode = pNode.right;
            while (pNode.left != null) {
                pNode = pNode.left;
            }
            return pNode;
        }
        while (pNode.next != null) {
            TreeLinkNode root = pNode.next;
            if (root.left == pNode) {
                return root;
            }
            pNode = pNode.next;
        }
        return null;
    }

    /**
     * (7)在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留，返回链表头指针。
     */
    public ListNode deleteDuplication(ListNode pHead) {

        ListNode vhead = new ListNode(-1);
        vhead.next = pHead;
        ListNode pre = vhead;
        ListNode cur = pHead;
        while (cur != null) {
            if (cur.next != null && cur.val == cur.next.val) {
                cur = cur.next;
                while (cur.next != null && cur.val == cur.next.val) {
                    cur = cur.next;
                }
                cur = cur.next;
                pre.next = cur;
            } else {
                pre = cur;
                cur = cur.next;
            }
        }
        return vhead.next;
    }

    /**
     * (8)给一个链表，若其中包含环，请找出该链表的环的入口结点，否则，输出null。
     */
    public ListNode EntryNodeOfLoop(ListNode pHead) {
        List<ListNode> list = new ArrayList<>();
        while (pHead != null) {
            if (list.size() != 0) {
                if (list.contains(pHead)) {
                    return list.get(list.indexOf(pHead));
                }
//                for (int i = 0; i < list.size(); ++i) {
//                    if (list.get(i) == pHead) {
//                        return list.get(i);
//                    }
//                }
            }
            list.add(pHead);
            pHead = pHead.next;
        }
        return null;
    }

    /**
     * (9)请实现一个函数用来找出字符流中第一个只出现一次的字符。
     */
    int[] chars = new int[128];
    Queue<Character> queue = new LinkedList<>();

    public void Insert(char ch) {
        if (chars[ch]++ == 0) {
            queue.add(ch);
        }
    }

    public char FirstAppearingOnce() {
        Character CHAR = null;
        char c = 0;
        while ((CHAR = queue.peek()) != null) {
            c = CHAR;
            if (chars[c] == 1) {
                return c;
            } else queue.remove();
        }
        return '#';
    }

    /**
     * (10)请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
     * 例如，字符串"+100","5e2","-123","3.1416"和"-1E-16"都表示数值。 但是"12e","1a3.14","1.2.3","+-5"和"12e+4.3"都不是。
     */
    public boolean isNumeric(char[] str) {
        String pattern = "^[-+]?\\d*(?:\\.\\d*)?(?:[eE][+\\-]?\\d+)?$";
        String s = new String(str);
        return Pattern.matches(pattern, s);
    }

    /**
     * (11)请实现一个函数用来匹配包括'.'和'*'的正则表达式。模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（包含0次）。
     * 在本题中，匹配是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但是与"aa.a"和"ab*a"均不匹配
     * TODO https://blog.nowcoder.net/n/8ef0794c21c3405b874aa579c4af2b57?f=comment
     */
    public boolean matchStr(char[] str, int i, char[] pattern, int j) {
        if (i == str.length && j == pattern.length) {
            return true;
        } else if (j == pattern.length) {
            return false;
        }
        boolean next = (j + 1 < pattern.length && pattern[j + 1] == '*');
        if (next) {
            if (i < str.length && (pattern[j] == '.' || str[i] == pattern[j])) {
                return matchStr(str, i, pattern, j + 2) || matchStr(str, i + 1, pattern, j);
            } else {
                return matchStr(str, i, pattern, j + 2);
            }
        } else {
            if (i < str.length && (pattern[j] == '.' || str[i] == pattern[j])) {
                return matchStr(str, i + 1, pattern, j + 1);
            } else {
                return false;
            }
        }
    }

    public boolean match(char[] str, char[] pattern) {
        return matchStr(str, 0, pattern, 0);
    }

    /**
     * (12)给定一个数组A[0,1,...,n-1],请构建一个数组B[0,1,...,n-1],其中B中的元素B[i]=A[0]*A[1]*...*A[i-1]*A[i+1]*...*A[n-1]。
     * 不能使用除法。
     */
    public int[] multiply(int[] A) {
        int[] B = new int[A.length];
        for (int i = 0; i < B.length; ++i) {
            int b = 1;
            for (int j = 0; j < A.length; ++j) {
                if (j != i) {
                    b = b * A[j];
                }
            }
            B[i] = b;
        }
        return B;
    }

    /**
     * (13)在一个长度为n的数组里的所有数字都在0到n-1的范围内。
     * 数组中某些数字是重复的，但不知道有几个数字是重复的。
     * 也不知道每个数字重复几次。请找出数组中任意一个重复的数字。
     * 例如，如果输入长度为7的数组{2,3,1,0,2,5,3}，那么对应的输出是第一个重复的数字2。
     */
    public boolean duplicate(int numbers[], int length, int[] duplication) {
        int[] numbs = new int[length]; // 此处也可用一个boolean数组。
        int j = 0;
        for (int i = 0; i < length; ++i) {
            if (numbs[numbers[i]]++ != 0) {
                duplication[j++] = numbers[i];
                return true;
            }
        }
        return false;
    }

//    public int StrToInt(String str) {
//        if (str.length() == 1) {
//
//        }
//    }

    /**
     * (15)写一个函数，求两个整数之和，要求在函数体内不得使用+、-、*、/四则运算符号。
     * 加法：a^b，进位：(a&b)<<1
     */
    public int Add(int num1, int num2) {
        int result, ans;
        do {
            result = num1 ^ num2;
            ans = (num1 & num2) << 1;
            num1 = result;
            num2 = ans;
        } while (ans != 0);
        return result;
    }

    /**
     * (16)求1+2+3+...+n，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
     */
    public int Sum_Solution(int n) {
        int sum = n;
        boolean ans = (n > 0) && ((sum += Sum_Solution(n - 1)) > 0);
        return sum;
    }

    /**
     * 每年六一儿童节,牛客都会准备一些小礼物去看望孤儿院的小朋友,今年亦是如此。HF作为牛客的资深元老,自然也准备了一些小游戏。
     * 其中,有个游戏是这样的:首先,让小朋友们围成一个大圈。然后,他随机指定一个数m,让编号为0的小朋友开始报数。
     * 每次喊到m-1的那个小朋友要出列唱首歌,然后可以在礼品箱中任意的挑选礼物,并且不再回到圈中,从他的下一个小朋友开始,继续0...m-1报数....这样下去....
     * 直到剩下最后一个小朋友,可以不用表演,并且拿到牛客名贵的“名侦探柯南”典藏版(名额有限哦!!^_^)。
     * 请你试着想下,哪个小朋友会得到这份礼品呢？(注：小朋友的编号是从0到n-1)
     * 如果没有小朋友，请返回-1
     */
    // 方法一：模拟
    public int LastRemaining_Solution1(int n, int m) {
        if (n <= 0 || m <= 0) return -1;
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < n; ++i) {
            list.add(i);
        }
        int cur = -1;
        while (list.size() > 1) {
            for (int i = 0; i < m; ++i) {
                cur++;
                if (cur == list.size()) {
                    cur = 0;
                }
            }
            list.remove(cur);
            cur--;
        }
        return list.get(0);
    }

    // 方法二：递归
    // 假设f(n, m) 表示最终留下元素的序号。比如上例子中表示为:f(5,3) = 3
    // 首先，长度为 n 的序列会先删除第 m % n 个元素，然后剩下一个长度为 n - 1 的序列。
    // 那么，我们可以递归地求解 f(n - 1, m)，就可以知道对于剩下的 n - 1 个元素，最终会留下第几个元素，我们设答案为 x = f(n - 1, m)。
    // 由于我们删除了第 m % n 个元素，将序列的长度变为 n - 1。
    // 当我们知道了 f(n - 1, m) 对应的答案 x 之后，我们也就可以知道，长度为 n 的序列最后一个删除的元素，
    // 应当是从 m % n 开始数的第 x 个元素。因此有 f(n, m) = (m % n + x) % n = (m + x) % n。
    // 当n等于1时，f(1,m) = 0
    private int f(int n, int m) {
        if (n == 1) return 0;
        int x = f(n - 1, m);
        return (x + m) % n;
    }

    public int LastRemaining_Solution2(int n, int m) {
        return f(n, m);
    }

    // 思路三：迭代
    public int LastRemaining_Solution3(int n, int m) {
        if (n <= 0) return -1;
        int index = 0;
        for (int i = 2; i <= n; ++i) {
            index = (index + m) % i;
        }
        return index;
    }

    /**
     * 一副扑克牌,里面有2个大王,2个小王。随机抽取5张牌（大小王可以充当任何一张牌），如果能组成顺子返回true，否则返回false。
     */
    public boolean isContinuous(int[] numbers) {
        TreeSet<Integer> set = new TreeSet<>();
        if (numbers.length < 5 || numbers.length > 5) {
            return false;
        }
        int num = 0;
        for (int i = 0; i < numbers.length; ++i) {
            if (numbers[i] == 0) {
                num++;
            } else {
                set.add(numbers[i]);
            }
        }
        if (num + set.size() != 5) {
            return false;
        }
        if ((set.last() - set.first()) < 5) {
            return true;
        }
        return false;
    }
//
//    public String ReverseSentence(String str) {
//    }
}


























