package exercise;

import java.util.ArrayList;

import exercise.BuyList.Item;

public class Customer {
    // 고객의 구매 목록
    private final BuyList buyList;
    // 고객의 장바구니
    private Basket basket;
    private int money = 20_000;
    public Customer(BuyList buyList) {
        this.buyList = buyList;
    }

    // 장바구니를 챙김
    public void bring(Basket basket) {
        this.basket = basket;
    }

    public void pickFoods(FoodStand foodStand) throws NotFindItemException{
        for(Item item : buyList.getItems()){
            int check= 0 ;
            for(int i = 0 ; i<item.getAmount() ; i++){
                for(int j = foodStand.getFoods().size()-1 ; j>=0;j--){
                    if(foodStand.getFoods().get(j).getName().matches(item.getName()+".*")){
                        basket.add(foodStand.getFoods().get(j));
                        foodStand.getFoods().remove(j);
                        check++;
                        j=0;
                    }
                }
            }
            if(check != item.getAmount()){
                throw new NotFindItemException("재고가 부족합니다.");
            }
        }
    }

    public void payTox(Counter counter)throws NoMoneyException{
        this.money = counter.calculate(basket, money);
    }
}
class NotFindItemException extends Exception{
    NotFindItemException(String msg){
        super(msg);
    }
}
