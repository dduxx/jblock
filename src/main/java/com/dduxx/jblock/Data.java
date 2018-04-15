package com.dduxx.jblock.blockchain;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
 * abstraction of data contained in the block of the block chain.
 * @author nmagee
 * date: 2018-04-14
 */
public abstract class Data {
    
    private static Logger log = LoggerFactory.getLogger(Data.class);
    
    /**
     * convert whatever information is in your data to a byte array. used to help in hash 
     *     calculations
     * @return data representation in bytes
     */
    public abstract byte[] dataToBytes();
    
    /**
     * convenience method that appends all byte arrays into a single byte array
     * @param arrays the arrays to combine
     * @return the combined byte arrays
     */
    public static byte[] appender(byte[] ... arrays) {
        byte[] combined = {};
        
        for(int i=0; i<arrays.length; i++) {
            
            if(combined.length == 0) {//if no arrays combined add the first one
                combined = arrays[i];
            }
            else {//add the next array to the already combined array
                byte[] subcombined = new byte[combined.length + arrays[i].length];
                
                System.arraycopy(combined, 0, subcombined, 0, combined.length);
                System.arraycopy(arrays[i], 0, subcombined, combined.length, arrays[i].length);
                
                combined = subcombined;
            }
        }
        
        return combined;
    }
    
    public static String bytesToHex(byte[] bytes) {
        String hex = "";
        for(int i=0; i<bytes.length; i++) {
            String part = Integer.toHexString(0xff & bytes[i]);
            hex = hex + part;
        }
        return hex;
    }
    
    public static byte[] asBytes(String s) {
        return s.getBytes();
    }
    
    public static byte[] asBytes(boolean b) {
        return new byte[]{(byte) (b?1:0)};
    }
    
    public static byte[] asBytes(byte b) {
        return new byte[] {b};
    }
    
    public static byte[] asBytes(char c) {
        return new byte[]{(byte)c};
    }
    
    public static byte[] asBytes(double d) {
        ByteBuffer buffer = ByteBuffer.allocate(Double.BYTES);
        buffer.putDouble(d);
        return buffer.array();
    }
    
    public static byte[] asBytes(float f) {
        ByteBuffer buffer = ByteBuffer.allocate(Float.BYTES);
        buffer.putFloat(f);
        return buffer.array();
    }
    
    public static byte[] asBytes(int i) {
        ByteBuffer buffer = ByteBuffer.allocate(Integer.BYTES);
        buffer.putInt(i);
        return buffer.array();
    }
    
    public static byte[] asBytes(long l) {
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.putLong(l);
        return buffer.array();
    }
    
    public static byte[] asBytes(short s) {
        ByteBuffer buffer = ByteBuffer.allocate(Short.BYTES);
        buffer.putShort(s);
        return buffer.array();
    }
    
    public static byte[] asBytes(Object o) {
        if(!(o instanceof Serializable)) {
            log.error("object must be serializable to convert to bytes");
            throw new UnsupportedOperationException();
        }
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
          ObjectOutputStream stream = new ObjectOutputStream(bos);   
          stream.writeObject(o);
          stream.flush();
          byte[] bytes = bos.toByteArray();
          return bytes;
        } catch(IOException e) {
            log.error("unable to convert object to bytes", e);
            throw new RuntimeException(e);
        } finally {
            try {
                bos.close();
            } catch (IOException e) {
                log.error("unable to close byte stream", e);
            }
        }
    }
}
