package org.nhnacademy.tip;

import java.awt.Color;

public class MovableBlock extends HpBlock{

    public MovableBlock(Point location, int width, int height, int d) {
        super(location, width, height, Color.BLACK, 5555);
        this.setMotion(new Vector(8,d));
    }
    @Override
    public void next(){
        super.next();
    }
}
