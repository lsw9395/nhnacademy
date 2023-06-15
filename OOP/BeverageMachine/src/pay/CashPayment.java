package pay;

public class CashPayment implements Payment{
    int [] coinArr = {1000, 500, 100, 50};
    int balance;
    @Override
    public boolean pay(int price) {
        if(balance < price){
            return false;
        }
        int price2 = balance - price;
        balance -= price;
        for(int coin : coinArr){
            price2 = getCount(coin, price2);
        }
        returnCash();
        return true;
    }
    
    public int getCount(int coin,int price){
        int count =(price /coin);
        System.out.println(coin+"원:"+count+"개");
        return price = price - (coin*(price/coin));
    }

    public void setBalance(int balance){
        this.balance = balance;
    }
    
    public int returnCash(){
        System.out.println("거스름 돈 : "+balance);
        return balance;
    }
}
