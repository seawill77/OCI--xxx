package leetcode_1To300;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
public class _56_MergeIntervals {
    /**
     * 56. Merge Intervals
     * For example,
     Given [1,3],[2,6],[8,10],[15,18],
     return [1,6],[8,10],[15,18].

                sta     end
     |___|       |____|
       |_____|       |___|

     time : O(nlogn) space : O(n)

     * @param intervals
     * @return
     */
   class Solution {
    public int[][] merge(int[][] intervals) {
     if (intervals == null || intervals.length <= 1) return intervals;
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        int start = intervals[0][0];
        int end = intervals[0][1];
        List<int[]> res = new ArrayList<>();
        
        for (int[] interval : intervals) {
            if (interval[0] <= end) {
                end = Math.max(end, interval[1]);
            } else {
                res.add(new int[]{start, end});
                start = interval[0];
                end = interval[1];
            }
        }
        res.add(new int[]{start, end});
        
        int[][] ans = new int[res.size()][2];
        
        for (int i = 0; i < res.size(); i++) {
            ans[i][0] = res.get(i)[0];
            ans[i][1] = res.get(i)[1];
        }
        
        return ans;
    }
}
