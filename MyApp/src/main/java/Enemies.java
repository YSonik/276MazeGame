/** Parent class for Cats and MouseTraps
 * It  contains the getters and setters for the player's Enemies
 * @author Scott Luu
 */

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

}
