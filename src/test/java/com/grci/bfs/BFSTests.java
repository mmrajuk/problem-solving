package com.grci.bfs;

import com.common.TreeNode;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class BFSTests {

    @Test
    public void testLevelOrderTraversal(){
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);

        BFSProblems bfs = new BFSProblems();
        List<List<Integer>> result = bfs.levelOrderTraversal(root, false);
        assertEquals(Lists.newArrayList(Lists.newArrayList(12),Lists.newArrayList(7,1),
                Lists.newArrayList(9,10,5)),result);
    }

    @Test
    public void testReverseLevelOrderTraversal(){
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);

        BFSProblems bfs = new BFSProblems();
        List<List<Integer>> result = bfs.levelOrderTraversal(root, true);
        assertEquals(Lists.newArrayList(Lists.newArrayList(9,10,5),Lists.newArrayList(7,1),
                Lists.newArrayList(12)),result);
    }

    @Test
    public void testzigZagTraversal(){
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        root.right.left.left = new TreeNode(20);
        root.right.left.right = new TreeNode(17);

        BFSProblems bfs = new BFSProblems();
        List<List<Integer>> result = bfs.zigZagTraversal(root);
        assertEquals(Lists.newArrayList(Lists.newArrayList(12),Lists.newArrayList(1,7),
                Lists.newArrayList(9,10,5),Lists.newArrayList(17,20)),result);
    }
}
