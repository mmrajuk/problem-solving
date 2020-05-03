package com.grci.dfs;

import com.common.TreeNode;

public class Diameter {

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
}
