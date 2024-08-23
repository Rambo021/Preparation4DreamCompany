package com.booking.oa.binarytree;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author ooo
 * @description 二叉树的最近公共祖先
 * https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree/description/
 * @date 2024/8/23 11:35:45
 */
public class LowestCommonAncestor {

    public static void main(String[] args) {
        String s = "  abc de    f  ";
        String[] sAry = s.split(" ");
//        String[] a = new
        for (String s1 : sAry) {

        }
        Arrays.asList(sAry).stream().collect(Collectors.joining(" "));
        System.out.println(sAry);
        // 构建一个示例二叉树
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        /*LowestCommonAncestor lca = new LowestCommonAncestor();
        TreeNode p = root.left.left;
        TreeNode q = root.left.right;
        TreeNode ancestor = lca.lowestCommonAncestor(root, p, q);
        System.out.println("Lowest Common Ancestor: " + ancestor.val);*/
    }

    /**
     * 递归后序遍历：左右中，从下到上
     * 递归出口：当前节点为空或等于p/q
     * @param root
     * @return
     */
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q){
        if (root == null || root == p || root == q){
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left != null && right != null){
            return root;
        }

        return left == null ? right : left;
    }
}

class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
