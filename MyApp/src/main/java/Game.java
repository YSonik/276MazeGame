import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game extends JFrame implements KeyListener {

    private Tile[][] levelMap;//Game Template
    private JLabel[][] gameMap;//Mapping of levelMap to JFrame
    private Mouse myMouse;
    private Cat cat1;
    private Cat cat2;
    private JLabel mouseLabel;

    JLabel catLabel1;
    JLabel catLabel2;

    Game()
    {
        //Create a Mouse
        myMouse = new Mouse();//coords set to 8,8
        createMouseLabel();
        // Create multiple cats
        cat1 = new Cat();
        cat2 = new Cat();
        createCatLabel();
        createTileMap();
        createFrame();
        createGameMap();
    }

    public void createMouseLabel()
    {
        mouseLabel = new JLabel();
        mouseLabel.setBounds(800,900,100,100);
        mouseLabel.setBackground(Color.blue);
        mouseLabel.setOpaque(true);
        ImageIcon icon = new ImageIcon(myMouse.getMouseImage());
        mouseLabel.setIcon(icon);
        this.add(mouseLabel);
        this.validate();
    }

    public void createCatLabel()
    {
        catLabel1 = new JLabel();
        catLabel1.setBounds(400,200,100,100);
        catLabel1.setBackground(Color.yellow);
        catLabel1.setOpaque(true);
        this.add(catLabel1);
        this.validate();

        catLabel2 = new JLabel();
        catLabel2.setBounds(800,500,100,100);
        catLabel2.setBackground(Color.yellow);
        catLabel2.setOpaque(true);
        this.add(catLabel2);
        this.validate();
    }

    public void createFrame()
    {
        //Set up frame and add mouse label
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000,1200);//extra 100 pixels at the top for displaying statistic
        this.setLayout(null);
        this.addKeyListener(this);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.WHITE);
    }

    private void createGameMap()
    {
        int x = 0;
        int y = 100;
        JLabel tempLabel = new JLabel();

        for(int i = 0; i< 10; i++)
        {
            for(int j = 0; j <10; j++)
            {
                if(levelMap[i][j].getisBarrier() == true ) {
                    tempLabel = new JLabel();
                    tempLabel.setBounds(x,y,100,100);
                    tempLabel.setBackground(Color.red);
                    tempLabel.setOpaque(true);
                }
                else if(levelMap[i][j].getisReward() == true)
                {
                    tempLabel = new JLabel();
                    tempLabel.setBounds(x,y,100,100);
                    tempLabel.setBackground(Color.yellow);
                    tempLabel.setOpaque(true);

                }
                else if(levelMap[i][j].getisEmpty() == true)
                {
                    tempLabel = new JLabel();
                    tempLabel.setBounds(x,y,100,100);
                    tempLabel.setBackground(Color.white);
                    tempLabel.setOpaque(true);
                }
                else if(levelMap[i][j].getisEntrance() == true)
                {
                    tempLabel = new JLabel("Entrance");
                    tempLabel.setBounds(x,y,100,100);
                    tempLabel.setBackground(Color.white);
                    tempLabel.setOpaque(true);
                }
                else if (levelMap[i][j].getisExit() == true)
                {
                    tempLabel = new JLabel("Exit");
                    tempLabel.setBounds(x,y,100,100);
                    tempLabel.setBackground(Color.white);
                    tempLabel.setOpaque(true);
                }
                this.add(tempLabel);
                x += 100;
            }
            y+= 100;
            x= 0;

        }
        this.setVisible(true);

    }

    private void createTileMap()
    {
        //fill map with empty tiles:
        levelMap = new Tile[10][10];
        for(int i = 0; i < 10; i++)
        {
            for(int j = 0; j< 10; j++)
            {
                levelMap[i][j] = new Tile(false,false,false,false,false, true);
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
        levelMap[1][0]  = new Tile(false,false,false,true,false,false);
        //Set Entrance Tile
        levelMap[8][9] = new Tile(false,false,true,false,false,false);

        //Create the internal maze layout
        //Structure1:
        for(int i = 2; i <5;i++ )
        {
            levelMap[2][i].setEmpty(false);
            levelMap[2][i].setBarrier(true);
        }
        levelMap[3][2] = new Tile(true,false,false,false,false,false);

        //Structure2
        levelMap[5][2] = new Tile(true,false,false,false,false,false);
        levelMap[6][2] = new Tile(true,false,false,false,false,false);
        levelMap[6][3] = new Tile(true,false,false,false,false,false);
        levelMap[7][3] = new Tile(true,false,false,false,false,false);
        levelMap[7][4] = new Tile(true,false,false,false,false,false);
        levelMap[5][4] = new Tile(true,false,false,false,false,false);

        //Structure3
        levelMap[5][8] = new Tile(true,false,false,false,false,false);
        levelMap[5][7] = new Tile(true,false,false,false,false,false);
        levelMap[5][6] = new Tile(true,false,false,false,false,false);
        levelMap[6][6] = new Tile(true,false,false,false,false,false);
        levelMap[7][6] = new Tile(true,false,false,false,false,false);
        levelMap[7][7] = new Tile(true,false,false,false,false,false);

        //Structure4
        levelMap[2][6] = new Tile(true,false,false,false,false,false);
        levelMap[2][7] = new Tile(true,false,false,false,false,false);
        levelMap[3][7] = new Tile(true,false,false,false,false,false);
    }

    public void moveMouse(String direction)
    {
        try {
            //Perform checks in each case to see whether the new spot is a valid movement(no barriers)
            if (direction == "left") {
                if (levelMap[myMouse.getCurrentY()][myMouse.getCurrentX() - 1].getisBarrier() == false) {
                    levelMap[myMouse.getCurrentY()][myMouse.getCurrentX()] = new Tile(false, false, false, false, false, true);
                    myMouse.setCurrentX(myMouse.getCurrentX() - 1);
                    int direction1 = cat1.chase(myMouse.getCurrentX(), myMouse.getCurrentY(), levelMap);
                    int direction2 = cat2.chase(myMouse.getCurrentX(), myMouse.getCurrentY(), levelMap);
                    levelMap[myMouse.getCurrentY()][myMouse.getCurrentX()] = new Tile(false, false, false, false, true, false);
                    mouseLabel.setLocation(mouseLabel.getX() - 100, mouseLabel.getY());
                    switch (direction1) {
                        case 1:
                            catLabel1.setLocation(catLabel1.getX() + 100, catLabel1.getY());
                            break;
                        case 2:
                            catLabel1.setLocation(catLabel1.getX() - 100, catLabel1.getY());
                            break;
                        case 3:
                            catLabel1.setLocation(catLabel1.getX(), catLabel1.getY() + 100);
                            break;
                        case 4:
                            catLabel1.setLocation(catLabel1.getX(), catLabel1.getY() - 100);
                            break;
                        default:
                            break;
                    }
                    switch (direction2) {
                        case 1:
                            catLabel2.setLocation(catLabel2.getX() + 100, catLabel2.getY());
                            break;
                        case 2:
                            catLabel2.setLocation(catLabel2.getX() - 100, catLabel2.getY());
                            break;
                        case 3:
                            catLabel2.setLocation(catLabel2.getX(), catLabel2.getY() + 100);
                            break;
                        case 4:
                            catLabel2.setLocation(catLabel2.getX(), catLabel2.getY() - 100);
                            break;
                        default:
                            break;
                    }
                }
            } else if (direction == "right") {
                if (levelMap[myMouse.getCurrentY()][myMouse.getCurrentX() + 1].getisBarrier() == false) {
                    levelMap[myMouse.getCurrentY()][myMouse.getCurrentX()] = new Tile(false, false, false, false, false, true);
                    myMouse.setCurrentX(myMouse.getCurrentX() + 1);
                    int direction1 = cat1.chase(myMouse.getCurrentX(), myMouse.getCurrentY(), levelMap);
                    int direction2 = cat2.chase(myMouse.getCurrentX(), myMouse.getCurrentY(), levelMap);
                    levelMap[myMouse.getCurrentY()][myMouse.getCurrentX()] = new Tile(false, false, false, false, true, false);
                    mouseLabel.setLocation(mouseLabel.getX() + 100, mouseLabel.getY());
                    switch (direction1) {
                        case 1:
                            catLabel1.setLocation(catLabel1.getX() + 100, catLabel1.getY());
                            break;
                        case 2:
                            catLabel1.setLocation(catLabel1.getX() - 100, catLabel1.getY());
                            break;
                        case 3:
                            catLabel1.setLocation(catLabel1.getX(), catLabel1.getY() + 100);
                            break;
                        case 4:
                            catLabel1.setLocation(catLabel1.getX(), catLabel1.getY() - 100);
                            break;
                        default:
                            break;
                    }
                    switch (direction2) {
                        case 1:
                            catLabel2.setLocation(catLabel2.getX() + 100, catLabel2.getY());
                            break;
                        case 2:
                            catLabel2.setLocation(catLabel2.getX() - 100, catLabel2.getY());
                            break;
                        case 3:
                            catLabel2.setLocation(catLabel2.getX(), catLabel2.getY() + 100);
                            break;
                        case 4:
                            catLabel2.setLocation(catLabel2.getX(), catLabel2.getY() - 100);
                            break;
                        default:
                            break;
                    }
                }
            } else if (direction == "up") {
                if (levelMap[myMouse.getCurrentY() - 1][myMouse.getCurrentX()].getisBarrier() == false) {
                    levelMap[myMouse.getCurrentY()][myMouse.getCurrentX()] = new Tile(false, false, false, false, false, true);
                    myMouse.setCurrentY(myMouse.getCurrentY() - 1);
                    int direction1 = cat1.chase(myMouse.getCurrentX(), myMouse.getCurrentY(), levelMap);
                    int direction2 = cat2.chase(myMouse.getCurrentX(), myMouse.getCurrentY(), levelMap);
                    levelMap[myMouse.getCurrentY()][myMouse.getCurrentX()] = new Tile(false, false, false, false, true, false);
                    mouseLabel.setLocation(mouseLabel.getX(), mouseLabel.getY() - 100);
                    switch (direction1) {
                        case 1:
                            catLabel1.setLocation(catLabel1.getX() + 100, catLabel1.getY());
                            break;
                        case 2:
                            catLabel1.setLocation(catLabel1.getX() - 100, catLabel1.getY());
                            break;
                        case 3:
                            catLabel1.setLocation(catLabel1.getX(), catLabel1.getY() + 100);
                            break;
                        case 4:
                            catLabel1.setLocation(catLabel1.getX(), catLabel1.getY() - 100);
                            break;
                        default:
                            break;
                    }
                    switch (direction2) {
                        case 1:
                            catLabel2.setLocation(catLabel2.getX() + 100, catLabel2.getY());
                            break;
                        case 2:
                            catLabel2.setLocation(catLabel2.getX() - 100, catLabel2.getY());
                            break;
                        case 3:
                            catLabel2.setLocation(catLabel2.getX(), catLabel2.getY() + 100);
                            break;
                        case 4:
                            catLabel2.setLocation(catLabel2.getX(), catLabel2.getY() - 100);
                            break;
                        default:
                            break;
                    }
                }
            } else if (direction == "down") {
                if (levelMap[myMouse.getCurrentY() + 1][myMouse.getCurrentX()].getisBarrier() == false) {
                    levelMap[myMouse.getCurrentY()][myMouse.getCurrentX()] = new Tile(false, false, false, false, false, true);
                    myMouse.setCurrentY(myMouse.getCurrentY() + 1);
                    int direction1 = cat1.chase(myMouse.getCurrentX(), myMouse.getCurrentY(), levelMap);
                    int direction2 = cat2.chase(myMouse.getCurrentX(), myMouse.getCurrentY(), levelMap);
                    levelMap[myMouse.getCurrentY()][myMouse.getCurrentX()] = new Tile(false, false, false, false, true, false);
                    mouseLabel.setLocation(mouseLabel.getX(), mouseLabel.getY() + 100);
                    switch (direction1) {
                        case 1:
                            catLabel1.setLocation(catLabel1.getX() + 100, catLabel1.getY());
                            break;
                        case 2:
                            catLabel1.setLocation(catLabel1.getX() - 100, catLabel1.getY());
                            break;
                        case 3:
                            catLabel1.setLocation(catLabel1.getX(), catLabel1.getY() + 100);
                            break;
                        case 4:
                            catLabel1.setLocation(catLabel1.getX(), catLabel1.getY() - 100);
                            break;
                        default:
                            break;
                    }
                    switch (direction2) {
                        case 1:
                            catLabel2.setLocation(catLabel2.getX() + 100, catLabel2.getY());
                            break;
                        case 2:
                            catLabel2.setLocation(catLabel2.getX() - 100, catLabel2.getY());
                            break;
                        case 3:
                            catLabel2.setLocation(catLabel2.getX(), catLabel2.getY() + 100);
                            break;
                        case 4:
                            catLabel2.setLocation(catLabel2.getX(), catLabel2.getY() - 100);
                            break;
                        default:
                            break;
                    }
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Not move");
        }
    }

    public static void main(String[] args) {
        Game myGame = new Game();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode())
        {
            case 37: moveMouse("left");

                                break;
            case 39: moveMouse("right");

                                break;
            case 40: moveMouse("down");

                                break;
            case 38 : moveMouse("up");

                                break;
        }
    }
}
