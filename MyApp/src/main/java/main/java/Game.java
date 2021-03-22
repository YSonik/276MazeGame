package main.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;
import java.util.Random;

/**
 * This is the aggregate class which brings together all of the various pieces of CheeseRun. It is here that all of the objects are instantiated through various functions and
 * where their visual representations are created and manipulated.
 *
 *  @Auhtor Yogesh Sonik, Tian Yang Zhou, Scott Luu, and Canh Nhat Minh Le
 * */
public class Game extends JFrame implements KeyListener {

    private final static int ONE_SECOND = 1000;
    private Timer timer;
    private Boolean inGame;
    private Boolean winGame;
    private int score;

    private Tile[][] levelMap;
    private Mouse myMouse;
    private JLabel mouseLabel;

    private Cheese cheese1;
    private Cheese cheese2;
    private JLabel Cheese1;
    private JLabel Cheese2;

    private OrganicCheese orgCheese1,orgCheese2;
    private JLabel organic1,organic2;

    private Cat cat1;
    private Cat cat2;
    private JLabel scoreText;
    JLabel catLabel1;
    JLabel catLabel2;

    private MouseTrap trap1, trap2;
    JLabel trapLabel1;
    JLabel trapLabel2;

    private int countSteps = 0;
    private int rand1,rand2,rand3,rand4;
    int elapsedTime = 0;
    int seconds = 0;
    int minutes = 0;
    int hours = 0;

    String seconds_string = String.format("%02d", seconds);
    String minutes_string = String.format("%02d", minutes);
    String hours_string = String.format("%02d", hours );

    JLabel timeLabel = new JLabel();

    private int count = 0;

    /**
     * This is the constructor for the game class which instantiates the various objects required for the game such as: mouse, cat, mousetrap, and cheese.
     * It also creates jLables for all objects by calling member functions which have been delegated responsibility for a specific object.
     * Additionally, this function initializes all private data members that belong to the class.
     * @Author Yogesh Sonik
     * */

