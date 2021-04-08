import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.*;
import java.awt.*;

/**
 * <h1> Cat </h1>
 * The Cat program contains X and Y setters and getters of the moving enemies within a 2D grid map.
 * In addition, a "chase" method is implemented with a simple algorithm for the moving enemies
 * to get closer to the player after every tick.
 * <p>
 * @author Scott Luu  
 * @version 2.1
 */


public class Cat extends Enemies {
    
    public JLabel catLabel;
    private BufferedImage catImage;

    /**
     * Constructor for the program which contains X and Y coordinates of the moving enemies
     * and catImage
     * @param X first parameter to Cat
     * @param Y second parameter to Cat
     */
    Cat(int currentX, int currentY)
    {
        super(currentX, currentY);
        try {
            Path catPath = Paths.get("Images/cat.png").toRealPath();;
            this.catImage = ImageIO.read(new File(catPath.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        createCatLabel();

    }



    /**
     * This is the void function, it will create cat label
     * */
    public void createCatLabel()
    {
        catLabel = new JLabel();
        catLabel.setBounds(this.currentX*100,(this.currentY+1)*100,100,100);
        catLabel.setBackground(Color.white);
        catLabel.setOpaque(true);
        Image catImg = getCatImage().getScaledInstance(catLabel.getWidth(), catLabel.getHeight(),
                Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(catImg);
        catLabel.setIcon(icon);

    }

    /**
     * These methods are used by the chase method to check the surroundings of the moving Enemies
     * and determine which direction does not have a barrier.
     *
     * @param map Only parameter to these methods
     * @return boolean These methods return whether the provided coordinates are Barriers (true), or not (false).
     */
    public boolean rightblocked(Tile[][] map){
        return map[this.getCurrentY()][this.getCurrentX() + 1].getisBarrier();
    }
    public boolean leftblocked(Tile[][] map){
        return map[this.getCurrentY()][this.getCurrentX() - 1].getisBarrier();
    }
    public boolean topblocked(Tile[][] map){
        return map[this.getCurrentY()-1][this.getCurrentX()].getisBarrier();
    }
    public boolean bottomblocked(Tile[][] map){
        return map[this.getCurrentY()+1][this.getCurrentX()].getisBarrier();
    }

    public void setCurrentX(int x)
    {
        this.currentX = x;
        catLabel.setLocation(this.currentX*100,(this.currentY+1)*100);

    }

    public void setCurrentY(int y)
    {
        this.currentY = y;
        catLabel.setLocation(this.currentX*100,(this.currentY+1)*100);
    }



   /**
     * The chase method moves the moving enemies closer to the player based on the player's X and Y
     * coordinates. The method uses the above functions to check
     * for barriers and adjust the the enemies' coordinates accordingly.
     *
     * @param mouseCurrentX First parameter to the chase method - Player's X-coordinate
     * @param mouseCurrentY Second parameter to the chase method - Player's Y-coordinate
     * @param map Third parameter to the chase method - 2D-grid map
     * @return int This returns a number in regards to the direction the enemies will move to.
     * (e.g: 1 = right; 2 = left; 3 = down; 4 = up)
     */
    public int chase (int mouseCurrentX, int mouseCurrentY, Tile [][] map) {
        int distanceX = mouseCurrentX - this.getCurrentX();
        int distanceY = mouseCurrentY - this.getCurrentY();
        //if Mouse is on the right
        while (distanceX > 0) {
            if (!rightblocked(map)) {
                this.setCurrentX(this.getCurrentX() + 1);
                return 1;
            }
            //If Mouse is at bottom right
            else if(distanceY > 0){
                if (!bottomblocked(map) ) {
                    this.setCurrentY(this.getCurrentY() + 1);
                    return 3;
                }
                else if (!leftblocked(map)  ) {
                    this.setCurrentX(this.getCurrentX() - 1);
                    return 2;
                }
                else if (!topblocked(map)) {
                    this.setCurrentY(this.getCurrentY() - 1);
                    return 4;
                }
                else{
                    //to be sure
                    return 0;
                }
            }

            //Mouse is top right
            else if(distanceY < 0){
                if (!topblocked(map) ) {
                    this.setCurrentY(this.getCurrentY() - 1);
                    return 4;
                }
                else if (!leftblocked(map) ) {
                    this.setCurrentX(this.getCurrentX() - 1);
                    return 2;
                }
                else  if (!bottomblocked(map) ) {
                    this.setCurrentY(this.getCurrentY() + 1);
                    return 3;
                }
                else{
                    //to be sure
                    return 0;
                }
            }

            else{
                return 0;
            }
        }


        //If Mouse is on left
        while (distanceX < 0){
            if (!leftblocked(map)) {
                this.setCurrentX(this.getCurrentX() - 1);
                return 2;
            }
            else if(distanceY > 0){
                if (!bottomblocked(map)) {
                    this.setCurrentY(this.getCurrentY() + 1);
                    return 3;
                }
                else if (!rightblocked(map)) {
                    this.setCurrentX(this.getCurrentX() + 1);
                    return 1;
                }
                else if (!topblocked(map)) {
                    this.setCurrentY(this.getCurrentY() - 1);
                    return 4;
                }
                else{
                    //to be sure
                    return 0;
                }
            }

            else if(distanceY < 0){
                if (!topblocked(map)) {
                    this.setCurrentY(this.getCurrentY() - 1);
                    return 4;
                }else if (!bottomblocked(map)) {
                    this.setCurrentY(this.getCurrentY() + 1);
                    return 3;
                }
                else if (!rightblocked(map)) {
                    this.setCurrentX(this.getCurrentX() + 1);
                    return 1;
                }
                else{
                    //to be sure
                    return 0;
                }
            }


            else{
                return 0;
            }

        }

        //Mouse is at bottom
        while (distanceY > 0 && distanceX == 0) {
            if (!bottomblocked(map)) {
                this.setCurrentY(this.getCurrentY() + 1);
                return 3;
            }

            else if (!leftblocked(map)) {
                this.setCurrentX(this.getCurrentX() - 1);
                return 2;
            }
            else if (!rightblocked(map)) {
                this.setCurrentX(this.getCurrentX() + 1);
                return 1;
            }
            else if (!topblocked(map)) {
                this.setCurrentY(this.getCurrentY() - 1);
                return 4;
            }
            else{
                return 0;
            }



            //Mouse is at top
        } while (distanceY < 0 && distanceX == 0 ){
            if (!topblocked(map)) {
                this.setCurrentY(this.getCurrentY() - 1);
                return 4;

            }
            else if (!bottomblocked(map)) {
                this.setCurrentY(this.getCurrentY() + 1);
                return 3;
            }
            else if (!rightblocked(map) ) {
                this.setCurrentX(this.getCurrentX() + 1);
                return 1;
            }
            else if (!leftblocked(map) ) {
                this.setCurrentX(this.getCurrentX() - 1);
                return 2;
            }

            else{
                return 0;
            }

        }
        return 0;

    }

   

    /** This method gets and returns Cat's BufferedImage CatImage*/    

    public BufferedImage getCatImage () {
        return catImage;
    }

    

}
