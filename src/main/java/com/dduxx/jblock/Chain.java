package com.dduxx.jblock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * doubly linked list chain. items CAN NOT be removed from the list. items can only be added to the
 *     end of the list. TThis is done because blocks in the chain are meant to be immutable and
 *     never be removed.
 * @author nmagee
 * date: 2018-04-15
 */
//TODO: make methods to write chain to JSON
//TODO: make methods to build a chain from json
public class Chain {
    private static final Logger log = LoggerFactory.getLogger(Chain.class);
    private Block head;
    private Block tail;
    private Integer length;
    
    /**
     * creates an empty chain and sets head and tail to null. also sets length count to 0.
     * TODO: make this private and create initialization method.
     */
    public Chain() {
        this.head = null;
        this.tail = null;
        this.length = 0;
    }
    
    /**
     * return true if head == null.
     * @return
     */
    public boolean isEmpty() {
        return head == null;
    }
    
    public Integer length() {
        return this.length;
    }
    
    /**
     * uses default block creation to add a block to the chain. this means that time is set to 
     *     the current system time and the default hashing algorithm is used
     * @param data the information to store in the block
     */
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
    
    /**
     * allows adding to the chain and setting the block time to a specific time
     * @param data the information to store in the block
     * @param timestamp time the block was created
     */
    public void add(Data data, long timestamp) {
        if(head == null) {
            Block block = new Block(null, data, timestamp);
            head = block;
            tail = head;
        }
        else {
            Block block = new Block(tail, data, timestamp);
            tail.setNext(block);
            tail = block;
        }
        length++;
    }
    
    /**
     * adds a block to the chain based on the given creation time and the given data. also,
     *     allows for setting a specific hashing algorithm
     * @param data the information to store in the block
     * @param timestamp the time the block was created
     * @param HASH_ALGORITHM the hashing algorithm to use to create the block hash
     */
    public void add(Data data, long timestamp, String HASH_ALGORITHM) {
        if(head == null) {
            Block block = new Block(null, data, timestamp, null, HASH_ALGORITHM);
            head = block;
            tail = head;
        }
        else {
            Block block = new Block(tail, data, timestamp, null, HASH_ALGORITHM);
            tail.setNext(block);
            tail = block;
        }
        length++;
    }
    
    public Block getHead() {
        return this.head;
    }
    
    public Block getTail() {
        return this.tail;
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
    
    /**
     * compare two Block chains based on the hash values. returns true if the chains are the same
     *     false otherwise
     * @param a
     * @param b
     * @return true if chains are the same, false otherwise
     */
    public static boolean resolve(Chain a, Chain b) {
        boolean equal = a.length() == b.length();
        Block currentA = a.getHead();
        Block currentB = b.getHead();
        while(currentA!=null && currentB!=null && equal) {
            log.info("comparing " + Data.bytesToHex(currentA.getHash()) + " to " 
                    + Data.bytesToHex(currentB.getHash()));            
            equal = equal && Arrays.equals(currentA.getHash(), currentB.getHash());
            currentA = currentA.getNext();
            currentB = currentB.getNext();
        }
        return equal;
    }
}
