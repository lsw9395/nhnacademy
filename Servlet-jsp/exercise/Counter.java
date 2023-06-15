package exercise;

import java.text.DecimalFormat;

public class Counter {
    public int calculate(Basket basket, int money) throws NoMoneyException{
        int sum = 0;
        for(Food food: basket.getFoods()){
            if(money==0 || money<food.getPrice()){
                throw new NoMoneyException("돈이 부족합니다.");
            } else {
                money-=food.getPrice();
                sum+=food.getPrice();
            }
        }
        DecimalFormat formatter = new DecimalFormat("###,###");
        System.out.println("총 가격은 "+formatter.format(sum)+"원 입니다.");
        System.out.println("고객님 결제 후 잔액: "+formatter.format(money));
        return money;
    }
}
class NoMoneyException extends Exception{
    NoMoneyException(String msg){
        super(msg);
    }
}