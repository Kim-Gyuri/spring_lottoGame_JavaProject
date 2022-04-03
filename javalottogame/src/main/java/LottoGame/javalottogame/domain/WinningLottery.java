package LottoGame.javalottogame.domain;

import LottoGame.javalottogame.domain.lotto.Lottery;
import LottoGame.javalottogame.domain.lotto.LottoNumber;

public class WinningLottery {

    public static final String DUPLICATE_BONUS_BALL_ERROR_MESSAGE = "중복된 보너스 볼 입력입니다.";
    private final Lottery lotto;
    private final LottoNumber bonusBall;

    public WinningLottery(final Lottery lotto, final LottoNumber bonusBall) {
        validateHasBonusBallInLotto(lotto, bonusBall);
        this.lotto = lotto;
        this.bonusBall = bonusBall;
    }

    private void validateHasBonusBallInLotto(final Lottery lotto, final LottoNumber bonusBall) {
        if (lotto.containNumber(bonusBall)) {
            throw new IllegalArgumentException(DUPLICATE_BONUS_BALL_ERROR_MESSAGE);
        }
    }

    public boolean IsContainBonusBall(final Lottery lotto) {
        return lotto.containNumber(bonusBall);
    }

    public int IsHaveCommonNumber(final Lottery lotto) {
        return lotto.containCommonNumber(this.lotto);
    }

}
