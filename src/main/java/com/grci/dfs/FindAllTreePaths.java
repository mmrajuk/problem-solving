package com.grci.dfs;

import java.util.ArrayList;
import java.util.List;

public class FindAllTreePaths {

    public static List<List<Integer>> findAllPaths(TreeNode root, int sum) {
        List<List<Integer>> allPaths = new ArrayList<>();
        List<Integer> currentPath = new ArrayList<>();
        findPath(root,sum,currentPath,allPaths);
        return allPaths;
    }

    private static void findPath(TreeNode root, int sum,
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


    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        int sum = 23;
        List<List<Integer>> result = FindAllTreePaths.findAllPaths(root, sum);
        System.out.println("Tree paths with sum " + sum + ": " + result);
    }
}
