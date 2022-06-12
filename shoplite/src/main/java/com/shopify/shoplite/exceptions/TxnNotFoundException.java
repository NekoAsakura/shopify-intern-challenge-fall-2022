package com.shopify.shoplite.exceptions;

public class TxnNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 5016812401135889608L;

    private long id;

    public TxnNotFoundException(long id) {
        super("Transaction not found with id: " + id);

        this.id = id;
    }

    public long getId() {
        return id;
    }
}
