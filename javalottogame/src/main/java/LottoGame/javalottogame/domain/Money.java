package LottoGame.javalottogame.domain;

public class Money {

    public static final String OUT_OF_RANGE_ERROR_MESSAGE = "돈은 양의 정수여야 합니다.";
    private final int value;

    public Money(final int value) {
        validateRangeInt(value);
        this.value = value;
    }

    private void validateRangeInt(final int value) {
        if (value <= 0) {
            throw new IllegalArgumentException(OUT_OF_RANGE_ERROR_MESSAGE);
        }
    }

    public int getValue() {
        return value;
    }
}
