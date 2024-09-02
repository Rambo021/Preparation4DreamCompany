package com.microsoft;

import java.util.*;

/**
 * 二叉树锯齿形层序遍历
 */
public class ZigzagLevelOrder {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.right = new TreeNode(5);

        ZigzagLevelOrder z = new ZigzagLevelOrder();
        z.zigzagLevelOrder(root);

    }
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Deque<TreeNode> deque = new LinkedList<>();
        deque.offer(root);
        boolean leftToRight = true;

        while (!deque.isEmpty()) {
            int levelSize = deque.size();
            List<Integer> currentLevel = new ArrayList<>();

            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode;
                if (leftToRight) {
                    currentNode = deque.pollFirst();
                    currentLevel.add(currentNode.val);

                    // 从左到右添加子节点
                    if (currentNode.left != null) {
                        deque.offerLast(currentNode.left);
                    }
                    if (currentNode.right != null) {
                        deque.offerLast(currentNode.right);
                    }
                } else {
                    currentNode = deque.pollLast();
                    currentLevel.add(currentNode.val);

                    // 从右到左添加子节点
                    if (currentNode.right != null) {
                        deque.offerFirst(currentNode.right);
                    }
                    if (currentNode.left != null) {
                        deque.offerFirst(currentNode.left);
                    }
                }
            }

            result.add(currentLevel);
            leftToRight = !leftToRight; // 切换方向
        }

        return result;
    }

    static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

}
