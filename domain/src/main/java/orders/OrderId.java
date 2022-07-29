package orders;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@Getter
@EqualsAndHashCode
@ToString
public class OrderId {

    private final UUID id;

    public OrderId() {
        this.id = UUID.randomUUID();
    }
}
