import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestCatMovement {

    private Game myGame;

    @BeforeEach
    void setup()
    {
        myGame = new Game(true);
    }

    //When Cats have same X with Mouse and Mouse move left and do not hit barriers
    @Test
    void MouseMoveLeftIsEmpty_AlignedVert() {
        myGame.getCat1().setCurrentX(6);
        myGame.getCat1().setCurrentY(1);
        myGame.validate();
        assertEquals(myGame.getCat1().catLabel.getX(),600);
        assertEquals(myGame.getCat1().catLabel.getY(),200);

        myGame.getCat2().setCurrentX(6);
        myGame.getCat2().setCurrentY(4);
        myGame.validate();
        assertEquals(myGame.getCat2().catLabel.getX(),600);
        assertEquals(myGame.getCat2().catLabel.getY(),500);

        myGame.getMyMouse().setCurrentX(6);
        myGame.getMyMouse().setCurrentY(8);
        myGame.validate();

        assertEquals(myGame.getMyMouse().mouseLabel.getX(),600);
        assertEquals(myGame.getMyMouse().mouseLabel.getY(),900);
        myGame.moveMouse("left");
        assertEquals(myGame.getMyMouse().mouseLabel.getX(),500);
        assertEquals(myGame.getMyMouse().mouseLabel.getY(),900);

        assertEquals(myGame.getCat1().getCurrentX(), 5);
        assertEquals(myGame.getCat1().getCurrentY(), 1);
        assertEquals(myGame.getCat2().getCurrentX(), 5);
        assertEquals(myGame.getCat2().getCurrentY(), 4);
    }
    //One cat hits barrier while the other does not
    @Test
    void OneCatHitBarrier(){
        myGame.getCat1().setCurrentX(3);
        myGame.getCat1().setCurrentY(3);
        myGame.validate();
        //Integration test here

        myGame.getCat2().setCurrentX(5);
        myGame.getCat2().setCurrentY(8);
        myGame.validate();
        //Integration test here

        myGame.getMyMouse().setCurrentX(3);
        myGame.getMyMouse().setCurrentY(8);
        myGame.validate();
        //Integration test here

        myGame.moveMouse("left");

        assertEquals(2, myGame.getMyMouse().getCurrentX());
        assertEquals(8, myGame.getMyMouse().getCurrentY());
        //Integration test here

        assertEquals(3, myGame.getCat1().getCurrentX());
        assertEquals(4, myGame.getCat1().getCurrentY());
        //Integration test here

        assertEquals(4, myGame.getCat2().getCurrentX());
        assertEquals(8, myGame.getCat2().getCurrentY());
        //Integration test here


    }

    //When Cats have same X with Mouse and Mouse move right
    @Test
    void MouseMoveRight_Aligned() {
        //Cat one
        myGame.getCat1().setCurrentX(6);
        myGame.getCat1().setCurrentY(1);
        myGame.validate();
        assertEquals(myGame.getCat1().catLabel.getX(),600);
        assertEquals(myGame.getCat1().catLabel.getY(),200);

        //Cat two
        myGame.getCat2().setCurrentX(6);
        myGame.getCat2().setCurrentY(4);
        myGame.validate();
        assertEquals(myGame.getCat2().catLabel.getX(),600);
        assertEquals(myGame.getCat2().catLabel.getY(),500);

        //Mouse
        myGame.getMyMouse().setCurrentX(6);
        myGame.getMyMouse().setCurrentY(8);
        myGame.validate();
        assertEquals(myGame.getMyMouse().mouseLabel.getX(),600);
        assertEquals(myGame.getMyMouse().mouseLabel.getY(),900);

        myGame.moveMouse("right");
        assertEquals(myGame.getMyMouse().mouseLabel.getX(),700);
        assertEquals(myGame.getMyMouse().mouseLabel.getY(),900);

        assertEquals(myGame.getCat1().getCurrentX(), 7);
        assertEquals(myGame.getCat1().getCurrentY(), 1);
        assertEquals(myGame.getCat2().getCurrentX(), 7);
        assertEquals(myGame.getCat2().getCurrentY(), 4);

    }

     //When cats are right of mouse_Same Y Different X
    @Test
    void CatsRightOfMouse(){
        myGame.getCat1().setCurrentX(5);
        myGame.getCat1().setCurrentY(8);
        myGame.validate();
        assertEquals(myGame.getCat1().catLabel.getX(),500);
        assertEquals(myGame.getCat1().catLabel.getY(),900);

        myGame.validate();
        myGame.getCat2().setCurrentX(6);
        myGame.getCat2().setCurrentY(8);
        assertEquals(myGame.getCat2().catLabel.getX(),600);
        assertEquals(myGame.getCat2().catLabel.getY(),900);

        myGame.getMyMouse().setCurrentX(3);
        myGame.getMyMouse().setCurrentY(8);
        myGame.validate();
        assertEquals(myGame.getMyMouse().mouseLabel.getX(),300);
        assertEquals(myGame.getMyMouse().mouseLabel.getY(),900);
        myGame.moveMouse("left");
        assertEquals(myGame.getMyMouse().mouseLabel.getX(),200);
        assertEquals(myGame.getMyMouse().mouseLabel.getY(),900);

        assertEquals(myGame.getCat1().getCurrentX(), 4);
        assertEquals(myGame.getCat1().getCurrentY(), 8);
        assertEquals(myGame.getCat2().getCurrentX(), 5);
        assertEquals(myGame.getCat2().getCurrentY(), 8);

    }

    //When Cats are left of Mouse and mouse move right. Same Y different X
    @Test
    void CatsLeftOfMouse(){
        myGame.getCat1().setCurrentX(2);
        myGame.getCat1().setCurrentY(1);
        myGame.validate();
        assertEquals(myGame.getCat1().catLabel.getX(),200);
        assertEquals(myGame.getCat1().catLabel.getY(),200);

        myGame.getCat2().setCurrentX(3);
        myGame.getCat2().setCurrentY(1);
        myGame.validate();
        assertEquals(myGame.getCat2().catLabel.getX(),300);
        assertEquals(myGame.getCat2().catLabel.getY(),200);

        myGame.getMyMouse().setCurrentX(3);
        myGame.getMyMouse().setCurrentY(8);
        myGame.validate();
        assertEquals(myGame.getMyMouse().mouseLabel.getX(),300);
        assertEquals(myGame.getMyMouse().mouseLabel.getY(),900);
        myGame.moveMouse("right");
        assertEquals(myGame.getMyMouse().mouseLabel.getX(),400);
        assertEquals(myGame.getMyMouse().mouseLabel.getY(),900);

        assertEquals(myGame.getCat1().getCurrentX(), 3);
        assertEquals(myGame.getCat1().getCurrentY(), 1);
        assertEquals(myGame.getCat2().getCurrentX(), 4);
        assertEquals(myGame.getCat2().getCurrentY(), 1);

    }

    //When Cat1 is left of Mouse, Cat2 is right of Mouse and mouse move Left. Different X and Y
    @Test
    void CatsLeftOfMouse_Left(){
        myGame.getCat1().setCurrentX(4);
        myGame.getCat1().setCurrentY(1);
        myGame.validate();
        assertEquals(myGame.getCat1().catLabel.getX(),400);
        assertEquals(myGame.getCat1().catLabel.getY(),200);

        myGame.getCat2().setCurrentX(8);
        myGame.getCat2().setCurrentY(4);
        myGame.validate();
        assertEquals(myGame.getCat2().catLabel.getX(),800);
        assertEquals(myGame.getCat2().catLabel.getY(),500);

        myGame.getMyMouse().setCurrentX(7);
        myGame.getMyMouse().setCurrentY(8);
        myGame.validate();
        assertEquals(myGame.getMyMouse().mouseLabel.getX(),700);
        assertEquals(myGame.getMyMouse().mouseLabel.getY(),900);
        myGame.moveMouse("left");
        assertEquals(myGame.getMyMouse().mouseLabel.getX(),600);
        assertEquals(myGame.getMyMouse().mouseLabel.getY(),900);
        assertEquals(myGame.getCat1().getCurrentX(), 5);
        assertEquals(myGame.getCat1().getCurrentY(), 1);
        assertEquals(myGame.getCat2().getCurrentX(), 7);
        assertEquals(myGame.getCat2().getCurrentY(), 4);

    }

    //When Cat1 is left of Mouse, Cat2 is right of Mouse and mouse move Left. Different X and Y
    @Test
    void CatsLeftOfMouse_Right(){
        myGame.getCat1().setCurrentX(4);
        myGame.getCat1().setCurrentY(1);
        myGame.validate();
        assertEquals(myGame.getCat1().catLabel.getX(),400);
        assertEquals(myGame.getCat1().catLabel.getY(),200);

        myGame.getCat2().setCurrentX(8);
        myGame.getCat2().setCurrentY(4);
        myGame.validate();
        assertEquals(myGame.getCat2().catLabel.getX(),800);
        assertEquals(myGame.getCat2().catLabel.getY(),500);

        myGame.getMyMouse().setCurrentX(7);
        myGame.getMyMouse().setCurrentY(8);
        myGame.validate();
        assertEquals(myGame.getMyMouse().mouseLabel.getX(),700);
        assertEquals(myGame.getMyMouse().mouseLabel.getY(),900);
        myGame.moveMouse("right");
        assertEquals(myGame.getMyMouse().mouseLabel.getX(),800);
        assertEquals(myGame.getMyMouse().mouseLabel.getY(),900);
        assertEquals(myGame.getCat1().getCurrentX(), 5);
        assertEquals(myGame.getCat1().getCurrentY(), 1);
        assertEquals(myGame.getCat2().getCurrentX(), 7);
        assertEquals(myGame.getCat2().getCurrentY(), 4);

    }
    //When mouse move left. Both cats and mouse are at the barrier. Same X different Y
        @Test
    void MoveLeftIsBarrier() {
            myGame.getCat1().setCurrentX(1);
            myGame.getCat1().setCurrentY(2);
            myGame.validate();
            assertEquals(myGame.getCat1().catLabel.getX(),100);
            assertEquals(myGame.getCat1().catLabel.getY(),300);

            myGame.getCat2().setCurrentX(1);
            myGame.getCat2().setCurrentY(5);
            assertEquals(myGame.getCat2().catLabel.getX(),100);
            assertEquals(myGame.getCat2().catLabel.getY(),600);

            myGame.getMyMouse().setCurrentX(1);
            myGame.getMyMouse().setCurrentY(8);
            myGame.validate();
            assertEquals(myGame.getMyMouse().mouseLabel.getX(),100);
            assertEquals(myGame.getMyMouse().mouseLabel.getY(),900);
            myGame.moveMouse("left");
            assertEquals(myGame.getMyMouse().mouseLabel.getX(),100);
            assertEquals(myGame.getMyMouse().mouseLabel.getY(),900);

            assertEquals(myGame.getCat1().getCurrentX(), 1);
            assertEquals(myGame.getCat1().getCurrentY(), 3);
            assertEquals(myGame.getCat2().getCurrentX(), 1);
            assertEquals(myGame.getCat2().getCurrentY(), 6);
            assertEquals(myGame.getCat1().leftblocked(myGame.getMyMap().getLevelMap()), true );
            assertEquals(myGame.getCat2().leftblocked(myGame.getMyMap().getLevelMap()), true );
        }


    //When cats are cornered and mouse move right
    @Test
    void CatsLowerRightCornered(){
        myGame.getCat1().setCurrentX(7);
        myGame.getCat1().setCurrentY(4);
        myGame.validate();
        assertEquals(myGame.getCat1().catLabel.getX(),700);
        assertEquals(myGame.getCat1().catLabel.getY(),500);

        myGame.getCat2().setCurrentX(8);
        myGame.getCat2().setCurrentY(4);
        myGame.validate();
        assertEquals(myGame.getCat2().catLabel.getX(),800);
        assertEquals(myGame.getCat2().catLabel.getY(),500);

        myGame.getMyMouse().setCurrentX(8);
        myGame.getMyMouse().setCurrentY(8);
        myGame.validate();
        assertEquals(myGame.getMyMouse().mouseLabel.getX(),800);
        assertEquals(myGame.getMyMouse().mouseLabel.getY(),900);
        myGame.moveMouse("right");
        assertEquals(myGame.getMyMouse().mouseLabel.getX(),900);
        assertEquals(myGame.getMyMouse().mouseLabel.getY(),900);

        assertEquals(myGame.getCat1().getCurrentX(), 8);
        assertEquals(myGame.getCat1().getCurrentY(), 4);
        assertEquals(myGame.getCat2().getCurrentX(), 7);
        assertEquals(myGame.getCat2().getCurrentY(), 4);
        assertEquals(myGame.getCat1().bottomblocked(myGame.getMyMap().getLevelMap()), true );
        assertEquals(myGame.getCat1().rightblocked(myGame.getMyMap().getLevelMap()), true );
        assertEquals(myGame.getCat2().bottomblocked(myGame.getMyMap().getLevelMap()), true );
    }


    //When mouse move right and Cat hits barrier. Same X different Y
    @Test
    void MoveRightIsBarrier() {
        myGame.getCat1().setCurrentX(1);
        myGame.getCat1().setCurrentY(2);
        myGame.validate();
        assertEquals(myGame.getCat1().catLabel.getX(),100);
        assertEquals(myGame.getCat1().catLabel.getY(),300);

        myGame.getCat2().setCurrentX(1);
        myGame.getCat2().setCurrentY(5);
        myGame.validate();
        assertEquals(myGame.getCat2().catLabel.getX(),100);
        assertEquals(myGame.getCat2().catLabel.getY(),600);

        myGame.getMyMouse().setCurrentX(1);
        myGame.getMyMouse().setCurrentY(8);
        myGame.validate();
        assertEquals(myGame.getMyMouse().mouseLabel.getX(),100);
        assertEquals(myGame.getMyMouse().mouseLabel.getY(),900);
        myGame.moveMouse("right");
        assertEquals(myGame.getMyMouse().mouseLabel.getX(),200);
        assertEquals(myGame.getMyMouse().mouseLabel.getY(),900);

        assertEquals(myGame.getCat1().getCurrentX(), 1);
        assertEquals(myGame.getCat1().getCurrentY(), 3);
        assertEquals(myGame.getCat2().getCurrentX(), 1);
        assertEquals(myGame.getCat2().getCurrentY(), 6);
    }

    //When Cat Eats Mouse from the left
    @Test
    void CollideMouseLeft() {
        myGame.testing = true;
        myGame.getCat1().setCurrentX(2);
        myGame.getCat1().setCurrentY(8);
        myGame.validate();
        assertEquals(myGame.getCat1().catLabel.getX(),200);
        assertEquals(myGame.getCat1().catLabel.getY(),900);

        myGame.getCat2().setCurrentX(1);
        myGame.getCat2().setCurrentY(6);
        myGame.validate();
        assertEquals(myGame.getCat2().catLabel.getX(),100);
        assertEquals(myGame.getCat2().catLabel.getY(),700);

        myGame.getMyMouse().setCurrentX(1);
        myGame.getMyMouse().setCurrentY(8);
        myGame.validate();
        assertEquals(myGame.getMyMouse().mouseLabel.getX(),100);
        assertEquals(myGame.getMyMouse().mouseLabel.getY(),900);
        myGame.moveMouse("left");
        assertEquals(myGame.getMyMouse().mouseLabel.getX(),100);
        assertEquals(myGame.getMyMouse().mouseLabel.getY(),900);

        assertEquals(myGame.getMyMouse().getCurrentX(), 1);
        assertEquals(myGame.getMyMouse().getCurrentY(), 8);
        assertEquals(myGame.getCat1().getCurrentX(), 1);
        assertEquals(myGame.getCat1().getCurrentY(), 8);
        assertEquals(myGame.getCat2().getCurrentX(), 1);
        assertEquals(myGame.getCat2().getCurrentY(), 7);
        assertFalse(myGame.getWinGame());
    }

    //When Cat Eats Mouse from the left
    @Test
    void CollideMouseRight() {
        myGame.testing = true;
        myGame.getCat1().setCurrentX(7);
        myGame.getCat1().setCurrentY(1);
        myGame.validate();
        assertEquals(myGame.getCat1().catLabel.getX(),700);
        assertEquals(myGame.getCat1().catLabel.getY(),200);

        myGame.getCat2().setCurrentX(6);
        myGame.getCat2().setCurrentY(1);
        myGame.validate();
        assertEquals(myGame.getCat2().catLabel.getX(),600);
        assertEquals(myGame.getCat2().catLabel.getY(),200);

        myGame.getMyMouse().setCurrentX(8);
        myGame.getMyMouse().setCurrentY(1);
        myGame.validate();
        assertEquals(myGame.getMyMouse().mouseLabel.getX(),800);
        assertEquals(myGame.getMyMouse().mouseLabel.getY(),200);
        myGame.moveMouse("right");
        assertEquals(myGame.getMyMouse().mouseLabel.getX(),800);
        assertEquals(myGame.getMyMouse().mouseLabel.getY(),200);

        assertEquals(myGame.getMyMouse().getCurrentX(), 8);
        assertEquals(myGame.getMyMouse().getCurrentY(), 1);
        assertEquals(myGame.getCat1().getCurrentX(), 8);
        assertEquals(myGame.getCat1().getCurrentY(), 1);
        assertEquals(myGame.getCat2().getCurrentX(), 7);
        assertEquals(myGame.getCat2().getCurrentY(), 1);
        assertFalse(myGame.getWinGame());
    }

    //When Mouse and Cats have same X. Check if there's Barrier
    @Test
    void MouseUp(){
        myGame.getCat1().setCurrentX(5);
        myGame.getCat1().setCurrentY(1);
        myGame.validate();
        assertEquals(myGame.getCat1().catLabel.getX(),500);
        assertEquals(myGame.getCat1().catLabel.getY(),200);

        myGame.getCat2().setCurrentX(5);
        myGame.getCat2().setCurrentY(4);
        myGame.validate();
        assertEquals(myGame.getCat2().catLabel.getX(),500);
        assertEquals(myGame.getCat2().catLabel.getY(),500);

        myGame.getMyMouse().setCurrentX(5);
        myGame.getMyMouse().setCurrentY(8);
        myGame.validate();
        assertEquals(myGame.getMyMouse().mouseLabel.getX(),500);
        assertEquals(myGame.getMyMouse().mouseLabel.getY(),900);
        myGame.moveMouse("up");
        assertEquals(myGame.getMyMouse().mouseLabel.getX(),500);
        assertEquals(myGame.getMyMouse().mouseLabel.getY(),800);

        assertEquals(myGame.getCat1().getCurrentX(), 5);
        assertEquals(myGame.getCat1().getCurrentY(), 2);
        assertEquals(myGame.getCat2().getCurrentX(), 5);
        assertEquals(myGame.getCat2().getCurrentY(), 5);
    }

    //When Mouse and Cats have same X. Check if there's Barrier
    @Test
    void MouseDown(){
        myGame.getCat1().setCurrentX(5);
        myGame.getCat1().setCurrentY(2);
        myGame.validate();
        assertEquals(myGame.getCat1().catLabel.getX(),500);
        assertEquals(myGame.getCat1().catLabel.getY(),300);

        myGame.getCat2().setCurrentX(5);
        myGame.getCat2().setCurrentY(5);
        myGame.validate();
        assertEquals(myGame.getCat2().catLabel.getX(),500);
        assertEquals(myGame.getCat2().catLabel.getY(),600);

        myGame.getMyMouse().setCurrentX(5);
        myGame.getMyMouse().setCurrentY(7);
        myGame.validate();
        assertEquals(myGame.getMyMouse().mouseLabel.getX(),500);
        assertEquals(myGame.getMyMouse().mouseLabel.getY(),800);
        myGame.moveMouse("down");
        assertEquals(myGame.getMyMouse().mouseLabel.getX(),500);
        assertEquals(myGame.getMyMouse().mouseLabel.getY(),900);

        assertEquals(myGame.getCat1().getCurrentX(), 5);
        assertEquals(myGame.getCat1().getCurrentY(), 3);
        assertEquals(myGame.getCat2().getCurrentX(), 5);
        assertEquals(myGame.getCat2().getCurrentY(), 6);
    }
    //WHen mouse move up and a cat is on the left. Different X and Y
    @Test
    void MouseUpTopLeft(){
        myGame.getCat1().setCurrentX(4);
        myGame.getCat1().setCurrentY(1);
        myGame.validate();
        assertEquals(myGame.getCat1().catLabel.getX(),400);
        assertEquals(myGame.getCat1().catLabel.getY(),200);

        myGame.getMyMouse().setCurrentX(8);
        myGame.getMyMouse().setCurrentY(8);
        myGame.validate();
        assertEquals(myGame.getMyMouse().mouseLabel.getX(),800);
        assertEquals(myGame.getMyMouse().mouseLabel.getY(),900);
        myGame.moveMouse("up");
        assertEquals(myGame.getMyMouse().mouseLabel.getX(),800);
        assertEquals(myGame.getMyMouse().mouseLabel.getY(),800);
        assertEquals(myGame.getCat1().getCurrentX(), 5);
        assertEquals(myGame.getCat1().getCurrentY(), 1);
    }

    //When mouse is moving up and collide with Cat
    @Test
    void EatMouseUp(){
        myGame.testing = true;
        myGame.getCat1().setCurrentX(5);
        myGame.getCat1().setCurrentY(5);
        myGame.validate();
        assertEquals(myGame.getCat1().catLabel.getX(),500);
        assertEquals(myGame.getCat1().catLabel.getY(),600);

        myGame.getCat2().setCurrentX(5);
        myGame.getCat2().setCurrentY(6);
        myGame.validate();
        assertEquals(myGame.getCat2().catLabel.getX(),500);
        assertEquals(myGame.getCat2().catLabel.getY(),700);

        myGame.getMyMouse().setCurrentX(5);
        myGame.getMyMouse().setCurrentY(7);
        myGame.validate();
        assertEquals(myGame.getMyMouse().mouseLabel.getX(),500);
        assertEquals(myGame.getMyMouse().mouseLabel.getY(),800);
        myGame.moveMouse("up");
        assertEquals(myGame.getMyMouse().mouseLabel.getX(),500);
        assertEquals(myGame.getMyMouse().mouseLabel.getY(),700);

        assertEquals(myGame.getCat1().getCurrentX(), 5);
        assertEquals(myGame.getCat1().getCurrentY(), 5);
        assertEquals(myGame.getCat2().getCurrentX(), 5);
        assertEquals(myGame.getCat2().getCurrentY(), 6);
        assertFalse(myGame.getWinGame());
    }

    //When mouse is moving up and collide with Cat
    @Test
    void EatMouseDown(){
        myGame.testing = true;
        myGame.getCat1().setCurrentX(1);
        myGame.getCat1().setCurrentY(4);
        myGame.validate();
        assertEquals(myGame.getCat1().catLabel.getX(),100);
        assertEquals(myGame.getCat1().catLabel.getY(),500);

        myGame.getCat2().setCurrentX(1);
        myGame.getCat2().setCurrentY(3);
        myGame.validate();
        assertEquals(myGame.getCat2().catLabel.getX(),100);
        assertEquals(myGame.getCat2().catLabel.getY(),400);

        myGame.getMyMouse().setCurrentX(1);
        myGame.getMyMouse().setCurrentY(2);
        myGame.validate();
        assertEquals(myGame.getMyMouse().mouseLabel.getX(),100);
        assertEquals(myGame.getMyMouse().mouseLabel.getY(),300);
        myGame.moveMouse("down");
        assertEquals(myGame.getMyMouse().mouseLabel.getX(),100);
        assertEquals(myGame.getMyMouse().mouseLabel.getY(),400);

        assertEquals(myGame.getCat1().getCurrentX(), 1);
        assertEquals(myGame.getCat1().getCurrentY(), 4);
        assertEquals(myGame.getCat2().getCurrentX(), 1);
        assertEquals(myGame.getCat2().getCurrentY(), 3);
        assertFalse(myGame.getWinGame());
    }

    @Test
    void CatOverCheese(){
        myGame.getCat1().setCurrentX(2);
        myGame.getCat1().setCurrentY(4);
        myGame.validate();
        assertEquals(myGame.getCat1().catLabel.getX(),200);
        assertEquals(myGame.getCat1().catLabel.getY(),500);

        myGame.getMyMouse().setCurrentX(2);
        myGame.getMyMouse().setCurrentY(8);
        myGame.validate();
        assertEquals(myGame.getMyMouse().mouseLabel.getX(),200);
        assertEquals(myGame.getMyMouse().mouseLabel.getY(),900);
        myGame.moveMouse("right");

        assertEquals(myGame.getMyMouse().mouseLabel.getX(),300);
        assertEquals(myGame.getMyMouse().mouseLabel.getY(),900);
        assertTrue(myGame.getCheese2().cheeseLabel.isVisible());
    }

    @Test
    void CatOverTrap2(){
        myGame.getCat1().setCurrentX(5);
        myGame.getCat1().setCurrentY(4);
        myGame.validate();
        assertEquals(myGame.getCat1().catLabel.getX(),500);
        assertEquals(myGame.getCat1().catLabel.getY(),500);

        myGame.getMyMouse().setCurrentX(5);
        myGame.getMyMouse().setCurrentY(8);
        myGame.validate();
        assertEquals(myGame.getMyMouse().mouseLabel.getX(),500);
        assertEquals(myGame.getMyMouse().mouseLabel.getY(),900);
        myGame.moveMouse("left");
        assertEquals(myGame.getMyMouse().mouseLabel.getX(),400);
        assertEquals(myGame.getMyMouse().mouseLabel.getY(),900);
        assertTrue(myGame.getMyMap().getLevelMap()[4][4].getisMouseTrap());
        assertTrue(myGame.getTrap2().trapLabel.isVisible());
        myGame.dispose();
    }

    @Test
    void CatOverTrap1(){
        myGame.getCat2().setCurrentX(5);
        myGame.getCat2().setCurrentY(3);
        myGame.validate();
        assertEquals(myGame.getCat2().catLabel.getX(),500);
        assertEquals(myGame.getCat2().catLabel.getY(),400);

        myGame.getMyMouse().setCurrentX(5);
        myGame.getMyMouse().setCurrentY(8);
        myGame.validate();
        assertEquals(myGame.getMyMouse().mouseLabel.getX(),500);
        assertEquals(myGame.getMyMouse().mouseLabel.getY(),900);
        myGame.moveMouse("right");
        assertEquals(myGame.getMyMouse().mouseLabel.getX(),600);
        assertEquals(myGame.getMyMouse().mouseLabel.getY(),900);
        assertTrue(myGame.getMyMap().getLevelMap()[3][6].getisMouseTrap());
        assertTrue(myGame.getTrap1().trapLabel.isVisible());
    }
}
