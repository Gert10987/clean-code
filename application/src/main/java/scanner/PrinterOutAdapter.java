package scanner;

import orders.Order;
import scanner.model.Billing;
import scanner.model.Invoice;

import java.util.Optional;

class PrinterOutAdapter implements OutPorts.PrinterPort {
    @Override
    public Optional<Billing> getBill(Order order) {
        return Optional.of(new Invoice("1234", order.getActualTotalPrice().toString()));
    }
}
