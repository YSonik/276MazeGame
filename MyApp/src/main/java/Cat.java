//package Main.java;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Cat {
    private int currentX;
    private int currentY;
    private final int catHeight = 25;
    private final int catWidth = 25;
    private BufferedImage catImage;

    Cat(int startX, int startY)
    {
        this.currentX = startX;
        this.currentY = startY;
        try {
            Path catPath = Paths.get("MyApp/Images/cat.png").toRealPath();;
            this.catImage = ImageIO.read(new File(catPath.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    


    public boolean rightblocked(Tile [][] map){
        return map[this.getCurrentY()][this.getCurrentX() + 1].getisBarrier();
    }
    public boolean leftblocked(Tile [][] map){
        return map[this.getCurrentY()][this.getCurrentX() - 1].getisBarrier();
    }
    public boolean topblocked(Tile [][] map){
        return map[this.getCurrentY()-1][this.getCurrentX()].getisBarrier();
    }
    public boolean bottomblocked(Tile [][] map){
        return map[this.getCurrentY()+1][this.getCurrentX()].getisBarrier();
    }

    



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
                        System.out.println("return 3");
                        return 3;
                }
                else if (!leftblocked(map)  ) {
                    this.setCurrentX(this.getCurrentX() - 1);
                    return 2;
                }
                else if (!topblocked(map)) {
                        this.setCurrentY(this.getCurrentY() - 1);
                        System.out.println("return 4");
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
                    System.out.println("return 4");
                    return 4;
                }
                else if (!leftblocked(map) ) {
                    this.setCurrentX(this.getCurrentX() - 1);
                    return 2;
                }
                else  if (!bottomblocked(map) ) {
                        this.setCurrentY(this.getCurrentY() + 1);
                        System.out.println("return 3");
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
                        System.out.println("return 3");
                        return 3;
                }
                else if (!rightblocked(map)) {
                    this.setCurrentX(this.getCurrentX() + 1);
                    return 1;  
                }
                else if (!topblocked(map)) {
                        this.setCurrentY(this.getCurrentY() - 1);
                        System.out.println("return 4");
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
                        System.out.println("return 4");
                        return 4;
                }else if (!bottomblocked(map)) {                   
                        this.setCurrentY(this.getCurrentY() + 1);
                        System.out.println("return 3");
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
                        System.out.println("return 3");
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
                        System.out.println("return 4");
                        return 4;
                }
                else{
                    return 0;
                }
    
                

        //Mouse is at top                
        } while (distanceY < 0 && distanceX == 0 ){
                if (!topblocked(map)) {
                    this.setCurrentY(this.getCurrentY() - 1);
                    System.out.println("return 4");
                    return 4;
                    
                }
                else if (!bottomblocked(map)) {
                        this.setCurrentY(this.getCurrentY() + 1);
                        System.out.println("return 3");
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

    public BufferedImage getCatImage () {
        return catImage;
    }

}

