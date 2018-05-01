package com.dduxx.jblock;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class TestTransaction extends Data{
    private long transactionId;
    private boolean transactionResult;
    private double price;
    
    public TestTransaction() {
        
    }
    
    public TestTransaction(long transactionId, boolean transactionResult, double price) {
        this.transactionId = transactionId;
        this.transactionResult = transactionResult;
        this.price = price;
    }

    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    public boolean isTransactionResult() {
        return transactionResult;
    }

    public void setTransactionResult(boolean transactionResult) {
        this.transactionResult = transactionResult;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "TestTransaction [transactionId=" + transactionId + ", transactionResult="
                + transactionResult + ", price=" + price + "]";
    }

    @Override
    public byte[] buildImmutableBytes() {
        return Data.appender(Data.asBytes(transactionId), 
                Data.asBytes(transactionResult), 
                Data.asBytes(price));
    }

    @Override
    public String toJson() throws JsonGenerationException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(this);
        return json;
    }

    @Override
    public Data fromJson(String json) throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, TestTransaction.class);
        
    }

}
