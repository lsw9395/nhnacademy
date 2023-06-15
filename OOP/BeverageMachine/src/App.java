import java.util.Scanner;

import factory.machine.Machine;
import pay.Payments;
import product.beverage.Menu;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        Machine machine = new Machine();
        System.out.println("원하시는 음류를 선택해주세요.");
        for(Menu type : Menu.values()){
            System.out.println((type.ordinal()+1)+". "+type);
        }
        int i = scanner.nextInt()-1;
        machine.selectBeverage(i);
        System.out.println("원하시는 결제 방법을 선택해주세요.");
        for(Payments type : Payments.values()){
            System.out.println((type.ordinal()+1)+". "+type);
        }
        i = scanner.nextInt()-1;
        machine.selectPayment(i);
        machine.makeBeverage();
        scanner.close();
    }
}
