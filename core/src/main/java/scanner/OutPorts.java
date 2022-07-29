package scanner;

import orders.Order;
import orders.OrderId;
import scanner.model.Billing;

import java.util.Optional;

interface OutPorts {

    interface PrinterPort {

        Optional<Billing> getBill(Order order);
    }

    interface OrdersDatabasePort {

        void add(Order order);

        Optional<Order> getById(OrderId id);

        void update(Order order);
    }
}
