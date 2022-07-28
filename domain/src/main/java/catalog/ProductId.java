package catalog;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@Getter
@EqualsAndHashCode
@ToString
public class ProductId {

    private final UUID id;

    ProductId() {
        this.id = UUID.randomUUID();
    }
}
