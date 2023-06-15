package factory.machine;

import framework.Beverage;
import product.cup.Cup;
import product.cup.PaperCup;
import product.cup.PlasticCup;

public class CupMachine {
    Cup cup;
    public Cup takeCup(Beverage beverage){
        System.out.println("컵을 받아주세요!");
        if(!beverage.isState()){
            cup = new PlasticCup();
        } else {
            cup = new PaperCup();
        }
        return cup;
    }
}
