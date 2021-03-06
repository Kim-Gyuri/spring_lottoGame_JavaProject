package LottoGame.javalottogame.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);
    private static final String INPUT_MONEY_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String INPUT_MANUAL_BUY_MESSAGE = "수동으로 구매할 로또 수를 입력해 주세요.";
    public static final String INPUT_MANUAL_BUY_NUMBERS_MESSAGE = "수동으로 구매할 번호를 입력해 주세요.";
    private static final String INPUT_WINNING_NUMBER_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String INPUT_BONUS_BALL_MESSAGE = "보너스 볼을 입력해 주세요.";
    private static final String INPUT_ERROR_MESSAGE = "입력은 정수여야 합니다.";
    private static final String NUMBER_DELIMITER = ",";

    private InputView() { }

    private static int getInt() {
        try{
            String input = scanner.nextLine().trim();
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INPUT_ERROR_MESSAGE);
        }
    }

    public static int getInputMoney() {
        System.out.println(INPUT_MONEY_MESSAGE);
        return getInt();
    }

    public static int getManualBuyQuantity() {
        System.out.println(INPUT_MANUAL_BUY_MESSAGE);
        return getInt();
    }

    public static List<Integer> getInputManualLottoNumber() {
        try{
            String input = scanner.nextLine().trim();
            return Arrays.stream(input.split(NUMBER_DELIMITER))
                    .map(String::trim)
                    .map(Integer::new)
                    .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INPUT_ERROR_MESSAGE);
        }
    }

    public static List<Integer> getWinningNumber() {
        OutputView.getMessage(INPUT_WINNING_NUMBER_MESSAGE);
        return getInputManualLottoNumber();
    }

    public static Integer getBonusBall() {
        System.out.println(INPUT_BONUS_BALL_MESSAGE);
        return getInt();
    }


}
