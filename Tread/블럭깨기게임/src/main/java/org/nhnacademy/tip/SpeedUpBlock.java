package org.nhnacademy.tip;

import java.awt.Color;

public class SpeedUpBlock extends HpBlock{
    double speed=0;
    public SpeedUpBlock(Point location, int width, int height) {
        super(location, width, height, Color.PINK, 1);
        this.speed = 1.2;
    }

    public double getSpeed(){
        return speed;
    }
    @Override
    public void next(){
        super.next();
    }
}
