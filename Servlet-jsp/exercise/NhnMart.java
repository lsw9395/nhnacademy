package exercise;

import java.util.Scanner;

class NhnMartShell {
    public static void main(String[] args) {
        try {
            NhnMart mart = new NhnMart();
            mart.prepareMart();

            BuyList buyList = inputBuyListFromShell();

            Customer jordan = new Customer(buyList);
            // 장바구니를 챙긴다.
            jordan.bring(mart.provideBasket());
            // 식품을 담는다.
            jordan.pickFoods(mart.getFoodStand());
            // 카운터에서 계산한다.
            jordan.payTox(mart.getCounter());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private static BuyList inputBuyListFromShell() {
        System.out.println("NHN 마트에 오신 것을 환영합니다. 사고 싶은 물건을 골라주세요.(예시: 양파 3 / 계란 2 / 파 1)");
        Scanner sc = new Scanner(System.in);
        String itemsList = sc.nextLine();
        BuyList buyList = new BuyList();
        String[] items = itemsList.split("/");
        for(String i : items){
            String[] item = i.trim().split(" ");
            buyList.add(new BuyList.Item(item[0], Integer.parseInt(item[1])));
        }

        return buyList;
    }
}

public class NhnMart {
    private final FoodStand foodStand = new FoodStand();

    public void prepareMart() {
        fillFoodStand();
    }
    public FoodStand getFoodStand(){
        return foodStand;
    }

    private void fillFoodStand() {
        for (int i = 0; i < 2; i++) {
            foodStand.add(new Food("양파", 1_000));
        }
        for (int i = 0; i < 5; i++) {
            foodStand.add(new Food("계란(30개)", 5_000));
        }
        for (int i = 0; i < 10; i++) {
            foodStand.add(new Food("파", 500));
        }
        for (int i = 0; i < 20; i++) {
            foodStand.add(new Food("사과", 2_000));
        }
    }

    public Basket provideBasket() {
        return new Basket();
    }
    public Counter getCounter(){
        return new Counter();
    }
}
