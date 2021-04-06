import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * <h1> MouseTrap </h1>
 * The MouseTrap program sets and gets the coordinates
 * for the stationary enemies, and has a penalty used to reduce player's score. 
 * <p>
 * @author Scott Luu  
 *
 */
public class MouseTrap extends Enemies {
    private int penalty;
    private BufferedImage MTImage;
    
    MouseTrap(int currentX, int currentY){
        super(currentX,currentY);
        this.penalty = 6; 
        // Get image for the traps
        try {
            Path MTPath = Paths.get("MyApp/Images/mousetrap.jpg").toRealPath();;
            this.MTImage = ImageIO.read(new File(MTPath.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    //getPenalty method used to obtain the penalty value
    public int getPenalty(){
        return penalty;
    }

    public BufferedImage getMTImage () {
        return MTImage;
    }
}
