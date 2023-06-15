package factory.paymentfactory;

import java.util.Scanner;

import framework.PaymentFactory;
import pay.CashPayment;
import pay.Payment;

public class CashPaymentFactory implements PaymentFactory{

    @Override
    public Payment createPayment() {
        System.out.println("현금 결제");
        Scanner sc = new Scanner(System.in);
        System.out.println("넣을 현금을 입력해주세요!");
        int money = sc.nextInt();
        CashPayment cash = new CashPayment();
        cash.setBalance(money);
        sc.close();
        return cash;
    }
}
