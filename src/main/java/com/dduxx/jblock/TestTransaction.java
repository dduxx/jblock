package com.dduxx.jblock;

public class TestTransaction extends Data{
    private long transactionId;
    private boolean transactionResult;
    private double price;
    
    

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
    public byte[] dataToBytes() {
        return Data.appender(Data.asBytes(transactionId), 
                Data.asBytes(transactionResult), 
                Data.asBytes(price));
    }

}
