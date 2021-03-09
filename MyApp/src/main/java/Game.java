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
    private Cheese cheese1, cheese2, cheese3;
    private OrganicCheese orgCheese1, orgCheese2, orgCheese3;
    private OrganicCheese orgCheese;
    private JLabel mouseLabel;

    JLabel catLabel1;
    JLabel catLabel2;
    JLabel cheeseLabel1;
    JLabel cheeseLabel2;
    JLabel cheeseLabel3;
    JLabel organicCheeseLabel1;
    JLabel organicCheeseLabel2;
    JLabel organicCheeseLabel3;

    Game()
    {
        //Create a Mouse
        myMouse = new Mouse();//coords set to 8,8
        //Create cheese
        cheese1 = new Cheese(8,1, 10);
        cheese2 = new Cheese(3,3,10);
        cheese3 = new Cheese(7,6,10);
        createMouseLabel();
        
        // Create multiple cats
        cat1 = new Cat();
        cat1.setCurrentX(4);
        cat1.setCurrentY(2);
        cat2 = new Cat();
        cat1.setCurrentX(8);
        cat1.setCurrentY(5);
        createCatLabel();

        createTileMap();
        createFrame();
        createGameMap();
        createCheeseLabel();
    }

    public void createOrganicCheese()
    {

        organicCheeseLabel1 = new JLabel();
        organicCheeseLabel1.setBounds(800,200,100,100);
        organicCheeseLabel1.setBackground(Color.pink);
        organicCheeseLabel1.setOpaque(true);
        ImageIcon orgCheeseIcon1 = new ImageIcon(orgCheese1.orgCheeseImg());
        organicCheeseLabel1.setIcon(orgCheeseIcon1);
        this.add(organicCheeseLabel1);
        this.validate();

        organicCheeseLabel2 = new JLabel();
        organicCheeseLabel2.setBounds(300,400,100,100);
        organicCheeseLabel2.setBackground(Color.pink);
        organicCheeseLabel2.setOpaque(true);
        ImageIcon orgCheeseIcon2 = new ImageIcon(orgCheese2.orgCheeseImg());
        organicCheeseLabel2.setIcon(orgCheeseIcon2);
        this.add(organicCheeseLabel2);
        this.validate();

        organicCheeseLabel3 = new JLabel();
        organicCheeseLabel3.setBounds(700,700,100,100);
        organicCheeseLabel3.setBackground(Color.pink);
        organicCheeseLabel3.setOpaque(true);
        ImageIcon orgCheeseIcon3 = new ImageIcon(orgCheese3.orgCheeseImg());
        organicCheeseLabel3.setIcon(orgCheeseIcon3);
        this.add(organicCheeseLabel3);
        this.validate();
    }
    public void createCheeseLabel()
    {
        cheeseLabel1 = new JLabel();
        cheeseLabel1.setBounds(800,200,100,100);
        cheeseLabel1.setBackground(Color.orange);
        cheeseLabel1.setOpaque(true);
        ImageIcon cheeseicon1 = new ImageIcon(cheese1.getCheeseImg());
        cheeseLabel1.setIcon(cheeseicon1);
        this.add(cheeseLabel1);
        this.validate();

        cheeseLabel2 = new JLabel();
        cheeseLabel2.setBounds(300,400,100,100);
        cheeseLabel2.setBackground(Color.orange);
        cheeseLabel2.setOpaque(true);
        ImageIcon cheeseicon2 = new ImageIcon(cheese2.getCheeseImg());
        cheeseLabel2.setIcon(cheeseicon2);
        this.add(cheeseLabel2);
        this.validate();

        cheeseLabel3 = new JLabel();
        cheeseLabel3.setBounds(700,700,100,100);
        cheeseLabel3.setBackground(Color.orange);
        cheeseLabel3.setOpaque(true);
        ImageIcon cheeseicon3 = new ImageIcon(cheese3.getCheeseImg());
        cheeseLabel3.setIcon(cheeseicon3);
        this.add(cheeseLabel3);
        this.validate();
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
                    levelMap[myMouse.getCurrentY()][myMouse.getCurrentX()] = new Tile(false, false, false, false, true, false);
                    mouseLabel.setLocation(mouseLabel.getX() - 100, mouseLabel.getY());

                    cat1.chase(myMouse.getCurrentX(), myMouse.getCurrentY(), levelMap);
                    cat2.chase(myMouse.getCurrentX(), myMouse.getCurrentY(), levelMap);
                    catLabel1.setLocation(cat1.getCurrentX()*100, cat1.getCurrentY()*100);
                    catLabel2.setLocation(cat2.getCurrentX()*100, cat2.getCurrentY()*100);
                }
            } else if (direction == "right") {
                if (levelMap[myMouse.getCurrentY()][myMouse.getCurrentX() + 1].getisBarrier() == false) {
                    levelMap[myMouse.getCurrentY()][myMouse.getCurrentX()] = new Tile(false, false, false, false, false, true);
                    myMouse.setCurrentX(myMouse.getCurrentX() + 1);
                    levelMap[myMouse.getCurrentY()][myMouse.getCurrentX()] = new Tile(false, false, false, false, true, false);
                    mouseLabel.setLocation(mouseLabel.getX() + 100, mouseLabel.getY());
                    
                    cat1.chase(myMouse.getCurrentX(), myMouse.getCurrentY(), levelMap);
                    cat2.chase(myMouse.getCurrentX(), myMouse.getCurrentY(), levelMap);
                    catLabel1.setLocation(cat1.getCurrentX()*100, cat1.getCurrentY()*100);
                    catLabel2.setLocation(cat2.getCurrentX()*100, cat2.getCurrentY()*100);
                }
            } else if (direction == "up") {
                if (levelMap[myMouse.getCurrentY() - 1][myMouse.getCurrentX()].getisBarrier() == false) {
                    levelMap[myMouse.getCurrentY()][myMouse.getCurrentX()] = new Tile(false, false, false, false, false, true);
                    myMouse.setCurrentY(myMouse.getCurrentY() - 1);
                   
                    levelMap[myMouse.getCurrentY()][myMouse.getCurrentX()] = new Tile(false, false, false, false, true, false);
                    mouseLabel.setLocation(mouseLabel.getX(), mouseLabel.getY() - 100);
                    
                    cat1.chase(myMouse.getCurrentX(), myMouse.getCurrentY(), levelMap);
                    cat2.chase(myMouse.getCurrentX(), myMouse.getCurrentY(), levelMap);
                    catLabel1.setLocation(cat1.getCurrentX()*100, cat1.getCurrentY()*100);
                    catLabel2.setLocation(cat2.getCurrentX()*100, cat2.getCurrentY()*100);
                }
            } else if (direction == "down") {
                if (levelMap[myMouse.getCurrentY() + 1][myMouse.getCurrentX()].getisBarrier() == false) {
                    levelMap[myMouse.getCurrentY()][myMouse.getCurrentX()] = new Tile(false, false, false, false, false, true);
                    myMouse.setCurrentY(myMouse.getCurrentY() + 1);
                    
                    levelMap[myMouse.getCurrentY()][myMouse.getCurrentX()] = new Tile(false, false, false, false, true, false);
                    mouseLabel.setLocation(mouseLabel.getX(), mouseLabel.getY() + 100);

                    cat1.chase(myMouse.getCurrentX(), myMouse.getCurrentY(), levelMap);
                    cat2.chase(myMouse.getCurrentX(), myMouse.getCurrentY(), levelMap);
                    catLabel1.setLocation(cat1.getCurrentX()*100, cat1.getCurrentY()*100);
                    catLabel2.setLocation(cat2.getCurrentX()*100, cat2.getCurrentY()*100);
                    
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
