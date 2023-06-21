package org.nhnacademy.tip;

import java.awt.Color;

public class Bar extends HpBlock{
    String name;
    public Bar(Point location, int width, int height) {
        super(location, width, height, Color.BLACK, 5555);
        this.name = "bar";
    }
    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
    @Override
    public void next(){
        super.next();
    }
}
