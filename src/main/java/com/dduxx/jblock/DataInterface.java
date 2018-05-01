package com.dduxx.jblock;

public interface DataInterface {

    /**
     * method should convert whatever data you want to make immutable in the data block into a 
     *     byte[]. this is the part of the Data within the block that will be used to calculate
     *     the hash. convenience methods are included to help with this conversion. see asBytes()
     * @return byte[] representation of the data which you want to use to calculate the hash
     */
    public abstract byte[] buildImmutableBytes();
    
    /**
     * should return a json formatted string of the data
     * @return a json string
     */
    public String toJson();
    
    /**
     * maps from json to the data object
     * @return the object
     */
    public Data fromJson();
}
