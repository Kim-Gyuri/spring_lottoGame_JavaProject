package LottoGame.javalottogame.domain;

public class BuyerStorage {

    public static final int LOTTERY_PRICE = 1000;
    public static final String LOTTERY_MINIMUM_PRICE_ERROR_MESSAGE ="돈은 %d원 이상이어야 합니다.";
    public static final String Auto_BUYER_ERROR_MESSAGE = "수동 구매는 %d장보다 더 많이 살 수 없습니다.";
    private final int totalOfBuyQuantity;
    private int manualBuyQuantity;

    public BuyerStorage(final Money money) {
        validateMinimumPurchasePrice(money);
        this.totalOfBuyQuantity = money.getValue()/LOTTERY_PRICE;
    }

    public BuyerStorage(final Money money, final int manualBuyQuantity) {
        this(money);
        validateMaximumManualPurchase(manualBuyQuantity);
        this.manualBuyQuantity = manualBuyQuantity;
    }

    private void validateMinimumPurchasePrice(final Money money) {
        if (money.getValue() < LOTTERY_PRICE) {
            throw new IllegalArgumentException(
                    String.format(LOTTERY_MINIMUM_PRICE_ERROR_MESSAGE, LOTTERY_PRICE));
        }
    }

    private void validateMaximumManualPurchase(final int manualBuyQuantity) {
        if (manualBuyQuantity > totalOfBuyQuantity) {
            throw new IllegalArgumentException(
                    String.format(Auto_BUYER_ERROR_MESSAGE, totalOfBuyQuantity));
        }
    }

    public int getManualBuyQuantity() {
        return manualBuyQuantity;
    }

    public int getAutoBuyQuantity() {
        return totalOfBuyQuantity - manualBuyQuantity;
    }
}
