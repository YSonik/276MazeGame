package main.java;

public class Rewards {
    protected int x;
    protected int y;
    protected int value;


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
