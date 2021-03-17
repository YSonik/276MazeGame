package main.java;

import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MouseTrap {
    private int penalty;
    private BufferedImage MTImage;
    protected int currentX;
    protected int currentY;
    protected int EnemyHeight = 25;
    protected int EnemyWidth = 25;

    
    MouseTrap(){
        this.penalty = 6; 
        this.currentX = getCurrentX();
        this.currentY = getCurrentY();

        try {
            Path MTPath = Paths.get("Images/mousetrap.jpg").toRealPath();;
            this.MTImage = ImageIO.read(new File(MTPath.toString()));
        } catch (IOException e) {
            e.printStackTrace();
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

    public int getPenalty(){
        return penalty;
    }

    public BufferedImage getMTImage () {
        return MTImage;
    }
}
