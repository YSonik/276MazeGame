package main.java;

public class Enemies{
    protected int currentX;
    protected int currentY;

    Enemies(int currentX, int currentY){
        this.currentX = currentX;
        this.currentY = currentY;
    }

    public int getCurrentX() {
        return this.currentX;
    }

    public int getCurrentY() {
        return this.currentY; }

    public void setCurrentX(int currentX) {
        this.currentX = currentX;
    }

    public void setCurrentY(int currentY) {
        this.currentY = currentY;
    }

    
}
