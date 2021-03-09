public class Rewards {
    private int xCoord;
    private int yCoord;
    private int value;
    private final int chHeight = 100;
    private final int chWidth = 100;


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
