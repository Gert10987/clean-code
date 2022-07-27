package catalog;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.UUID;

@EqualsAndHashCode
@ToString
public class ProductId {

    private final UUID id;

    ProductId() {
        this.id = UUID.randomUUID();
    }
}
