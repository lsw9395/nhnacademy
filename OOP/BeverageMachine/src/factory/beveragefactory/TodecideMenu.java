package factory.beveragefactory;

import framework.Beverage;

public class TodecideMenu {
    Beverage orderBeverage;
    public Beverage orderMenu(int m){
        switch(m){
            case 0: orderBeverage = new AmericanoFactory().createBeverage(); break;
            case 1: orderBeverage = new CafeLatteFactory().createBeverage(); break;
            case 2: orderBeverage = new MocaccinoFactory().createBeverage(); break;
            case 3: orderBeverage = new HotChocoFactory().createBeverage(); break;
            case 4: orderBeverage = new AmericanoFactory().createBeverage(); orderBeverage.setState(false); break; 
            case 5: orderBeverage = new HotChocoFactory().createBeverage(); orderBeverage.setState(false); break;
            case 6: orderBeverage = new CafeLatteFactory().createBeverage(); orderBeverage.setState(false); break;
            case 7: orderBeverage = new PeachIceTeaFactory().createBeverage(); orderBeverage.setState(false); break;
            default: throw new IllegalArgumentException();
        }
        return orderBeverage;
    }
}
