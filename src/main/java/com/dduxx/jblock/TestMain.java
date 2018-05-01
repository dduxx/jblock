package com.dduxx.jblock;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestMain {
    private static final Logger log = LoggerFactory.getLogger(TestMain.class);
    
    public static void main(String[] args) {
        Chain chain = new Chain();
        
        chain.add(new TestTransaction(1L, true, 29.99), 4L);
        chain.add(new TestTransaction(2L, false, 31.99), 5L);
        chain.add(new TestTransaction(3L, true, 21.29), 6L);
        
        try {
            log.info(chain.get(0).getData().toJson());
        } catch (JsonGenerationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
}
