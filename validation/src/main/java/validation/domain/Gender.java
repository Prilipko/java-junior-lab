package validation.domain;

public enum Gender {
    INDIVIDUAL("M"), CORPORATE("F");

    private final String code;

    Gender(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return this.code;
    }
}
