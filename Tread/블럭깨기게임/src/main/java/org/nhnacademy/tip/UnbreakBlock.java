package org.nhnacademy.tip;

import java.awt.Color;

public class UnbreakBlock extends HpBlock{

    public UnbreakBlock(Point location, int width, int height) {
        super(location, width, height, Color.black , 5555);
    }
    
    @Override
    public void next(){
        super.next();
    }
}
