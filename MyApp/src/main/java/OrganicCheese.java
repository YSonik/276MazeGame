import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.awt.image.BufferedImage;

public class OrganicCheese extends Rewards {
    private boolean isInvisible;
    private BufferedImage orgCheese;
    OrganicCheese(int x, int y, int value, boolean invisibility)
    {
        super(x,y,value);
        this.isInvisible = invisibility;
        //drawOrgCheese();
        try {
            Path orgCheesePath = Paths.get("Images/stack_of_cheese.png").toRealPath();;
            this.orgCheese = ImageIO.read(new File(orgCheesePath.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage orgCheeseImg()
    {
        return this.orgCheese;
    }

    public void setInvisibility(boolean invisibility)
    {
        this.isInvisible = invisibility;
    }

    public boolean getInvisibility(){
        return this.isInvisible;
    }

    /*public void drawOrgCheese()
    {
        try {
            Path orgCheesePath = Paths.get("Images/stack_of_cheese.png").toRealPath();;
            this.orgCheese = ImageIO.read(new File(orgCheesePath.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

}
