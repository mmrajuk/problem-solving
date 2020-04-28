package com.grci.dfs;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PathSequenceTests {

    @Test
    public void testPathSequence(){
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(0);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(1);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(5);

        PathSequence sequence = new PathSequence();
        assertFalse(sequence.hasPathSequence(root,new int[]{1,0,7}));
        assertTrue(sequence.hasPathSequence(root,new int[]{1,1,6}));
    }
}
