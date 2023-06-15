package product.beverage;

import framework.Beverage;

public class Americano implements Beverage{
    private String name;
    private int price;
    private boolean state;

    public Americano(){
        this.name = "아메리카노";
        this.price = 1500;
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
        this.price += 500;
        this.name = "아이스 아메리카노";
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
        if(state){
            System.out.println(name+"를 추출중입니다..");
            System.out.println("에스프레소를 60ml 넣습니다.");
            System.out.println("따뜻한 물을 넣습니다.");
        } else {
            System.out.println(name + "를 추출중입니다..");
            System.out.println("에스프레소를 60ml 넣습니다.");
            System.out.println("찬 물을 넣습니다.");
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
