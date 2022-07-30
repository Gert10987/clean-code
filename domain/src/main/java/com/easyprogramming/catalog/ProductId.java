package com.easyprogramming.catalog;

import lombok.*;

import java.util.UUID;

@Getter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
public class ProductId {

    private final UUID id;

    ProductId() {
        this.id = UUID.randomUUID();
    }
}
