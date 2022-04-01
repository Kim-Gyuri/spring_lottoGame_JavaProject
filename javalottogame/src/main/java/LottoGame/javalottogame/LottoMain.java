package LottoGame.javalottogame;

import LottoGame.javalottogame.domain.controller.LottoController;

public class LottoMain {
    public static void main(String[] args) {
        //System.out.println(Rank.WIN_FIRST_PLACE.getCountOfMatch());
        LottoController lottoController = new LottoController();
        lottoController.start();
    }
}
