import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * <h1> OrganicCheese </h1>
 * This OrganicCheese program sets and gets the coordinates and values
 * of Organic Cheese Rewards while also import the image
 * <p>
 * @author Canh Nhat Minh Le
 */
public class OrganicCheese extends Rewards {
    private BufferedImage orgCheeseImg;
    public JLabel organicLabel;
    /**
     * Constructor for the program which contains X and Y coordinates of the OrganicCheese
     * while also imports the OrganicCheese Image
     * @param x indicates the x-coordinate of OrganicCheese
     * @param y indicates the y-coordinate of OrganicCheese
     * @param value indicates the value of OrganicCheese
     */
    OrganicCheese(int x, int y, int value) {
        super(x, y, value);
        try {
            Path orgCheesePath = Paths.get("Images/stack_of_cheese.png").toRealPath();
            this.orgCheeseImg = ImageIO.read(new File(orgCheesePath.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        createOrganicCheeseLabel();
    }

    public BufferedImage getOrgCheeseImg(){
        return orgCheeseImg;
    }
    /**
     * Void function to setup locations and draw "Organic Cheese" labels
     * */
    public void createOrganicCheeseLabel(){
        //Create the first Organic Cheese label and draw on JFrame
        organicLabel = new JLabel();
        organicLabel.setBounds(y*100, (x+1)*100, 100, 100);
        organicLabel.setOpaque(true);
        Image orgCheeseImg1 = this.getOrgCheeseImg().getScaledInstance(organicLabel.getWidth(), organicLabel.getHeight(),
                Image.SCALE_SMOOTH);
        ImageIcon icon1 = new ImageIcon(orgCheeseImg1);
        organicLabel.setIcon(icon1);
        organicLabel.setVisible(false);
    }
}

