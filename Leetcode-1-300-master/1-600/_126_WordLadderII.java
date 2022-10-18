package leetcode_1To300;

import java.util.*;

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
public class _126_WordLadderII {
    /**
     * 126. Word Ladder II
     * Given two words (beginWord and endWord), and a dictionary's word list,
     * find all shortest transformation sequence(s) from beginWord to endWord, such that:

     Only one letter can be changed at a time
     Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
     For example,

     Given:
     beginWord = "hit"
     endWord = "cog"
     wordList = ["hot","dot","dog","lot","log","cog"]

     Return
     [
     ["hit","hot","dot","dog","cog"],
     ["hit","hot","lot","log","cog"]
     ]

     BFS + DFS

     无向图 -> BFS -> 树 -> DFS -> 结果

     hit -> hot -> dot -> dog - cog
                -> lot -> log - cog

     map : hot (hit)
           dot (hot)
           lot (hot)
           dog (dot)
           log (lot)
           cog (dog,log)

     time : O(V + E) * wordList(max(length))  不确定
            O(n ^ 2)
     space : O(n)

     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    
    class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<String> path = new ArrayList<>();
        List<List<String>> result = new ArrayList<List<String>>();
        HashMap<String, List<String>> graph = new HashMap<String, List<String>>();
        HashSet<String> dict = new HashSet<>(wordList);
        buildGraph(beginWord, endWord, graph, dict);
        dfs(beginWord, endWord, graph, path, result);
        return result;
    }
    
    private void buildGraph(String beginWord, String endWord, HashMap<String, List<String>> graph, HashSet<String> dict) {
        HashSet<String> visited = new HashSet<>();
        HashSet<String> toVisit = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        toVisit.add(beginWord);
        boolean foundEnd = false;
        
        while(!queue.isEmpty()) {
            visited.addAll(toVisit);
            toVisit.clear();
            int count = queue.size();
            
            for (int i = 0; i < count; i++) {
                String word = queue.poll();
                List<String> children = getNextLevel(word, dict);
                for (String child : children) {
                    if (child.equals(endWord)) foundEnd = true;
                    if (!visited.contains(child)) {
                        if (!graph.containsKey(word)) {
                            graph.put(word, new ArrayList<String>());
                        }
                        graph.get(word).add(child);
                    }
                    if (!visited.contains(child) && !toVisit.contains(child)) {
                        queue.offer(child);
                        toVisit.add(child);
                    }
                }
            }
            
            if (foundEnd) break;
        }
    }
    
    private List<String> getNextLevel(String word, HashSet<String> dict) {
        List<String> result = new ArrayList<>();
        char[] chs = word.toCharArray();
        
        for (int i = 0; i < chs.length; i++) {
            for (char c = 'a'; c <= 'z'; c++) {
                if (chs[i] == c) continue;
                char t = chs[i];
                chs[i] = c;
                String target = String.valueOf(chs);
                if (dict.contains(target)) result.add(target);
                chs[i] = t;
            }
        }
        
        return result;
    }
    
    private void dfs(String curWord, String endWord, HashMap<String, List<String>> graph, List<String> path, List<List<String>> result) {
        path.add(curWord);
        
        if (curWord.equals(endWord)) result.add(new ArrayList<String>(path));
        else if (graph.containsKey(curWord)) {
            for (String nextWord : graph.get(curWord)) {
                dfs(nextWord, endWord, graph, path, result);
            }
        }

        path.remove(path.size()-1);
    }
}
    
    
    
************************* ************************* *************************    *************************
    
    
    


    class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        
        List<List<String>> res = new ArrayList<>();
        if (wordList.size() == 0) return res;

        boolean found = false;

        Queue<String> queue = new LinkedList<>();
        HashSet<String> unvisited = new HashSet<>(wordList);
        HashSet<String> visited = new HashSet<>();

        HashMap<String, List<String>> map = new HashMap<>();

        queue.offer(beginWord);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int s = 0; s < size; s++) {
                String word = queue.poll();
                for (int i = 0; i < word.length(); i++) {
                StringBuilder builder = new StringBuilder(word);
                for (char ch = 'a'; ch <= 'z'; ch++) {
                    builder.setCharAt(i, ch);
                    String newWord = builder.toString();
                    if (unvisited.contains(newWord)) {
                        if (visited.add(newWord)) {
                            queue.offer(newWord);
                        }
                        if (map.containsKey(newWord)) {
                            map.get(newWord).add(word);
                        } else {
                            List<String> list = new ArrayList<>();
                            list.add(word);
                            map.put(newWord, list);
                        }
                        if (newWord.equals(endWord)) {
                            found = true;
                            }
                        }
                    }
                }
            }
            if (found) break;
            unvisited.removeAll(visited);
            visited.clear();
        }
        dfs(res, new ArrayList<>(), map, endWord, beginWord);
        return res;
    }

    private void dfs(List<List<String>> res, List<String> list, HashMap<String, List<String>> map, String word, String start) {
        if (word.equals(start)) {
            list.add(0, start);
            res.add(new ArrayList<>(list));
            // list.remove(list.size - 1)
            list.remove(0);
            return;
        }
        list.add(0, word);
        if (map.get(word) != null) {
            for (String s : map.get(word)) {
                dfs(res, list, map, s, start);
            }
        }
        list.remove(0);
        
    }
}
