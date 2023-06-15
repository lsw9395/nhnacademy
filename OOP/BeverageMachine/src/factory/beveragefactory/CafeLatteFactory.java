package factory.beveragefactory;

import framework.Beverage;
import framework.BeverageFactory;
import product.beverage.CafeLatte;

public class CafeLatteFactory implements BeverageFactory{

    @Override
    public Beverage createBeverage() {
        return new CafeLatte();
    }
    
}
