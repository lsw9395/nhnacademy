public class Calculator{
    private int memory;
    boolean turn = false;
    public int getMemory(){
        return this.memory;
    }

    public void setMemory(int memory){
        while(turn){
            System.out.println("waiting");
        }
            turn = true;
        try {
            Thread.sleep(1000);
            this.memory = memory;
            System.out.println(Thread.currentThread().getName() + ": "+this.memory);
        } catch (Exception e) {
            // TODO: handle exception
        }
        turn = false ;
    }
}
//바쁜대기 while문을 이용해서 특정한 값이 변경되면 다음 실행이 진행되는 거임

class User extends Thread{
    private Calculator calculator;
    int memory;

    public User(String name, int memory){
        this.setName(name); //쓰레드의 이름을 저장하는 거임.
        this.memory = memory;
    }

    public void setCalculator(Calculator calculator){
        this.calculator = calculator;
    }

    @Override
    public void run(){
        calculator.setMemory(this.memory);
    }
}

class Test{
    public static void main(String[] args){
        Calculator calculator = new Calculator();
        User user1 = new User("User1", 100);
        User user2 = new User("User2", 50);

        user1.setCalculator(calculator);
        user1.start();

        user2.setCalculator(calculator);
        user2.start();
    }
}