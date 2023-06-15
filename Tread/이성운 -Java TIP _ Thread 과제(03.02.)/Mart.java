import java.util.Random;

public class Mart {
    MySemaphore space;
    Store[] store;
    public Mart(int size){
        space = new MySemaphore(size*5);
        store = new Store[size];
        for(int i = 0; i < size ; i++){
            store[i] = new Store(5, "Store"+i);
        }
    }

    public Store[] getStore(){
        return store;
    }
    public void enter(Buyer buyer) throws InterruptedException {
        space.acquire();
        buyer.getStore(store[new Random().nextInt(store.length)]);
        System.out.println(buyer.getName()+"이 마트에 입장");
    }

    public void exit(Buyer buyer){
        System.out.println(buyer.getName()+"이 마트에서 퇴장");
        space.release();
    }

}
