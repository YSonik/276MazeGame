public class Cheese extends Rewards{

    Cheese(int x, int y, int value) {
        super(x, y, value);

        this.x = x;
        this.y = y;
        this. value = value;
    }

    @Override
    public void setX(int x) {
        super.setX(x);
    }

    @Override
    public void setY(int y) {
        super.setY(y);
    }

    @Override
    public void setValue(int value) {
        super.setValue(value);
    }

    @Override
    public int getX() {
        return super.getX();
    }

    @Override
    public int getY() {
        return super.getY();
    }

    @Override
    public int getValue() {
        return super.getValue();
    }
}
