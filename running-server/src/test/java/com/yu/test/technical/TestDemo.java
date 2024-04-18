package com.yu.test.technical;
import com.yu.test.pojo.TreeNode;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.LinkedList;
import java.util.Queue;


/**
 * @author: lzy
 * @create: 2024-04-01 10:26
 * @Description:
 * @version: v1.0
 */
@SpringBootTest
public class TestDemo {

    @Test
    public void num_1(){
        int[] num = {2,3,1,1,4};
        test(num);
    }

    public int test(int[] nums){
        int n = nums.length - 1;
        int steps = 0;
        while(n > 0){
            for(int i = 0; i < n ; i++){
                if(i + nums[i] >= n){
                    n=i;
                    steps++;
                    break;
                }
            }
        }
        return steps;
    }

    @Test
    public void num_238() {
        int[] nums = new int[]{
          1,2,3,4
        };
        int n = nums.length;
        int[] answer = new int[n];
        int temp = 1;
        int j = 0;
        while(n > 0){
            for(int i = 0; i < nums.length; i++){
                if(i == j) continue;
                temp = nums[i] * temp;
            }
            answer[j] = temp;
            j++;
            n--;
            temp = 1;
        }
        for (int i : answer) {
            System.out.println(i+",");
        }
    }

    // 将二叉树的顺序存储变为链式存储
    public static TreeNode buildTree(char[] arr, int index) {
        TreeNode root = null;
        if (index < arr.length) {
            if (arr[index] == '#') {
                return null;
            }
            root = new TreeNode(arr[index]);
            root.left = buildTree(arr, 2 * index + 1);
            root.right = buildTree(arr, 2 * index + 2);
        }
        return root;
    }

    @Test
    public void levelOrderTraverse() {
        char[] arr = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', '#', '#', 'H', '#', '#', 'I' };
        TreeNode T = buildTree(arr, 0);

        if (T == null)
            return;

        Queue<TreeNode> q = new LinkedList<>();
        TreeNode node = null;
        // 首先根结点入队
        q.add(T);
        while (!q.isEmpty()) {
            // 队头出队
            node = q.remove();
            System.out.print(node.val);

            // 左右子结点入队
            if (node.left != null) {
                q.add(node.left);
            }
            if (node.right != null) {
                q.add(node.right);
            }
        }
    }

}
