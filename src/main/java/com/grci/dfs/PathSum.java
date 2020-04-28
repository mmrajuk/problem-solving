package com.grci.dfs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

public class PathSum {

    /**
     *
     * @param root
     * @param sum
     * @return
     * Given a binary tree and a number ‘S’,
     * find if the tree has a path from root-to-leaf such that
     * the sum of all the node values of tha path equals ‘S’.
     */
    public boolean hasMatchingSumPath(TreeNode root, int sum) {
        if(root == null)
            return false;
        //as path sum must be from root to leaf node, the following condition does the trick.
        if(root.left == null //left node null
                && root.right == null && //right node null
                sum == root.val) //sum should be equal to leaf node value,
            // since we are subtracting node value during traverse from top to bottom
            return true;

        return hasMatchingSumPath(root.left, sum - root.val) //subtracting node value from sum
                || hasMatchingSumPath(root.right,sum-root.val);//subtracting node value from sum
    }


    /**
     *
     * @param root
     * @param sum
     * @return
     * Given a binary tree and a number ‘S’,
     * find all paths from root-to-leaf such that the sum of all the node values of each path equals ‘S’.
     */
    public List<List<Integer>> findAllMatchingSumPaths(TreeNode root, int sum) {
        List<List<Integer>> allPaths = new ArrayList<>();
        List<Integer> currentPath = new ArrayList<>();
        findMatchingSumPath(root,sum,currentPath,allPaths);
        return allPaths;
    }

    private void findMatchingSumPath(TreeNode root, int sum,
                                     List<Integer> currentPath,
                                     List<List<Integer>> allPaths){
        if(root == null)
            return;
        currentPath.add(root.val);
        if(root.left==null && root.right==null&&sum==root.val){
            allPaths.add(new ArrayList<>(currentPath));
        } else {
            findMatchingSumPath(root.left,sum-root.val,currentPath,allPaths);
            findMatchingSumPath(root.right, sum-root.val,currentPath,allPaths);
        }
        currentPath.remove(currentPath.size() - 1);//remove leaf node from current path
    }

    /**
     *
     * @param root
     * @return
     * Given a binary tree where each node can only have a digit (0-9) value,
     * each root-to-leaf path will represent a number.
     * Find the total sum of all the numbers represented by all paths.
     */
    public int findAllPathSum(TreeNode root){
        return findPathSum(root, 0);
    }

    public int findPathSum(TreeNode node, int pathSum){
        if(node == null)
            return 0;
        pathSum = pathSum*10 + node.val;

        if(node.left==null && node.right == null)
            return pathSum;
        return findPathSum(node.left,pathSum)+ findPathSum(node.right,pathSum);
    }


    /**
     *
     * @param root
     * @return
     * Given a binary tree and a number ‘S’, find all paths in the tree such that
     * the sum of all the node values of each path equals ‘S’.
     * Please note that the paths can start or end at any node but
     * all paths must follow direction from parent to child (top to bottom).
     */
    public int countSumPaths(TreeNode root, int sum){
        if(root == null)
            return 0;
        List<List<Integer>> sumPaths = new ArrayList<>();
        List<Integer> currentPath = new ArrayList<>();
        countSumPaths(root,sum,0,currentPath,sumPaths);
        return sumPaths.size();
    }

    private void countSumPaths(TreeNode root, int sum, int currentPathSum,List<Integer> currentPath ,
                               List<List<Integer>> sumPaths) {
        if(root == null){
            return;
        }
        currentPath.add(root.val);
        //find if there is a sub path sum matching at currently added node
        ListIterator<Integer> listIterator = currentPath.listIterator(currentPath.size());
        int pathSum = 0;
        List<Integer> subPath = new ArrayList<>();
        while (listIterator.hasPrevious()){
            int val = listIterator.previous();
            pathSum += val;
            subPath.add(val);
            if(pathSum == sum){
                Collections.reverse(subPath);
                sumPaths.add(subPath);
            }
        }
        countSumPaths(root.left,sum,currentPathSum,currentPath,sumPaths);
        countSumPaths(root.right,sum,currentPathSum,currentPath,sumPaths);
        currentPath.remove(currentPath.size()-1);//last index.
    }

    int maxSum = Integer.MIN_VALUE;
    /**
     *
     * @param root
     * @return
     * Find the path with the maximum sum in a given binary tree.
     * Write a function that returns the maximum sum.
     * A path can be defined as a sequence of nodes between any two nodes and doesn’t necessarily pass through the root.
     */
    public int findTreeMaxSum(TreeNode root){
        calculateSum(root);
        return maxSum;
    }

    private int calculateSum(TreeNode root){

        if(root == null)
            return 0;

        int leftSubTreeSum = calculateSum(root.left);
        int rightSubTreeSum = calculateSum(root.right);

        leftSubTreeSum = Math.max(leftSubTreeSum, 0);
        rightSubTreeSum = Math.max(rightSubTreeSum, 0);

        int sum = leftSubTreeSum + rightSubTreeSum + root.val; // +1 includes current node.

        maxSum = Math.max(maxSum , sum);

        return Math.max(leftSubTreeSum, rightSubTreeSum) + root.val;
    }
}
