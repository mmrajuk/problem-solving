package com.grci.dfs;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

class DFS {


    public boolean hasSumPath(TreeNode root, int sum) {
        if(root == null)
            return false;
        if(root.left == null && root.right == null && sum == root.val)
            return true;

        return hasSumPath(root.left, sum - root.val)
                ||hasSumPath(root.right,sum-root.val);
    }

    public List<List<Integer>> findAllPaths(TreeNode root, int sum) {
        List<List<Integer>> allPaths = new ArrayList<>();
        List<Integer> currentPath = new ArrayList<>();
        findPath(root,sum,currentPath,allPaths);
        return allPaths;
    }

    private void findPath(TreeNode root, int sum,
                                 List<Integer> currentPath,
                                 List<List<Integer>> allPaths){
        if(root == null)
            return;
        currentPath.add(root.val);
        if(root.left==null && root.right==null&&sum==root.val){
            allPaths.add(new ArrayList<>(currentPath));
        } else {
            findPath(root.left,sum-root.val,currentPath,allPaths);
            findPath(root.right, sum-root.val,currentPath,allPaths);
        }
        currentPath.remove(currentPath.size() - 1);
    }

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
     * @param sequence
     * @return
     *
     * Find if given sequence existed in tree path
     */
    public boolean findGivenSequencePath(TreeNode root,int[] sequence){
        return findGivenSequencePath(root,sequence,0);
    }

    private boolean findGivenSequencePath(TreeNode root, int[] sequence, int level) {
        if(root == null)
            return false;
        if(root.val!=sequence[level])
            return false;
        if(root.val == sequence[level] && sequence.length == level + 1)
            return true;
        return findGivenSequencePath(root.left,sequence,level+1)||
                    findGivenSequencePath(root.right,sequence,level+1);
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
        ListIterator<Integer> listIterator = currentPath.listIterator(currentPath.size());
        int pathSum = 0;
        while (listIterator.hasPrevious()){
            pathSum += listIterator.previous();
            if(pathSum == sum){
                sumPaths.add(new ArrayList<>(currentPath));
            }
        }
        countSumPaths(root.left,sum,currentPathSum,currentPath,sumPaths);
        countSumPaths(root.right,sum,currentPathSum,currentPath,sumPaths);
        if(currentPath.size()>0)
            currentPath.remove(currentPath.size()-1);//last index.
    }





    int treeDiameter = 0;
    /**
     *
     * @param root
     * @return
     *
     * The diameter of a tree is the number of nodes on the longest path between any two leaf nodes.
     * The diameter of a tree may or may not pass through the root
     * Note: You can always assume that there are at least two leaf nodes in the given tree.
     */
    public int findTreeDiameter(TreeNode root){
        calculateHeight(root);
        return treeDiameter;
    }

    private int calculateHeight(TreeNode root){

        if(root == null)
            return 0;

        int leftSubTreeHeight = calculateHeight(root.left);
        int rightSubTreeHeight = calculateHeight(root.right);

        int diameter = leftSubTreeHeight + rightSubTreeHeight + 1; // +1 includes current node.

        treeDiameter = Math.max(treeDiameter, diameter);

        return Math.max(leftSubTreeHeight, rightSubTreeHeight) + 1;
    }

    int maxSum = Integer.MIN_VALUE;
    /**
     *
     * @param root
     * @return
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

