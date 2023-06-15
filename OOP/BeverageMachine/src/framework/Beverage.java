package framework;

public interface Beverage {
    String getName();
    int getPrice();
    boolean isState();
    void setState(boolean state);
    void make();
}
