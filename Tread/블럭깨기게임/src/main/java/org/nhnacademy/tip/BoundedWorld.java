package org.nhnacademy.tip;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.*;

public class BoundedWorld extends MovableWorld {
    List<Bounds> blockList = new ArrayList<>();
    List<Bounds> blockList2 = new LinkedList<>();
    int score = 0;
    int num;
    int count = 0;
    public BoundedWorld(int width, int height) {
        super(width, height);
    }

    public int getScore(){
        return score;
    }

    public int getBlock(){
        return blockList2.size();
    }

    public List<Bounds> getList(){
        return blockList2;
    }


    @Override
    public void add(Bounds bounds) {
        synchronized (boundsList) {
            for (Bounds other : boundsList) {
                if (other instanceof Bounded) {
                    ((Bounded) other).addExcludedBounds(bounds);
                }
            }

            if (bounds instanceof Bounded) {
                ((Bounded) bounds).setBounds(0, 0, getWidth(), getHeight());

                for (Bounds other : boundsList) {
                    ((Bounded) bounds).addExcludedBounds(other);
                }
            }
        }
        super.add(bounds);
    }

    public void addBlockList(Bounds bounds){
        blockList2.add(bounds);
    }

    public void clear() {
        synchronized (boundsList) {
            synchronized(blockList){
                blockList = boundsList.stream().filter(bounds -> bounds instanceof HpBlock).filter(bounds -> ((HpBlock) bounds).getHp() == 0).collect(Collectors.toList());
                for(Bounds bounds2 : blockList){
                    boundsList.remove(bounds2);
                    if(bounds2 != null){
                        for (Bounds bounds : boundsList) {
                            if(!(bounds instanceof HpBlock)){
                                ((BoundedBall) bounds).removeBounds(bounds2);
                                blockList2.remove(bounds2);
                                score += 5;
                            }
                        }
                    }
                }
            }
        }
    }

    public int boundCount(){
        return count;
    }
    public void addCount(){
        count++;
    }

    @Override
    public void run(long seconds) {
        long startTime = System.currentTimeMillis();

        while (!Thread.interrupted() && (System.currentTimeMillis() < startTime + seconds * 1000)) {
            try {
                clear();
                repaint();
                Thread.sleep(interval);
            } catch (InterruptedException ignore) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
