package 牛客网.剑指offer.java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @since 2020/7/15 11:37
 *
 * 如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。
 * 如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。
 * 我们使用Insert()方法读取数据流，使用GetMedian()方法获取当前读取数据的中位数。
 */
public class GetMedian {

    ArrayList<Integer> array = new ArrayList();

    public void Insert(Integer num) {
        array.add(num);
    }

    private void BubbleSort() {
        for (int i = 0; i < array.size() - 1; ++i) {
            for (int j = 0; j < array.size() - 1 - i; ++j) {
                if (array.get(j) > array.get(j + 1)) {
                    int tmp = array.get(j);
                    array.set(j, array.get(j + 1));
                    array.set(j + 1, tmp);
                }
            }
        }
    }

    private void InsertSort(Integer num) {
        if (array.size() == 0) {
            array.add(num);
        } else {
            int index = -1;
            for (int i = 0; i < array.size(); ++i) {
                if (array.get(i) > num) {
                    index = i;
                    break;
                }
            }
            if (index == -1) {
                array.add(num);
            } else {
                array.add(0);
                for (int i = array.size() - 1; i > index; --i) {
                    array.set(i, array.get(i - 1));
                }
                array.set(index, num);
            }
        }
    }

    public Double GetMedian() {
        Collections.sort(array);
        int index = array.size() / 2;
        if (array.size() % 2 != 0) {
            return (double) array.get(index);
        }
        return ((double) (array.get(index - 1)) + (double) array.get(index)) / 2;
    }

    // 维护两个堆
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    });
    int count = 0; // 记录当前个数

    public void insert(Integer num) {
        if (count % 2 == 0) {
            maxHeap.offer(num);
            int max = maxHeap.poll();
            minHeap.offer(max);
        } else {
            minHeap.offer(num);
            int min = minHeap.poll();
            maxHeap.offer(min);
        }
        count++;
    }

    public Double getMedian() {
        // 注意此处的浮点数与整数转换
        if (count % 2 == 0) {
            return ((minHeap.peek() + maxHeap.peek()) / 2.0);
        } else {
            return (double)minHeap.peek();
        }
    }
}
