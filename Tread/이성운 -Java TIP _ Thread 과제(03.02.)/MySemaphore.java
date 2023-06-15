public class MySemaphore {
    int max;
    int size;

    public MySemaphore(int size){
        this.max = size;
        this.size = 0;
    }

    public synchronized void acquire() throws InterruptedException {
        while(size==max){
            wait();
        }
        size++;
    }
    
    public synchronized void release(){
        size--;
        notifyAll();
    }
}
