package org.nhnacademy.tip;

import java.awt.Color;
import java.util.Random;

public class Hp2upBlock extends HpBlock{

    public Hp2upBlock(Point location, int width, int height) {
        super(location, width, height, Color.red, new Random().nextInt(4));
        //TODO Auto-generated constructor stub
    }
    @Override
    public void next(){
        super.next();
    }
}
