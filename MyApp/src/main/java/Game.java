import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game extends JFrame implements KeyListener {
    private int score;
    private Tile[][] levelMap;//Game Template
    private JLabel[][] gameMap;//Mapping of levelMap to JFrame
    private Mouse myMouse;
    private Cat cat1;
    private Cat cat2;
    private Cheese cheese1;
    private Cheese cheese2;
    private Cheese cheese3;
    private JLabel Cheese1;
    private JLabel Cheese2;
    private JLabel Cheese3;
    private JLabel mouseLabel;
    private JLabel scoreText;
    JLabel catLabel1;
    JLabel catLabel2;
    private int count = 0;
    Game()
    {
        //Create a Mouse
        myMouse = new Mouse();//coords set to 8,8
        //set score
        int score = 0;
        //Create Cheese
        cheese1 = new Cheese(6,7,5);
        cheese2 = new Cheese(4,3,5);
        cheese3 = new Cheese(2,8,5);
        createCheeseLabel();

        createMouseLabel();
        // Create multiple cats
        cat1 = new Cat();
        cat2 = new Cat();
        createCatLabel();
        createTileMap();
        createFrame();
        createGameMap();
        createScoreText();
    }

    public void createScoreText()
    {
       scoreText = new JLabel();
       scoreText.setBounds(50,50,100,50);
       scoreText.setOpaque(true);
       this.add(scoreText);
       this.validate();
    }


    public void createCheeseLabel()
    {
        Cheese1 = new JLabel("Cheese1");
        Cheese1.setBounds(cheese1.y*100,(cheese1.x+1)*100,100,100);
        Cheese1.setBackground(Color.white);
        Cheese1.setOpaque(true);
        Image cheeseImg = cheese1.getCheeseImg().getScaledInstance(Cheese1.getWidth(), Cheese1.getHeight(),
                Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(cheeseImg);
        Cheese1.setIcon(icon);
        this.add(Cheese1);
        this.validate();

        Cheese2 = new JLabel("Cheese2");
        Cheese2.setBounds(cheese2.y*100,(cheese2.x+1)*100,100,100);
        Cheese2.setBackground(Color.white);
        Cheese2.setOpaque(true);
        Image cheeseImg2 = cheese2.getCheeseImg().getScaledInstance(Cheese2.getWidth(), Cheese2.getHeight(),
                Image.SCALE_SMOOTH);
        ImageIcon icon2 = new ImageIcon(cheeseImg2);
        Cheese2.setIcon(icon2);
        this.add(Cheese2);
        this.validate();

        Cheese3 = new JLabel("Cheese3");
        Cheese3.setBounds(cheese3.y*100,(cheese3.x+1)*100,100,100);
        Cheese3.setBackground(Color.white);
        Cheese3.setOpaque(true);
        Image cheeseImg3 = cheese3.getCheeseImg().getScaledInstance(Cheese3.getWidth(), Cheese3.getHeight(),
                Image.SCALE_SMOOTH);
        ImageIcon icon3 = new ImageIcon(cheeseImg3);
        Cheese3.setIcon(icon3);

        this.add(Cheese3);
        this.validate();
    }

    public void createMouseLabel()
    {
        mouseLabel = new JLabel();
        mouseLabel.setBounds(800,900,100,100);
        mouseLabel.setBackground(Color.blue);
        mouseLabel.setOpaque(true);
        Image mouseImg = myMouse.getMouseImage().getScaledInstance(mouseLabel.getWidth(), mouseLabel.getHeight(),
                Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(mouseImg);
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
                levelMap[i][j] = new Tile(false,false,false,false,false, true,false,false,false);
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
        levelMap[1][0]  = new Tile(false,false,false,true,false,false,false,false,false);
        //Set Entrance Tile
        levelMap[8][9] = new Tile(false,false,true,false,false,false,false,false,false);

        //Create the internal maze layout
        //Structure1:
        for(int i = 2; i <5;i++ )
        {
            levelMap[2][i].setEmpty(false);
            levelMap[2][i].setBarrier(true);
        }
        levelMap[3][2] = new Tile(true,false,false,false,false,false,false,false, false);

        //Structure2
        levelMap[5][2] = new Tile(true,false,false,false,false,false,false,false,false);
        levelMap[6][2] = new Tile(true,false,false,false,false,false,false,false,false);
        levelMap[6][3] = new Tile(true,false,false,false,false,false,false,false,false);
        levelMap[7][3] = new Tile(true,false,false,false,false,false,false,false,false);
        levelMap[7][4] = new Tile(true,false,false,false,false,false,false,false,false);
        levelMap[5][4] = new Tile(true,false,false,false,false,false,false,false,false);

        //Structure3
        levelMap[5][8] = new Tile(true,false,false,false,false,false,false,false,false);
        levelMap[5][7] = new Tile(true,false,false,false,false,false,false,false,false);
        levelMap[5][6] = new Tile(true,false,false,false,false,false,false,false,false);
        levelMap[6][6] = new Tile(true,false,false,false,false,false,false,false,false);
        levelMap[7][6] = new Tile(true,false,false,false,false,false,false,false,false);
        levelMap[7][7] = new Tile(true,false,false,false,false,false,false,false,false);

        //Structure4
        levelMap[2][6] = new Tile(true,false,false,false,false,false,false,false,false);
        levelMap[2][7] = new Tile(true,false,false,false,false,false,false,false,false);
        levelMap[3][7] = new Tile(true,false,false,false,false,false,false,false,false);


        //Set locations for cheese
        levelMap[6][7] = new Tile(false,false,false,false,false,false,true, false,false);
        levelMap[4][3] = new Tile(false,false,false,false,false,false,true, false,false);
        levelMap[2][8] = new Tile(false,false,false,false,false,false,true, false,false);


    }

    public void moveMouse(String direction)
    {
        try {
            //Perform checks in each case to see whether the new spot is a valid movement(no barriers)
            if (direction == "left") {
                if (!levelMap[myMouse.getCurrentY()][myMouse.getCurrentX() - 1].getisBarrier()) {
                    if(count == 0){scoreText.setText("Score: " + 0);}
                    //Mouse position update plus cheese check
                    if(levelMap[myMouse.getCurrentY()][myMouse.getCurrentX() - 1].getisCheese())
                    {
                        score += cheese1.value;
                        System.out.println(score);
                        scoreText.setText("Score: " + score);
                        count++;
                        //Remove Labels
                        if(myMouse.getCurrentX()-1 == cheese1.getY())
                        {
                            Cheese1.setVisible(false);
                            this.repaint();
                        }
                        else
                        {
                            Cheese2.setVisible(false);
                            scoreText.setVisible(true);
                            this.repaint();
                        }

                    }
                    levelMap[myMouse.getCurrentY()][myMouse.getCurrentX()] = new Tile(false, false, false, false, false, true,false,false,false);
                    myMouse.setCurrentX(myMouse.getCurrentX() - 1);
                    int direction1 = cat1.chase(myMouse.getCurrentX(), myMouse.getCurrentY(), levelMap);
                    int direction2 = cat2.chase(myMouse.getCurrentX(), myMouse.getCurrentY(), levelMap);
                    levelMap[myMouse.getCurrentY()][myMouse.getCurrentX()] = new Tile(false, false, false, false, true, false,false,false,false);
                    mouseLabel.setLocation(mouseLabel.getX() - 100, mouseLabel.getY());

                    //Cat Position update
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
                if (!levelMap[myMouse.getCurrentY()][myMouse.getCurrentX() + 1].getisBarrier()) {

                    //Mouse position update plus cheese check
                    if(levelMap[myMouse.getCurrentY()][myMouse.getCurrentX() + 1].getisCheese())
                    {
                        score += cheese1.value;
                        System.out.println(score);
                        //Remove Labels
                        count++;
                        Cheese2.setVisible(false);
                        this.repaint();

                    }
                    levelMap[myMouse.getCurrentY()][myMouse.getCurrentX()] = new Tile(false, false, false, false, false, true,false,false,false);
                    myMouse.setCurrentX(myMouse.getCurrentX() + 1);
                    int direction1 = cat1.chase(myMouse.getCurrentX(), myMouse.getCurrentY(), levelMap);
                    int direction2 = cat2.chase(myMouse.getCurrentX(), myMouse.getCurrentY(), levelMap);
                    levelMap[myMouse.getCurrentY()][myMouse.getCurrentX()] = new Tile(false, false, false, false, true, false,false,false,false);
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
            }

            else if (direction == "up") {
                if (!levelMap[myMouse.getCurrentY() - 1][myMouse.getCurrentX()].getisBarrier()) {
                    //Mouse position update plus cheese check
                    if(levelMap[myMouse.getCurrentY() - 1][myMouse.getCurrentX()].getisCheese())
                    {
                        score += cheese1.value;
                        System.out.println(score);
                        //Remove Labels
                        count++;
                        scoreText.setText("Score: " + score);
                        Cheese3.setVisible(false);
                        this.repaint();

                    }

                    levelMap[myMouse.getCurrentY()][myMouse.getCurrentX()] = new Tile(false, false, false, false, false, true,false,false,false);
                    myMouse.setCurrentY(myMouse.getCurrentY() - 1);
                    int direction1 = cat1.chase(myMouse.getCurrentX(), myMouse.getCurrentY(), levelMap);
                    int direction2 = cat2.chase(myMouse.getCurrentX(), myMouse.getCurrentY(), levelMap);
                    levelMap[myMouse.getCurrentY()][myMouse.getCurrentX()] = new Tile(false, false, false, false, true, false,false,false,false);
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
                if (!levelMap[myMouse.getCurrentY() + 1][myMouse.getCurrentX()].getisBarrier()) {

                    //Mouse position update plus cheese check
                    if(levelMap[myMouse.getCurrentY() + 1][myMouse.getCurrentX()].getisCheese()) {
                        score += cheese1.value;
                        System.out.println(score);
                        //Remove Labels
                        count++;
                        scoreText.setText("Score: " + score);
                        Cheese3.setVisible(false);
                        this.repaint();

                    }

                    levelMap[myMouse.getCurrentY()][myMouse.getCurrentX()] = new Tile(false, false, false, false, false, true,false,false,false);
                    myMouse.setCurrentY(myMouse.getCurrentY() + 1);
                    int direction1 = cat1.chase(myMouse.getCurrentX(), myMouse.getCurrentY(), levelMap);
                    int direction2 = cat2.chase(myMouse.getCurrentX(), myMouse.getCurrentY(), levelMap);
                    levelMap[myMouse.getCurrentY()][myMouse.getCurrentX()] = new Tile(false, false, false, false, true, false,false,false,false);
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
        for(int i = 0; i< 10; i++)
        {
            for(int j = 0; j <10; j++)
            {
                if(levelMap[i][j].getisBarrier() == true ) {
                    System.out.print("[ ]");
                }
                else if(levelMap[i][j].getisReward() == true)
                {
                    System.out.print(" * ");
                }
                else if(levelMap[i][j].getIsCharacter() == true)
                {
                    System.out.print(" M ");
                }
                else if (levelMap[i][j].getisCheese() == true)
                {
                    System.out.print(" * ");
                }
                else{
                    System.out.print("   ");
                }
            }
            System.out.print("\n");
            System.out.println(score);
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
