package com.dduxx.jblock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestMain {
    private static final Logger log = LoggerFactory.getLogger(TestMain.class);
    
    public static void main(String[] args) {
        Block first = new Block(
                new byte[] {}, 
                new TestTransaction(1L, true, 29.99), 
                System.currentTimeMillis());
        
        Block second = new Block(
                first.getHash(), 
                new TestTransaction(2L, false, 31.99), 
                System.currentTimeMillis());
        
        Block third = new Block(
                second.getHash(),
                new TestTransaction(3L, true, 21.29),
                System.currentTimeMillis());
        
        log.info(first.toString());
        log.info(second.toString());
        log.info(third.toString());
    }
}
