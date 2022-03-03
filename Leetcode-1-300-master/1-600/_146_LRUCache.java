package leetcode_1To300;

import java.util.HashMap;

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
public class _146_LRUCache {

    /**
     * 146. LRU Cache
     * Design and implement a data structure for Least Recently Used (LRU) cache.
     * It should support the following operations: get and put.

     get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
     put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity,
     it should invalidate the least recently used item before inserting a new item.

     Follow up:
     Could you do both operations in O(1) time complexity?

     Example:

     _146_LRUCache cache = new _146_LRUCache( 2  capacity  );

    cache.put(1, 1);
    cache.put(2, 2);
    cache.get(1);       // returns 1
    cache.put(3, 3);    // evicts key 2
    cache.get(2);       // returns -1 (not found)
    cache.put(4, 4);    // evicts key 1
    cache.get(1);       // returns -1 (not found)
    cache.get(3);       // returns 3
    cache.get(4);       // returns 4

    HashMap + Double Linked List

     插入：1，不存在 -> capacity -> 1,head = null 2,head != null
          2，存在
     取出：1，不存在
          2，存在
     => 排序

     time : O(1)
     **/

   class LRUCache {
    
    Map<Integer, ListNode> map = null;
    int capacity = 0;
    ListNode head = new ListNode();
    ListNode tail = new ListNode();
    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        ListNode node = map.get(key);
        if(node != null) {
            removeNode(node);
            addNode(node);
            return node.val;
        }
        return -1;
    }

    public void put(int key, int value) {
        ListNode node = map.get(key);
        if(node != null) {
            removeNode(node);
            node.val = value;
            addNode(node);
        } else {
            if(map.size() == capacity) {
                map.remove(tail.prev.key);
                removeNode(tail.prev);
            }
            ListNode newNode = new ListNode();
            newNode.key = key;
            newNode.val = value;
            map.put(key, newNode);
            addNode(newNode);
        }
    }
    
    private void addNode(ListNode node) {
        ListNode temp = head.next;
        head.next = node;
        node.next = temp;
        temp.prev = node;
        node.prev = head;
    }
    
    private void removeNode(ListNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    
    class ListNode {
        int key;
        int val;
        ListNode next;
        ListNode prev;
    }
}
