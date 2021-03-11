import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class OrganicCheese extends Rewards{
    private BufferedImage orgCheeseImg;
    OrganicCheese(int x, int y, int value) {
        super(x, y, value);
        try {
            Path orgCheesePath = Paths.get("MyApp/Images/stack_of_cheese.png").toRealPath();
            this.orgCheeseImg = ImageIO.read(new File(orgCheesePath.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public BufferedImage getOrgCheeseImg(){
        return orgCheeseImg;
    }

}
