package factory.machine;

import factory.beveragefactory.TodecideMenu;
import factory.paymentfactory.TodicidePayment;
import framework.Beverage;
import pay.Payment;
import product.cup.Cup;

public class Machine {
    private Payment payment;
    private Beverage beverage;
    private CupMachine cupMachine;
    private IceMachine iceMachine;

    public void selectBeverage(int m){
        beverage = new TodecideMenu().orderMenu(m);
        System.out.println(beverage.toString());
    }

    public void selectPayment(int p){
        payment = new TodicidePayment().clickPayment(p);
    }

    public Machine(){
        this.cupMachine = new CupMachine();
        this.iceMachine = new IceMachine();
    }

    private boolean paying(){
        if(payment.pay(beverage.getPrice())){
            System.out.println(payment.getClass().getSimpleName()+" 결제 성공");
            return true;
        } else {
            System.out.println(payment.getClass().getSimpleName()+"결제 실패");
            return false;
        }
    }


    public void makeBeverage(){
        if(!paying()){
            return;
        }
        Cup cup = cupMachine.takeCup(beverage);
        
        System.out.println(cup.getType()+"을 얻었습니다.");

        iceMachine.addIce(cup);

        beverage.make();

        System.out.println("완성되었습니다. 가져가세요!");
    }


}
