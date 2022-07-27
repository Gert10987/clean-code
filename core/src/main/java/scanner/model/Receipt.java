package scanner.model;

public record Receipt(String totalPrice) implements Billing {

    @Override
    public String getContent() {
        return totalPrice;
    }
}
