package com.dduxx.jblock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestMain {
    private static final Logger log = LoggerFactory.getLogger(TestMain.class);
    
    public static void main(String[] args) {
        Chain chain = new Chain();
        
        chain.add(new TestTransaction(1L, true, 29.99), 4L);
        chain.add(new TestTransaction(2L, false, 31.99), 5L);
        chain.add(new TestTransaction(3L, true, 21.29), 6L);
        
        Chain chainb = new Chain();
        chainb.add(new TestTransaction(1L, true, 29.99), 4L);
        chainb.add(new TestTransaction(2L, false, 31.99), 5L);
        chainb.add(new TestTransaction(3L, true, 21.29), 6L);
        
        log.info("same? " + Chain.resolve(chain, chainb));
        
    }
}
