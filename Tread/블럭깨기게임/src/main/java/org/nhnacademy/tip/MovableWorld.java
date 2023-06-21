package org.nhnacademy.tip;

public class MovableWorld extends World {
    long interval;

    public MovableWorld(int width, int height) {
        super(width, height);
        interval = 100;
    }

    public long getInterval() {
        return interval;
    }

    public void setInterval(long interval) {
        this.interval = interval;
        synchronized (boundsList) {
            for (Bounds bounds : boundsList) {
                if (bounds instanceof Movable) {
                    ((Movable) bounds).setInterval(interval);
                }
            }
        }
    }

    public void add(Bounds bounds) {
        super.add(bounds);
        if (bounds instanceof Movable) {
            ((Movable) bounds).start();
            setInterval(24);
        }
    }

    public void next() {
        for (Bounds bounds : getBoundsList()) {
            if (bounds instanceof Movable) {
                ((Movable) bounds).next();
            }
        }
    }

    public void run(long seconds) {
        long startTime = System.currentTimeMillis();

        while (!Thread.interrupted() && (System.currentTimeMillis() < startTime + seconds * 1000)) {
            try {
                repaint();
                Thread.sleep(interval);
            } catch (InterruptedException ignore) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
