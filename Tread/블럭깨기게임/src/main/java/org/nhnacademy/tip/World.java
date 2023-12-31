package org.nhnacademy.tip;

import java.util.List;
import java.util.LinkedList;
import java.awt.Graphics;
import javax.swing.JPanel;

public class World extends JPanel {
    final List<Bounds> boundsList;

    public World(int width, int height) {
        super();

        boundsList = new LinkedList<>();

        setSize(width, height);
    }

    protected List<Bounds> getBoundsList() {
        return boundsList;
    }

    public void add(Bounds bounds) {
        synchronized (boundsList) {
            if (bounds instanceof Drawable) {
                ((Drawable) bounds).coordinateTransformer(p -> new Point(p.getX(), getHeight() - p.getY()));
            }
            boundsList.add(bounds);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawRect(0, 0, getWidth(), getHeight());
        synchronized (boundsList) {
            for (Bounds bounds : boundsList) {
                if (bounds instanceof Drawable) {
                    ((Drawable) bounds).draw(g);
                }
            }
        }
    }
}
