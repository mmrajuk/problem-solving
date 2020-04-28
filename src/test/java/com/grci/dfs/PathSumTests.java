package com.grci.dfs;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class PathSumTests {


    @Test
    public void testMatchingSumPath(){

        PathSum tps = new PathSum();

        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);

        assertTrue(tps.hasMatchingSumPath(root,23));
        assertFalse(tps.hasMatchingSumPath(root,16));
    }

    @Test
    public void testFindAllMatchingSumPaths(){
        PathSum tps = new PathSum();
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);

        List<List<Integer>> allPathsList = tps.findAllMatchingSumPaths(root,23);

        assertEquals(allPathsList.size(),2);
        assertEquals(allPathsList.get(0).get(0).intValue(),12);
        assertEquals(allPathsList.get(0).get(1).intValue(),7);
        assertEquals(allPathsList.get(0).get(2).intValue(),4);
        assertEquals(allPathsList.get(1).get(0).intValue(),12);
        assertEquals(allPathsList.get(1).get(1).intValue(),1);
        assertEquals(allPathsList.get(1).get(2).intValue(),10);
    }

    @Test
    public void testFindAllPathSum(){
        PathSum tps = new PathSum();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(0);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(1);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(5);

        assertEquals(tps.findAllPathSum(root),332);
    }



    @Test
    public void testCountSumPaths(){
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        PathSum tps = new PathSum();
        assertEquals(2,tps.countSumPaths(root,11));

        root = new TreeNode(1);
        root.left = new TreeNode(7);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(5);
        root.right = new TreeNode(9);
        root.right.left = new TreeNode(2);
        root.right.right = new TreeNode(3);
        assertEquals(3,tps.countSumPaths(root,12));

    }

    @Test
    public void testTreeMaxSum(){
        PathSum tps = new PathSum();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        assertEquals(tps.findTreeMaxSum(root),6);

        PathSum tps1 = new PathSum();
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(6);
        root.right.left.left = new TreeNode(7);
        root.right.left.right = new TreeNode(8);
        root.right.right.left = new TreeNode(9);
        assertEquals(tps1.findTreeMaxSum(root),31);

        PathSum tps2 = new PathSum();
        root = new TreeNode(-1);
        root.left = new TreeNode(-3);
        assertEquals(tps2.findTreeMaxSum(root),-1);
    }
}
