package com.grci.dfs;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DiameterTest {

    @Test
    public void testTreeDiameter() {

        Diameter diameter = new Diameter();

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(6);
        assertEquals(diameter.findTreeDiameter(root),5);

        root.left.left = null;
        root.right.left.left = new TreeNode(7);
        root.right.left.right = new TreeNode(8);
        root.right.right.left = new TreeNode(9);
        root.right.left.right.left = new TreeNode(10);
        root.right.right.left.left = new TreeNode(11);
        assertEquals(diameter.findTreeDiameter(root),7);
    }
}
