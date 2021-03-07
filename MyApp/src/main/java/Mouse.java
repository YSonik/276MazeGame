

public class Mouse {
    private int currentX;
    private int currentY;
    private int cheeseEaten;
    private final int mouseHeight = 25;
    private final int mouseWidth = 25;

    Mouse()
    {
        this.currentX = 8;
        this.currentY = 8;
        this.cheeseEaten = 0;
    }

    public int getCurrentX() {
        return currentX;
    }

    public int getCurrentY() {
        return currentY;
    }

    public int getCheeseEaten() {
        return cheeseEaten;
    }

    public void setCurrentX(int currentX) {
        this.currentX = currentX;
    }

    public void setCurrentY(int currentY) {
        this.currentY = currentY;
    }

    public void setCheeseEaten(int cheeseEaten) {
        this.cheeseEaten = cheeseEaten;
    }

}
