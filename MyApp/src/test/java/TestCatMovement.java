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
//
//    @Test
//    void MouseMoveRightIsEmpty_AlignedVert() {
//        myGame.getCat1().setCurrentX(6);
//        myGame.getCat1().setCurrentY(1);
//        myGame.getCat2().setCurrentX(6);
//        myGame.getCat2().setCurrentY(4);
//        myGame.getMyMouse().setCurrentX(6);
//        myGame.getMyMouse().setCurrentY(8);
//        myGame.moveMouse("right");
//        myGame.validate();
//        assertEquals(myGame.getCat1().getCurrentX(), 7);
//        assertEquals(myGame.getCat1().getCurrentY(), 1);
//        assertEquals(myGame.getCat2().getCurrentX(), 7);
//        assertEquals(myGame.getCat2().getCurrentY(), 4);
//    }
//
//    //When mouse move right and Cat hits barrier. Same X different Y
//    @Test
//    void MoveRightIsBarrier() {
//        myGame.getCat1().setCurrentX(1);
//        myGame.getCat1().setCurrentY(2);
//        myGame.getCat2().setCurrentX(1);
//        myGame.getCat2().setCurrentY(5);
//        myGame.getMyMouse().setCurrentX(1);
//        myGame.getMyMouse().setCurrentY(8);
//        myGame.moveMouse("right");
//        myGame.validate();
//        assertEquals(myGame.getCat1().getCurrentX(), 1);
//        assertEquals(myGame.getCat1().getCurrentY(), 3);
//        assertEquals(myGame.getCat2().getCurrentX(), 1);
//        assertEquals(myGame.getCat2().getCurrentY(), 6);
//        assertEquals(myGame.getCat1().rightblocked(myGame.getMyMap().getLevelMap()), true );
//        assertEquals(myGame.getCat2().rightblocked(myGame.getMyMap().getLevelMap()), true );
//
//    }
//
//    //Ask about Catcollide
//    @Test
//    void CollideMouseLeft() {
//        //myGame.getCat1().setCurrentX(5);
//        //myGame.getCat1().setCurrentY(2)
//        myGame.getCat2().setCurrentX(2);
//        myGame.getCat2().setCurrentY(8);
//        myGame.getMyMouse().setCurrentX(1);
//        myGame.getMyMouse().setCurrentY(8);
//        myGame.moveMouse("left");
//        myGame.validate();
//        assertEquals(myGame.getMyMouse().getCurrentX(), 1);
//        assertEquals(myGame.getMyMouse().getCurrentY(), 8);
//        assertEquals(myGame.getMyMap().getLevelMap()[8][0].getisBarrier(), true);
//        assertEquals(myGame.getCat2().getCurrentX(), 1);
//        assertEquals(myGame.getCat2().getCurrentY(), 8);
//        //assertEquals(myGame.Catscollide(), true);
//       //assertFalse(myGame.getMyMouse().mouseLabel.isVisible());*/
//    }
//
//    //When Mouse and Cats have same X. Check if there's Barrier
//    @Test
//    void MouseDown(){
////        myGame.getCat1().setCurrentX(5);
////        myGame.getCat1().setCurrentY(1);
////        myGame.getCat2().setCurrentX(5);
////        myGame.getCat2().setCurrentY(4);
////        myGame.getMyMouse().setCurrentX(5);
////        myGame.getMyMouse().setCurrentY(7);
////        myGame.moveMouse("down");
////        assertEquals(myGame.getCat1().getCurrentX(), 5);
////        assertEquals(myGame.getCat1().getCurrentY(), 2);
////        assertEquals(myGame.getCat2().getCurrentX(), 5);
////        assertEquals(myGame.getCat2().getCurrentY(), 5);
////        assertEquals(myGame.getCat1().leftblocked(myGame.getMyMap().getLevelMap()),  false);
////        assertEquals(myGame.getCat2().leftblocked(myGame.getMyMap().getLevelMap()), false);
//    }
//
//    //When Mouse and Cats have same X. Check if there's Barrier
//    @Test
//    void MouseUp(){
//        myGame.getCat1().setCurrentX(5);
//        myGame.getCat1().setCurrentY(1);
//        myGame.getCat2().setCurrentX(5);
//        myGame.getCat2().setCurrentY(4);
//        myGame.getMyMouse().setCurrentX(5);
//        myGame.getMyMouse().setCurrentY(7);
//        int x = myGame.getMyMouse().getCurrentX();
//        int y = myGame.getMyMouse().getCurrentY();
//        int move = myGame.getCat1().chase(x,y, myGame.getMyMap().getLevelMap());
//        int move2 = myGame.getCat2().chase(x,y, myGame.getMyMap().getLevelMap());
//        assertEquals(move, 3);
//        assertEquals(move2, 3);
//        assertEquals(myGame.getCat1().getCurrentX(), 5);
//        assertEquals(myGame.getCat1().getCurrentY(), 2);
//        assertEquals(myGame.getCat2().getCurrentX(), 5);
//        assertEquals(myGame.getCat2().getCurrentY(), 5);
//        assertEquals(myGame.getCat1().bottomblocked(myGame.getMyMap().getLevelMap()),  false);
//        assertEquals(myGame.getCat2().bottomblocked(myGame.getMyMap().getLevelMap()), false);
//    }

}