package main.java;

public class Tile
{
    private boolean isEntrance;
    private boolean isExit;
    private boolean isBarrier;
    private boolean isReward;
    private boolean isCharacter;
    private boolean isEmpty;
    private boolean isCheese;
    private boolean isOrganicCheese;
    private boolean isMouseTrap;

    Tile(boolean hasBarrier, boolean hasReward, boolean isEntrance, boolean isExit, boolean isCharacter, boolean isEmpty,
         boolean isCheese, boolean isOrganicCheese, boolean isMouseTrap)
    {
        this.isBarrier = hasBarrier;
        this.isReward = hasReward;
        this.isEntrance = isEntrance;
        this.isExit = isExit;
        this.isCharacter = isCharacter;
        this.isEmpty = isEmpty;
        this.isCheese = isCheese;
        this.isOrganicCheese = isOrganicCheese;
        this.isMouseTrap = isMouseTrap;
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


    public boolean getisMouseTrap() {
        return isMouseTrap;
    }

    public boolean getisCheese() {
        return isCheese;
    }

    public boolean getIsOrganicCheese(){
        return isOrganicCheese;
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
        this.isEmpty = empty;
    }

    public void setEntrance(boolean value)
    {
        this.isEntrance = value;
    }

    public void setExit(boolean exit) {
        this.isExit = exit;
    }

    public void setCheese(boolean cheese) {
        isCheese = cheese;
    }
    public void setIsOrganicCheese(boolean orgCheese){isOrganicCheese = orgCheese;}
    public void setMouseTrap(boolean mouseTrap) {
        isMouseTrap = mouseTrap;
    }

}
