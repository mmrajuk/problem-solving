package com.grci.dfs;

public class PathSequence {

    /**
     *
     * @param root
     * @param sequence
     * @return
     *
     * Given a binary tree and a number sequence,
     * find if the sequence is present as a root-to-leaf path in the given tree.
     */
    public boolean hasPathSequence(TreeNode root, int[] sequence){
        return hasPathSequence(root,sequence,0);
    }

    private boolean hasPathSequence(TreeNode root, int[] sequence, int matchingIndex) {
        if(root == null)
            return false;
        if(root.val!=sequence[matchingIndex])
            return false;
        if(root.val == sequence[matchingIndex] && root.left==null && root.right==null)
            return true;
        return hasPathSequence(root.left,sequence,matchingIndex+1)||
                hasPathSequence(root.right,sequence,matchingIndex+1);
    }
}
