package leetcode_301To600;

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



class Codec {

    // Encodes a tree to a single string.
    public String serialize(Node root) {
        if (root == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Queue<Node> queue = new LinkedList<Node>();
        queue.offer(root);
        sb.append(root.val).append(",").append(root.children.size()).append(",");
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            for (Node node : cur.children) {
                queue.offer(node);
                sb.append(node.val).append(",").append(node.children.size()).append(",");
            }
        }
        return sb.toString(); 
    }

    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        if (data.length() == 0) {
            return null;
        }
        String[] nodes = data.split(",");
        Queue<Node> queue = new LinkedList<Node>();
        Queue<Integer> childQueue = new LinkedList<Integer>();
        Node root = new Node(Integer.valueOf(nodes[0]));
        queue.offer(root);
        childQueue.offer(Integer.valueOf(nodes[1]));
        int i = 2;
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            cur.children = new ArrayList<>();
            int n = childQueue.poll();
            for (int j = 0; j < n; j++) {
                Node child = new Node(Integer.valueOf(nodes[i++]));
                childQueue.offer(Integer.valueOf(nodes[i++]));
                queue.offer(child);
                cur.children.add(child);
            }
        }
        return root;
    }
}
















public class _428_SerializeandDeserializeNaryTree {

    /**
     * time : O(n)
     * space : O(n)
     * @param root
     * @return
     */
    // Encodes a tree to a single string.
    public String serialize(Node root) {
        List<String> list = new LinkedList<>();
        serializeHelper(root, list);
        return String.join(",",list);
    }

    private void serializeHelper(Node root, List<String> list) {
        if (root == null) {
            return;
        }
        list.add(String.valueOf(root.val));
        list.add(String.valueOf(root.children.size()));
        for (Node child : root.children) {
            serializeHelper(child, list);
        }
    }

    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        if (data == null || data.length() == 0) {
            return null;
        }
        String[] s = data.split(",");
        Queue<String> queue = new LinkedList<>(Arrays.asList(s));
        return deserializeHelper(queue);
    }

    private Node deserializeHelper(Queue<String> queue) {
        Node root = new Node();
        root.val = Integer.parseInt(queue.poll());
        int size = Integer.parseInt(queue.poll());
        root.children = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            root.children.add(deserializeHelper(queue));
        }
        return root;
    }

}
