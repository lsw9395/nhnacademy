package org.nhnacademy.tip;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.List;
import javax.swing.event.EventListenerList;

public class BoundedBall extends MovableBall implements Bounded {
    Rectangle bounds;
    List<Bounds> boundsList;
    EventListenerList listenerList;
    int num = 0;

    public BoundedBall(Point location, int radius, Color color) {
        super(location, radius, color);

        bounds = new Rectangle(Integer.MIN_VALUE / 2, Integer.MIN_VALUE / 2, Integer.MIN_VALUE, Integer.MIN_VALUE);
        boundsList = new LinkedList<>();
        listenerList = new EventListenerList();
    }

    public Vector getMotion(){
        return motion;
    }

    public void setBounds(int x, int y, int width, int height) {
        bounds = new Rectangle(x, y, width, height);
    }

    public void addExcludedBounds(Bounds bounds) {
        boundsList.add(bounds);
    }

    public void removeBounds(Bounds bounds){
        boundsList.remove(bounds);
    }

    public int getBoundedCount(){
        return num;
    }

    public void next() {
        super.next();

        if ((getLocation().getX() - getRadius() < bounds.getX())
            || (bounds.getX() + bounds.getWidth() < getLocation().getX() + getRadius())) {
            motion.flipX();
            num++;
        }

        if ((getLocation().getY() - getRadius() < bounds.getY())
            || (bounds.getY() + bounds.getHeight() < getLocation().getY() + getRadius())) {
            motion.flipY();
            num++;
        }

        for (Bounds bounds : boundsList) {
            if (isCollision(bounds)) {
                Rectangle intersection = getIntersection(bounds);
                if ((getWidth() == intersection.getWidth())
                    || (bounds.getWidth() == intersection.getWidth())) {
                    motion.flipY();
                    num++;
                } else if ((getHeight() == intersection.getHeight())
                    || (bounds.getHeight() == intersection.getHeight())) {
                    motion.flipX();
                    num++;
                } else {
                    motion.flipY();
                    motion.flipX();
                    num++;
                }

                CollisionEventListener[] listeners = listenerList.getListeners(CollisionEventListener.class);
                for (int i = 0; i < listeners.length; i++) {
                    listeners[i].collisionEvent(new CollisionEvent(this, bounds));
                }
            }
            
            
        }
    }

    public void addCollisionEventListener(CollisionEventListener listener) {
        listenerList.add(CollisionEventListener.class, listener);
    }

}
