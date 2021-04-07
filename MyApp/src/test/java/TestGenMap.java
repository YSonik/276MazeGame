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
        check = true;
    }

    @Test
    public void checkMapBoundary() {
        //map = new LevelOne();
        
        
        //check Left column
        for (int i = 2; i< 10; i++){
            if(!(layout[i][0].getisBarrier())){
                check  = false;
                break;
            }
        }
        //check Top row
        for(int i = 0; i < 10; i++)
        {
            if(!(layout[0][i].getisBarrier())){
                check  = false;
                break;
            }
        }
        //check Bottom Row
        for(int i = 0; i<10;i++)
        {
            if(!(layout[9][i].getisBarrier())){
                check  = false;
                break;
            }
        }

        //check Right Column
        for (int i = 0; i< 8; i++){
            if(!(layout[i][9].getisBarrier())){
                check  = false;
                break;
            }
        }


        assertEquals(true, check);
    }

    @Test
    public void checkExit(){
        if(!(layout[1][0].getisExit())){
            check = false;
        }
        assertEquals(true, check);
    }

    @Test
    public void checkEntrance(){
        if(!(layout[8][9].getisEntrance())){
            check = false;
        }
        assertEquals(true, check);
    }

    @Test
    public void checkStructure(){
        
        //check Structure1
        for(int i = 2; i <5;i++ )
        {   if(!(layout[2][i].getisBarrier())){
                check = false;
                break;
            }    
        }
        if((!layout[3][2].getisBarrier())){
            check = false;
        }

        

        //Check Structure2
        for (int i = 5; i<8; i++){
            if(!(layout[i][2].getisBarrier() || layout[i][4].getisBarrier())){
                check = false;
                break;
            }
        }


        assertEquals(true, check);
    }




}