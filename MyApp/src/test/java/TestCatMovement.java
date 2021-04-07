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

    @Test
    void MoveLeftIsEmpty() {
        myGame.getMyMouse().setCurrentX(8);
        myGame.getMyMouse().setCurrentY(8);
        myGame.validate();
        myGame.moveMouse("left");
        assertEquals(myGame.getMyMouse().getCurrentX(), 7);
        assertEquals(myGame.getMyMouse().getCurrentY(), 8);
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