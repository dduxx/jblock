package com.dduxx.jblock;

/** 
 * abstraction of data contained in the block of the block chain.
 * @author nmagee
 * date: 2018-04-14
 */
public abstract class Data {
    
    /**
     * convert whatever information is in your data to a byte array. used to help in hash 
     *     calculations
     * @return data representation in bytes
     */
    public abstract byte[] dataToBytes();
    
    /**
     * appends all byte arrays into one byte array
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
                int index = 0;
                
                for(int j=0; j<combined.length; j++) {
                    subcombined[index] = combined[j];
                    index++;
                }
                
                for(int j=0; j<arrays[i].length; j++) {
                    subcombined[index] = arrays[i][j];
                    index++;
                }
                
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
}
