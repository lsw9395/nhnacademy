package pay;

public class CardPayment implements Payment{
    int balance = 10000;
    @Override
    public boolean pay(int price) {
        if(balance < price){
            return false;
        }
        balance -= price;
        return true;
    }
}
