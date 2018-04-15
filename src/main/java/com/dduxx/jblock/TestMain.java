package com.dduxx.jblock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dduxx.jblock.blockchain.Block;

public class TestMain {
    private static final Logger log = LoggerFactory.getLogger(TestMain.class);
    
    public static void main(String[] args) {
        Block first = new Block(
                new byte[] {}, 
                new Person("foo", "bar", "foo@bar.com"), 
                System.currentTimeMillis());
        
        Block second = new Block(
                first.getHash(), 
                new Person("john", "doe", "john@doe.com"), 
                System.currentTimeMillis());
        
        Block third = new Block(
                second.getHash(),
                new Person("jane", "doe", "jane@doe.com"),
                System.currentTimeMillis());
        
        log.info(first.toString());
        log.info(second.toString());
        log.info(third.toString());
    }
}
