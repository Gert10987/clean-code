package com.easyprogramming.orders;

import lombok.*;

import java.util.UUID;

@Getter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
public class OrderId {

    private final UUID id;

    public OrderId() {
        this.id = UUID.randomUUID();
    }
}
