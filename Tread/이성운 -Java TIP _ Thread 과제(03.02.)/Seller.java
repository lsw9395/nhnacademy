import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Seller extends Thread{
    Store[] store;
    Mart mart;
    public Seller(Mart mart, String id){
        super(id);
        this.mart = mart;
    }

    public void getStore(Store[] store){
        this.store = store;
    }
    
    @Override
    public void run(){
        while(!Thread.interrupted()){
            try {
                    store[new Random().nextInt(store.length)].sell();
                    Thread.sleep(ThreadLocalRandom.current().nextInt(1000,10000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}