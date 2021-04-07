import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestCatMovement {

    private Game myGame;

    @BeforeEach
    void setup()
    {
        myGame = new Game();
    }

    //Test two Cats
    @Test
    void Cats_MoveLeftIsEmpty() {
        myGame.getCat1().setCurrentX(4);
        myGame.getCat1().setCurrentY(1);
        myGame.getCat2().setCurrentX(8);
        myGame.getCat2().setCurrentY(4);
        myGame.moveMouse("left");
        myGame.validate();
        assertEquals(myGame.getCat1().getCurrentX(), 5);
        assertEquals(myGame.getCat1().getCurrentY(), 1);
        assertEquals(myGame.getCat2().getCurrentX(), 7);
        assertEquals(myGame.getCat2().getCurrentY(), 4);
    }

    @Test
    void MoveLeftIsBarrier() {
        myGame.getMyMouse().setCurrentX(8);
        myGame.getMyMouse().setCurrentY(7);
        myGame.validate();
        myGame.moveMouse("left");
        assertEquals(myGame.getMyMouse().getCurrentX(), 8);
        assertEquals(myGame.getMyMouse().getCurrentY(), 7);
    }

    @Test
    void MoveLeftIsCheese() {
        myGame.getMyMouse().setCurrentX(8);
        myGame.getMyMouse().setCurrentY(6);
        myGame.validate();
        myGame.moveMouse("left");
        assertEquals(myGame.getMyMouse().getCurrentX(), 7);
        assertEquals(myGame.getMyMouse().getCurrentY(), 6);
        assertEquals(myGame.getScore(),5);
        assertEquals(myGame.getMyMap().getLevelMap()[6][7].getIsCharacter(),true );
        assertEquals(myGame.getMyMap().getLevelMap()[6][7].getisCheese(), false);
        assertFalse(myGame.getCheese1().cheeseLabel.isVisible());
    }

    @Test
    void MoveLeftIsOrgCheese() {
        myGame.getMyMouse().setCurrentX(8);
        myGame.getMyMouse().setCurrentY(8);
        myGame.validate();
        myGame.moveMouse("up");
        myGame.moveMouse("up");
        myGame.moveMouse("up");
        myGame.getMyMouse().setCurrentX(4);
        myGame.getMyMouse().setCurrentY(1);
        myGame.validate();
        myGame.setRand2(40);
        myGame.setCountSteps(40);
        myGame.moveMouse("left");
        assertEquals(myGame.getMyMouse().getCurrentX(), 3);
        assertEquals(myGame.getMyMouse().getCurrentY(), 1);
        assertEquals(myGame.getScore(),10);
        assertEquals(myGame.getMyMap().getLevelMap()[1][3].getIsCharacter(),true );
        assertEquals(myGame.getMyMap().getLevelMap()[1][3].getIsOrganicCheese(), false);
        assertFalse(myGame.getOrgCheese2().organicLabel.isVisible());
    }
}