//package main.java;

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

    public int chase (int mouseCurrentX, int mouseCurrentY, Tile[][] map) {
        int distanceX = mouseCurrentX - this.getCurrentX();
        int distanceY = mouseCurrentY - this.getCurrentY();
        //if Mouse is on the right
        while (distanceX > 0) {
            if (map[this.getCurrentY()][this.getCurrentX() + 1].getisBarrier() == false) {
                    this.setCurrentX(this.getCurrentX() + 1);
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
            else if(!map[this.getCurrentY()+1][this.getCurrentX()].getisBarrier()){
                        this.setCurrentY(this.getCurrentY() + 1);
                        System.out.println("return 3");
                        return 3;
            }
            else{
                return 0;
            }

        
        } 
        while (distanceX < 0){
            if (map[this.getCurrentY()][this.getCurrentX() - 1].getisBarrier() == false) {
                this.setCurrentX(this.getCurrentX() - 1);
                return 2;
            }
            else if(map[this.getCurrentY()-1][this.getCurrentX()].getisBarrier() == false){
                    this.setCurrentY(this.getCurrentY() - 1);
                        System.out.println("return 4");
                        return 4;
                }
                else if (map[this.getCurrentY()][this.getCurrentX() + 1].getisBarrier() == false) {
                    this.setCurrentX(this.getCurrentX() + 1);
                    return 1;
                }
                else if(map[this.getCurrentY()+1][this.getCurrentX()].getisBarrier() == false){
                    this.setCurrentY(this.getCurrentY() + 1);
                    System.out.println("return 3");
                    return 3;
                }
                else{
                    return 0;
                }

            

        } 
        while (distanceY > 0 && distanceX == 0) {
                if (map[this.getCurrentY() + 1][this.getCurrentX()].getisBarrier() == false) {
                    this.setCurrentY(this.getCurrentY() + 1);
                    System.out.println("return 3");
                    return 3;
                }

                else if(map[this.getCurrentY()][this.getCurrentX()-1].getisBarrier() == false){
                        this.setCurrentX(this.getCurrentX() - 1);
                            System.out.println("return 2");
                            return 2;
                    }
                    else if (map[this.getCurrentY()][this.getCurrentX() + 1].getisBarrier() == false) {
                        this.setCurrentX(this.getCurrentX() + 1);
                        return 1;
                    }
                    else if (map[this.getCurrentY() - 1][this.getCurrentX()].getisBarrier() == false) {
                        this.setCurrentY(this.getCurrentY() - 1);
                        System.out.println("return 4");
                        return 4;
                    }
                    else{
                        return 0;
                    }
    
                


        } while (distanceY < 0 && distanceX == 0 ){
                if (map[this.getCurrentY() - 1][this.getCurrentX()].getisBarrier() == false) {
                    this.setCurrentY(this.getCurrentY() - 1);
                    System.out.println("return 4");
                    return 4;
                }
                else if(map[this.getCurrentY()+1][this.getCurrentX() ].getisBarrier() == false){
                        this.setCurrentY(this.getCurrentY() + 1);
                            System.out.println("return 3");
                            return 3;
                }
                    else if (map[this.getCurrentY()][this.getCurrentX() + 1].getisBarrier() == false) {
                        this.setCurrentX(this.getCurrentX() + 1);
                        return 1;
                    }
                    else if (map[this.getCurrentY()][this.getCurrentX() - 1].getisBarrier() == false) {
                        this.setCurrentX(this.getCurrentX() - 1);
                        System.out.println("return 2");
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

