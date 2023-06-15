package factory.paymentfactory;

import framework.PaymentFactory;
import pay.OnlinePayment;
import pay.Payment;

public class OnlinePaymentFactory implements PaymentFactory{

    @Override
    public Payment createPayment() {
        System.out.println("온라인 결제");
        return new OnlinePayment();
    }
    
}
