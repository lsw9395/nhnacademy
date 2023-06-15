package factory.paymentfactory;

import pay.Payment;

public class TodicidePayment {
    Payment payment;
    public Payment clickPayment(int p){
        switch(p){
            case 0: return new CardPaymentFactory().createPayment();
            case 1: return new CashPaymentFactory().createPayment();
            case 2: return new OnlinePaymentFactory().createPayment();
        }
        throw new IllegalArgumentException();
    }
}
