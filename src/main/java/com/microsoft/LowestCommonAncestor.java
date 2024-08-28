package com.microsoft;

/**
 * 二叉树的最近公共祖先
 */
public class LowestCommonAncestor {
    /**
     * 递归后序遍历二叉树，从叶子结点开始查找，
     * 如果当前节点的左右子树中分别找到了p/q，那么当前节点即其公共祖先；
     * 如果当前节点仅仅是p或q的祖先，则返回当前节点，继续向上查找
     * 出口条件：当前节点为空或等于p/q
     * @param root
     * @param p
     * @param q
     * @return
     */
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
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

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
