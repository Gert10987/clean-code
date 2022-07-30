package catalog;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

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
