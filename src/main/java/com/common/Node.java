package com.common;

public class Node implements Cloneable{

    public int data;
    public Node next;

    public Node(int data){
        this.data=data;
    }

    public int size(){
        int size = 0;
        if(this == null)
            return size;

        Node temp = this;
        while(temp!=null){
            temp = temp.next;
            size++;
        }
        return size;
    }
}
