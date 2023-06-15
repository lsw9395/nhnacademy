package product.cup;

import framework.Beverage;

public abstract class Cup {
    private Beverage beverage;

    public void addBeverage(Beverage beverage){
        this.beverage = beverage;
    }

    public Beverage getBeverage(){
        return this.beverage;
    }
    public abstract String getType();
}
