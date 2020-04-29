package com.grci.bfs;

import com.common.TreeNode;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BFSTraversalTest {

    @Test
    public void testLevelOrderTraversal(){
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);

        BFSTraversal bfs = new BFSTraversal();
        List<List<Integer>> result = bfs.levelOrderTraversal(root);
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

        BFSTraversal bfs = new BFSTraversal();
        List<List<Integer>> result = bfs.reverseLevelOrderTraversal(root);
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

        BFSTraversal bfs = new BFSTraversal();
        List<List<Integer>> result = bfs.zigZagTraversal(root);
        assertEquals(Lists.newArrayList(Lists.newArrayList(12),Lists.newArrayList(1,7),
                Lists.newArrayList(9,10,5),Lists.newArrayList(17,20)),result);
    }

    @Test
    public void testLevelAverage(){
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);

        BFSTraversal bfs = new BFSTraversal();
        List<Double> result = bfs.calculateLevelAverage(root);
        assertEquals(Lists.newArrayList(12.0,4.0,6.5),result);
    }

    @Test
    public void testMinDepth(){
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);

        BFSTraversal bfs = new BFSTraversal();
        assertEquals(2,bfs.findMinDepth(root));
        root.left.left = new TreeNode(9);
        root.right.left.left = new TreeNode(11);
        assertEquals(3,bfs.findMinDepth(root));
    }
}
