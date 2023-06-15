import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.TreeMap;
import java.util.Map.Entry;

public class RedBlackTree {
    public static void main(String[] args) {
        TreeMap<Integer,Number> tree = new TreeMap<Integer,Number>();
        //2번.
        for(int i = 1 ; i< 31; i++){
            tree.put(i, new Number(new Random().nextInt(100)));
        }
        //3번.
        List<Number> list = new ArrayList<>(tree.values());
        System.out.println("3번");
        for(Number i : list){
            System.out.print(i.getNum()+", ");
        }
        System.out.println("\n-------------------------------------");
        Collections.sort(list);
        for(Number i : list){
            System.out.print(i.getNum()+", ");
        }
        //4번
        System.out.println("\n무작위 정수들의 합: "+allSum(tree));

    }
    public static int allSum(TreeMap<Integer,Number> tree){
        int sum = 0;
        for(Integer i: tree.keySet()){
            sum+=tree.get(i).getNum();
        }
        return sum;
    }
}
