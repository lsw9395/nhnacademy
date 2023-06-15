package product.beverage;

import framework.Beverage;

public class Mocaccino implements Beverage{
    private String name;
    private int price;
    private boolean state;
    
    public Mocaccino(){
        this.name = "모카치노";
        this.price = 3000;
        this.state = true;
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public boolean isState() {
        return state;
    }
    
    @Override
    public void setState(boolean state){
        this.state = state;
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
    @Override
    public void make() {
            System.out.println(name+"를 추출중입니다..");
            System.out.println("잔에 초코릿소스 1펌프를 넣는다.");
            System.out.println("에스프레소 30ml를 추출한다.");
            System.out.println("뜨거운 우유와 거품을 150ml를 넣는다.");
            System.out.println("마무리로 시나몬 파우더나 초코 파우더를 뿌린다.");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}