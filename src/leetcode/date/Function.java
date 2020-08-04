package leetcode.date;

import java.util.*;

/**
 * @since 2020/7/25 7:59
 */
public class Function {

    // 1201 2020-07-29 11:33:42
    class TODO1 {
        private long lcmAB;
        private long lcmAC;
        private long lcmBC;
        private long lcmABC;

        public int nthUglyNumber(int n, int a, int b, int c) {
            if (n < 1 || a < 1 || b < 1 || c < 1) return -1;
            long lcmAB = lcm(a, b);
            long lcmAC = lcm(a, c);
            long lcmBC = lcm(b, c);
            long lcmABC = lcm(lcmAB, c);
            long low = Math.min(Math.min(a, b), c);
            long high = low * n;
            long res = binarySearch(low, high, a, b, c, n);
            long leftA = res % a;
            long leftB = res % b;
            long leftC = res % c;
            return (int) (res - Math.min(Math.min(leftA, leftB), leftC));
        }

        private long binarySearch(long low, long high, int a, int b, int c, int n) {
            if (low >= high) return low;
            long mid = (low + high) >> 1;
            long count = mid / a + mid / b + mid / c - mid / lcmAB - mid / lcmBC - mid / lcmAC + mid / lcmABC;
            if (count == n) return mid;
            if (count < n) return binarySearch(mid + 1, high, a, b, c, n);
            return binarySearch(low, mid - 1, a, b, c, n);
        }

        // 最小公倍数
        private long lcm(long a, long b) {
            long multi = a * b;
            while (b > 0) {
                long tmp = a % b;
                a = b;
                b = tmp;
            }
            return multi / a;
        }
    }

    // 1477 2020-07-31 14:52:46
    // 此方法超时
    public int maxEvents(int[][] events) {
        Arrays.sort(events, (o1, o2) -> o1[1] == o2[1] ? o1[0] - o2[0] : o1[1] - o2[1]);
        boolean[] flag = new boolean[100001];
        int res = 0;
        for (int[] event : events) {
            for (int day = event[0]; day <= event[1]; day++) {
                if (!flag[day]) {
                    flag[day] = true;
                    res++;
                    break;
                }
            }
        }
        return res;
    }

    // 207 2020-08-04 08:37:55
    // 深度优先搜索，如果在搜索某一节点过程中遇到另一个搜索中的节点则其中存在环，即返回false
    List<List<Integer>> edges;
    int[] visited;
    boolean valid = true;

    // 节点有三种状态，0 未搜索，1 搜索中，2 已完成
    private void dfs(int u) {
        visited[u] = 1;
        for (int v : edges.get(u)) {
            if (visited[v] == 0) {
                dfs(v);
                if (!valid) return;
            } else if (visited[v] == 1) {
                valid = false;
                return;
            }
        }
        visited[u] = 2;
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        edges = new ArrayList<>();
        for (int i = 0; i < numCourses; i++)
            edges.add(new ArrayList<>());
        visited = new int[numCourses];
        for (int[] info : prerequisites)
            edges.get(info[1]).add(info[0]);
        for (int i = 0; i < numCourses && valid; i++)
            if (visited[i] == 0)
                dfs(i);
        return valid;
    }

    class BFS {
        List<List<Integer>> edges;
        int[] indeg;

        public boolean canFinish(int numCourses, int[][] prerequisites) {
            edges = new ArrayList<>();
            for (int i = 0; i < numCourses; i++)
                edges.add(new ArrayList<>());
            indeg = new int[numCourses];
            for (int[] info : prerequisites) {
                edges.get(info[1]).add(info[0]);
                ++indeg[info[0]]; // 出度
            }
            Queue<Integer> queue = new LinkedList<>();
            for (int i = 0; i < numCourses; i++)
                if (indeg[i] == 0)
                    queue.offer(i);
            int visited = 0;
            while (!queue.isEmpty()) {
                ++visited;
                int u = queue.poll();
                for (int v : edges.get(u)) {
                    --indeg[v];
                    if (indeg[v] == 0)
                        queue.offer(v);
                }
            }
            return visited == numCourses;
        }
    }

    // 219 2020-08-04 10:00:16
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) return true;
            set.add(nums[i]);
            if (set.size() > k)
                set.remove(nums[i - k]);
        }
        return false;
    }

    // 970 2020-08-04 09:34:03
    public List<Integer> powerfulIntegers(int x, int y, int bound) {
        Set<Integer> set = new HashSet<>();
        for (int a = 1; a < bound; a *= x) {
            for (int b = 1; a + b <= bound; b *= y) {
                set.add(a + b);
                if (y == 1) break;
            }
            if (x == 1) break;
        }
        return new ArrayList<>(set);
    }

    // 278 2020-08-04 10:13:49
    public int firstBadVersion(int n) {
        int l = 1, r = n;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (isBadVersion(mid))
                r = mid;
            else l = mid + 1;
        }
        return l;
    }

    // 443 2020-08-04 10:30:33
    // 指针 anchor 指向当前读到连续字符串的起始位置
    public int compress(char[] chars) {
        int anchor = 0, write = 0;
        for (int read = 0; read < chars.length; read++) {
            if (read + 1 == chars.length || chars[read + 1] != chars[read]) {
                chars[write++] = chars[anchor];
                if (read > anchor) {
                    for (char c : ("" + (read - anchor + 1)).toCharArray()) {
                        chars[write++] = c;
                    }
                }
                anchor = read + 1;
            }
        }
        return write;
    }

    // 819 2020-08-04 11:22:15
    public String mostCommonWord(String paragraph, String[] banned) {
        paragraph += '.';
        Set<String> banset = new HashSet<>();
        for (String word : banned) banset.add(word);
        Map<String, Integer> count = new HashMap<>();
        String ans = "";
        int ansfreq = 0;
        StringBuilder word = new StringBuilder();
        for (char c : paragraph.toCharArray()) {
            if (Character.isLetter(c))
                word.append(Character.toLowerCase(c));
            else if (word.length() > 0) {
                String finalword = word.toString();
                if (!banset.contains(finalword)) {
                    count.put(finalword, count.getOrDefault(finalword, 0) + 1);
                    if (count.get(finalword) > ansfreq) {
                        ans = finalword;
                        ansfreq = count.get(finalword);
                    }
                }
                word = new StringBuilder();
            }
        }
        return ans;
    }
}

