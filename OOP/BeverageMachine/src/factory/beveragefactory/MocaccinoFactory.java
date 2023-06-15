package factory.beveragefactory;

import framework.Beverage;
import framework.BeverageFactory;
import product.beverage.Mocaccino;

public class MocaccinoFactory implements BeverageFactory{

    @Override
    public Beverage createBeverage() {
        return new Mocaccino();
    }
    
}