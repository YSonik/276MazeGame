import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


public class TestGenMap {
    
    private LevelOne map;
    Tile[][] layout;
    Boolean check;


    @BeforeEach
    void setup(){
        map = new LevelOne();
        layout = map.getLevelMap();
        check = false;
    }

    @Test
    public void checkMapBoundary() {
        //map = new LevelOne();
        
        
        //check Left column
        for (int i = 2; i< 10; i++){
            if(!(layout[i][0].getisBarrier())){
                assertEquals(true, check);
                break;
            }
        }
        //check Top row
        for(int i = 0; i < 10; i++)
        {
            if(!(layout[0][i].getisBarrier())){
                assertEquals(true, check);
                break;
            }
        }
        //check Bottom Row
        for(int i = 0; i<10;i++)
        {
            if(!(layout[9][i].getisBarrier())){
                assertEquals(true, check);
                break;
            }
        }

        //check Right Column
        for (int i = 0; i< 8; i++){
            if(!(layout[i][9].getisBarrier())){
                assertEquals(true, check);
                break;
            }
        }
    }

    @Test
    public void checkExit(){
        if(!(layout[1][0].getisExit())){
            assertEquals(true, check);
        }
    }

    @Test
    public void checkEntrance(){
        if(!(layout[8][9].getisEntrance())){
            assertEquals(true, check);
        }
        
    }

    @Test
    public void checkStructure(){
        
        //check Structure1
        for(int i = 2; i <5;i++ )
        {   if(!(layout[2][i].getisBarrier())){
                assertEquals(true, check);
                break;
            }    
        }
        if((!layout[3][2].getisBarrier())){
            assertEquals(true, check);
        }

        

        //Check Structure2
        for (int i = 5; i<8; i++){
            if(!(layout[i][2].getisBarrier())){
                assertEquals(true, check);
                break;
            }
            if(!(layout[i][4].getisBarrier())){
                assertEquals(true, check);
                break;
            }
        }

        //Check Structure3
        for(int i = 5; i<8; i++){
            if(!(layout[i][6].getisBarrier())){
                assertEquals(true, check);
                break;
            }
        }
        if (!layout[5][7].getisBarrier()){
            assertEquals(true, check);
        }
        if (!layout[5][8].getisBarrier()){
            assertEquals(true, check);
        }
        if (!layout[7][7].getisBarrier()){
            assertEquals(true, check);
        }

        //Check Structure4
        if (!layout[2][7].getisBarrier()){
            assertEquals(true, check);
        }
        if (!layout[3][7].getisBarrier()){
            assertEquals(true, check);
        }
        if (!layout[2][6].getisBarrier()){
            assertEquals(true, check);
        }

    }

    @Test
    public void checkCheeselocs(){
    //Check Cheese's locations
        if (!layout[6][7].getisCheese()){
            assertEquals(true, check);
        }
        if (!layout[4][3].getisCheese()){
            assertEquals(true, check);
        }
    }

    @Test
    public void checkTraplocs(){
        if (!layout[3][6].getisMouseTrap()){
            assertEquals(true, check);
        }
        if (!layout[4][4].getisMouseTrap()){
            assertEquals(true, check);
        }
    }
    








}