package LottoGame.javalottogame.domain.lotto;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LottoNumber {

    private static final int LOTTO_MIN_NUMBER = 1;
    private static final int LOTTO_MAX_NUMBER = 45;
    public static final String OUT_RANGE_NUMBER_ERROR_MESSAGE = "1 ~ 45 사이의 숫자입력만 가능합니다.";

    private static final Map<Integer, LottoNumber> lottoNumbers = new HashMap<>();
    private final int number;

    static {
        for (int i=LOTTO_MIN_NUMBER; i<=LOTTO_MAX_NUMBER; i++) {
            lottoNumbers.put(i, new LottoNumber(i));
        }
    }

    private LottoNumber(final int number) {
        this.number = number;
    }

    public static LottoNumber from(final int number) {
        if (!lottoNumbers.containsKey(number)) {
            throw new IllegalArgumentException(OUT_RANGE_NUMBER_ERROR_MESSAGE);
        }
        return lottoNumbers.get(number);
    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj==null || getClass()!= obj.getClass()) {
            return false;
        }
        LottoNumber that = (LottoNumber) obj;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    public String getStringNumber() {
        return Integer.toString(number);
    }
}
