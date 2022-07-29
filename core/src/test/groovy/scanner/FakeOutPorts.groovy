package scanner

import orders.Order
import orders.OrderId

class FakeOutPorts {

    static class TestOrdersDatabaseAdapter implements OutPorts.OrdersDatabasePort {

        private Map<OrderId, Order> orders = [:]

        @Override
        void add(Order order) {
            orders.put(order.getId(), order)
        }

        @Override
        Optional<Order> getById(OrderId orderId) {
            return Optional.ofNullable(orders.get(orderId))
        }
    }
}
