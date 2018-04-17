package com.dduxx.jblock;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * doubly linked list chain. items can not be removed from the list. items can only be added to the
 *     end of the list.
 * @author nmagee
 * date: 2018-04-15
 */
//TODO: add methods for navigation and searching the chain
public class Chain {
    private static final Logger log = LoggerFactory.getLogger(Chain.class);
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
    
    /**
     * get the block at the given index. if the index given is out of bounds an IndexOutOfBounds 
     *     exception is thrown. checks to see if the index is closer to the beginning or end of the
     *     chain and start the search accordingly. worst case time complexity is O(n/2).
     * @param index the index of a given block.
     * @return the block at the given index
     */
    public Block get(int index) {
        if(index > length) {
            throw new IndexOutOfBoundsException("Index " + index 
                    + " is out of bounds. chain length is " + length);
        }
        else {
            if( (double) index > (double) length/2D) {
                log.info("starting from end of chain");
                Block current = tail;
                int i = length-1;
                while(i>index) {
                    current = current.getPrevious();
                    i--;
                }
                return current;
            }
            else {
                log.info("starting from beginning of chain");
                Block current = head;
                int i = 0;
                while(i<index) {
                    current = current.getNext();
                    i++;
                }
                return current;
            }
        }
    }
    
    /**
     * return the chain elements in a list. if the chain is empty an empty list will be returned
     * @return a list of blocks in the chain
     */
    public List<Block> asList(){
        Block current = head;
        List<Block> list = new ArrayList<>();
        while(current != null) {
            list.add(current);
            current = current.getNext();
        }
        return list;
    }
}
