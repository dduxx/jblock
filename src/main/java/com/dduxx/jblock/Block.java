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
    public static final String  DIGEST_ALGORITHM = "SHA-256";
    
    private static Logger log = LoggerFactory.getLogger(Block.class);
    
    private Block previous;
    private byte[] hash;
    private Data data;
    private long timestamp;
    private Block next;
    
    
    public Block(Block previous, Data data, long timestamp, Block next) {
        this.previous = previous;
        this.data = data;
        this.timestamp = timestamp;
        this.hash = Block.calculateHash(previous, data, timestamp);
    }
    
    public Block(Block previous, Data data, long timestamp) {
        this(previous, data, timestamp, null);
    }
    
    public Block(Block previous, Data data) {
        this(previous, data, System.currentTimeMillis(), null);
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
     * calculates the hash for this particular block in the chain
     * @param previous hash of the previous block
     * @param data the data in the block
     * @param timestamp when the block was created
     * @return the hash for this block as a byte array
     */
    public static byte[] calculateHash(Block previous, Data data, long timestamp) {
        try {
            MessageDigest digest = MessageDigest.getInstance(Block.DIGEST_ALGORITHM);
            
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
            log.error("no hashing algorithim existed for given: " + Block.DIGEST_ALGORITHM, e);
            throw new RuntimeException(e);
        }
        
    }
    
}
