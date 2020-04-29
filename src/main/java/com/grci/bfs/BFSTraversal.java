package com.grci.bfs;

import com.common.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class BFSTraversal {


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
    public List<List<Integer>> levelOrderTraversal(TreeNode root){
        List<List<Integer>> result = new ArrayList<>();
        if(root == null)
            return null;
        Queue<TreeNode> queue = new LinkedList<>();//Queue is implemented using Linked List in Java
        queue.add(root);
        while(!queue.isEmpty()){
            //level size to iterate over
            int lSize = queue.size();
            //placeholder for holding elements in a level
            List<Integer> lElements = new ArrayList<>();
            for(int i=0; i<lSize; i++) {
                TreeNode node = queue.poll();
                lElements.add(node.val);
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }
            result.add(lElements);
        }
        return result;
    }


    /**
     *
     * @param root
     * @return
     *
     * Given a binary tree, populate an array to represent its level-by-level traversal in reverse order,
     * i.e., the lowest level comes first.
     * You should populate the values of all nodes in each level from left to right in separate sub-arrays.
     */
    public List<List<Integer>> reverseLevelOrderTraversal(TreeNode root){
        List<List<Integer>> result = new LinkedList<>();
        if(root == null)
            return null;
        Queue<TreeNode> queue = new LinkedList<>();//Queue is implemented using Linked List in Java
        queue.add(root);
        while(!queue.isEmpty()){
            //level size to iterate over
            int lSize = queue.size();
            //placeholder for holding elements in a level
            List<Integer> lElements = new ArrayList<>();
            for(int i=0; i<lSize; i++) {
                TreeNode node = queue.poll();
                lElements.add(node.val);
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }
            result.add(0,lElements);
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
        Queue<TreeNode> queue = new LinkedList<>();//Queue is implemented using Linked List in Java
        queue.add(root);
        boolean zigZag = false; //every alternate level should be
        while(!queue.isEmpty()){
            //level size to iterate over
            int lSize = queue.size();
            //placeholder for holding elements in a level
            List<Integer> lElements = new ArrayList<>();
            for(int i=0; i<lSize; i++) {
                TreeNode node = queue.poll();
                if(zigZag)
                    lElements.add(0,node.val);
                else
                    lElements.add(node.val);
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }

            result.add(lElements);
            zigZag = !zigZag;
        }
        return result;
    }


    /**
     *
     * @param root
     * @return
     *
     * Given a binary tree, populate an array to represent the averages of all of its levels.
     */
    public List<Double> calculateLevelAverage(TreeNode root){

        List<Double> averages = new ArrayList<>();
        if(root == null)
            return null;
        Queue<TreeNode> queue = new LinkedList<>();//Queue is implemented using Linked List in Java
        queue.add(root);
        while(!queue.isEmpty()){
            //level size to iterate over
            int lSize = queue.size();
            //placeholder for holding elements in a level
            List<Integer> lElements = new ArrayList<>();
            for(int i=0; i<lSize; i++) {
                TreeNode node = queue.poll();
                lElements.add(node.val);
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }
            averages.add(lElements.stream().mapToInt(ele -> ele).average().getAsDouble());
        }
        return averages;

    }


    /**
     *
     * @param root
     * @return
     *
     * Find the minimum depth of a binary tree.
     * The minimum depth is the number of nodes along the shortest path from the root node to the nearest leaf node.
     */
    public int findMinDepth(TreeNode root){
        if(root == null)
            return 0;
        Queue<TreeNode> queue = new LinkedList<>();//Queue is implemented using Linked List in Java
        queue.add(root);
        int mindDepth = 1;//root node.
        while(!queue.isEmpty()){
            //level size to iterate over
            int lSize = queue.size();
            for(int i=0; i<lSize; i++) {
                TreeNode node = queue.poll();
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
                if(node.left==null && node.right==null)
                    return mindDepth;
            }
            mindDepth++;
        }
        return mindDepth;

    }


    /**
     *
     * @param root
     * @param key node value
     * @return
     *
     * Given a binary tree and a node, find the level order successor of the given node in the tree.
     * The level order successor is the node that appears right after the given node in the level order traversal.
     */
    public TreeNode findLevelOrderSuccessor(TreeNode root, int key){
        if(root == null)
            return null;
        Queue<TreeNode> queue = new LinkedList<>();//Queue is implemented using Linked List in Java
        queue.add(root);
        while(!queue.isEmpty()){
            //level size to iterate over
            int lSize = queue.size();
            for(int i=0; i<lSize; i++) {
                TreeNode node = queue.poll();
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
                if(node.val == key)
                    return queue.poll();
            }
        }
        return null;
    }

}
