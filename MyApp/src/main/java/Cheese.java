import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.awt.image.BufferedImage;


public class Cheese extends Rewards{

    private BufferedImage cheeseImg;
    Cheese(int x, int y, int value){

        super(x, y, value);
       // drawCheese();
        try {
            Path cheesePath = Paths.get("MyApp/Images/cheese.png").toRealPath();;
            this.cheeseImg = ImageIO.read(new File(cheesePath.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getCheeseImg()
    {
        return this.cheeseImg;
    }
    /*public void drawCheese()
    {
        try {
            Path cheesePath = Paths.get("Images/cheese.png").toRealPath();;
            this.cheeseImg = ImageIO.read(new File(cheesePath.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}
