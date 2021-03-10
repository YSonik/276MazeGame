import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game extends JFrame implements KeyListener {
    private Boolean inGame;
    private int score;
    private Tile[][] levelMap;//Game Template
    private JLabel[][] gameMap;//Mapping of levelMap to JFrame
    private Mouse myMouse;
    
    private Cheese cheese1;
    private Cheese cheese2;
    private Cheese cheese3;
    private JLabel Cheese1;
    private JLabel Cheese2;
    private JLabel Cheese3;
    private JLabel mouseLabel;
    
    private Cat cat1;
    private Cat cat2;
    JLabel catLabel1;
    JLabel catLabel2;

    private MouseTrap trap1, trap2;
    JLabel trapLabel1;
    JLabel trapLabel2;

   

    //JButton Reset;


    


    Game()
    {
        //Create a Mouse
        myMouse = new Mouse();//coords set to 8,8
        score = 0;
        //Create Cheese
        cheese1 = new Cheese(6,7,5);
        cheese2 = new Cheese(4,3,5);
        cheese3 = new Cheese(2,8,5);
        createCheeseLabel();

        createMouseLabel();
        // Create multiple cats
        cat1 = new Cat(7, 1);
        cat2 = new Cat(7, 2);
        createCatLabel();

        // Create mousetraps
        trap1 = new MouseTrap();
        trap1.setCurrentX(3);
        trap1.setCurrentY(6);
        trap2 = new MouseTrap();
        trap2.setCurrentX(4);
        trap2.setCurrentY(4);
        createTrapLabel();  

        scoreBelowZero();
        
        createTileMap();
        createFrame();
        createGameMap();

        
        inGame = true; 

    }

    public boolean scoreBelowZero(){
        return score < 0;
    }


  
    

    public void catMoveDraw(int direction1, int direction2) {
        //Cat Position update
        switch (direction1) {
            case 1:
                this.catLabel1.setLocation(this.catLabel1.getX() + 100, this.catLabel1.getY());
                break;
            case 2:
                this.catLabel1.setLocation(this.catLabel1.getX() - 100, this.catLabel1.getY());
                break;
            case 3:
                this.catLabel1.setLocation(this.catLabel1.getX(), this.catLabel1.getY() + 100);
                break;
            case 4:
                catLabel1.setLocation(this.catLabel1.getX(), this.catLabel1.getY() - 100);
                break;
            default:
                this.inGame = false;
                break;
        }
        switch (direction2) {
            case 1:
                this.catLabel2.setLocation(this.catLabel2.getX() + 100, this.catLabel2.getY());
                break;
            case 2:
                this.catLabel2.setLocation(this.catLabel2.getX() - 100, this.catLabel2.getY());
                break;
            case 3:
                this.catLabel2.setLocation(this.catLabel2.getX(), this.catLabel2.getY() + 100);
                break;
            case 4:
                this.catLabel2.setLocation(this.catLabel2.getX(), this.catLabel2.getY() - 100);
                break;
            default:
                this.inGame = false;
                break;
        }

    }

    public void createTrapLabel()
    {
        trapLabel1 = new JLabel("Trap1");
        trapLabel1.setBounds(trap1.currentY*100,(trap1.currentX +1)*100,100,100);
        trapLabel1.setBackground(Color.pink);
        trapLabel1.setOpaque(true);
        //ImageIcon t1 = new ImageIcon(trap1.getMTImage());
        //trapLabel1.setIcon(t1);
        this.add(trapLabel1);
        this.validate();

        trapLabel2 = new JLabel("Trap2");
        trapLabel2.setBounds(trap2.currentY*100,(trap2.currentX +1)*100,100,100);
        trapLabel2.setBackground(Color.pink);
        trapLabel2.setOpaque(true);
       // ImageIcon t2 = new ImageIcon(trap2.getMTImage());
        //trapLabel2.setIcon(t2);
        this.add(trapLabel2);
        this.validate();
    }

    public void createCheeseLabel()
    {
        Cheese1 = new JLabel("Cheese1");
        Cheese1.setBounds(cheese1.y*100,(cheese1.x+1)*100,100,100);
        Cheese1.setBackground(Color.green);
        Cheese1.setOpaque(true);
        this.add(Cheese1);
        this.validate();

        Cheese2 = new JLabel("Cheese2");
        Cheese2.setBounds(cheese2.y*100,(cheese2.x+1)*100,100,100);
        Cheese2.setBackground(Color.green);
        Cheese2.setOpaque(true);
        this.add(Cheese2);
        this.validate();

        Cheese3 = new JLabel("Cheese3");
        Cheese3.setBounds(cheese3.y*100,(cheese3.x+1)*100,100,100);
        Cheese3.setBackground(Color.green);
        Cheese3.setOpaque(true);
        this.add(Cheese3);
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
        catLabel1.setBounds(cat1.getCurrentY()*100,(cat1.getCurrentX()+1)*100,100,100);
        catLabel1.setBackground(Color.yellow);
        catLabel1.setOpaque(true);
        this.add(catLabel1);
        this.validate();

        catLabel2 = new JLabel();
        catLabel2.setBounds(cat2.getCurrentY()*100,(cat2.getCurrentX()+1)*100,100,100);
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

        //Set locations for traps
        
        levelMap[3][6] = new Tile(false,false,false,false,false,false,false, false,true);
        levelMap[4][4] = new Tile(false,false,false,false,false,false,false, false,true);


    }

    public void moveMouse(String direction)
    {
        try {
            //Perform checks in each case to see whether the new spot is a valid movement(no barriers)
            if (direction == "left") {
                
                if (levelMap[myMouse.getCurrentY()][myMouse.getCurrentX() - 1].getisBarrier() == false) {

                    //Mouse position update plus cheese check
                    if( levelMap[myMouse.getCurrentY()][myMouse.getCurrentX()-1].getisCheese() == true)
                    {
                        score += cheese1.value;
                        System.out.println(score);
                        //Remove Labels
                        if(myMouse.getCurrentX()-1 == cheese1.getY())
                        {
                            Cheese1.setVisible(false);
                            this.repaint();
                        }
                        else
                        {
                            Cheese2.setVisible(false);
                            this.repaint();
                        }

                    }
                    //Mouse position update plus trap check
                    if( levelMap[myMouse.getCurrentY()][myMouse.getCurrentX()-1].getisMouseTrap() == true)
                    {
                        score -= trap1.getPenalty();
                        if(scoreBelowZero()){
                            System.out.println("Game Over");
                        }
                        System.out.println(score);
                        //Remove Labels
                        trapLabel2.setVisible(false);
                        this.repaint();    
                    }

                    

                    levelMap[myMouse.getCurrentY()][myMouse.getCurrentX()] = new Tile(false, false, false, false, false, true,false,false,false);
                    levelMap[cat1.getCurrentY()][cat1.getCurrentX()] = new Tile(false, false, false, false, false, true, false, false, false);
                    levelMap[cat2.getCurrentY()][cat2.getCurrentX()] = new Tile(false, false, false, false, false, true, false, false, false);
                    myMouse.setCurrentX(myMouse.getCurrentX() - 1);
                    
                    int direction1 = cat1.chase(myMouse.getCurrentX(), myMouse.getCurrentY(), levelMap);
                    int direction2 = cat2.chase(myMouse.getCurrentX(), myMouse.getCurrentY(), levelMap);
                    catMoveDraw(direction1, direction2);
                    levelMap[myMouse.getCurrentY()][myMouse.getCurrentX()] = new Tile(false, false, false, false, true, false,false,false,false);
                    if(levelMap[cat1.getCurrentY()][cat1.getCurrentX()].getIsCharacter() || levelMap[cat2.getCurrentY()][cat2.getCurrentX()].getIsCharacter()) {
                        inGame = false;
                    } else {
                        levelMap[cat1.getCurrentY()][cat1.getCurrentX()] = new Tile(false, false, false, false, false, false, false, true, false);
                        levelMap[cat2.getCurrentY()][cat2.getCurrentX()] = new Tile(false, false, false, false, false, false, false, true, false);
                    }
                    mouseLabel.setLocation(mouseLabel.getX() - 100, mouseLabel.getY());
                    if (!inGame) {
                        System.out.println("Game Over");
                    }
                }

            } else if (direction == "right") {
                if (levelMap[myMouse.getCurrentY()][myMouse.getCurrentX() + 1].getisBarrier() == false) {

                    //Mouse position update plus cheese check
                    if( levelMap[myMouse.getCurrentY()][myMouse.getCurrentX()+1].getisCheese() == true)
                    {
                        score += cheese1.value;
                        System.out.println(score);
                        //Remove Labels
                        Cheese2.setVisible(false);
                        this.repaint();

                    }
                    
                    //Mouse position update plus trap check
                    if( levelMap[myMouse.getCurrentY()][myMouse.getCurrentX()+1].getisMouseTrap() == true)
                    {
                        score -= trap1.getPenalty();
                        if(scoreBelowZero()){
                            System.out.println("Game Over");
                        }
                        System.out.println(score);
                        //Remove Labels
                        if(myMouse.getCurrentX()+1 == trap1.getCurrentY())
                        {
                            trapLabel1.setVisible(false);
                            this.repaint();
                        }
                        else{
                            trapLabel2.setVisible(false);
                            this.repaint();
                        }
                    }


                    levelMap[myMouse.getCurrentY()][myMouse.getCurrentX()] = new Tile(false, false, false, false, false, true,false,false,false);
                    levelMap[cat1.getCurrentY()][cat1.getCurrentX()] = new Tile(false, false, false, false, false, true, false, false, false);
                    levelMap[cat2.getCurrentY()][cat2.getCurrentX()] = new Tile(false, false, false, false, false, true, false, false, false);
                    myMouse.setCurrentX(myMouse.getCurrentX() + 1);
                    int direction1 = cat1.chase(myMouse.getCurrentX(), myMouse.getCurrentY(), levelMap);
                    int direction2 = cat2.chase(myMouse.getCurrentX(), myMouse.getCurrentY(), levelMap);
                    catMoveDraw(direction1, direction2);
                    levelMap[myMouse.getCurrentY()][myMouse.getCurrentX()] = new Tile(false, false, false, false, true, false,false,false,false);
                    if(levelMap[cat1.getCurrentY()][cat1.getCurrentX()].getIsCharacter() || levelMap[cat2.getCurrentY()][cat2.getCurrentX()].getIsCharacter()) {
                        inGame = false;
                    } else {
                        levelMap[cat1.getCurrentY()][cat1.getCurrentX()] = new Tile(false, false, false, false, false, false, false, true, false);
                        levelMap[cat2.getCurrentY()][cat2.getCurrentX()] = new Tile(false, false, false, false, false, false, false, true, false);
                    }
                    mouseLabel.setLocation(mouseLabel.getX() + 100, mouseLabel.getY());
                    if (!inGame) {
                        System.out.println("Game Over");
                    }
                }
            }

            else if (direction == "up") {
                if (levelMap[myMouse.getCurrentY() - 1][myMouse.getCurrentX()].getisBarrier() == false) {
                    //Mouse position update plus cheese check
                    if( levelMap[myMouse.getCurrentY()-1][myMouse.getCurrentX()].getisCheese() == true)
                    {
                        score += cheese1.value;
                        System.out.println(score);
                        //Remove Labels

                        Cheese3.setVisible(false);
                        this.repaint();
                    }

                    //Mouse position update plus trap check
                    if( levelMap[myMouse.getCurrentY()-1][myMouse.getCurrentX()].getisMouseTrap() == true)
                    {
                        score -= trap1.getPenalty();
                        if(scoreBelowZero()){
                            System.out.println("Game Over");
                        }
                        System.out.println(score);
                        //Remove Labels
                        trapLabel1.setVisible(false);
                        this.repaint();
                    }

                    levelMap[myMouse.getCurrentY()][myMouse.getCurrentX()] = new Tile(false, false, false, false, false, true,false,false,false);
                    levelMap[cat1.getCurrentY()][cat1.getCurrentX()] = new Tile(false, false, false, false, false, true, false, false, false);
                    levelMap[cat2.getCurrentY()][cat2.getCurrentX()] = new Tile(false, false, false, false, false, true, false, false, false);
                    myMouse.setCurrentY(myMouse.getCurrentY() - 1);
                    int direction1 = cat1.chase(myMouse.getCurrentX(), myMouse.getCurrentY(), levelMap);
                    int direction2 = cat2.chase(myMouse.getCurrentX(), myMouse.getCurrentY(), levelMap);
                    catMoveDraw(direction1, direction2);
                    levelMap[myMouse.getCurrentY()][myMouse.getCurrentX()] = new Tile(false, false, false, false, true, false,false,false,false);
                    if(levelMap[cat1.getCurrentY()][cat1.getCurrentX()].getIsCharacter() || levelMap[cat2.getCurrentY()][cat2.getCurrentX()].getIsCharacter()) {
                        inGame = false;
                    } else {
                        levelMap[cat1.getCurrentY()][cat1.getCurrentX()] = new Tile(false, false, false, false, false, false, false, true, false);
                        levelMap[cat2.getCurrentY()][cat2.getCurrentX()] = new Tile(false, false, false, false, false, false, false, true, false);
                    }
                    mouseLabel.setLocation(mouseLabel.getX(), mouseLabel.getY() - 100);
                    if (!inGame) {
                        System.out.println("Game Over");
                    }
                }

            } else if (direction == "down") {
                if (levelMap[myMouse.getCurrentY() + 1][myMouse.getCurrentX()].getisBarrier() == false) {

                    //Mouse position update plus cheese check
                    if( levelMap[myMouse.getCurrentY()+1][myMouse.getCurrentX()].getisCheese() == true) {
                        score += cheese1.value;
                        System.out.println(score);
                        //Remove Labels
                        if(myMouse.getCurrentY()+1 == cheese2.getX())
                        {
                            Cheese2.setVisible(false);
                            this.repaint();
                        }
                        else
                        {
                            Cheese3.setVisible(false);
                            this.repaint();
                        }
                    }
                    //Mouse position update plus trap check
                    if( levelMap[myMouse.getCurrentY()+1][myMouse.getCurrentX()].getisMouseTrap() == true)
                    {
                        score -= trap2.getPenalty();
                        if(scoreBelowZero()){
                            System.out.println("Game Over");
                        }
                        System.out.println(score);
                        //Remove Labels
                        trapLabel2.setVisible(false);
                        this.repaint();
                    }


                    levelMap[myMouse.getCurrentY()][myMouse.getCurrentX()] = new Tile(false, false, false, false, false, true, false, false, false);
                    levelMap[cat1.getCurrentY()][cat1.getCurrentX()] = new Tile(false, false, false, false, false, true, false, false, false);
                    levelMap[cat2.getCurrentY()][cat2.getCurrentX()] = new Tile(false, false, false, false, false, true, false, false, false);
                    myMouse.setCurrentY(myMouse.getCurrentY() + 1);
                    int direction1 = cat1.chase(myMouse.getCurrentX(), myMouse.getCurrentY(), levelMap);
                    int direction2 = cat2.chase(myMouse.getCurrentX(), myMouse.getCurrentY(), levelMap);
                    catMoveDraw(direction1, direction2);
                    levelMap[myMouse.getCurrentY()][myMouse.getCurrentX()] = new Tile(false, false, false, false, true, false,false,false,false);
                    if(levelMap[cat1.getCurrentY()][cat1.getCurrentX()].getIsCharacter() || levelMap[cat2.getCurrentY()][cat2.getCurrentX()].getIsCharacter()) {
                        inGame = false;
                    } else {
                        levelMap[cat1.getCurrentY()][cat1.getCurrentX()] = new Tile(false, false, false, false, false, false, false, true, false);
                        levelMap[cat2.getCurrentY()][cat2.getCurrentX()] = new Tile(false, false, false, false, false, false, false, true, false);
                    }
                    mouseLabel.setLocation(mouseLabel.getX(), mouseLabel.getY() + 100);
                    if (!inGame) {
                        System.out.println("Game Over");
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
                else if(levelMap[i][j].getisMouseTrap() == true)
                {
                    System.out.print(" T ");
                }
                else if(levelMap[i][j].getisCat() == true)
                {
                    System.out.print(" C ");
                }
                else{
                    System.out.print("   ");
                }
            }
            System.out.print("\n");
            //System.out.println(score);
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
