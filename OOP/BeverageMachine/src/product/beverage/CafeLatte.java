package product.beverage;

import framework.Beverage;

public class CafeLatte implements Beverage{
    private String name;
    private int price;
    private boolean state;
    
    public CafeLatte(){
        this.name = "카페라떼";
        this.price = 2000;
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
        this.name = "아이스 카페라떼";
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
            System.out.println("따뜻한 우유를 넣습니다.");
            System.out.println("에스프레소 60ml를 넣습니다.");
        } else {
            System.out.println(name + "를 추출중입니다..");
            System.out.println("찬 우유를 넣습니다.");
            System.out.println("에스프레소 60ml를 넣습니다.");
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
