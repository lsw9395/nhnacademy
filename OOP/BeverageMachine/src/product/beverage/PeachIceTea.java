package product.beverage;

import framework.Beverage;

public class PeachIceTea implements Beverage{
    private String name;
    private int price;
    private boolean state;
    
    public PeachIceTea(){
        this.name = "복숭아 아이스티";
        this.price = 2500;
        this.state = true;
    }@Override
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
            System.out.println("아이스 " + name + "를 추출중입니다..");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
