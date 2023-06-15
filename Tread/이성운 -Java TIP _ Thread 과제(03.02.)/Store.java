public class Store{
    MySemaphore space;
    int product;
    String id;
    public Store(int size, String id){
        space = new MySemaphore(size);
        this.product = 10;
        this.id = id;
    }

    public void enter() throws InterruptedException {
        try {
            space.acquire();
            System.out.println(Thread.currentThread().getName() + "이"+id+"에 입장");
        } catch (InterruptedException e) {
            throw e;
        }
    }
    public void exit() {
        System.out.println(Thread.currentThread().getName() +"이"+id+ "에서 퇴장");
        space.release();
    }

    public synchronized void buy() throws InterruptedException {
        while(product == 0){
            System.out.println(Thread.currentThread().getName()+"이"+id+ "에서 구매대기");
            wait();
            Thread.sleep(200);
        }
        --product;
        System.out.println(Thread.currentThread().getName()+"이 구매 완료"+id+"의 재고: "+product);
        notify();
    }

    public synchronized void sell() throws InterruptedException {
        if(product>=10){
            System.out.println(Thread.currentThread().getName()+ "이 "+id+"에 물품이 가득합니다.");
            Thread.sleep(100);
        } else{
            ++product;
            System.out.println(Thread.currentThread().getName()+" 납품 완료. "+id+"의 재고 :" + product);
            notify();
        }
        
    }
}
