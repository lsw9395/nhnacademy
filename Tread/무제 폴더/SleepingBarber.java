import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.ArrayBlockingQueue;

public class SleepingBarber {
    public static void main(String[] args) throws InterruptedException{
        WaitingRoom waitingRoom = new WaitingRoom(10);
        Barber barber = new Barber(waitingRoom);
        Thread thread1 = new Thread(barber);
        thread1.start();

        for(int i = 0; i < 15; i++){
            Customer customer = new Customer(waitingRoom);
            Thread customer2 = new Thread(customer);
            customer2.start();
            barber.wakeUp();
            int j = 2000 + new Random().nextInt(4)*1000;
            Thread.sleep(j);
        }
    }
}
class Barber implements Runnable{
    public WaitingRoom waitingRoom;
    public boolean check = false;
    public Barber(WaitingRoom waitingRoom){
        this.waitingRoom = waitingRoom;
    }

    public synchronized void wakeUp(){
        this.notify();
    }

    public synchronized void sleeping() throws InterruptedException{
        this.wait();
    }

    @Override
    public void run() {
        try {
            while(true){
                if(waitingRoom.getCount()==0) {
                    System.out.println("이발사zzZZzzZZZ");
                    sleeping();
                } else {
                    Customer customer = waitingRoom.getCustomer();
                    customer.callAndShave();
                    waitingRoom.setCount();
                    System.out.println("이발사가 손님" + customer.getId()+"을 부르고 이발을 시작합니다.");
                    Thread.sleep(5000);
                    System.out.println("손님" + customer.getId()+"이발 종료.");
                }

                }
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}

class Customer implements Runnable{
    private static final AtomicInteger idGenerator = new AtomicInteger();
    private int id;
    public WaitingRoom waitingRoom;
    public boolean shaved;
    public boolean check = false;
    public Customer(WaitingRoom waitingRoom){
        this.id = idGenerator.incrementAndGet();
        this.waitingRoom = waitingRoom;
    }
    @Override
    public void run() {
        try {
            waitingRoom.addCustomer(this);
            while(!waitToBeCalledAndShaved()){
                System.out.println("손님" + this.id + "이 들어와서 차례를 기다립니다.(대기번호:"+waitingRoom.getCount()+")");
                waiting();
            }
            shaved = true;


        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public synchronized void callAndShave() throws InterruptedException{
        this.notify();
        this.check = true;
    }

    public synchronized void waiting() throws InterruptedException{
        this.wait();
    }

    public boolean waitToBeCalledAndShaved(){
        return check;
    }
    public boolean isShaved() {
        return shaved;
    }
    public int getId(){
        return id;
    }
}

class WaitingRoom {
    public int count = 0;
    public ArrayBlockingQueue<Customer> waiting;

    public WaitingRoom(int size){
        this.waiting = new ArrayBlockingQueue<Customer>(size);
    }

    public void addCustomer(Customer customer) throws InterruptedException{
        waiting.put(customer);
        count++;

    }

    public Customer getCustomer() throws InterruptedException{
        return waiting.take();
    }
    public int getCount(){
        return count;
    }
    public void setCount(){
        count--;
    }
}
