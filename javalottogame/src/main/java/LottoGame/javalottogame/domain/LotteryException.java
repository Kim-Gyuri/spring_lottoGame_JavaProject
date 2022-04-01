package LottoGame.javalottogame.domain;

public class LotteryException {

    public static final int LOTTERY_PRICE = 1000;
    public static final String LOTTERY_MINIMUM_PRICE_ERROR_MESSAGE ="돈은 %d원 이상이어야 합니다.";
    public static final String Auto_BUYER_ERROR_MESSAGE = "수동 구매는 %d장보다 더 많이 살 수 없슨디ㅏ.";
    private final int totalOfPurchase;
    private int countOfManualPurchase;

    public LotteryException(final Money money) {
        validateMinimumPurchasePrice(money);
        this.totalOfPurchase = money.getValue()/LOTTERY_PRICE;
    }

    public LotteryException(final Money money, final int countOfManualPurchase) {
        this(money);
        validateMaximumManualPurchase(countOfManualPurchase);
        this.countOfManualPurchase = countOfManualPurchase;
    }

    private void validateMinimumPurchasePrice(final Money money) {
        if (money.getValue() < LOTTERY_PRICE) {
            throw new IllegalArgumentException(
                    String.format(LOTTERY_MINIMUM_PRICE_ERROR_MESSAGE, LOTTERY_PRICE));
        }
    }

    private void validateMaximumManualPurchase(final int countOfManualPurchase) {
        if (countOfManualPurchase > totalOfPurchase) {
            throw new IllegalArgumentException(
                    String.format(LOTTERY_MINIMUM_PRICE_ERROR_MESSAGE, totalOfPurchase));
        }
    }

    public int getCountOfManualPurchase() {
        return countOfManualPurchase;
    }

    public int getCountOfAutoPurchase() {
        return totalOfPurchase - countOfManualPurchase;
    }
}
