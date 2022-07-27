package scanner.model;

public record Invoice(String nip) implements Billing {

    @Override
    public String getContent() {
        return nip;
    }
}
