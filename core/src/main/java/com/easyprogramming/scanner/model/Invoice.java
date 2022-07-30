package com.easyprogramming.scanner.model;

public record Invoice(String nip, String totalPrice) implements Billing {

    @Override
    public String getContent() {
        return nip + "_" + totalPrice;
    }
}
