import java.util.concurrent.ThreadLocalRandom;

public class Buyer extends Thread{
    Mart mart;
    Store store;

    public Buyer(String name, Mart mart){
        super(name);
        this.mart = mart;
    }

    public void getStore(Store store){
        this.store = store;
    }

    @Override
    public void run(){
        if(!Thread.interrupted()){
            try {
                mart.enter(this);
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000,10000));
                store.enter();
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000,10000));
                store.buy();
                store.exit();
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000,10000));
                mart.exit(this);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}