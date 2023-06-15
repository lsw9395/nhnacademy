

import java.util.concurrent.Semaphore;

public class SleepingBarber2 {
    public static final int CHAIRS = 5;
    public static final int CUSTOMER = 10;

    public static Semaphore barber = new Semaphore(0);
    public static Semaphore customer = new Semaphore(0);
    public static Semaphore mutex = new Semaphore(1);

    public static int waiting = 0;

}

class Barber2 implements Runnable{
    @Override
    public void run(){
        while(true){
            try {
                System.out.println("이발사zzZZzzZZZ");
                SleepingBarber2.barber.acquire();
                SleepingBarber2.mutex.acquire();
                SleepingBarber2.waiting--;
                SleepingBarber2.customer.release();
                SleepingBarber2.mutex.release();
                System.out.println("이발사가 이발중입니다.");
                Thread.sleep(1000);
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }
}

class Customer2 implements Runnable{
    private int id;

    public Customer2(int id){
        this.id = id;
    }

    public void run(){
        try {
            SleepingBarber2.mutex.acquire();
            if(SleepingBarber2.waiting == SleepingBarber2.CHAIRS){
                System.out.println("손님 " + id + " 이 자리가 없어 떠났습니다.");
                SleepingBarber2.mutex.release();
                return;
            }
            System.out.println("손님 " + id + " 이 기다리는중");
            SleepingBarber2.waiting++;
            SleepingBarber2.barber.release();
            SleepingBarber2.mutex.release();

            SleepingBarber2.customer.acquire();
            System.out.println("손님 "+ id +" 이 이발을 받고 있습니다.");
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}

class SemaphoreTest{
    public static void main(String[] args){
        Barber2 barber = new Barber2();
        Thread barber2 = new Thread(barber);
        barber2.start();

        for(int i = 0; i < SleepingBarber2.CUSTOMER; i++){
            Customer2 customer = new Customer2(i);
            Thread customer2 = new Thread(customer);
            customer2.start();
        }
    }

}


