package org.nhnacademy.tip;

import java.awt.Color;

public class Block extends BoundedBox{
    
    public Block(Point location, int width, int height, Color color) {
        super(location, width, height, color);
    }
    
    @Override
    public void next(){
        super.next();
    }
}
