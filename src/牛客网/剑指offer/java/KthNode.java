package 牛客网.剑指offer.java;

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

    private void dfs(TreeNode pRoot, int k) {
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
    public TreeNode kthNode(TreeNode pRoot, int k) {
        if (pRoot == null || k <= 0) {
            return null;
        }
        dfs(pRoot, k);
        return treeNode;
    }

    /**
     * (2)序列化和反序列化二叉树。
     */
    public String Serialize(TreeNode root) { // 先序遍历
        if (root == null) {
            return "#";
        } else {
            return root.val + "," + Serialize(root.left) + "," + Serialize(root.right);
        }
    }

    int index = -1;

    public TreeNode Deserialize(String str) {
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
    public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
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
    public ArrayList<ArrayList<Integer>> PrintS(TreeNode pRoot) {
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
    private boolean isSame(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) return true;
        if (node1 == null || node2 == null) return false;
        return node1.val == node2.val && isSame(node1.left, node2.right) && isSame(node1.right, node2.left);
    }

    public boolean isSymmetrical(TreeNode pRoot) {
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

    private void Insert(char ch) {
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
    private boolean matchStr(char[] str, int i, char[] pattern, int j) {
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

    /**
     * (14)将一个字符串转换成一个整数，要求不能使用字符串转换整数的库函数。
     * 数值为0或者字符串不是一个合法的数值则返回0
     */
    public int StrToInt(String str) {
        int length = str.length();
        int isNegative = 1, overValue = 0;
        int digit = 0, value = 0;
        if (length == 0) return 0;
        else {
            int idx = 0;
            if (str.charAt(0) == '-') {
                isNegative = -1;
                idx = 1;
            } else if (str.charAt(0) == '+') {
                idx = 1;
            }
            for (; idx < length; idx++) {
                digit = str.charAt(idx) - '0';
                overValue = isNegative * value - Integer.MAX_VALUE / 10 + (((isNegative + 1) / 2 + digit > 8) ? 1 : 0);
                if (digit < 0 || digit > 9) return 0;
                else if (overValue > 0) return 0;
                value = value * 10 + isNegative * digit;
            }
            return value;
        }
    }

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
     * (17)每年六一儿童节,牛客都会准备一些小礼物去看望孤儿院的小朋友,今年亦是如此。HF作为牛客的资深元老,自然也准备了一些小游戏。
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
     * (18)一副扑克牌,里面有2个大王,2个小王。随机抽取5张牌（大小王可以充当任何一张牌），如果能组成顺子返回true，否则返回false。
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


    /**
     * (19)反转字符串
     */
    public String ReverseSentence(String str) {
        if (str == null || str.length() <= 1) return str;
        String[] strings = str.split(" ");
        if (strings.length <= 1) return str; // 不加此句时，在输入为" "时无法返回" "，即使在方法的第一句进行返回也不可，尚不知原因。
        int length = strings.length;
        String[] ret = new String[length];
        for (int i = 0; i < length; ++i) {
            ret[i] = strings[length - i - 1];
        }
        return String.join(" ", ret);
    }

    /**
     * (20)对于一个给定的字符序列 str，请你把其循环左移 n 位后的序列输出。
     * TODO 程序应该不会出问题的吧，在牛客运行不成功。
     */
    public String LeftRotateString(String str, int n) {
        int length = str.length();
        if (length == 0) return "";
        String[] ret = new String[length];
        String[] strings = str.split("");
        for (int i = 0; i < length; ++i) {
            ret[i] = strings[(i + n) % length];
        }
        return String.join("", ret);
    }

    /**
     * (21)输入一个递增排序的数组和一个数字S，在数组中查找两个数，使得他们的和正好是S，
     * 如果有多对数字的和等于S，输出两个数的乘积最小的。
     */
    public ArrayList<Integer> FindNumbersWithSum(int[] array, int sum) {
        ArrayList<Integer> ret = new ArrayList<>();
        if (array.length < 2) return ret;
        int slow = 0, high = array.length - 1;
        int min = Integer.MAX_VALUE;
        while (slow < high) {
            int cur = array[slow] + array[high];
            if (cur < sum) {
                slow++;
            } else if (cur > sum) {
                high--;
            } else {
                if (min > array[slow] * array[high]) {
                    ret.clear();
                    ret.add(array[slow]);
                    ret.add(array[high]);
                    min = array[slow] * array[high];
                }
                slow++;
            }
        }
        return ret;
    }

    // 一遍哈希
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int flag = target - nums[i];
            if (map.containsKey(flag)) {
                return new int[]{map.get(flag), i};
            }
            map.put(nums[i], i);
        }
        return new int[]{};
    }

    /**
     * (22)求得所有和为 sum 的连续正数序列。
     */
    public ArrayList<ArrayList<Integer>> FindContinuousSequence1(int sum) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
        for (int i = 1; i <= sum / 2; ++i) {
            for (int j = i + 1; j < sum; ++j) {
                int tmp = 0;
                for (int k = i; k <= j; ++k) {
                    tmp += k;
                }
                if (sum == tmp) {
                    ArrayList<Integer> ans = new ArrayList<>();
                    for (int k = i; k <= j; ++k) {
                        ans.add(k);
                        ret.add(ans);
                    }
                } else if (tmp > sum) {
                    break;
                }
            }
        }
        return ret;
    }

    public ArrayList<ArrayList<Integer>> FindContinuousSequence2(int sum) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
        int tmp = 0;
        for (int i = 1; i <= sum / 2; ++i) {
            for (int j = i; j < sum; ++j) {
                tmp += j; // 将之前每一步的计算结果保留下来。
                if (sum == tmp) {
                    ArrayList<Integer> ans = new ArrayList<>();
                    for (int k = i; k <= j; ++k) {
                        ans.add(k);
                    }
                    ret.add(ans);
                } else if (tmp > sum) {
                    tmp = 0;
                    break;
                }
            }
        }
        return ret;
    }

    // 滑动窗口
    public ArrayList<ArrayList<Integer>> FindContinuousSequence3(int sum) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
        int left = 1, right = 1;
        int tmp = 0;
        while (left <= sum / 2) {
            if (tmp < sum) {
                tmp += right;
                ++right;
            } else if (tmp > sum) {
                tmp -= left;
                ++left;
            } else {
                ArrayList<Integer> ans = new ArrayList<>();
                for (int k = left; k < right; ++k) {
                    ans.add(k);
                }
                ret.add(ans);
                tmp -= left;
                ++left;
            }
        }
        return ret;
    }

    /**
     * (23)一个整型数组里除了两个数字之外，其他的数字都出现了两次。请写程序找出这两个只出现一次的数字。
     *
     * @param array 整型数组
     * @param num1  长度为1的数组，将num1[0]设置为返回结果
     * @param num2  长度为1的数组，将num2[0]设置为返回结果
     */
    public void FindNumsAppearOnce(int[] array, int num1[], int num2[]) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : array) {
            if (map.containsKey(num)) {
                map.put(num, 2);
            } else {
                map.put(num, 1);
            }
        }
        int count = 0;
        for (int k : array) {
            if (map.get(k) == 1) {
                if (count == 0) {
                    num1[0] = k;
                    count++;
                } else {
                    num2[0] = k;
                }
            }
        }
    }

    /**
     * (24)输入一棵二叉树，判断该二叉树是否是平衡二叉树。
     */
    private int depth(TreeNode root) {
        if (root == null) return 0;
        int left = depth(root.left);
        if (left == -1) return -1;
        int right = depth(root.right);
        if (right == -1) return -1;
        if (left - right < (-1) || left - right > 1) {
            return -1;
        } else {
            return 1 + Math.max(left, right);
        }
    }

    public boolean IsBalanced_Solution(TreeNode root) {
        return depth(root) != -1;
    }

    /**
     * (25)输入一棵二叉树，求该树的深度。
     */
    public int TreeDepth(TreeNode root) {
        if (root == null) return 0;
        int left = TreeDepth(root.left);
        int right = TreeDepth(root.right);
        int result = 1 + Math.max(left, right);
        return result;
    }

    /**
     * (26)统计一个数字在排序数组中出现的次数。
     */
    // 暴力破解
    public int GetNumberOfK(int[] array, int k) {
        int number = 0;
        for (int num : array) {
            if (num == k) {
                number++;
            }
        }
        return number;
    }

    // 二分，因数组有序 TODO 统计一个数字在排序数组中出现的次数，一丢丢不太理解。
    private int upperBound(int[] array, int k) {
        int left = 0, right = array.length - 1, mid;
        while (left <= right) {
            mid = (left + right) >> 1;
            if (array[mid] <= k) { // 等于k，left 向右走
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    private int lowerBound(int[] array, int k) {
        int left = 0, right = array.length - 1, mid;
        while (left <= right) {
            mid = (left + right) >> 1;
            if (array[mid] < k) {
                left = mid + 1;
            } else { // 等于k，right 向左走
                right = mid - 1;
            }
        }
        return left;
    }

    public int GetNumberOfK2(int[] array, int k) {
        if (array == null || array.length == 0) {
            return 0;
        }
        int lowerIndex = lowerBound(array, k);
        int upperIndex = upperBound(array, k);
        return upperIndex - lowerIndex;
    }

    /**
     * (27)输入两个链表，找出它们的第一个公共结点。
     * （注意因为传入数据是链表，所以错误测试数据的提示是用其他方式显示的，保证传入数据是正确的）
     */
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        if (pHead1 == null || pHead2 == null) return null;
        ListNode p1 = pHead1;
        ListNode p2 = pHead2;
        while (p1 != p2) {
            p1 = p1 == null ? pHead2 : p1.next;
            p2 = p2 == null ? pHead1 : p2.next;
        }
        return p1;
    }

    /**
     * (28)在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。
     * 输入一个数组,求出这个数组中的逆序对的总数P。并将P对1000000007取模的结果输出。
     * 即输出 P % 1000000007
     * TODO 归并求逆序对数，先这样吧！
     */
    private void MergeSort(int[] array, int start, int end) {
        if (start >= end) return;
        int mid = (start + end) / 2;
        MergeSort(array, start, mid);
        MergeSort(array, mid + 1, end);
        MergeOne(array, start, mid, end);
    }

    int cnt = 0;

    private void MergeOne(int[] array, int start, int mid, int end) {
        int[] tmp = new int[end - start + 1];
        int k = 0, i = start, j = mid + 1;
        while (i <= mid && j <= end) {
            if (array[i] <= array[j]) {
                tmp[k++] = array[i++];
            } else {
                tmp[k++] = array[j++];
                cnt = (cnt + (mid - i + 1)) % 1000000007;
            }
        }
        while (i <= mid) {
            tmp[k++] = array[i++];
        }
        while (j <= end) {
            tmp[k++] = array[j++];
        }
        for (int m = 0; m < k; ++m) {
            array[start + m] = tmp[m];
        }
    }

    public int InversePairs(int[] array) {
        MergeSort(array, 0, array.length - 1);
        return cnt;
    }

    /**
     * (29)在一个字符串(0<=字符串长度<=10000，全部由字母组成)中找到第一个只出现一次的字符,并返回它的位置,
     * 如果没有则返回 -1（需要区分大小写）.（从0开始计数）
     * <p>
     * 本来是用 Map 来写的，（因为不知道 charAt 方法，将 st r用 split 方法转换了）
     */
    public int FirstNotRepeatingChar(String str) {
        if (str == null || str.length() == 0) return -1;
        int[] count = new int[256];
        for (int i = 0; i < str.length(); ++i) {
            count[str.charAt(i)]++;
        }
        for (int i = 0; i < str.length(); ++i) {
            if (count[str.charAt(i)] == 1) {
                return i;
            }
        }
        return -1;
    }

    /**
     * (30)把只包含质因子2、3和5的数称作丑数（Ugly Number）。例如6、8都是丑数，但14不是，因为它包含质因子7。
     * 习惯上我们把1当做是第一个丑数。求按从小到大的顺序的第N个丑数。
     * 2020-07-17 20:45:47 TODO 真的看不懂
     */
    public int GetUglyNumber_Solution(int index) {
        if (index < 7) {
            return index;
        }
        int[] ret = new int[index];
        ret[0] = 1;
        int pos2 = 0, pos3 = 0, pos5 = 0;
        for (int i = 1; i < index; ++i) {
            ret[i] = Math.min(Math.min(ret[pos2] * 2, ret[pos3] * 3), ret[pos5] * 5);
            if (ret[i] == ret[pos2] * 2) pos2++;
            if (ret[i] == ret[pos3] * 3) pos3++;
            if (ret[i] == ret[pos5] * 5) pos5++;
        }
        return ret[index - 1];
    }

    /**
     * (31)输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
     * 例如输入数组{3，32，321}，则打印出这三个数字能排成的最小数字为321323。
     * 思路：两个数字拼接在一起，只有两个顺序。
     * 2020-07-17 21:01:03
     */
    public String PrintMinNumber(int[] numbers) {
        String result = "";
        ArrayList<Integer> list = new ArrayList<>();
        for (int num : numbers) list.add(num);
        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                String s1 = o1 + "" + o2;
                String s2 = o2 + "" + o1;
                return s1.compareTo(s2);
            }
        });
        for (int i : list) result += i;
        return result;
    }

    /**
     * (32)求出任意非负整数区间中1出现的次数（从1 到 n 中 1 出现的次数）。
     * TODO 此题解法详见 https://leetcode-cn.com/problems/1nzheng-shu-zhong-1chu-xian-de-ci-shu-lcof/solution/mian-shi-ti-43-1n-zheng-shu-zhong-1-chu-xian-de-2/
     * 2020-07-17 21:02:59
     */
    // 动态规划
    public int FindGreatestSumOfSubArray1(int[] array) {
        int sz = array.length;
        int[] dp = new int[sz + 1];
        dp[0] = 0;
        for (int i = 1; i <= sz; ++i) {
            dp[i] = 1;
        }
        int ret = array[0];
        for (int i = 1; i <= sz; ++i) {
            dp[i] = Math.max(array[i - 1], dp[i - 1] + array[i - 1]);
            ret = Math.max(ret, dp[i]);
        }
        return ret;
    }

    // 不太完善
    public int FindGreatestSumOfSubArray2(int[] array) {
        int ret = array[0];
        int tmp = 0;
        for (int k : array) {
            if (tmp + k < 0) {
                tmp = 0;
            } else {
                tmp += k;
            }
            ret = Math.max(ret, tmp);
        }
        return ret;
    }

    /**
     * (33)输入n个整数，找出其中最小的K个数。
     * 例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4,。
     */
    public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        ArrayList<Integer> ret = new ArrayList<>();
        return ret;
    }

    // 反转数。
    public boolean isPalindrome(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) return false;
        int revertedNumber = 0;
        while (x > revertedNumber) {
            revertedNumber = revertedNumber * 10 + x % 10;
            x /= 10;
        }
        // 当数字长度为奇数时，我们可 vertedNumber/10 去除处于中位的数字。
        return x == revertedNumber || x == revertedNumber / 10;
    }

    public int[] intersect(int[] nums1, int[] nums2) {
//        // 将长度短的数组换到前面。
//        if (nums1.length > nums2.length) {
//            return intersect(nums2, nums1);
//        }
//        HashMap<Integer, Integer> map = new HashMap<>();
//        for (int num : nums1) {
//            int count = map.getOrDefault(num, 0) + 1;
//            map.put(num, count);
//        }
//        int[] ans = new int[nums1.length];
//        int index = 0;
//        for (int num : nums2) {
//            int count = map.getOrDefault(num, 0);
//            if (count > 0) {
//                ans[index++] = num;
//                count--;
//                if (count > 0) {
//                    map.put(num, count);
//                } else {
//                    map.remove(num);
//                }
//            }
//        }
//        return Arrays.copyOfRange(ans, 0, index);
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int min = Math.min(nums1.length, nums2.length);
        int max = Math.max(nums1.length, nums2.length);
        int[] ans = new int[min];
        int i = 0, j = 0, index = 0;
        while (i < max && j < max) {
            if (nums1[i] == nums2[j]) {
                ans[index++] = nums1[i];
                i++;
                j++;
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                j++;
            }
        }
        return Arrays.copyOfRange(ans, 0, index);
    }

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        char[] chars = s.toCharArray();
        int i = 0;
        while (i < chars.length) {
            if (stack.size() == 0) {
                stack.push(chars[i++]);
            }
            char c1 = stack.peek();
            char c2 = chars[i];
            if (c1 == '(' && c2 == ')' || c1 == '[' && c2 == ']' || c1 == '{' && c2 == '}') {
                i++;
                stack.pop();
            } else {
                stack.push(chars[i++]);
            }
        }
        return stack.isEmpty();
    }
}



















