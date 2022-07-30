package com.easyprogramming.scanner;

import com.easyprogramming.orders.Order;
import com.easyprogramming.scanner.model.Invoice;
import com.easyprogramming.scanner.model.Billing;

import java.util.Optional;

class PrinterOutAdapter implements OutPorts.PrinterPort {
    @Override
    public Optional<Billing> getBill(Order order) {
        return Optional.of(new Invoice("1234", order.getActualTotalPrice().toString()));
    }
}
