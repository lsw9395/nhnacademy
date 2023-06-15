package factory.beveragefactory;

import framework.Beverage;
import framework.BeverageFactory;
import product.beverage.HotChoco;

public class HotChocoFactory implements BeverageFactory{

    @Override
    public Beverage createBeverage() {
        return new HotChoco();
    }
    
}
