/** Parent class for Cats and MouseTraps
 * It  contains the getters and setters for the player's Enemies
 * @author Scott Luu
 */

public class Enemies{
    private int currentX;
    private int currentY;

    Enemies(int currentX, int currentY){
        this.currentX = currentX;
        this.currentY = currentY;
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
