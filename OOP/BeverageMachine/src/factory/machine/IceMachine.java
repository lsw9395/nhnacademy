package factory.machine;

import product.cup.Cup;
import product.cup.PlasticCup;

public class IceMachine {
    public void addIce(Cup cup){
        if(cup instanceof PlasticCup){
            System.out.println("얼음을 받아주세요.");
            System.out.println("얼음을 받았습니다.");
        }
    }
}
