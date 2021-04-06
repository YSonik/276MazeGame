import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
/**
 * <h1> Cheese </h1>
 * This Cheese class setup the Cheese rewards
 * <p>
 * @author Canh Nhat Minh Le
 *
 */
public class Cheese extends Rewards{
    private BufferedImage cheeseImg;
    public JLabel cheeseLabel;

    /**
     * Constructor for the program which contains X and Y coordinates of the cheese
     * while also imports the Cheese Image
     * @param x indicates the x-coordinate of cheese
     * @param y indicates the y-coordinate of cheese
     * @param value indicates the value of cheese
     */
    Cheese(int x, int y, int value) {
        super(x, y, value);
        try {
            Path cheesePath = Paths.get("Images/cheese.png").toRealPath();;
            this.cheeseImg = ImageIO.read(new File(cheesePath.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        createCheeseLabel();
    }
    public BufferedImage getCheeseImg(){
        return cheeseImg;
    }

    /**
     * Void function to setup locations and draw "Cheese" labels
     * @author Canh Nhat Minh Le
     * */
    public void createCheeseLabel()
    {   cheeseLabel = new JLabel();
        cheeseLabel.setBounds(y*100,(x+1)*100,100,100);
        cheeseLabel.setBackground(Color.white);
        cheeseLabel.setOpaque(true);
        Image cheeseImg = this.getCheeseImg().getScaledInstance(cheeseLabel.getWidth(), cheeseLabel.getHeight(),
                Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(cheeseImg);
        cheeseLabel.setIcon(icon);
        cheeseLabel.setVisible(true);
    }
}
