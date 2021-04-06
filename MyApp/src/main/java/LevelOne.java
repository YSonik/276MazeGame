public class LevelOne {

    private Tile[][] levelMap;

    LevelOne()
    {
        createTileMap();
    }
    /**
     * This is the void method called by the LevelOne constructor which creates a 2D array of tiles and encodes all indices to the objects that should be placed at that location in the maze.
     * @author Yogesh Sonik
     * */
    private void createTileMap()
    {
        //fill map with empty tiles:
        levelMap = new Tile[10][10];
        for(int i = 0; i < 10; i++)
        {
            for(int j = 0; j< 10; j++)
            {
                levelMap[i][j] = new Tile(false,false,false,false, true,false,false,false);
            }
        }

        //Left column
        for(int i = 0; i< 10; i++)
        {
            levelMap[i][0].setEmpty(false);
            levelMap[i][0].setBarrier(true);

        }

        //Top Row
        for(int i = 0; i < 10; i++)
        {
            levelMap[0][i].setEmpty(false);
            levelMap[0][i].setBarrier(true);
        }

        //Bottom Row
        for(int i = 0; i<10;i++)
        {
            levelMap[9][i].setEmpty(false);
            levelMap[9][i].setBarrier(true);
        }

        //Right Column
        for(int i = 0; i < 10;i++)
        {
            levelMap[i][9].setEmpty(false);
            levelMap[i][9].setBarrier(true);
        }

        //Set Exit Tile
        levelMap[1][0]  = new Tile(false,false,true,false,false,false,false,false);
        //Set Entrance Tile
        levelMap[8][9] = new Tile(false,true,false,false,false,false,false,false);

        //Create the internal maze layout
        //Structure1:
        for(int i = 2; i <5;i++ )
        {
            levelMap[2][i].setEmpty(false);
            levelMap[2][i].setBarrier(true);
        }
        levelMap[3][2] = new Tile(true,false,false,false,false,false,false, false);

        //Structure2
        levelMap[5][2] = new Tile(true,false,false,false,false,false,false,false);
        levelMap[6][2] = new Tile(true,false,false,false,false,false,false,false);
        levelMap[6][4] = new Tile(true,false,false,false,false,false,false,false);
        levelMap[7][2] = new Tile(true,false,false,false,false,false,false,false);
        levelMap[7][4] = new Tile(true,false,false,false,false,false,false,false);
        levelMap[5][4] = new Tile(true,false,false,false,false,false,false,false);

        //Structure3
        levelMap[5][8] = new Tile(true,false,false,false,false,false,false,false);
        levelMap[5][7] = new Tile(true,false,false,false,false,false,false,false);
        levelMap[5][6] = new Tile(true,false,false,false,false,false,false,false);
        levelMap[6][6] = new Tile(true,false,false,false,false,false,false,false);
        levelMap[7][6] = new Tile(true,false,false,false,false,false,false,false);
        levelMap[7][7] = new Tile(true,false,false,false,false,false,false,false);

        //Structure4
        levelMap[2][6] = new Tile(true,false,false,false,false,false,false,false);
        levelMap[2][7] = new Tile(true,false,false,false,false,false,false,false);
        levelMap[3][7] = new Tile(true,false,false,false,false,false,false,false);


        //Set locations for cheese
        levelMap[6][7] = new Tile(false,false,false,false,false,true, false,false);
        levelMap[4][3] = new Tile(false,false,false,false,false,true, false,false);

        //Set locations for traps
        levelMap[3][6] = new Tile(false,false,false,false,false,false, false,true);
        levelMap[4][4] = new Tile(false,false,false,false,false,false, false,true);


    }

    public Tile[][] getLevelMap() {
        return levelMap;
    }




}
