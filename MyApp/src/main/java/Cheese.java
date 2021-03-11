import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
public class Cheese extends Rewards{
    private BufferedImage cheeseImg;
    Cheese(int x, int y, int value) {
        super(x, y, value);
        try {
            Path cheesePath = Paths.get("MyApp/Images/cheese.png").toRealPath();;
            this.cheeseImg = ImageIO.read(new File(cheesePath.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public BufferedImage getCheeseImg(){
        return cheeseImg;
    }
}
