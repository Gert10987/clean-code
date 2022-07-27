package scanner;

import orders.Order;
import scanner.model.Billing;

import java.util.Optional;

interface OutPorts {

    interface PrinterPort {

        Optional<Billing> getBill(Order order);
    }
}