    Game()
    {

        //Create a Mouse
        myMouse = new Mouse();//coords set to 8,8
        createMouseLabel();

        //set score
        score = 0;

        //Create Cheese
        cheese1 = new Cheese(6, 7, 5);
        cheese2 = new Cheese(4, 3, 5);
        createCheeseLabel();

        //Create Organic Cheese
        orgCheese1 = new OrganicCheese(7,2,10);
        orgCheese2 = new OrganicCheese(1,3,10);
        createOrganicCheeseLabel();

        //randomizing appearance of Organic Cheese
         rand1 = getRandomNumber(4,10);
         rand2 = getRandomNumber(20, 25);
         rand3 = getRandomNumber(40,50);
         rand4 = getRandomNumber(20,25);

        // Create multiple cats
        cat1 = new Cat(4, 1);
        cat2 = new Cat(8, 4);
        createCatLabel();

        // Create mousetraps
        trap1 = new MouseTrap(3,6);
        
        trap2 = new MouseTrap(4,4);
        
        createTrapLabel();

        scoreBelowZero();
        createTileMap();
        createFrame();
        createGameMap();
        createScoreText();

        if (count == 0){scoreText.setText("Score: " + 0);}

        inGame = true;
        winGame = false;
        createTimerLabel();

        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if(inGame) {
                    updateTimerLabel();
                }
            }
        };
        Timer timer = new Timer(1000, taskPerformer);
        timer.start();
    }



    public void createTimerLabel(){
        timeLabel.setText(hours_string+":"+minutes_string+":"+seconds_string);
        timeLabel.setBounds(200,40,400,70 );
        timeLabel.setFont(new Font("Moon",Font.PLAIN, 20));
        this.add(timeLabel);
        this.validate();
    }

    public void updateTimerLabel(){
        elapsedTime=elapsedTime+1000;
        hours = (elapsedTime/3600000);
        minutes = (elapsedTime/60000) % 60;
        seconds = (elapsedTime/1000) % 60;
        seconds_string = String.format("%02d", seconds);
        minutes_string = String.format("%02d", minutes);
        hours_string = String.format("%02d", hours);
        timeLabel.setText(hours_string+":"+minutes_string+":"+seconds_string);

    }

    public void winPage() {
        ImageIcon testIcon = new ImageIcon("Images/win.png");
        Object[] option = { "Play Again" };
        int test = JOptionPane.showOptionDialog(null, "Total Score: " + score + "\n" +
                        "Time: " + hours_string+":"+minutes_string+":"+seconds_string  , " Win",
                                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                                    testIcon, option, option[0]);
		if(test == -1){
            System.exit(ABORT);
        }
        else{
            this.setVisible(false);
            new Game();
        }
    }

    public boolean scoreBelowZero(){
        return score < 0;
    }

    public void gameOver() {
        ImageIcon testIcon = new ImageIcon("Images/MouseLose.jpg");
        Object[] option = { "Play Again" };
        int test = JOptionPane.showOptionDialog(null, "Total Score: " + score + "\n" +
                        "Time: " + hours_string+":"+minutes_string+":"+seconds_string  , " GAME OVER",
                                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                                    testIcon, option, option[0]);
		if(test == -1){
            System.exit(ABORT);
        }
        else{
            this.setVisible(false);
            new Game();
        }
	}

    public boolean Catscollide(){
        return ((catLabel1.getX() == mouseLabel.getX() && catLabel1.getY() == mouseLabel.getY()) ||
                (catLabel2.getX() == mouseLabel.getX() && catLabel2.getY() == mouseLabel.getY()));

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
                break;
        }
        switch (direction2) {
            case 1:
                this.catLabel2.setLocation(this.catLabel2.getX() + 100, this.catLabel2.getY());
                if((this.catLabel1.getX() == this.catLabel2.getX()) && (this.catLabel1.getY() == this.catLabel2.getY())){
                    cat2.setCurrentX(cat2.getCurrentX()-1);
                    this.catLabel2.setLocation(this.catLabel2.getX() - 100, this.catLabel2.getY());
                }
                break;
            case 2:
                this.catLabel2.setLocation(this.catLabel2.getX() - 100, this.catLabel2.getY());
                if((this.catLabel1.getX() == this.catLabel2.getX()) && (this.catLabel1.getY() == this.catLabel2.getY())){
                    cat2.setCurrentX(cat2.getCurrentX()+1);
                    this.catLabel2.setLocation(this.catLabel2.getX() + 100, this.catLabel2.getY());
                }
                break;
            case 3:
                this.catLabel2.setLocation(this.catLabel2.getX(), this.catLabel2.getY() + 100);
                if((this.catLabel1.getX() == this.catLabel2.getX()) && (this.catLabel1.getY() == this.catLabel2.getY())){
                    cat2.setCurrentY(cat2.getCurrentY()-1);
                    this.catLabel2.setLocation(this.catLabel2.getX(), this.catLabel2.getY() - 100);
                }
                break;
            case 4:
                this.catLabel2.setLocation(this.catLabel2.getX(), this.catLabel2.getY() - 100);
                if((this.catLabel1.getX() == this.catLabel2.getX()) && (this.catLabel1.getY() == this.catLabel2.getY())){
                    cat2.setCurrentY(cat2.getCurrentY()+1);
                    this.catLabel2.setLocation(this.catLabel2.getX(), this.catLabel2.getY() + 100);
                }
                break;
            default:
                break;
        }

    }

    public void createTrapLabel()
    {
        trapLabel1 = new JLabel("Trap1");
        trapLabel1.setBounds(trap1.currentY*100,(trap1.currentX +1)*100,100,100);
        trapLabel1.setBackground(Color.pink);
        trapLabel1.setOpaque(true);
        Image trapImg1 = trap1.getMTImage().getScaledInstance(trapLabel1.getWidth(), trapLabel1.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon icon1 = new ImageIcon(trapImg1);
        trapLabel1.setIcon(icon1);
        this.add(trapLabel1);
        this.validate();

        trapLabel2 = new JLabel("Trap2");
        trapLabel2.setBounds(trap2.currentY*100,(trap2.currentX +1)*100,100,100);
        trapLabel2.setBackground(Color.pink);
        trapLabel2.setOpaque(true);
        Image trapImg2 = trap2.getMTImage().getScaledInstance(trapLabel2.getWidth(), trapLabel2.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon icon2 = new ImageIcon(trapImg2);
        trapLabel2.setIcon(icon2);
        this.add(trapLabel2);
        this.validate();
    }

    public void createScoreText()
    {
       scoreText = new JLabel();
       scoreText.setBounds(40,40,300,70);
       scoreText.setFont(new Font("Moon", Font.PLAIN, 20));
       this.add(scoreText);
       this.validate();
    }

    public void createOrganicCheeseLabel(){
        organic1 = new JLabel();
        organic1.setBounds(orgCheese1.y*100, (orgCheese1.x+1)*100, 100, 100);
        Image orgCheeseImg1 = orgCheese1.getOrgCheeseImg().getScaledInstance(organic1.getWidth(), organic1.getHeight(),
                Image.SCALE_SMOOTH);
        ImageIcon icon1 = new ImageIcon(orgCheeseImg1);
        organic1.setIcon(icon1);
        this.add(organic1);
        this.validate();

        organic2 = new JLabel();
        organic2.setBounds(orgCheese2.y*100, (orgCheese2.x+1)*100, 100, 100);
        Image orgCheeseImg2 = orgCheese2.getOrgCheeseImg().getScaledInstance(organic2.getWidth(), organic2.getHeight(),
                Image.SCALE_SMOOTH);
        ImageIcon icon2 = new ImageIcon(orgCheeseImg2);
        organic2.setIcon(icon2);
        this.add(organic2);
        this.validate();
        organic1.setVisible(false);
        organic2.setVisible(false);

    }

    /**
     * This is the void function called by the Game constructor to create new jLabels for all cheese object that need to be displayed on the map.
     * @Author Yogesh Sonik
     * */
    public void createCheeseLabel()
    {
        //Create jLabel for cheese1 and draw it on the jFrame at the correct location.
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

        //Create jLabel for cheese2 and draw it on the jFrame at the correct location.
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
    }

    /**
     * This is the void function, which is called by the Game constructor, creates a jLabel for the mouse and draws it at the correct location.
     * @Author Yogesh Sonik
     * */
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
        catLabel1.setBackground(Color.white);
        catLabel1.setOpaque(true);
        Image catImg = cat1.getCatImage().getScaledInstance(catLabel1.getWidth(), catLabel1.getHeight(),
                Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(catImg);
        catLabel1.setIcon(icon);
        this.add(catLabel1);
        this.validate();

        catLabel2 = new JLabel();
        catLabel2.setBounds(800,500,100,100);
        catLabel2.setBackground(Color.white);
        catLabel2.setOpaque(true);
        Image catImg2 = cat1.getCatImage().getScaledInstance(catLabel2.getWidth(), catLabel2.getHeight(),
                Image.SCALE_SMOOTH);
        ImageIcon icon2 = new ImageIcon(catImg2);
        catLabel2.setIcon(icon2);
        this.add(catLabel2);
        this.validate();
    }

   /**
    * This is the void function called by the Game constructor to create the jFrame that corresponds to the game page of CheeseRun where the maze will be displayed.
    * @Author Yogesh Sonik
    * */
    public void createFrame()
    {
        //Set up frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000,1200);//extra 100 pixels at the top for displaying game statistic
        this.setLayout(null);
        this.addKeyListener(this);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.WHITE);
    }

    /**
     * This is the void class called by the Game constructor to traverse the Tile array called levelMap and place the jLabels
     * that correspond to static objects like barriers, entrances and exits onto the jFrame at the corresponding coordinates.
     * @Author Yogesh Sonik
     * */
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

    /**
     * This is the void class called by the Game constructor which creates a 2D array of tiles and encodes all indices to the objects that should be placed at that location in the maze.
     * @Author Yogesh Sonik
     * */
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
        //levelMap[6][3] = new Tile(true,false,false,false,false,false,false,false,false);
        levelMap[6][4] = new Tile(true,false,false,false,false,false,false,false,false);
        levelMap[7][2] = new Tile(true,false,false,false,false,false,false,false,false);
        //levelMap[7][3] = new Tile(true,false,false,false,false,false,false,false,false);
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
        //levelMap[2][8] = new Tile(false,false,false,false,false,false,true, false,false);

        //Set locations for traps
        levelMap[3][6] = new Tile(false,false,false,false,false,false,false, false,true);
        levelMap[4][4] = new Tile(false,false,false,false,false,false,false, false,true);


    }

    /**
     * This is the void function called by the actionListener method called "KeyPressed" which recieves which direction to move the mouse label as a string and then preforms various checks to validate the movement
     * and then updates the map with regards to any changes that may have occurred.
     * @Author Yogesh Sonik
     * @param direction the direction in which the mouse label should be moved in response to the key input detected by the KeyPressed method.
     * @return void
     * */
    public void moveMouse(String direction) {
        setOrgCheeseAp();
        try {
            //Perform checks in each case to see whether the new spot is a valid movement(no barriers)
            if (direction == "left") {
                countSteps++;
                //Check to see if the new location is a barrier
                if (!levelMap[myMouse.getCurrentY()][myMouse.getCurrentX() - 1].getisBarrier()) {

                    //If the new location is not a barrier, perform checks to see whether there was any cheese there.
                    if (levelMap[myMouse.getCurrentY()][myMouse.getCurrentX() - 1].getisCheese()) {

                        //if the location had cheese, update score
                        this.myMouse.setCheeseEaten(this.myMouse.getCheeseEaten() + 1);
                        score += cheese1.value;
                        scoreText.setText("Score: " + score);
                        count++;
                        this.repaint();

                        //Remove cheese label for the cheese that the mouse ate
                        if (myMouse.getCurrentX() - 1 == cheese1.getY() && myMouse.getCurrentY() == cheese1.getX()) {
                            Cheese1.setVisible(false);
                            this.repaint();
                        } else {
                            Cheese2.setVisible(false);
                            this.repaint();
                        }
                    }
                    //If there wasn't cheese check to see if there was organic cheese
                    else if (levelMap[myMouse.getCurrentY()][myMouse.getCurrentX() - 1].getIsOrganicCheese()) {

                        //If there was organic cheese, update score and remove the corresponding jLabel
                        if(countSteps < rand2 || countSteps > rand3)
                        {
                            score += 0;
                        }
                        else
                        {
                            score += orgCheese1.value;
                            scoreText.setText("Score: " + score);
                            organic2.setVisible(false);
                            this.repaint();
                        }
                    }

                    //If there isn't any type of reward, check if there was a mousetrap
                    else if (levelMap[myMouse.getCurrentY()][myMouse.getCurrentX() - 1].getisMouseTrap() == true) {

                        //Subtract penalty from score and check if the score is negative
                        score -= trap1.getPenalty();
                        scoreText.setText("Score: " + score);

                        if(scoreBelowZero()){
                            inGame = false;
                            gameOver();
                        }
                        //Remove trap label
                        trapLabel2.setVisible(false);
                        this.repaint();
                    }

                    //Update the position of the mouse label, the mouse tile, and mouse object
                    levelMap[myMouse.getCurrentY()][myMouse.getCurrentX()] = new Tile(false, false, false, false, false, true,false,false,false);
                    myMouse.setCurrentX(myMouse.getCurrentX() - 1);
                    levelMap[myMouse.getCurrentY()][myMouse.getCurrentX()] = new Tile(false, false, false, false, true, false,false,false,false);
                    mouseLabel.setLocation(mouseLabel.getX() - 100, mouseLabel.getY());

                }

            } else if (direction == "right") {
                countSteps++;
                if (!levelMap[myMouse.getCurrentY()][myMouse.getCurrentX() + 1].getisBarrier()) {

                    //If the new location is not a barrier, perform checks to see whether there was any cheese there.
                    if (levelMap[myMouse.getCurrentY()][myMouse.getCurrentX() + 1].getisCheese()) {

                        //if the location had cheese, update score
                        this.myMouse.setCheeseEaten(this.myMouse.getCheeseEaten() + 1);
                        score += cheese1.value;
                        scoreText.setText("Score: " + score);
                        count++;
                        this.repaint();

                        //Remove cheese label for the cheese that the mouse ate
                        if (myMouse.getCurrentX() + 1 == cheese1.getY() && myMouse.getCurrentY() == cheese1.getX()) {
                            Cheese1.setVisible(false);
                            this.repaint();
                        } else {
                            Cheese2.setVisible(false);
                            this.repaint();
                        }
                    }

                    //If there wasn't cheese check to see if there was organic cheese
                    else if (levelMap[myMouse.getCurrentY()][myMouse.getCurrentX() + 1].getIsOrganicCheese()) {

                        //If there was organic cheese, update score and remove the corresponding jLabel
                        if (countSteps < rand2 || countSteps > rand3) {
                            score += 0;
                        } else {
                            if (myMouse.getCurrentX() + 1 == orgCheese2.getY() && myMouse.getCurrentY() == orgCheese2.getX()) {
                                score += orgCheese1.value;
                                scoreText.setText("Score: " + score);
                                organic2.setVisible(false);
                                this.repaint();
                            }
                        }

                        if (countSteps < rand1 || countSteps > rand4) {
                            score += 0;
                        } else {
                            if (myMouse.getCurrentX() + 1 == orgCheese1.getY() && myMouse.getCurrentY() == orgCheese1.getX()) {
                                score += orgCheese1.value;
                                scoreText.setText("Score: " + score);
                                organic1.setVisible(false);
                                this.repaint();
                            }
                        }
                    }

                    //If there isn't any type of reward, check if there was a mousetrap
                    else if (levelMap[myMouse.getCurrentY()][myMouse.getCurrentX() + 1].getisMouseTrap() == true) {

                        //Subtract penalty from score and check if the score is negative
                        score -= trap1.getPenalty();
                        scoreText.setText("Score: " + score);

                        if (scoreBelowZero()) {
                            inGame = false;
                            gameOver();
                        }
                        //Remove trap label
                        if (myMouse.getCurrentX() + 1 == trap1.getCurrentY() && myMouse.getCurrentY() == trap1.getCurrentX()) {
                            trapLabel1.setVisible(false);
                            this.repaint();
                        } else {
                            trapLabel2.setVisible(false);
                            this.repaint();
                        }

                    }
                    //Update the position of the mouse label, the mouse tile, and mouse object
                    levelMap[myMouse.getCurrentY()][myMouse.getCurrentX()] = new Tile(false, false, false, false, false, true, false, false, false);
                    myMouse.setCurrentX(myMouse.getCurrentX() + 1);
                    levelMap[myMouse.getCurrentY()][myMouse.getCurrentX()] = new Tile(false, false, false, false, true, false, false, false, false);
                    mouseLabel.setLocation(mouseLabel.getX() + 100, mouseLabel.getY());

                }
            }

            else if (direction == "up") {
                countSteps++;

                //If the new location is not a barrier, perform checks to see whether there was any cheese there.
                if (!levelMap[myMouse.getCurrentY() - 1][myMouse.getCurrentX()].getisBarrier()) {

                    //if the location had cheese, update score
                    if (levelMap[myMouse.getCurrentY() - 1][myMouse.getCurrentX()].getisCheese()) {

                        this.myMouse.setCheeseEaten(this.myMouse.getCheeseEaten() + 1);
                        score += cheese1.value;
                        scoreText.setText("Score: " + score);
                        count++;
                        this.repaint();

                        //Remove cheese label for the cheese that the mouse ate
                        if (myMouse.getCurrentY() - 1 == cheese2.getX() && myMouse.getCurrentX() == cheese2.getY()) {
                            Cheese2.setVisible(false);
                            this.repaint();
                        }
                    }
                    //If there wasn't cheese check to see if there was organic cheese
                    else if (levelMap[myMouse.getCurrentY() - 1][myMouse.getCurrentX()].getIsOrganicCheese()) {

                        //If there was organic cheese, update score and remove the corresponding jLabel
                        if (countSteps < rand1 || countSteps > rand4) {
                            score += 0;
                        }
                        else {
                            score += orgCheese2.value;
                            scoreText.setText("Score: " + score);
                            organic1.setVisible(false);
                            this.repaint();
                        }
                    }

                    ///If there isn't any type of reward, check if there was a mousetrap
                    else if (levelMap[myMouse.getCurrentY() - 1][myMouse.getCurrentX()].getisMouseTrap() == true) {

                        //Subtract penalty from score and check if the score is negative
                        score -= trap1.getPenalty();
                        scoreText.setText("Score: " + score);

                        if (scoreBelowZero()) {
                            inGame = false;
                            gameOver();
                        }

                        //Remove trap label
                        trapLabel1.setVisible(false);
                        this.repaint();


                    }
                    //Update the position of the mouse label, the mouse tile, and mouse object
                    levelMap[myMouse.getCurrentY()][myMouse.getCurrentX()] = new Tile(false, false, false, false, false, true, false, false,  false);
                    myMouse.setCurrentY(myMouse.getCurrentY() - 1);
                    levelMap[myMouse.getCurrentY()][myMouse.getCurrentX()] = new Tile(false, false, false, false, true, false, false, false,  false);
                    mouseLabel.setLocation(mouseLabel.getX(), mouseLabel.getY() - 100);

                }

            } else if (direction == "down") {
                countSteps++;
                if (!levelMap[myMouse.getCurrentY() + 1][myMouse.getCurrentX()].getisBarrier()) {

                    //If the new location is not a barrier, perform checks to see whether there was any cheese there.
                    if (levelMap[myMouse.getCurrentY() + 1][myMouse.getCurrentX()].getisCheese()) {

                        //if the location had cheese, update score
                        this.myMouse.setCheeseEaten(this.myMouse.getCheeseEaten() + 1);
                        score += cheese1.value;
                        scoreText.setText("Score: " + score);
                        count++;
                        this.repaint();

                        //Remove cheese label for the cheese that the mouse ate
                        if (myMouse.getCurrentY() + 1 == cheese1.getX() && myMouse.getCurrentX() == cheese1.getY()) {
                            Cheese1.setVisible(false);
                            this.repaint();
                        } else {
                            Cheese2.setVisible(false);
                            this.repaint();
                        }
                    }

                    //Mouse position update plus trap check
                    else if (levelMap[myMouse.getCurrentY() + 1][myMouse.getCurrentX()].getisMouseTrap() == true) {
                        score -= trap2.getPenalty();
                        if (scoreBelowZero()) {
                            inGame = false;
                            gameOver();
                        }
                        //Remove Labels
                        trapLabel2.setVisible(false);
                        this.repaint();
                    }

                    //Update the position of the mouse label, the mouse tile, and mouse object
                    levelMap[myMouse.getCurrentY()][myMouse.getCurrentX()] = new Tile(false, false, false, false, false, true, false, false,  false);
                    myMouse.setCurrentY(myMouse.getCurrentY() + 1);
                    levelMap[myMouse.getCurrentY()][myMouse.getCurrentX()] = new Tile(false, false, false, false, true, false, false, false,  false);
                    mouseLabel.setLocation(mouseLabel.getX(), mouseLabel.getY() + 100);

                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Not move");
        }

        // In response to the movement by the mouse, move the cats
        int direction1 = cat1.chase(myMouse.getCurrentX(), myMouse.getCurrentY(), levelMap);
        int direction2 = cat2.chase(myMouse.getCurrentX(), myMouse.getCurrentY(), levelMap);
        catMoveDraw(direction1, direction2);

        //Collision detection using Catlabels
        if (Catscollide()){
            gameOver();
        }

        if(this.myMouse.getCheeseEaten() == 2 && this.myMouse.getCurrentY() == 1 && this.myMouse.getCurrentX() == 0) {
            winGame = true;
        }

        if(winGame == true) {
            winPage();
        }
    }

        public void setOrgCheeseAp ()
        {
            if (countSteps == rand1) {
                organic1.setVisible(true);
            }

            if (countSteps == rand2) {
                organic2.setVisible(true);
            }

            if (countSteps == rand4) {
                organic1.setVisible(false);
            }

            if (countSteps == rand3) {
                organic2.setVisible(false);
            }
        }

    public int getRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
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
