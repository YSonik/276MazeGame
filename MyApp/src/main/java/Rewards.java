
/**
 * <h1> Rewards </h1>
 * The Rewards program sets and gets the coordinates and the values of
 * two types of rewards (Cheese and organic cheese)
 * <p>
 * @author Canh Nhat Minh Le
 *
 */
public class Rewards {
    protected int x;
    protected int y;
    protected int value;

    /**
     * This is the constructor for the Rewards class which takes in three integer parameter
     * @param x indicating the x-coordinate of the reward.
     * @param y indicating the y-coordinate of the reward.
     * @param value indicating the score value of the reward.
     * */
    Rewards(int x, int y, int value)
    {
        this.x = x;
        this.y = y;
        this.value = value;
    }

    public void setX (int x)
    {
        this.x = x;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public int getX()
    {
        return this.x;
    }

    public int getY()
    {
        return this.y;
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
