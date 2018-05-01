package com.dduxx.jblock;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * class representing a block in the block chain
 * @author nmagee
 * date: 2018-04-14
 */
public class Block {
    public static final String  DEFAULT_HASH_ALGORITHM = "SHA-256";
    
    private static Logger log = LoggerFactory.getLogger(Block.class);
    
    private Block previous;
    private byte[] hash;
    private Data data;
    private long timestamp;
    private Block next;
    
    /**
     * block creation that allows setting specific values for time, the next block in the chain,
     *     and the hash algorithm to use. if this is the first block in the chain set the previous
     *     block value to null
     * @param previous the previous block in the chain
     * @param data the data contained in the block
     * @param timestamp when the block was created
     * @param next the next block in the chain
     * @param HASH_ALGORITHM the hashing algorithm to use. must be valid for use by MessageDigest
     */
    public Block(Block previous, Data data, long timestamp, Block next, String HASH_ALGORITHM) {
        this.previous = previous;
        this.data = data;
        this.timestamp = timestamp;
        this.hash = Block.calculateHash(previous, data, timestamp, HASH_ALGORITHM);
    }
    
    /**
     * block which uses default hashing algorithm but allows setting of the timestamp to a specific
     *     value. if this is the first block in the chain set previous to null
     * @param previous the previous block in the chain
     * @param data data in the block
     * @param timestamp when the block was created
     */
    public Block(Block previous, Data data, long timestamp) {
        this(previous, data, timestamp, null, Block.DEFAULT_HASH_ALGORITHM);
    }
    
    /**
     * basic block creation. uses default hash algorithm and current system time. if this is the 
     *     first block in the chain then set previous to null.
     * @param previous previous block in the chain
     * @param data the data in the block
     */
    public Block(Block previous, Data data) {
        this(previous, data, System.currentTimeMillis(), null, Block.DEFAULT_HASH_ALGORITHM);
    }

    public byte[] getHash() {
        return hash;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Block getPrevious() {
        return previous;
    }

    public void setPrevious(Block previous) {
        this.previous = previous;
    }
    
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
    
    public long getTimestamp() {
        return this.timestamp;
    }
    
    public Block getNext() {
        return next;
    }

    public void setNext(Block next) {
        this.next = next;
    }
    
    /**
     * check to see if there is another block in the chain. if next==null return false. otherwise 
     *     return true
     * @return true if there is another block in the chain
     */
    public boolean hasNext() {
        return next != null;
    }

    @Override
    public String toString() {
        if(previous == null) {
            return "Block["
                    + "hash=" + Data.bytesToHex(hash) 
                    + ", data=" + data.toString() 
                    + ", time=" + timestamp
                    + ", prev=[NONE]]";
        }{
            return "Block["
                    + "hash=" + Data.bytesToHex(hash) 
                    + ", data=" + data.toString() 
                    + ", time=" + timestamp
                    + ", prev=" + Data.bytesToHex(previous.getHash()) + "]";
        }
    }
    
    /**
     * calculates the hash for this particular block in the chain if the given hash algorithm is not
     *     supported this will throw an UnsupportedOperation exception.
     * @param previous hash of the previous block
     * @param data the data in the block
     * @param timestamp when the block was created
     * @return the hash for this block as a byte array
     */
    public static byte[] calculateHash(
            Block previous, 
            Data data, 
            long timestamp, 
            String HASH_ALGORITHM) {
        try {
            MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
            
            if(previous == null) {
                return digest.digest(Data.appender(
                        new byte[] {0}, 
                        data.buildImmutableBytes(), 
                        Data.asBytes(timestamp)));
            }
            else {
                return digest.digest(Data.appender(
                        previous.getHash(), 
                        data.buildImmutableBytes(), 
                        Data.asBytes(timestamp)));
            }
            
        } catch(NoSuchAlgorithmException e) {
            log.error("no hashing algorithim existed for given: " + HASH_ALGORITHM, e);
            throw new UnsupportedOperationException(e);
        }
        
    }
    
}
