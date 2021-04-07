import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


public class TestGenMap {
    
    private LevelOne map;
    @BeforeEach
    void setup(){
        map = new LevelOne();
    }

    @Test
    public void checkMapBoundary() {
        //map = new LevelOne();
        Tile[][] getb = map.getLevelMap();
        Boolean check = true;
        
        //check Left column
        for (int i = 2; i< 10; i++){
            if(!(getb[i][0].getisBarrier())){
                check  = false;
            }
        }
        //check Top row
        for(int i = 0; i < 10; i++)
        {
            if(!(getb[0][i].getisBarrier())){
                check  = false;
            }
        }

        //check Bottom Row
        for(int i = 0; i<10;i++)
        {
            if(!(getb[9][i].getisBarrier())){
                check  = false;
            }
        }

        //check Right Column
        for (int i = 0; i< 7; i++){
            if(!(getb[i][9].getisBarrier())){
                check  = false;
            }
        }


        assertEquals(true, check);
    }

}