package com.dduxx.jblock;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

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
     * @throws IOException 
     * @throws JsonMappingException 
     * @throws JsonGenerationException 
     */
    public String toJson() throws JsonGenerationException, JsonMappingException, IOException;
    
    /**
     * maps from json to the data object
     * @return the object
     * @throws IOException 
     * @throws JsonMappingException 
     * @throws JsonParseException 
     */
    public Data fromJson(String json) throws JsonParseException, JsonMappingException, IOException;
}
