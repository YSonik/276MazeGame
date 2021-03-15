package main.java;

import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MouseTrap extends Enemies {
    private int penalty;
    private BufferedImage MTImage;
    
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

    public int getPenalty(){
        return penalty;
    }

    public BufferedImage getMTImage () {
        return MTImage;
    }
}
