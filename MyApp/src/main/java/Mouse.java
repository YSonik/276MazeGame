import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
/**
 * <h1>Mouse Class</h1>
 * This class represents the movable mouse character
 * @author Yogesh Sonik
 * */
public class Mouse {
    private int currentX;
    private int currentY;
    private int cheeseEaten;
    private BufferedImage mouseImage;

    /**
     * This is the constructor for the Mouse class which sets the starting position of the mouse, sets cheese eaten to zero and imports image for the jLabel.
     * @author Yogesh Sonik
     * */
    Mouse()
    {
        this.currentX = 8;
        this.currentY = 8;
        this.cheeseEaten = 0;
        try {
            Path mousePath = Paths.get("MyApp/Images/mouse.png").toRealPath();;
            this.mouseImage = ImageIO.read(new File(mousePath.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getCurrentX() {
        return currentX;
    }

    public int getCurrentY() {
        return currentY;
    }

    public BufferedImage getMouseImage () {
        return mouseImage;
    }

    public int getCheeseEaten() {
        return cheeseEaten;
    }

    public void setCurrentX(int currentX) {
        this.currentX = currentX;
    }

    public void setCurrentY(int currentY) {
        this.currentY = currentY;
    }

    public void setCheeseEaten(int cheeseEaten) {
        this.cheeseEaten = cheeseEaten;
    }

}
