package org.nhnacademy.tip;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

public class MovableBall extends Ball implements Movable {
    long interval;
    Thread thread;
    final Vector motion;
    final List<Vector> effectList;

    public MovableBall(Point location, int radius, Color color) {
        super(location, radius, color);

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

    public void setInterval(long interval){
        this.interval = interval;
    }

    public void next() {
            for (Vector effect : effectList) {
                getMotion().add(effect);
            }

            location.move(motion.getDisplacement());
    }

    @Override
    public void run() {
        while(!Thread.interrupted()){
            try{
                next();
                Thread.sleep(interval);
            } catch (Exception e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
