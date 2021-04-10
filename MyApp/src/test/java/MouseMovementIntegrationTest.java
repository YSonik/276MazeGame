import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MouseMovementIntegrationTest {

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
        assertEquals(myGame.getMyMouse().mouseLabel.getX(),myGame.getMyMouse().getCurrentX()*100);
        assertEquals(myGame.getMyMouse().mouseLabel.getY(),(myGame.getMyMouse().getCurrentY()+1)*100);
    }

    @Test
    void MoveLeftIsBarrier() {
        myGame.getMyMouse().setCurrentX(8);
        myGame.getMyMouse().setCurrentY(7);
        myGame.validate();
        myGame.moveMouse("left");
        assertEquals(myGame.getMyMouse().getCurrentX(), 8);
        assertEquals(myGame.getMyMouse().getCurrentY(), 7);
        assertEquals(myGame.getMyMouse().mouseLabel.getX(),myGame.getMyMouse().getCurrentX()*100);
        assertEquals(myGame.getMyMouse().mouseLabel.getY(),(myGame.getMyMouse().getCurrentY()+1)*100);
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
        assertTrue(myGame.getMyMap().getLevelMap()[6][7].getIsCharacter());
        assertFalse(myGame.getMyMap().getLevelMap()[6][7].getisCheese());
        assertFalse(myGame.getCheese1().cheeseLabel.isVisible());
        assertEquals(myGame.getMyMouse().mouseLabel.getX(),myGame.getMyMouse().getCurrentX()*100);
        assertEquals(myGame.getMyMouse().mouseLabel.getY(),(myGame.getMyMouse().getCurrentY()+1)*100);
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
        assertTrue(myGame.getMyMap().getLevelMap()[1][3].getIsCharacter());
        assertFalse(myGame.getMyMap().getLevelMap()[1][3].getIsOrganicCheese());
        assertFalse(myGame.getOrgCheese2().organicLabel.isVisible());
        //Add integration tests here by checking the position of the jLabel
        assertEquals(myGame.getMyMouse().mouseLabel.getX(),myGame.getMyMouse().getCurrentX()*100);
        assertEquals(myGame.getMyMouse().mouseLabel.getY(),(myGame.getMyMouse().getCurrentY()+1)*100);

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
        assertTrue(myGame.getMyMap().getLevelMap()[4][4].getIsCharacter());
        assertFalse(myGame.getMyMap().getLevelMap()[4][4].getisMouseTrap());
        assertFalse(myGame.getTrap2().trapLabel.isVisible());
        assertEquals(myGame.getMyMouse().mouseLabel.getX(),myGame.getMyMouse().getCurrentX()*100);
        assertEquals(myGame.getMyMouse().mouseLabel.getY(),(myGame.getMyMouse().getCurrentY()+1)*100);
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
        assertTrue(myGame.getMyMap().getLevelMap()[4][4].getIsCharacter());
        assertFalse(myGame.getMyMap().getLevelMap()[4][4].getisMouseTrap());
        assertFalse(myGame.getInGame());
        assertEquals(myGame.getMyMouse().mouseLabel.getX(),myGame.getMyMouse().getCurrentX()*100);
        assertEquals(myGame.getMyMouse().mouseLabel.getY(),(myGame.getMyMouse().getCurrentY()+1)*100);
    }



    @Test
    void MoveRightIsEmpty() {
        myGame.getMyMouse().setCurrentX(7);
        myGame.getMyMouse().setCurrentY(8);
        myGame.validate();
        myGame.moveMouse("right");
        assertEquals(myGame.getMyMouse().getCurrentX(), 8);
        assertEquals(myGame.getMyMouse().getCurrentY(), 8);
        assertEquals(myGame.getMyMouse().mouseLabel.getX(),myGame.getMyMouse().getCurrentX()*100);
        assertEquals(myGame.getMyMouse().mouseLabel.getY(),(myGame.getMyMouse().getCurrentY()+1)*100);
    }

    @Test
    void MoveRightIsBarrier() {
        myGame.getMyMouse().setCurrentX(8);
        myGame.getMyMouse().setCurrentY(7);
        myGame.validate();
        myGame.moveMouse("right");
        assertEquals(myGame.getMyMouse().getCurrentX(), 8);
        assertEquals(myGame.getMyMouse().getCurrentY(), 7);
        assertEquals(myGame.getMyMouse().mouseLabel.getX(),myGame.getMyMouse().getCurrentX()*100);
        assertEquals(myGame.getMyMouse().mouseLabel.getY(),(myGame.getMyMouse().getCurrentY()+1)*100);
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
        assertTrue(myGame.getMyMap().getLevelMap()[4][3].getIsCharacter());
        assertFalse(myGame.getMyMap().getLevelMap()[4][3].getisCheese());
        assertFalse(myGame.getCheese2().cheeseLabel.isVisible());
        assertEquals(myGame.getMyMouse().mouseLabel.getX(),myGame.getMyMouse().getCurrentX()*100);
        assertEquals(myGame.getMyMouse().mouseLabel.getY(),(myGame.getMyMouse().getCurrentY()+1)*100);
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
        assertTrue(myGame.getMyMap().getLevelMap()[1][3].getIsCharacter());
        assertFalse(myGame.getMyMap().getLevelMap()[1][3].getIsOrganicCheese());
        assertFalse(myGame.getOrgCheese2().organicLabel.isVisible());
        assertEquals(myGame.getMyMouse().mouseLabel.getX(),myGame.getMyMouse().getCurrentX()*100);
        assertEquals(myGame.getMyMouse().mouseLabel.getY(),(myGame.getMyMouse().getCurrentY()+1)*100);
    }
    @Test
    void MoveRightIsTrap() {
        //Score Greater than or equal to penalty
        myGame.getMyMouse().setCurrentX(5);
        myGame.getMyMouse().setCurrentY(3);
        myGame.validate();
        myGame.setScore(6);
        myGame.moveMouse("right");
        assertEquals(myGame.getMyMouse().getCurrentX(), 6);
        assertEquals(myGame.getMyMouse().getCurrentY(), 3);
        assertEquals(myGame.getScore(),0);
        assertTrue(myGame.getMyMap().getLevelMap()[3][6].getIsCharacter());
        assertFalse(myGame.getMyMap().getLevelMap()[3][6].getisMouseTrap());
        assertFalse(myGame.getTrap1().trapLabel.isVisible());
        assertEquals(myGame.getMyMouse().mouseLabel.getX(),myGame.getMyMouse().getCurrentX()*100);
        assertEquals(myGame.getMyMouse().mouseLabel.getY(),(myGame.getMyMouse().getCurrentY()+1)*100);
    }

    @Test
    void MoveRightIsTrap2(){
        //Score less than penalty
        myGame.testing = true;
        myGame.getMyMouse().setCurrentX(5);
        myGame.getMyMouse().setCurrentY(3);
        myGame.validate();
        myGame.moveMouse("right");
        assertEquals(myGame.getMyMouse().getCurrentX(), 6);
        assertEquals(myGame.getMyMouse().getCurrentY(), 3);
        assertEquals(myGame.getScore(),-6);
        assertTrue(myGame.getMyMap().getLevelMap()[3][6].getIsCharacter());
        assertFalse(myGame.getMyMap().getLevelMap()[3][6].getisMouseTrap());
        assertFalse(myGame.getInGame());
        assertEquals(myGame.getMyMouse().mouseLabel.getX(),myGame.getMyMouse().getCurrentX()*100);
        assertEquals(myGame.getMyMouse().mouseLabel.getY(),(myGame.getMyMouse().getCurrentY()+1)*100);
    }

    @Test
    void MoveUpIsEmpty() {
        myGame.getMyMouse().setCurrentX(8);
        myGame.getMyMouse().setCurrentY(8);
        myGame.validate();
        myGame.moveMouse("up");
        assertEquals(myGame.getMyMouse().getCurrentX(), 8);
        assertEquals(myGame.getMyMouse().getCurrentY(), 7);
        assertEquals(myGame.getMyMouse().mouseLabel.getX(),myGame.getMyMouse().getCurrentX()*100);
        assertEquals(myGame.getMyMouse().mouseLabel.getY(),(myGame.getMyMouse().getCurrentY()+1)*100);
    }

    @Test
    void MoveUpIsBarrier() {
        myGame.getMyMouse().setCurrentX(7);
        myGame.getMyMouse().setCurrentY(8);
        myGame.validate();
        myGame.moveMouse("up");
        assertEquals(myGame.getMyMouse().getCurrentX(), 7);
        assertEquals(myGame.getMyMouse().getCurrentY(), 8);
        assertEquals(myGame.getMyMouse().mouseLabel.getX(),myGame.getMyMouse().getCurrentX()*100);
        assertEquals(myGame.getMyMouse().mouseLabel.getY(),(myGame.getMyMouse().getCurrentY()+1)*100);
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
        assertTrue(myGame.getMyMap().getLevelMap()[4][3].getIsCharacter());
        assertFalse(myGame.getMyMap().getLevelMap()[4][3].getisCheese());
        assertFalse(myGame.getCheese2().cheeseLabel.isVisible());
        assertEquals(myGame.getMyMouse().mouseLabel.getX(),myGame.getMyMouse().getCurrentX()*100);
        assertEquals(myGame.getMyMouse().mouseLabel.getY(),(myGame.getMyMouse().getCurrentY()+1)*100);
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
        assertTrue(myGame.getMyMap().getLevelMap()[7][3].getIsCharacter());
        assertFalse(myGame.getMyMap().getLevelMap()[7][3].getIsOrganicCheese());
        assertFalse(myGame.getOrgCheese1().organicLabel.isVisible());
        assertEquals(myGame.getMyMouse().mouseLabel.getX(),myGame.getMyMouse().getCurrentX()*100);
        assertEquals(myGame.getMyMouse().mouseLabel.getY(),(myGame.getMyMouse().getCurrentY()+1)*100);
    }
    @Test
    void MoveUpIsTrap() {
        //Score Greater than or equal to penalty
        myGame.getMyMouse().setCurrentX(6);
        myGame.getMyMouse().setCurrentY(4);
        myGame.validate();
        myGame.setScore(6);
        myGame.moveMouse("up");
        assertEquals(myGame.getMyMouse().getCurrentX(), 6);
        assertEquals(myGame.getMyMouse().getCurrentY(), 3);
        assertEquals(myGame.getScore(),0);
        assertTrue(myGame.getMyMap().getLevelMap()[3][6].getIsCharacter());
        assertFalse(myGame.getMyMap().getLevelMap()[3][6].getisMouseTrap());
        assertFalse(myGame.getTrap1().trapLabel.isVisible());
        assertEquals(myGame.getMyMouse().mouseLabel.getX(),myGame.getMyMouse().getCurrentX()*100);
        assertEquals(myGame.getMyMouse().mouseLabel.getY(),(myGame.getMyMouse().getCurrentY()+1)*100);
    }

    @Test
    void MoveUpIsTrap2(){
        //Score is less than penalty
        myGame.testing = true;
        myGame.getMyMouse().setCurrentX(6);
        myGame.getMyMouse().setCurrentY(4);
        myGame.validate();
        myGame.moveMouse("up");
        assertEquals(myGame.getMyMouse().getCurrentX(), 6);
        assertEquals(myGame.getMyMouse().getCurrentY(), 3);
        assertEquals(myGame.getScore(),-6);
        assertTrue(myGame.getMyMap().getLevelMap()[3][6].getIsCharacter());
        assertFalse(myGame.getMyMap().getLevelMap()[3][6].getisMouseTrap());
        assertFalse(myGame.getInGame());
        assertEquals(myGame.getMyMouse().mouseLabel.getX(),myGame.getMyMouse().getCurrentX()*100);
        assertEquals(myGame.getMyMouse().mouseLabel.getY(),(myGame.getMyMouse().getCurrentY()+1)*100);
    }

    @Test
    void MoveDownIsEmpty() {
        myGame.getMyMouse().setCurrentX(8);
        myGame.getMyMouse().setCurrentY(7);
        myGame.validate();
        myGame.moveMouse("down");
        assertEquals(myGame.getMyMouse().getCurrentX(), 8);
        assertEquals(myGame.getMyMouse().getCurrentY(), 8);
        assertEquals(myGame.getMyMouse().mouseLabel.getX(),myGame.getMyMouse().getCurrentX()*100);
        assertEquals(myGame.getMyMouse().mouseLabel.getY(),(myGame.getMyMouse().getCurrentY()+1)*100);
    }

    @Test
    void MoveDownIsBarrier() {
        myGame.getMyMouse().setCurrentX(2);
        myGame.getMyMouse().setCurrentY(4);
        myGame.validate();
        myGame.moveMouse("down");
        assertEquals(myGame.getMyMouse().getCurrentX(), 2);
        assertEquals(myGame.getMyMouse().getCurrentY(), 4);
        assertEquals(myGame.getMyMouse().mouseLabel.getX(),myGame.getMyMouse().getCurrentX()*100);
        assertEquals(myGame.getMyMouse().mouseLabel.getY(),(myGame.getMyMouse().getCurrentY()+1)*100);
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
        assertTrue(myGame.getMyMap().getLevelMap()[4][3].getIsCharacter());
        assertFalse(myGame.getMyMap().getLevelMap()[4][3].getisCheese());
        assertFalse(myGame.getCheese2().cheeseLabel.isVisible());
        assertEquals(myGame.getMyMouse().mouseLabel.getX(),myGame.getMyMouse().getCurrentX()*100);
        assertEquals(myGame.getMyMouse().mouseLabel.getY(),(myGame.getMyMouse().getCurrentY()+1)*100);
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
        assertTrue(myGame.getMyMap().getLevelMap()[7][3].getIsCharacter());
        assertFalse(myGame.getMyMap().getLevelMap()[7][3].getIsOrganicCheese());
        assertFalse(myGame.getOrgCheese1().organicLabel.isVisible());
        assertEquals(myGame.getMyMouse().mouseLabel.getX(),myGame.getMyMouse().getCurrentX()*100);
        assertEquals(myGame.getMyMouse().mouseLabel.getY(),(myGame.getMyMouse().getCurrentY()+1)*100);
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
        assertTrue(myGame.getMyMap().getLevelMap()[4][4].getIsCharacter());
        assertFalse(myGame.getMyMap().getLevelMap()[4][4].getisMouseTrap());
        assertFalse(myGame.getTrap2().trapLabel.isVisible());
        assertEquals(myGame.getMyMouse().mouseLabel.getX(),myGame.getMyMouse().getCurrentX()*100);
        assertEquals(myGame.getMyMouse().mouseLabel.getY(),(myGame.getMyMouse().getCurrentY()+1)*100);
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
        assertTrue(myGame.getMyMap().getLevelMap()[4][4].getIsCharacter());
        assertFalse(myGame.getMyMap().getLevelMap()[4][4].getisMouseTrap());
        assertFalse(myGame.getInGame());
        assertEquals(myGame.getMyMouse().mouseLabel.getX(),myGame.getMyMouse().getCurrentX()*100);
        assertEquals(myGame.getMyMouse().mouseLabel.getY(),(myGame.getMyMouse().getCurrentY()+1)*100);
    }

    @Test //This is the case where the mouse reaches the exit without eating all regular cheese
    void MouseReachExit(){
        myGame.testing = true;
        myGame.getMyMouse().setCurrentX(1);
        myGame.getMyMouse().setCurrentY(1);
        myGame.validate();
        myGame.moveMouse("left");
        assertEquals(myGame.getMyMouse().getCurrentX(),0);
        assertEquals(myGame.getMyMouse().getCurrentY(),1);
        assertFalse(myGame.getWinGame());
        assertEquals(myGame.getMyMouse().mouseLabel.getX(),myGame.getMyMouse().getCurrentX()*100);
        assertEquals(myGame.getMyMouse().mouseLabel.getY(),(myGame.getMyMouse().getCurrentY()+1)*100);
    }

    @Test //This is the case where the mouse reaches the exit after eating all regular cheese
    void MouseReachExit2(){
        myGame.testing = true;
        //eat cheese 1
        myGame.getMyMouse().setCurrentX(8);
        myGame.getMyMouse().setCurrentY(6);
        myGame.validate();
        myGame.moveMouse("left");
        //eat cheese 2
        myGame.getMyMouse().setCurrentX(3);
        myGame.getMyMouse().setCurrentY(5);
        myGame.validate();
        myGame.moveMouse("up");
        assertEquals(myGame.getScore(),10);
        assertEquals(myGame.getMyMouse().getCheeseEaten(),2);
        //Reach Exit
        myGame.getMyMouse().setCurrentX(1);
        myGame.getMyMouse().setCurrentY(1);
        myGame.validate();
        myGame.moveMouse("left");
        assertEquals(myGame.getMyMouse().getCurrentX(),0);
        assertEquals(myGame.getMyMouse().getCurrentY(),1);
        assertTrue(myGame.getWinGame());
        assertFalse(myGame.getInGame());
        assertEquals(myGame.getMyMouse().mouseLabel.getX(),myGame.getMyMouse().getCurrentX()*100);
        assertEquals(myGame.getMyMouse().mouseLabel.getY(),(myGame.getMyMouse().getCurrentY()+1)*100);
    }

    @Test
    void CatCollisionLeft(){
        myGame.testing = true;
        myGame.getMyMouse().setCurrentX(5);
        myGame.getMyMouse().setCurrentY(1);
        myGame.validate();
        myGame.moveMouse("left");
        assertEquals(myGame.getMyMouse().getCurrentX(),4);
        assertEquals(myGame.getMyMouse().getCurrentY(),1);
        assertFalse(myGame.getInGame());
        assertEquals(myGame.getCat1().getCurrentX(),myGame.getMyMouse().getCurrentX());
        assertEquals(myGame.getCat1().getCurrentY(),myGame.getMyMouse().getCurrentY());
        assertEquals(myGame.getMyMouse().mouseLabel.getX(),myGame.getMyMouse().getCurrentX()*100);
        assertEquals(myGame.getMyMouse().mouseLabel.getY(),(myGame.getMyMouse().getCurrentY()+1)*100);
    }

    @Test
    void CatCollisionRight(){
        myGame.testing = true;
        myGame.getMyMouse().setCurrentX(7);
        myGame.getMyMouse().setCurrentY(4);
        myGame.validate();
        myGame.moveMouse("right");
        assertEquals(myGame.getMyMouse().getCurrentX(),8);
        assertEquals(myGame.getMyMouse().getCurrentY(),4);
        assertFalse(myGame.getInGame());
        assertEquals(myGame.getCat2().getCurrentX(),myGame.getMyMouse().getCurrentX());
        assertEquals(myGame.getCat2().getCurrentY(),myGame.getMyMouse().getCurrentY());
        assertEquals(myGame.getMyMouse().mouseLabel.getX(),myGame.getMyMouse().getCurrentX()*100);
        assertEquals(myGame.getMyMouse().mouseLabel.getY(),(myGame.getMyMouse().getCurrentY()+1)*100);
    }

    @Test
    void CatCollisionDown(){
        myGame.testing = true;
        myGame.getMyMouse().setCurrentX(8);
        myGame.getMyMouse().setCurrentY(3);
        myGame.validate();
        myGame.moveMouse("down");
        assertEquals(myGame.getMyMouse().getCurrentX(),8);
        assertEquals(myGame.getMyMouse().getCurrentY(),4);
        assertFalse(myGame.getInGame());
        assertEquals(myGame.getCat2().getCurrentX(),myGame.getMyMouse().getCurrentX());
        assertEquals(myGame.getCat2().getCurrentY(),myGame.getMyMouse().getCurrentY());
        assertEquals(myGame.getMyMouse().mouseLabel.getX(),myGame.getMyMouse().getCurrentX()*100);
        assertEquals(myGame.getMyMouse().mouseLabel.getY(),(myGame.getMyMouse().getCurrentY()+1)*100);
    }

    @Test
    void CatCollisionUp(){
        myGame.testing = true;
        myGame.moveMouse("left");
        myGame.moveMouse("left");
        myGame.moveMouse("left");
        myGame.moveMouse("up");
        myGame.moveMouse("up");
        assertEquals(myGame.getMyMouse().getCurrentX(),5);
        assertEquals(myGame.getMyMouse().getCurrentY(),6);
        assertFalse(myGame.getInGame());
        assertEquals(myGame.getCat2().getCurrentX(),myGame.getMyMouse().getCurrentX());
        assertEquals(myGame.getCat2().getCurrentY(),myGame.getMyMouse().getCurrentY());
        assertEquals(myGame.getMyMouse().mouseLabel.getX(),myGame.getMyMouse().getCurrentX()*100);
        assertEquals(myGame.getMyMouse().mouseLabel.getY(),(myGame.getMyMouse().getCurrentY()+1)*100);
    }

}