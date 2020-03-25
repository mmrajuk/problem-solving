package com.grci.iprvl;

import com.common.Node;

public class ReverseLinkedList {


    /**
     *
     * @param head
     * @return
     */
    public Node recursiveReverse(Node head) {
        if(head.next == null) {
            return head;
        }
        Node n = recursiveReverse(head.next);
        head.next.next = head;
        head.next = null;
        return n;
    }

    /**
     *
     * @param head
     * @return
     */
    public Node iterativeReverse(Node head){

        Node current=head;
        Node prev = null;
        Node next = null;
        while(current != null){
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        return prev;
    }

    /**
     *
     * @param head
     * @param p
     * @param q
     * @return
     *
     * Given the head of a LinkedList and two positions ‘p’ and ‘q’, reverse the LinkedList from position ‘p’ to ‘q’.
     *  Ex: p=2, q=4
     *  I - 1->2->3->4->5->null
     *  O - 1->4->3->2->5->null
     *
     *  Similar problems:
     *  Reverse first k elements of a given linked list : here p=1;
     *
     */
    public Node reverseSubList(Node head, int p, int q) {
        Node current = head;
        Node prev = null;
        Node next = null;
        int i = 1;
        //move till 'p' nodes.
        while(current!=null&&p>i){
            prev = current;
            current = current.next;
            i++;
        }
        //keep this reference as we have to make its next node result of sublist reverse.
        Node subListBeforeNode = prev;
        Node subList = current;
        while(current!=null&&i<=q){
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
            i++;
        }
        if(subListBeforeNode == null)
            head = prev;
        else
            subListBeforeNode.next = prev;
        subList.next = current;
        return head;
    }


    public Node reverseEveryKSublist(Node head, int k){
        if(head == null)
            return head;
        Node prev = null;
        Node current = head;
        Node next = null;
        int internalCounter = k;
        while(current != null){
            Node subListBeforeNode = prev;
            Node subList = current;
            while(internalCounter>0 && current != null){
                next = current.next;
                current.next = prev;
                prev = current;
                current = next;
                internalCounter--;
            }
            if(subListBeforeNode == null){
                head = prev;
            } else {
                subListBeforeNode.next = prev;
            }
            subList.next = current;
            prev = subList;
            internalCounter = k;
        }
        return head;
    }

    public Node reverseEveryAlternateKSublist(Node head, int k){
        if(head == null)
            return head;
        Node prev = null;
        Node current = head;
        Node next = null;
        int internalCounter = k;
        while(current != null){
            Node subListBeforeNode = prev;
            Node subList = current;
            while(internalCounter>0 && current != null){
                next = current.next;
                current.next = prev;
                prev = current;
                current = next;
                internalCounter--;
            }
            if(subListBeforeNode == null){
                head = prev;
            } else {
                subListBeforeNode.next = prev;
            }
            subList.next = current;
            prev = subList;
            while(internalCounter<k && current!=null){
                prev = current;
                current = current.next;
                internalCounter++;
            }
        }
        return head;
    }

    /**
     *
     * @param head
     * @param k rotate k times.
     * @return
     */
    public Node rotate(Node head,int k){
        int size = head.size();
        //if size is '0' or '1' there is no need of rotation
        if(size==0 || size==1 || k==0)
            return head;

        k = size - k%size;
        //no need to rotate
        if(k==0)
            return head;
        //prepare first part of sublist that we have to rotate
        Node current = head;
        Node prev = null;
        while(k>0){
           prev = current;
           current = current.next;
           k--;
        }
        Node firstSubListLastNode = prev;
        Node secondSubListStartNode = current;
        while(current!=null){
            prev = current;
            current = current.next;
        }
        prev.next = head;
        firstSubListLastNode.next = current;
        head = secondSubListStartNode;
        return head;
    }

}
