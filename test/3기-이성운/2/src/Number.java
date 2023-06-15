public class Number implements Comparable<Number>{
    private int num;
    public Number(int num){
        this.num = num;
    }
    public int getNum(){
        return num;
    }
    @Override
    public int compareTo(Number o) {
        return (this.num > o.num ? -1 :(this.num == o.num ? 0:1));
    }
}
