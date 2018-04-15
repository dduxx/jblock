package com.dduxx.jblock;

/**
 * doubly linked list chain. items can not be removed from the list. items can only be added to the
 *     end of the list.
 * @author nmagee
 * date: 2018-04-15
 */
//TODO: add methods for navigation and searching the chain
public class Chain {
    private Block head;
    private Block tail;
    private Integer length;
    
    public Chain() {
        this.head = null;
        this.tail = null;
        this.length = 0;
    }
    
    public boolean isEmpty() {
        return head == null;
    }
    
    public Integer length() {
        return this.length;
    }
    
    public void add(Data data) {
        if(head == null) {
            Block block = new Block(null, data);
            head = block;
            tail = head;
        }
        else {
            Block block = new Block(tail, data);
            tail.setNext(block);
            tail = block;
        }
        length++;
    }
    
    public void add(Data data, long timestamp) {
        if(head == null) {
            Block block = new Block(null, data, timestamp);
            head = block;
            tail = head;
        }
        else {
            Block block = new Block(tail, data, timestamp);
            tail.setPrevious(block);
            tail = block;
        }
        length++;
    }
    
    public void add(Data data, long timestamp, String DIGEST_ALGORITHM) {
        if(head == null) {
            Block block = new Block(null, data, timestamp, null, DIGEST_ALGORITHM);
            head = block;
            tail = head;
        }
        else {
            Block block = new Block(tail, data, timestamp, null, DIGEST_ALGORITHM);
            tail.setPrevious(block);
            tail = block;
        }
        length++;
    }
    
    @Override
    public String toString() {
        String output = "Chain[";
        Block current = head;
        
        while(current != null) {
            if(current.getNext() == null) {
                output = output + current.toString() + "";
            }
            else {
                output = output + current.toString() + ", ";
            }
            current = current.getNext();
        }
        output = output + "]";
        return output;
    }
}
