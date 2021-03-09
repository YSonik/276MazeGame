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

    public int chase (int mouseCurrentX, int mouseCurrentY, Tile [][] map) {
        int distanceX = mouseCurrentX - this.getCurrentX();
        int distanceY = mouseCurrentY - this.getCurrentY();
        if (distanceX > 0) {
            if (map[this.getCurrentY()][this.getCurrentX() + 1].getisBarrier() == false) {
                this.setCurrentX(this.getCurrentX() + 1);
                return 1;
            }
        } else if (distanceX < 0){
            if (map[this.getCurrentY()][this.getCurrentX() - 1].getisBarrier() == false) {
                this.setCurrentX(this.getCurrentX() - 1);
                return 2;
            }
        } else {
            return 0;
        }
        if (distanceY > 0) {
            if (map[this.getCurrentY() + 1][this.getCurrentX()].getisBarrier() == false) {
                this.setCurrentY(this.getCurrentY() + 1);
                return 3;
            }
        } else if (distanceY < 0){
            if (map[this.getCurrentY() - 1][this.getCurrentX()].getisBarrier() == false) {
                this.setCurrentY(this.getCurrentY() - 1);
                return 4;
            }
        } else {
            return 0;
        }
        return 0;
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
