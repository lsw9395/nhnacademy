package factory.paymentfactory;

import framework.PaymentFactory;
import pay.CardPayment;
import pay.Payment;

public class CardPaymentFactory implements PaymentFactory{

    @Override
    public Payment createPayment() {
        System.out.println("카드 결제");
        return new CardPayment();
    }
    
}
