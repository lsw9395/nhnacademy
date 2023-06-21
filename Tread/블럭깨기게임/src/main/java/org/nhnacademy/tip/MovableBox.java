package org.nhnacademy.tip;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

public class MovableBox extends Box implements Movable {
    Thread thread;
    long interval;
    final Vector motion;
    final List<Vector> effectList;

    public MovableBox(Point location, int width, int height, Color color) {
        super(location, width, height, color);

        thread = new Thread(this);
        interval = 100;
        motion = new Vector();
        effectList = new LinkedList<>();
    }

    public void start() {
        this.thread.start();
    }

    public void stop() {
        this.thread.interrupt();
    }

    public void setMotion(Vector motion) {
        this.motion.set(motion);
    }

    public Vector getMotion() {
        return motion;
    }

    public void addEffect(Vector effect) {
        effectList.add(effect);
    }

    @Override
    public void setInterval(long interval) {
        this.interval = interval;
    }

    public void next() {
        location.move(motion.getDisplacement());
    }


    @Override
    public void run() {
        while(!Thread.interrupted()){
            try {
                next();
                Thread.sleep(interval);
            } catch (Exception e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
