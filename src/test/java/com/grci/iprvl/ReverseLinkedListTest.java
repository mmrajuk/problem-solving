package com.grci.iprvl;

import com.common.Node;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


public class ReverseLinkedListTest {


    @Test
    public void testIterativeApproach(){
        ReverseLinkedList rll = new ReverseLinkedList();

        // 2->4->6>8>10
        Node head = new Node(2);
        head.next = new Node(4);
        head.next.next = new Node(6);
        head.next.next.next = new Node(8);
        head.next.next.next.next = new Node(10);

        Node reverseLinkedList = rll.iterativeReverse(head);

        assertEquals(reverseLinkedList.data,10);
        assertEquals(reverseLinkedList.next.data,8);
        assertEquals(reverseLinkedList.next.next.data,6);
        assertEquals(reverseLinkedList.next.next.next.data,4);
        assertEquals(reverseLinkedList.next.next.next.next.data,2);
        assertNull(reverseLinkedList.next.next.next.next.next);
    }

    @Test
    public void testRecursiveApproach(){
        ReverseLinkedList rll = new ReverseLinkedList();

        // 2->4->6>8>10
        Node head = new Node(2);
        head.next = new Node(4);
        head.next.next = new Node(6);
        head.next.next.next = new Node(8);
        head.next.next.next.next = new Node(10);

        Node reverseLinkedList = rll.recursiveReverse(head);

        assertEquals(reverseLinkedList.data,10);
        assertEquals(reverseLinkedList.next.data,8);
        assertEquals(reverseLinkedList.next.next.data,6);
        assertEquals(reverseLinkedList.next.next.next.data,4);
        assertEquals(reverseLinkedList.next.next.next.next.data,2);
        assertNull(reverseLinkedList.next.next.next.next.next);
    }


    @Test
    public void testReverseSubList(){
        ReverseLinkedList rll = new ReverseLinkedList();

        // 1->2->3->4->5
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);

        Node reverseLinkedList = rll.reverseSubList(head,2,4);

        assertEquals(reverseLinkedList.data,1);
        assertEquals(reverseLinkedList.next.data,4);
        assertEquals(reverseLinkedList.next.next.data,3);
        assertEquals(reverseLinkedList.next.next.next.data,2);
        assertEquals(reverseLinkedList.next.next.next.next.data,5);
        assertNull(reverseLinkedList.next.next.next.next.next);
    }

    @Test
    public void testReverseFirstNNodes(){

        ReverseLinkedList rll = new ReverseLinkedList();

        // 1->2->3->4->5
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);

        Node reverseLinkedList = rll.reverseSubList(head,1,4);

        assertEquals(reverseLinkedList.data,4);
        assertEquals(reverseLinkedList.next.data,3);
        assertEquals(reverseLinkedList.next.next.data,2);
        assertEquals(reverseLinkedList.next.next.next.data,1);
        assertEquals(reverseLinkedList.next.next.next.next.data,5);
        assertNull(reverseLinkedList.next.next.next.next.next);

    }

    /**
     * When N is even reverse first n/2 nodes and second n/2 nodes
     */
    @Test
    public void testReverseFirstNBY2AndSecondNBY2NodesWhenNIsEven(){

        ReverseLinkedList rll = new ReverseLinkedList();

        // 1->2->3->4
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);

        Node reverseLinkedList = rll.reverseSubList(head, 1, 4 / 2);
        reverseLinkedList = rll.reverseSubList(reverseLinkedList,4/2+1,4);

        assertEquals(reverseLinkedList.data,2);
        assertEquals(reverseLinkedList.next.data,1);
        assertEquals(reverseLinkedList.next.next.data,4);
        assertEquals(reverseLinkedList.next.next.next.data,3);
        assertNull(reverseLinkedList.next.next.next.next);

    }

    /**
     * When N is odd, keep mid ele as is and reverse first n/2 nodes and second n/2 nodes
     */
    @Test
    public void testReverseFirstNBY2AndSecondNBY2NodesWhenNIsOdd(){

        ReverseLinkedList rll = new ReverseLinkedList();

        // 1->2->3->4->5
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);

        Node reverseLinkedList = rll.reverseSubList(head, 1, 5 / 2);
        reverseLinkedList = rll.reverseSubList(reverseLinkedList,5/2+2,5);

        assertEquals(reverseLinkedList.data,2);
        assertEquals(reverseLinkedList.next.data,1);
        assertEquals(reverseLinkedList.next.next.data,3);
        assertEquals(reverseLinkedList.next.next.next.data,5);
        assertEquals(reverseLinkedList.next.next.next.next.data,4);
        assertNull(reverseLinkedList.next.next.next.next.next);

    }

    @Test
    public void testReverseEveryKSizeSublist(){

        ReverseLinkedList rll = new ReverseLinkedList();

        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);
        head.next.next.next.next.next.next = new Node(7);
        head.next.next.next.next.next.next.next = new Node(8);

        Node revEveryKSublist = rll.reverseEveryKSublist(head,3);

        assertEquals(revEveryKSublist.data,3);
        assertEquals(revEveryKSublist.next.data,2);
        assertEquals(revEveryKSublist.next.next.data,1);
        assertEquals(revEveryKSublist.next.next.next.data,6);
        assertEquals(revEveryKSublist.next.next.next.next.data,5);
        assertEquals(revEveryKSublist.next.next.next.next.next.data,4);
        assertEquals(revEveryKSublist.next.next.next.next.next.next.data,8);
        assertEquals(revEveryKSublist.next.next.next.next.next.next.next.data,7);
        assertNull(revEveryKSublist.next.next.next.next.next.next.next.next);
    }

    @Test
    public void testReverseEveryKSizeAlternativeSublist(){

        ReverseLinkedList rll = new ReverseLinkedList();

        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);
        head.next.next.next.next.next.next = new Node(7);
        head.next.next.next.next.next.next.next = new Node(8);

        Node revEveryKSublist = rll.reverseEveryAlternateKSublist(head,2);

        assertEquals(revEveryKSublist.data,2);
        assertEquals(revEveryKSublist.next.data,1);
        assertEquals(revEveryKSublist.next.next.data,3);
        assertEquals(revEveryKSublist.next.next.next.data,4);
        assertEquals(revEveryKSublist.next.next.next.next.data,6);
        assertEquals(revEveryKSublist.next.next.next.next.next.data,5);
        assertEquals(revEveryKSublist.next.next.next.next.next.next.data,7);
        assertEquals(revEveryKSublist.next.next.next.next.next.next.next.data,8);
        assertNull(revEveryKSublist.next.next.next.next.next.next.next.next);
    }
    
    @Test
    public void testRotateList_1(){
        ReverseLinkedList rll = new ReverseLinkedList();
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);

        Node rotate = rll.rotate(head, 3);

        assertEquals(rotate.data,4);
        assertEquals(rotate.next.data,5);
        assertEquals(rotate.next.next.data,6);
        assertEquals(rotate.next.next.next.data,1);
        assertEquals(rotate.next.next.next.next.data,2);
        assertEquals(rotate.next.next.next.next.next.data,3);
        assertNull(rotate.next.next.next.next.next.next);
    }

    @Test
    public void testRotateList_2(){
        ReverseLinkedList rll = new ReverseLinkedList();
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);

        Node rotate = rll.rotate(head, 8);

        assertEquals(rotate.data,3);
        assertEquals(rotate.next.data,4);
        assertEquals(rotate.next.next.data,5);
        assertEquals(rotate.next.next.next.data,1);
        assertEquals(rotate.next.next.next.next.data,2);
        assertNull(rotate.next.next.next.next.next);
    }

}
