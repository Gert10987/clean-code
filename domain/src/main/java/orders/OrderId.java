package orders;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

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
