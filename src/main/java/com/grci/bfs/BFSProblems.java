package com.grci.bfs;

import com.common.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class BFSProblems {


    /**
     *
     * @param root
     *
     * Given a binary tree, populate an array to represent its level-by-level traversal.
     * You should populate the values of all nodes of each level from left to right in separate sub-arrays.
     *
     * Given a binary tree, populate an array to represent its level-by-level traversal in reverse order, i.e., the lowest level comes first.
     * You should populate the values of all nodes in each level from left to right in separate sub-arrays.
     */
    public List<List<Integer>> levelOrderTraversal(TreeNode root, boolean reverse){
        List<List<Integer>> result = new LinkedList<>();
        if(root == null)
            return null;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            //level size to iterate over
            int lSize = queue.size();
            //placeholder for holding elements in a level
            List<Integer> lElements = new ArrayList<>();
            int i = 0;
            while(i<lSize) {
                TreeNode node = queue.poll();
                lElements.add(node.val);
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
                i++;
            }
            if(reverse)
                result.add(0,lElements);
            else
                result.add(lElements);
        }
        return result;
    }

    /**
     *
     * @param root
     * @return
     *
     * Given a binary tree, populate an array to represent its zigzag level order traversal.
     * You should populate the values of all nodes of the first level from left to right,
     * then right to left for the next level and keep alternating in the same manner for the following levels.
     */
    public List<List<Integer>> zigZagTraversal(TreeNode root){
        List<List<Integer>> result = new ArrayList<>();
        if(root == null)
            return null;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean zigZag = false; //every alternate level should be
        while(!queue.isEmpty()){
            //level size to iterate over
            int lSize = queue.size();
            //placeholder for holding elements in a level
            List<Integer> lElements = new ArrayList<>();
            int i = 0;
            while(i<lSize) {
                TreeNode node = queue.poll();
                if(zigZag)
                    lElements.add(0,node.val);
                else
                    lElements.add(node.val);
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
                i++;
            }

            result.add(lElements);
            zigZag = !zigZag;
        }
        return result;
    }

}
