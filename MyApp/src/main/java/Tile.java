/**
 * <h1>Tile Class<h1/>
 * This class represents one uniform location on the maze grid that can be occupied by a an object.
 * @author Yogesh sonik
 *  @version 1.0
 * */

public class Tile
{
    private boolean isEntrance;
    private boolean isExit;
    private boolean isBarrier;
    private boolean isCharacter;
    private boolean isEmpty;
    private boolean isCheese;
    private boolean isOrganicCheese;
    private boolean isMouseTrap;

    /**
     * This is the constructor for the tile class which takes in 8 boolean parameters from which only one will be true to distinguish the identity of the object that it represents.
     * @param hasBarrier distinguishes the tile to represent a barrier
     * @param isCharacter distinguishes the tile as the location of the mouse
     * @param isCheese distinguishes the tile as the location of a cheese
     * @param isEmpty distinguishes the tile as an empty location
     * @param isEntrance distinguishes the tile as the entrance
     * @param isExit distinguishes the tile as the exit
     * @param isMouseTrap distinguishes the tile as a location for a mouse trap
     * @param isOrganicCheese distinguishes the tile as a location for an organic cheese
     * */
    Tile(boolean hasBarrier, boolean isEntrance, boolean isExit, boolean isCharacter, boolean isEmpty,
         boolean isCheese, boolean isOrganicCheese, boolean isMouseTrap)
    {
        this.isBarrier = hasBarrier;
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
