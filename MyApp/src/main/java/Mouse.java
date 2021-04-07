import java.awt.*;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
/**
 * <h1>Mouse Class</h1>
 * This class represents the movable mouse character
 * @author Yogesh Sonik
 * @version 2.0
 * */
public class Mouse {
    private int currentX;
    private int currentY;
    private int cheeseEaten;
    private BufferedImage mouseImage;
    public JLabel mouseLabel;

    /**
     * This is the constructor for the Mouse class which sets the starting position of the mouse, sets cheese eaten to zero and imports image for the jLabel.
     * */
    Mouse(int x, int y)
    {
        this.currentX = x;
        this.currentY = y;
        this.cheeseEaten = 0;
        try {
            Path mousePath = Paths.get("Images/mouse.png").toRealPath();;
            this.mouseImage = ImageIO.read(new File(mousePath.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        createMouseLabel();
    }

    /**
     * This is the void function, which is called by the Game constructor, creates a jLabel for the mouse and draws it at the correct location.
     * */
    public void createMouseLabel()
    {
        mouseLabel = new JLabel();
        mouseLabel.setBounds(currentX*100,(currentY + 1)*100,100,100);
        mouseLabel.setBackground(Color.blue);
        mouseLabel.setOpaque(true);
        Image mouseImg = this.getMouseImage().getScaledInstance(mouseLabel.getWidth(), mouseLabel.getHeight(),
                Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(mouseImg);
        mouseLabel.setIcon(icon);
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
        mouseLabel.setLocation(this.currentX*100,(this.currentY+1)*100);
    }

    public void setCurrentY(int currentY) {
        this.currentY = currentY;
        mouseLabel.setLocation(this.currentX*100,(this.currentY+1)*100);
    }

    public void setCheeseEaten(int cheeseEaten) {
        this.cheeseEaten = cheeseEaten;
    }

}
