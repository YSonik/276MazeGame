public class Tile
{
    private final int tileHeight = 100;
    private final int tileWidth = 100;
    private boolean isEntrance;
    private boolean isExit;
    private boolean isBarrier;
    private boolean isReward;
    private boolean isCharacter;
    private boolean isEmpty;
    //private boolean isCat;
    //private boolean isMouseTrap;

    Tile(boolean hasBarrier, boolean hasReward, boolean isEntrance, boolean isExit, boolean isCharacter, boolean isEmpty)
    {
        this.isBarrier = hasBarrier;
        this.isReward = hasReward;
        this.isEntrance = isEntrance;
        this.isExit = isExit;
        this.isCharacter = isCharacter;
        this.isEmpty = isEmpty;
    }

    public boolean getisBarrier()
    {
        return this.isBarrier;
    }

    public boolean getisReward()
    {
        return this.isReward;
    }

    public boolean getIsCharacter()
    {
        return this.isCharacter;
    }
    public boolean getisEmpty()
    {
        return this.isEmpty;
    }

    public boolean getisEntrance() {
        return this.isEntrance;
    }

    public boolean getisExit() {
        return this.isExit;
    }

    public void setBarrier(boolean value)
    {
        this.isBarrier = value;
    }

    public void setReward(boolean value)
    {
        this.isReward = value;
    }

    public void setCharacter(boolean value)
    {
        this.isCharacter = value;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }

    public void setEntrance(boolean value)
    {
        this.isEntrance = value;
    }
    public void setExit(boolean exit) {
        isExit = exit;
    }

}
