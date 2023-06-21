package org.nhnacademy.tip;

import java.awt.Color;
import java.awt.Rectangle;

public class HpBlock extends Block{
    private int hp;
    public HpBlock(Point location, int width, int height, Color color, int hp) {
        super(location, width, height, color);
        this.hp = hp;
    }

    public void setHp(){
        hp--;
    }

    public void settingHp(int hp){
        this.hp = hp;
    }



    public int getHp(){
        return this.hp;
    }

    @Override
    public  void next(){
        super.next();
        //System.out.println("check1");
        for (Bounds bounds : super.getList()) {
                if (isCollision(bounds)) {
                    Rectangle intersection = getIntersection(bounds);
                    if ((getWidth() == intersection.getWidth())
                        || (bounds.getWidth() == intersection.getWidth())) {
                        setHp();
                    } else if ((getHeight() == intersection.getHeight())
                        || (bounds.getHeight() == intersection.getHeight())) {
                        setHp();
                    } else {
                        setHp();
                    }
                }
            }
        }
}
