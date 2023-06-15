package factory.beveragefactory;

import framework.Beverage;
import framework.BeverageFactory;
import product.beverage.Americano;

public class AmericanoFactory implements BeverageFactory{

    @Override
    public Beverage createBeverage() {
        return new Americano();
    }
    
}
