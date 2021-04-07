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
        //Add integration tests here by checking the position of the jLabel

    }
    @Test
    void MoveLeftIsTrap() {
        //Score Greater equal to penalty
        myGame.getMyMouse().setCurrentX(5);
        myGame.getMyMouse().setCurrentY(4);
        myGame.validate();
        myGame.setScore(6);
        myGame.moveMouse("left");
        assertEquals(myGame.getMyMouse().getCurrentX(), 4);
        assertEquals(myGame.getMyMouse().getCurrentY(), 4);
        assertEquals(myGame.getScore(),0);
        assertEquals(myGame.getMyMap().getLevelMap()[4][4].getIsCharacter(),true );
        assertEquals(myGame.getMyMap().getLevelMap()[4][4].getisMouseTrap(), false);
        assertFalse(myGame.getTrap2().trapLabel.isVisible());
    }

    @Test
    void MoveLeftIsTrap2(){
        myGame.testing = true;
        myGame.getMyMouse().setCurrentX(5);
        myGame.getMyMouse().setCurrentY(4);
        myGame.validate();
        myGame.moveMouse("left");
        assertEquals(myGame.getMyMouse().getCurrentX(), 4);
        assertEquals(myGame.getMyMouse().getCurrentY(), 4);
        assertEquals(myGame.getScore(),-6);
        assertEquals(myGame.getMyMap().getLevelMap()[4][4].getIsCharacter(),true );
        assertEquals(myGame.getMyMap().getLevelMap()[4][4].getisMouseTrap(), false);
    }



    @Test
    void MoveRightIsEmpty() {
        myGame.getMyMouse().setCurrentX(7);
        myGame.getMyMouse().setCurrentY(8);
        myGame.validate();
        myGame.moveMouse("right");
        assertEquals(myGame.getMyMouse().getCurrentX(), 8);
        assertEquals(myGame.getMyMouse().getCurrentY(), 8);
    }

    @Test
    void MoveRightIsBarrier() {
        myGame.getMyMouse().setCurrentX(8);
        myGame.getMyMouse().setCurrentY(7);
        myGame.validate();
        myGame.moveMouse("right");
        assertEquals(myGame.getMyMouse().getCurrentX(), 8);
        assertEquals(myGame.getMyMouse().getCurrentY(), 7);
    }

    @Test
    void MoveRightIsCheese() {
        myGame.getMyMouse().setCurrentX(2);
        myGame.getMyMouse().setCurrentY(4);
        myGame.validate();
        myGame.moveMouse("right");
        assertEquals(myGame.getMyMouse().getCurrentX(), 3);
        assertEquals(myGame.getMyMouse().getCurrentY(), 4);
        assertEquals(myGame.getScore(),5);
        assertEquals(myGame.getMyMap().getLevelMap()[4][3].getIsCharacter(),true );
        assertEquals(myGame.getMyMap().getLevelMap()[4][3].getisCheese(), false);
        assertFalse(myGame.getCheese2().cheeseLabel.isVisible());
    }
    @Test
    void MoveRightIsOrgCheese() {
        myGame.getMyMouse().setCurrentX(8);
        myGame.getMyMouse().setCurrentY(8);
        myGame.validate();
        myGame.moveMouse("up");
        myGame.moveMouse("up");
        myGame.moveMouse("up");
        myGame.getMyMouse().setCurrentX(2);
        myGame.getMyMouse().setCurrentY(1);
        myGame.validate();
        myGame.setRand2(40);
        myGame.setCountSteps(40);
        myGame.moveMouse("right");
        assertEquals(myGame.getMyMouse().getCurrentX(), 3);
        assertEquals(myGame.getMyMouse().getCurrentY(), 1);
        assertEquals(myGame.getScore(),10);
        assertEquals(myGame.getMyMap().getLevelMap()[1][3].getIsCharacter(),true );
        assertEquals(myGame.getMyMap().getLevelMap()[1][3].getIsOrganicCheese(), false);
        assertFalse(myGame.getOrgCheese2().organicLabel.isVisible());
    }
    @Test
    void MoveRightIsTrap() {
        //Score Greater equal to penalty
        myGame.getMyMouse().setCurrentX(5);
        myGame.getMyMouse().setCurrentY(3);
        myGame.validate();
        myGame.setScore(6);
        myGame.moveMouse("right");
        assertEquals(myGame.getMyMouse().getCurrentX(), 6);
        assertEquals(myGame.getMyMouse().getCurrentY(), 3);
        assertEquals(myGame.getScore(),0);
        assertEquals(myGame.getMyMap().getLevelMap()[3][6].getIsCharacter(),true );
        assertEquals(myGame.getMyMap().getLevelMap()[3][6].getisMouseTrap(), false);
        assertFalse(myGame.getTrap1().trapLabel.isVisible());
    }

    @Test
    void MoveRightIsTrap2(){
        myGame.testing = true;
        myGame.getMyMouse().setCurrentX(5);
        myGame.getMyMouse().setCurrentY(3);
        myGame.validate();
        myGame.moveMouse("right");
        assertEquals(myGame.getMyMouse().getCurrentX(), 6);
        assertEquals(myGame.getMyMouse().getCurrentY(), 3);
        assertEquals(myGame.getScore(),-6);
        assertEquals(myGame.getMyMap().getLevelMap()[3][6].getIsCharacter(),true );
        assertEquals(myGame.getMyMap().getLevelMap()[3][6].getisMouseTrap(), false);
    }

    @Test
    void MoveUpIsEmpty() {
        myGame.getMyMouse().setCurrentX(8);
        myGame.getMyMouse().setCurrentY(8);
        myGame.validate();
        myGame.moveMouse("up");
        assertEquals(myGame.getMyMouse().getCurrentX(), 8);
        assertEquals(myGame.getMyMouse().getCurrentY(), 7);
    }

    @Test
    void MoveUpIsBarrier() {
        myGame.getMyMouse().setCurrentX(7);
        myGame.getMyMouse().setCurrentY(8);
        myGame.validate();
        myGame.moveMouse("up");
        assertEquals(myGame.getMyMouse().getCurrentX(), 7);
        assertEquals(myGame.getMyMouse().getCurrentY(), 8);
    }

    @Test
    void MoveUpIsCheese() {
        myGame.getMyMouse().setCurrentX(3);
        myGame.getMyMouse().setCurrentY(5);
        myGame.validate();
        myGame.moveMouse("up");
        assertEquals(myGame.getMyMouse().getCurrentX(), 3);
        assertEquals(myGame.getMyMouse().getCurrentY(), 4);
        assertEquals(myGame.getScore(),5);
        assertEquals(myGame.getMyMap().getLevelMap()[4][3].getIsCharacter(),true );
        assertEquals(myGame.getMyMap().getLevelMap()[4][3].getisCheese(), false);
        assertFalse(myGame.getCheese2().cheeseLabel.isVisible());
    }
    @Test
    void MoveUpIsOrgCheese() {
        myGame.getMyMouse().setCurrentX(3);
        myGame.getMyMouse().setCurrentY(8);
        myGame.validate();
        myGame.setRand1(40);
        myGame.setCountSteps(40);
        myGame.moveMouse("up");
        assertEquals(myGame.getMyMouse().getCurrentX(), 3);
        assertEquals(myGame.getMyMouse().getCurrentY(), 7);
        assertEquals(myGame.getScore(),10);
        assertEquals(myGame.getMyMap().getLevelMap()[7][3].getIsCharacter(),true );
        assertEquals(myGame.getMyMap().getLevelMap()[7][3].getIsOrganicCheese(), false);
        assertFalse(myGame.getOrgCheese2().organicLabel.isVisible());

    }
    @Test
    void MoveUpIsTrap() {
        //Score Greater equal to penalty
        myGame.getMyMouse().setCurrentX(6);
        myGame.getMyMouse().setCurrentY(4);
        myGame.validate();
        myGame.setScore(6);
        myGame.moveMouse("up");
        assertEquals(myGame.getMyMouse().getCurrentX(), 6);
        assertEquals(myGame.getMyMouse().getCurrentY(), 3);
        assertEquals(myGame.getScore(),0);
        assertEquals(myGame.getMyMap().getLevelMap()[3][6].getIsCharacter(),true );
        assertEquals(myGame.getMyMap().getLevelMap()[3][6].getisMouseTrap(), false);
        assertFalse(myGame.getTrap1().trapLabel.isVisible());
    }

    @Test
    void MoveUpIsTrap2(){
        myGame.testing = true;
        myGame.getMyMouse().setCurrentX(6);
        myGame.getMyMouse().setCurrentY(4);
        myGame.validate();
        myGame.moveMouse("up");
        assertEquals(myGame.getMyMouse().getCurrentX(), 6);
        assertEquals(myGame.getMyMouse().getCurrentY(), 3);
        assertEquals(myGame.getScore(),-6);
        assertEquals(myGame.getMyMap().getLevelMap()[3][6].getIsCharacter(),true );
        assertEquals(myGame.getMyMap().getLevelMap()[3][6].getisMouseTrap(), false);
    }

    @Test
    void MoveDownIsEmpty() {
        myGame.getMyMouse().setCurrentX(8);
        myGame.getMyMouse().setCurrentY(7);
        myGame.validate();
        myGame.moveMouse("down");
        assertEquals(myGame.getMyMouse().getCurrentX(), 8);
        assertEquals(myGame.getMyMouse().getCurrentY(), 8);
    }

    @Test
    void MoveDownIsBarrier() {
        myGame.getMyMouse().setCurrentX(2);
        myGame.getMyMouse().setCurrentY(4);
        myGame.validate();
        myGame.moveMouse("down");
        assertEquals(myGame.getMyMouse().getCurrentX(), 2);
        assertEquals(myGame.getMyMouse().getCurrentY(), 4);
    }

    @Test
    void MoveDownIsCheese() {
        myGame.getMyMouse().setCurrentX(3);
        myGame.getMyMouse().setCurrentY(3);
        myGame.validate();
        myGame.moveMouse("down");
        assertEquals(myGame.getMyMouse().getCurrentX(), 3);
        assertEquals(myGame.getMyMouse().getCurrentY(), 4);
        assertEquals(myGame.getScore(),5);
        assertEquals(myGame.getMyMap().getLevelMap()[4][3].getIsCharacter(),true );
        assertEquals(myGame.getMyMap().getLevelMap()[4][3].getisCheese(), false);
        assertFalse(myGame.getCheese2().cheeseLabel.isVisible());
    }
    @Test
    void MoveDownIsOrgCheese() {
        myGame.getMyMouse().setCurrentX(3);
        myGame.getMyMouse().setCurrentY(6);
        myGame.validate();
        myGame.setRand1(40);
        myGame.setCountSteps(40);
        myGame.moveMouse("down");
        assertEquals(myGame.getMyMouse().getCurrentX(), 3);
        assertEquals(myGame.getMyMouse().getCurrentY(), 7);
        assertEquals(myGame.getScore(),10);
        assertEquals(myGame.getMyMap().getLevelMap()[7][3].getIsCharacter(),true );
        assertEquals(myGame.getMyMap().getLevelMap()[7][3].getIsOrganicCheese(), false);
        assertFalse(myGame.getOrgCheese1().organicLabel.isVisible());

    }
    @Test
    void MoveDownIsTrap() {
        //Score Greater equal to penalty
        myGame.getMyMouse().setCurrentX(4);
        myGame.getMyMouse().setCurrentY(3);
        myGame.validate();
        myGame.setScore(6);
        myGame.moveMouse("down");
        assertEquals(myGame.getMyMouse().getCurrentX(), 4);
        assertEquals(myGame.getMyMouse().getCurrentY(), 4);
        assertEquals(myGame.getScore(),0);
        assertEquals(myGame.getMyMap().getLevelMap()[4][4].getIsCharacter(),true );
        assertEquals(myGame.getMyMap().getLevelMap()[4][4].getisMouseTrap(), false);
        assertFalse(myGame.getTrap2().trapLabel.isVisible());
    }

    @Test
    void MoveDownIsTrap2(){
        myGame.testing = true;
        myGame.getMyMouse().setCurrentX(4);
        myGame.getMyMouse().setCurrentY(3);
        myGame.validate();
        myGame.moveMouse("down");
        assertEquals(myGame.getMyMouse().getCurrentX(), 4);
        assertEquals(myGame.getMyMouse().getCurrentY(), 4);
        assertEquals(myGame.getScore(),-6);
        assertEquals(myGame.getMyMap().getLevelMap()[4][4].getIsCharacter(),true );
        assertEquals(myGame.getMyMap().getLevelMap()[4][4].getisMouseTrap(), false);
    }


}