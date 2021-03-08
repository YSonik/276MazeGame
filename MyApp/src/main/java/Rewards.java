import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Rewards {
    private int xCoord;
    private int yCoord;
    private int value;
    private final int chHeight = 25;
    private final int chWidth = 25;

    Rewards(int x, int y, int z)
    {
        this.xCoord = x;
        this.yCoord = y;
        this.value = z;
    }

    public void setXCoord (int x)
    {
        this.xCoord = x;
    }

    public void setYCoord(int y)
    {
        this.yCoord = y;
    }

    public int getXCoord()
    {
        return this.xCoord;
    }

    public int getYCoord()
    {
        return this.yCoord;
    }

    public void setValue(int value)
    {
        this.value = value;
    }

    public int getValue ()
    {
        return this.value;
    }





}
