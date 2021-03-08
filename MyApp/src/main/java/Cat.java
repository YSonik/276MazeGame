public class Cat {
    private int currentX;
    private int currentY;
    private final int catHeight = 25;
    private final int catWidth = 25;

    Cat()
    {
        this.currentX = 8;
        this.currentY = 8;
    }

    public int getCurrentX() {
        return currentX;
    }

    public int getCurrentY() {
        return currentY; }

    public void setCurrentX(int currentX) {
        this.currentX = currentX;
    }

    public void setCurrentY(int currentY) {
        this.currentY = currentY;
    }
}
