package LottoGame.javalottogame.domain.lotto;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Lottery {

    private static final int LOTTO_NUMBER_COUNT = 6;
    public static final String LOTTO_NUMBER_COUNT_ERROR_MESSAGE = "6개만 입력해야 합니다.";
    public static final String DUPLICATE_NUMBER_ERROR_MESSAGE = "중복된 숫자 입력입니다.";
    private final List<LottoNumber> numbers;

    public Lottery(final List<LottoNumber> numbers) {
        validateNumberCount(numbers);
        validateDistinct(numbers);
        this.numbers = numbers;
    }

    public static Lottery from(final List<Integer> number) {
        return new Lottery(number.stream()
                .map(LottoNumber::from)
                .collect(Collectors.toList()));
    }

    private void validateNumberCount(final List<LottoNumber> numbers) {
        if (numbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(LOTTO_NUMBER_COUNT_ERROR_MESSAGE);
        }
    }

    private void validateDistinct(final List<LottoNumber> numbers) {
        if (numbers.stream().distinct().count() != numbers.size()) {
            throw new IllegalArgumentException(DUPLICATE_NUMBER_ERROR_MESSAGE);
        }
    }

    public int countCommonValue(final Lottery lottery) {
        int totalCount = numbers.size() + lottery.numbers.size();
        Set<LottoNumber> set = new HashSet<>(numbers);
        return totalCount - set.size();
    }

    public boolean containNumber(final LottoNumber input) {
        return numbers.contains(input);
    }

    public List<Integer> getNumbers() {
        return Collections.unmodifiableList(numbers.stream()
                                           .map(LottoNumber::getNumber)
                                           .collect(Collectors.toList()));
    }

    public boolean isContain(Integer Number) {
        return (int) numbers.stream().filter(x -> x.getStringNumber().equals(numbers)).count() != 0;
    }

}
