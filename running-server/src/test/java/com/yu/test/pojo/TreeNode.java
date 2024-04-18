package com.yu.test.pojo;

/**
 * @author: lzy
 * @create: 2024-04-07 15:40
 * @Description:
 * @version: v1.0
 */
public class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
        TreeNode() {}
        public TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
}
