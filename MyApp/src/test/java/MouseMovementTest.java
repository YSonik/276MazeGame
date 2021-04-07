import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MouseMovementTest {

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
        assertEquals(myGame.getMyMap().getLevelMap()[7][6].getIsCharacter(),true );
        assertEquals(myGame.getMyMap().getLevelMap()[7][6].getisCheese(), false);
        assertFalse(myGame.getCheese1().cheeseLabel.isVisible());
    }
    @Test
    void MoveLeftIsOrgCheese() {
        myGame.getMyMouse().setCurrentX(3);
        myGame.getMyMouse().setCurrentY(1);
        myGame.validate();
        myGame.moveMouse("left");
        assertEquals(myGame.getMyMouse().getCurrentX(), 5);
        assertEquals(myGame.getMyMouse().getCurrentY(), 1);
        //assertEquals(myGame.getScore(),10);
        //assertEquals(myGame.getMyMap().getLevelMap()[7][6].getIsCharacter(),true );
        //assertEquals(myGame.getMyMap().getLevelMap()[7][6].getisCheese(), false);
        //assertFalse(myGame.getCheese1().cheeseLabel.isVisible());

    }
    @Test
    void MoveLeftIsTrap() {


    }



}