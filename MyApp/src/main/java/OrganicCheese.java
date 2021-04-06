import javax.imageio.ImageIO;
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
    }

    public BufferedImage getOrgCheeseImg(){
        return orgCheeseImg;
    }
}

