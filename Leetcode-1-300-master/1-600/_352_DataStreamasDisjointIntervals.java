package leetcode_1To300;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * 本代码来自 Cspiration，由 @Cspiration 提供
 * 题目来源：http://leetcode.com
 * - Cspiration 致力于在 CS 领域内帮助中国人找到工作，让更多海外国人受益
 * - 现有课程：Leetcode Java 版本视频讲解（1-900题）（上）（中）（下）三部
 * - 算法基础知识（上）（下）两部；题型技巧讲解（上）（下）两部
 * - 节省刷题时间，效率提高2-3倍，初学者轻松一天10题，入门者轻松一天20题
 * - 讲师：Edward Shi
 * - 官方网站：https://cspiration.com
 * - 版权所有，转发请注明出处
 */
public class _352_DataStreamasDisjointIntervals {

    /**
     * 352. Data Stream as Disjoint Intervals

     treeMap : (0,3) (7,7)

     lowerKey = null
     higherKey = 1
     val = 0

     */
    
    class SummaryRanges {

    TreeMap<Integer, int[]> map;
    /** Initialize your data structure here. */
    public SummaryRanges() {
        map = new TreeMap<>();
    }
    
    public void addNum(int val) {
        Integer floor = map.floorKey(val);
        Integer ceiling = map.ceilingKey(val);
        
        int[] insert = new int[2];
        insert[0] = val;
        insert[1] = val;
        
        if (floor != null) {
            //[1,3] [2] ==> [1,3]
            if (map.get(floor)[1] >= val) {
                return;
            } else {
                if (map.get(floor)[1] == val-1) {
                    insert[0] = floor;
                    insert[1] = val;
                    map.remove(floor);
                }
            }
        }
        
        if (ceiling != null) {
            if (map.get(ceiling)[0] == val + 1) {
                insert[1] = map.get(ceiling)[1];
                map.remove(val+1);
            }
        }
        
        map.put(insert[0], insert);
    }
    
    public int[][] getIntervals() {
        return map.values().toArray(new int[map.size()][2]);
    }
}
    
    
    

    TreeMap<Integer, Interval> treeMap;

    public _352_DataStreamasDisjointIntervals() {
        treeMap = new TreeMap<>();
    }

    // time : O(logn)
    public void addNum(int val) {
        if (treeMap.containsKey(val)) return;
        Integer lowerKey = treeMap.lowerKey(val);
        Integer higherKey = treeMap.higherKey(val);
        if (lowerKey != null && higherKey != null && treeMap.get(lowerKey).end + 1 == val
                && val + 1 == treeMap.get(higherKey).start) {
            treeMap.get(lowerKey).end = treeMap.get(higherKey).end;
            treeMap.remove(higherKey);
        } else if (lowerKey != null && val <= treeMap.get(lowerKey).end + 1) {
            treeMap.get(lowerKey).end = Math.max(treeMap.get(lowerKey).end, val);
        } else if (higherKey != null && treeMap.get(higherKey).start - 1 == val) {
            treeMap.put(val, new Interval(val, treeMap.get(higherKey).end));
            treeMap.remove(higherKey);
        } else {
            treeMap.put(val, new Interval(val, val));
        }
    }

    public List<Interval> getIntervals() {
        return new ArrayList<>(treeMap.values());
    }
}
