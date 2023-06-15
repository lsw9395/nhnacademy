package factory.beveragefactory;

import framework.Beverage;
import framework.BeverageFactory;
import product.beverage.PeachIceTea;

public class PeachIceTeaFactory implements BeverageFactory{

    @Override
    public Beverage createBeverage() {
        return new PeachIceTea();
    }
    
}
