package validation.domain;

public enum CustomerType {
    INDIVIDUAL("I"), CORPORATE("C");

    private final String code;

    CustomerType(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return this.code;
    }
}
