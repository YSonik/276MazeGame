
public class Cat {
    private int currentX;
    private int currentY;
    private final int catHeight = 25;
    private final int catWidth = 25;

    Cat(int startX, int startY)
    {
        this.currentX = startX;
        this.currentY = startY;
    }

    public int chase (int mouseCurrentX, int mouseCurrentY, Tile [][] map) {
        int distanceX = mouseCurrentX - this.getCurrentX();
        int distanceY = mouseCurrentY - this.getCurrentY();
        if (distanceX > 0 || (distanceX >0 && distanceY == 0)) {
            if (!map[this.getCurrentY()][this.getCurrentX() + 1].getisBarrier()) {
                    this.setCurrentX(this.getCurrentX() + 1);
                    System.out.println("return 1");
                    return 1;
            }
            else if(!map[this.getCurrentY()-1][this.getCurrentX()].getisBarrier()){
                        this.setCurrentY(this.getCurrentY() - 1);
                            System.out.println("return 4");
                            return 4;
            }
             else if (map[this.getCurrentY()][this.getCurrentX() - 1].getisBarrier() == false) {
                        this.setCurrentX(this.getCurrentX() - 1);
                        return 2;
                    }
                    else {
                        this.setCurrentY(this.getCurrentY() + 1);
                        System.out.println("return 3");
                        return 3;
                    }       
        }
   

        else if (distanceX < 0 || (distanceX < 0 && distanceY == 0)) {
            if (map[this.getCurrentY()][this.getCurrentX() - 1].getisBarrier() == false) {
                this.setCurrentX(this.getCurrentX() - 1);
                return 2;
            }
            else if(!map[this.getCurrentY()-1][this.getCurrentX()].getisBarrier()){
                        this.setCurrentY(this.getCurrentY() - 1);
                            System.out.println("return 4");
                            return 4;
            }
            else if (!map[this.getCurrentY()][this.getCurrentX() + 1].getisBarrier()) {
                this.setCurrentX(this.getCurrentX() + 1);
                System.out.println("return 1");
                return 1;
            }
            else {
                        this.setCurrentY(this.getCurrentY() + 1);
                        System.out.println("return 3");
                        return 3;
            }
        }

        else if (distanceY < 0 || (distanceY < 0 && distanceX == 0)) {
            if(!map[this.getCurrentY()-1][this.getCurrentX()].getisBarrier()){
                        this.setCurrentY(this.getCurrentY() - 1);
                            System.out.println("return 4");
                            return 4;
            }
            else if (map[this.getCurrentY()][this.getCurrentX() - 1].getisBarrier() == false) {
                this.setCurrentX(this.getCurrentX() - 1);
                return 2;
            } 
            else if (!map[this.getCurrentY()][this.getCurrentX() + 1].getisBarrier()) {
                this.setCurrentX(this.getCurrentX() + 1);
                System.out.println("return 1");
                return 1;
            }
            else {
                    this.setCurrentY(this.getCurrentY() + 1);
                    System.out.println("return 3");
                    return 3;
            }
        }


        else if (distanceY > 0 || (distanceY > 0 && distanceX == 0)) {
            if(!map[this.getCurrentY()-1][this.getCurrentX()].getisBarrier()){
                        this.setCurrentY(this.getCurrentY() + 1);
                        System.out.println("return 3");
                        return 3;
            }
            else if (map[this.getCurrentY()][this.getCurrentX() - 1].getisBarrier() == false) {
                this.setCurrentX(this.getCurrentX() - 1);
                return 2;
            } 
            else if (!map[this.getCurrentY()][this.getCurrentX() + 1].getisBarrier()) {
                this.setCurrentX(this.getCurrentX() + 1);
                System.out.println("return 1");
                return 1;
            }
            else {
                this.setCurrentY(this.getCurrentY() - 1);
                System.out.println("return 4");
                return 4;
            }
        }
        else{
            return 0;
        }

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
