# 算法回顾
> 数组、链表、哈希表、字符串以及双指针和栈队列已经很熟悉了，现在回顾一下：
> 1. 二叉树
>    - 理论知识；
>    - 递归遍历；
>    - 迭代遍历(重点)；
>    - 层序遍历；
>    - 构造二叉树；
>    - 二叉搜索树；
> 2. 回溯算法
>    - 理论知识；
>    - 组合问题；
>    - 子集问题；
> 3. 贪心算法
>    - 理论知识；
>    - 练习典型问题
> 4. 动态规划
>    - 理论知识；
>    - 简单问题练习；
>    - 01背包和典型问题；
>    - 完全背包和典型问题
> 5. 图和单调栈问题


## 一、二叉树
### 理论知识
1. 二叉树种类
 - 满二叉树：如果一棵二叉树只有度为0的结点和度为2的结点，并且度为0的结点在同一层上，则这棵二叉树为满二叉树。深度为k，有2^k-1个节点
 - 完全二叉树：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。若最底层为第 h 层（h从1开始），则该层包含 1~ 2^(h-1) 个节点。
2. 通过中序+后序/前序遍历可以唯一确定一颗二叉树

### 二叉树的遍历（Traverse）
#### 递归遍历（Recursive /rɪˈkɜːrsɪv/ Traversal）
递归遍历非常容易实现，主要需要通过挪动根节点的位置来决定各种顺序的遍历
- 前序遍历（preorder）
- 中序遍历（inorder）
- 后序遍历（postorder）

```java
public class Solution{
  public void traversal(TreeNode root, List<TreeNode> list){
    // exit
    if(root == null){
      return;
    }

    // logic
    list.add(root); // root node，it decides the sequnce of traversal
    traversal(root.left, list); // left
    traversal(root.right, list); // right
  }
}  
```

#### 迭代遍历（Iterative /ˈɪtərətɪv/ Traversal）
> 迭代遍历的统一写法：利用栈实现
```java
public class Solution{
  private List<Integer> ans = new ArrayList<>();
  public List<Integer> commonTraversal(TreeNode root) {
    // 使用栈模拟遍历过程：DFS，深度优先遍历，直到遇到叶子节点
    Stack<TreeNode> s = new Stack<>();
    if (root != null){
        s.push(root);
    }

    while(!s.isEmpty()){
      TreeNode node = s.pop();
      // 遇到叶子节点时将该节点加入结果列表
      if(node.left == null && node.right == null){
        ans.add(node.val);
      }else{
        // 如果左右子节点不为空则先入栈，因为时先入后出，所以一定是先将右子节点入栈
        if(node.right != null){
          s.push(node.right);
          node.right = null;
        }
        if(node.left != null){
          s.push(node.left);
          node.left = null;
        }
        // 根据前中后的遍历顺序，调整该节点的入栈顺序：最后入栈的最先处理
        s.push(node);
      }
    }
    return ans;
  }
}
```

#### 层序遍历（Level Order traversal）
> 使用队列存储每一层的节点子节点，先左后右，本层遍历完成后，下一层的所有子节点也都放入队列中等待下一次遍历了
```java
public class Solution{
    private List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        if(root != null){
            q.offer(root);
        }

        while(!q.isEmpty()){
            int n = q.size();
            List<Integer> list = new ArrayList<>();
            for(int i = 0; i < n; i++){
                TreeNode node = q.poll();
                list.add(node.val);
                if(node.left != null){
                    q.offer(node.left);
                }
                if(node.right != null){
                    q.offer(node.right);
                }
            }
            ans.add(list);
        }
        return ans;
    }
}
```

