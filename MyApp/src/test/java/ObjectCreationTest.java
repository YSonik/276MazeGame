import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ObjectCreationTest {
    @Test
    void MouseCreation() {
        Mouse newMouse = new Mouse(3,4);
        assertEquals(newMouse.getCurrentX(),3);
        assertEquals(newMouse.getCurrentY(),4);
        assertEquals(newMouse.getCheeseEaten(),0);
    }

    @Test
    void CatCreation() {
        Cat newCat = new Cat(3,4,"Images/cat1.png");
        assertEquals(newCat.getCurrentX(),3);
        assertEquals(newCat.getCurrentY(),4);
    }

    @Test
    void CheeseCreation() {
        Cheese newCheese = new Cheese(3,4,5);
        assertEquals(newCheese.getX(),3);
        assertEquals(newCheese.getY(),4);
        assertEquals(newCheese.getValue(),5);
    }

    @Test
    void OrganicCheeseCreation() {
        OrganicCheese newOrganicCheese = new OrganicCheese(3,4,5);
        assertEquals(newOrganicCheese.getX(),3);
        assertEquals(newOrganicCheese.getY(),4);
        assertEquals(newOrganicCheese.getValue(),5);
    }

    @Test
    void MouseTrapCreation() {
        MouseTrap newMouseTrap = new MouseTrap(3,4);
        assertEquals(newMouseTrap.getCurrentX(),3);
        assertEquals(newMouseTrap.getCurrentY(),4);
    }

    @Test
    void GameCreation() {
        Game newGame = new Game();
        assertEquals(newGame.getCat1().getCurrentX(),4);
        assertEquals(newGame.getCat1().getCurrentY(),1);
        assertEquals(newGame.getCat2().getCurrentX(),8);
        assertEquals(newGame.getCat2().getCurrentY(),4);
        assertEquals(newGame.getMyMouse().getCurrentY(),8);
        assertEquals(newGame.getMyMouse().getCurrentY(),8);
        assertEquals(newGame.getCheese1().getX(),6);
        assertEquals(newGame.getCheese1().getY(),7);
        assertEquals(newGame.getCheese1().getValue(),5);
        assertEquals(newGame.getCheese2().getX(),4);
        assertEquals(newGame.getCheese2().getY(),3);
        assertEquals(newGame.getCheese2().getValue(),5);
        assertEquals(newGame.getOrgCheese1().getX(),7);
        assertEquals(newGame.getOrgCheese1().getY(),3);
        assertEquals(newGame.getOrgCheese1().getValue(),10);
        assertEquals(newGame.getOrgCheese2().getX(),1);
        assertEquals(newGame.getOrgCheese2().getY(),3);
        assertEquals(newGame.getOrgCheese2().getValue(),10);
        assertEquals(newGame.getTrap1().getCurrentX(),3);
        assertEquals(newGame.getTrap1().getCurrentY(),6);
        assertEquals(newGame.getTrap2().getCurrentX(),4);
        assertEquals(newGame.getTrap2().getCurrentY(),4);
    }
}
