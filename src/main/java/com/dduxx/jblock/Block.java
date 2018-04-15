package com.dduxx.jblock.blockchain;

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
    
    private byte[] hash;
    private Data data;
    private byte[] previous;
    private long timestamp;
    
    public Block(byte[] previous, Data data, long timestamp) {
        this.previous = previous;
        this.data = data;
        this.timestamp = timestamp;
        this.hash = Block.calculateHash(previous, data, timestamp);
    }

    public byte[] getHash() {
        return hash;
    }

    public void setHash(byte[] hash) {
        this.hash = hash;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public byte[] getPrevious() {
        return previous;
    }

    public void setPrevious(byte[] previous) {
        this.previous = previous;
    }
    
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
    
    public long getTimestamp() {
        return this.timestamp;
    }
    
    @Override
    public String toString() {
        return "Block["
                + "hash=" + Data.bytesToHex(hash) 
                + ", data=" + data.toString() 
                + ", prev=" + Data.bytesToHex(previous) 
                + ", time=" + timestamp + "]";
    }
    
    /**
     * calculates the hash for this particular block in the chain
     * @param previous hash of the previous block
     * @param data the data in the block
     * @param timestamp when the block was created
     * @return the hash for this block as a byte array
     */
    public static byte[] calculateHash(byte[] previous, Data data, long timestamp) {
        try {
            log.info("using: " + Block.DIGEST_ALGORITHM + " to build hash");
            MessageDigest digest = MessageDigest.getInstance(Block.DIGEST_ALGORITHM);
            
            return digest.digest(Data.appender(
                    previous, 
                    data.dataToBytes(), 
                    Data.asBytes(timestamp)));
            
        } catch(NoSuchAlgorithmException e) {
            log.error("no hashing algorithim existed for given: " + Block.DIGEST_ALGORITHM, e);
            throw new RuntimeException(e);
        }
        
        
    }
    
}
