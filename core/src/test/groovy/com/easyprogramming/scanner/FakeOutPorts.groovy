package com.easyprogramming.scanner

import com.easyprogramming.orders.Order
import com.easyprogramming.orders.OrderId

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

        @Override
        void update(Order order) {
            orders.put(order.getId(), order)
        }
    }
}
