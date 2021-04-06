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
 *  @author Yogesh Sonik, Tian Yang Zhou, Scott Luu, and Canh Nhat Minh Le
 * */
public class Game extends JFrame implements KeyListener {

    private final static int ONE_SECOND = 1000;
    private Timer timer;
    private Boolean inGame;
    private Boolean winGame;
    private int score;

    private LevelOne myMap;
    private Mouse myMouse;
    private Cheese cheese1;
    private Cheese cheese2;
    private OrganicCheese orgCheese1,orgCheese2;
    private Cat cat1;
    private Cat cat2;
    private MouseTrap trap1, trap2;

    private JLabel scoreText;
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
     * @author Yogesh Sonik
     * */

    Game()
    {
        //Create a TileMap Object
        myMap = new LevelOne();

        // Create multiple cats
        cat1 = new Cat(4, 1);
        this.add(cat1.catLabel);
        this.validate();

        cat2 = new Cat(8, 4);
        this.add(cat2.catLabel);
        this.validate();

        //Create a Mouse
        myMouse = new Mouse();//coords set to 8,8
        this.add(myMouse.mouseLabel);
        this.validate();

        //set score
        score = 0;

        //Create Cheese
        cheese1 = new Cheese(6, 7, 5);
        this.add(cheese1.cheeseLabel);
        this.validate();
        cheese2 = new Cheese(4, 3, 5);
        this.add(cheese2.cheeseLabel);
        this.validate();

        //Create Organic Cheese
        orgCheese1 = new OrganicCheese(7,3,10);
        this.add(orgCheese1.organicLabel);
        this.validate();
        orgCheese2 = new OrganicCheese(1,3,10);
        this.add(orgCheese2.organicLabel);
        this.validate();


        //randomizing appearance of Organic Cheese
         rand1 = getRandomNumber(4,10);
         rand2 = getRandomNumber(40,45);
         rand3 = getRandomNumber(55,60);
         rand4 = getRandomNumber(25,40);


        // Create mousetraps
        trap1 = new MouseTrap(3,6);
        this.add(trap1.trapLabel);
        this.validate();
        trap2 = new MouseTrap(4,4);
        this.add(trap2.trapLabel);
        this.validate();

        scoreBelowZero();
        createFrame();
        createGameMap();
        createScoreText();

        if (count == 0){scoreText.setText("Score: " + 0);}

        inGame = true;
        winGame = false;
        createTimerLabel();

        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //If inGame == true, continue to update label
                if(inGame) {
                    updateTimerLabel();
                }
            }
        };
        //Execute taskPerformer every 1000ms, which is 1s
        Timer timer = new Timer(1000, taskPerformer);
        timer.start();
    }

    /**
     * This is void function draws the timer label
     * @author Canh Nhat Minh Le
     * */
    public void createTimerLabel(){
        timeLabel.setText(hours_string+":"+minutes_string+":"+seconds_string);
        timeLabel.setBounds(200,40,400,70 );
        timeLabel.setFont(new Font("Moon",Font.PLAIN, 20));
        this.add(timeLabel);
        this.validate();
    }

    /**
     * This void function updates each elements of the stopwatch and update Timer Label accordingly
     * Update Timer Label accordingly
     * @author Canh Nhat Minh Le
     * */
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

    /**
     * This is the void function, it will draw win page
     * @author Tianyang Zhou
     * */
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

    /**
     * This is the boolean function, it will check score below zero or not
     * @author Scott Luu
     * */
    public boolean scoreBelowZero(){
        return score < 0;
    }

    /**
     * This is the void function, it will set gameover
     * @author Scott Luu
     * */
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

    /**
     * This is the boolean function, it will check cat's collision
     * @author Scott Luu
     * */
    public boolean Catscollide(){
        return ((cat1.catLabel.getX() == myMouse.mouseLabel.getX() && cat1.catLabel.getY() == myMouse.mouseLabel.getY()) ||
                (cat2.catLabel.getX() == myMouse.mouseLabel.getX() && cat2.catLabel.getY() == myMouse.mouseLabel.getY()));

    }

     /**
     * This is the void function, it will create and draw cat label
     * @author Tianyang Zhou, Scott Luu
     * */
    public void catMoveDraw(int direction1, int direction2) {
        //Cat Position update
        switch (direction1) {
            case 1:
                cat1.catLabel.setLocation(cat1.catLabel.getX() + 100, cat1.catLabel.getY());
                break;
            case 2:
                cat1.catLabel.setLocation(cat1.catLabel.getX() - 100, cat1.catLabel.getY());
                break;
            case 3:
                cat1.catLabel.setLocation(cat1.catLabel.getX(), cat1.catLabel.getY() + 100);
                break;
            case 4:
                cat1.catLabel.setLocation(cat1.catLabel.getX(), cat1.catLabel.getY() - 100);
                break;
            default:
                break;
        }
        switch (direction2) {
            case 1:
                cat2.catLabel.setLocation(cat2.catLabel.getX() + 100, cat2.catLabel.getY());
                if((cat1.catLabel.getX() == cat2.catLabel.getX()) && (cat1.catLabel.getY() == cat2.catLabel.getY())){
                   cat2.setCurrentX(cat2.getCurrentX()-1);
                   cat2.catLabel.setLocation(cat2.catLabel.getX() - 100, cat2.catLabel.getY());
                }
                break;
            case 2:
                cat2.catLabel.setLocation(cat2.catLabel.getX() - 100, cat2.catLabel.getY());
                if((cat1.catLabel.getX() == cat2.catLabel.getX()) && (cat1.catLabel.getY() == cat2.catLabel.getY())){
                   cat2.setCurrentX(cat2.getCurrentX()+1);
                   cat2.catLabel.setLocation(cat2.catLabel.getX() + 100, cat2.catLabel.getY());
                }
                break;
            case 3:
                cat2.catLabel.setLocation(cat2.catLabel.getX(), cat2.catLabel.getY() + 100);
                if((cat1.catLabel.getX() == cat2.catLabel.getX()) && (cat1.catLabel.getY() == cat2.catLabel.getY())){
                    cat2.setCurrentY(cat2.getCurrentY()-1);
                    cat2.catLabel.setLocation(cat2.catLabel.getX(), cat2.catLabel.getY() - 100);
                }
                break;
            case 4:
                cat2.catLabel.setLocation(cat2.catLabel.getX(), cat2.catLabel.getY() - 100);
                if((cat1.catLabel.getX() == cat2.catLabel.getX()) && (cat1.catLabel.getY() == cat2.catLabel.getY())){
                    cat2.setCurrentY(cat2.getCurrentY()+1);
                    cat2.catLabel.setLocation(cat2.catLabel.getX(), cat2.catLabel.getY() + 100);
                }
                break;
            default:
                break;
        }

    }



    /**
     * Void function to locations setup and draw "Score" text
     * @author Canh Nhat Minh Le
     * */
    public void createScoreText()
    {
       scoreText = new JLabel();
       scoreText.setBounds(40,40,300,70);
       scoreText.setFont(new Font("Moon", Font.PLAIN, 20));
       this.add(scoreText);
       this.validate();
    }


   /**
    * This is the void function called by the Game constructor to create the jFrame that corresponds to the game page of CheeseRun where the maze will be displayed.
    * @author Yogesh Sonik
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
     * @author Yogesh Sonik,
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
                if(myMap.getLevelMap()[i][j].getisBarrier() == true ) {
                    tempLabel = new JLabel();
                    tempLabel.setBounds(x,y,100,100);
                    tempLabel.setBackground(Color.red);
                    tempLabel.setOpaque(true);
                }
                else if(myMap.getLevelMap()[i][j].getisCheese() == true)
                {
                    tempLabel = new JLabel();
                    tempLabel.setBounds(x,y,100,100);
                    tempLabel.setBackground(Color.white);
                    tempLabel.setOpaque(true);

                }
                else if(myMap.getLevelMap()[i][j].getisEmpty() == true)
                {
                    tempLabel = new JLabel();
                    tempLabel.setBounds(x,y,100,100);
                    tempLabel.setBackground(Color.white);
                    tempLabel.setOpaque(true);
                }
                else if(myMap.getLevelMap()[i][j].getisEntrance() == true)
                {
                    tempLabel = new JLabel("Entrance");
                    tempLabel.setBounds(x,y,100,100);
                    tempLabel.setBackground(Color.white);
                    tempLabel.setOpaque(true);
                }
                else if (myMap.getLevelMap()[i][j].getisExit() == true)
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
     * This is the void function called by the actionListener method called "KeyPressed" which recieves which direction to move the mouse label as a string and then preforms various checks to validate the movement
     * and then updates the map with regards to any changes that may have occurred.
     * @author Yogesh Sonik
     * @param direction the direction in which the mouse label should be moved in response to the key input detected by the KeyPressed method.
     * */
    public void moveMouse(String direction) {
        //Setup Organic Cheese as mouse starts to move
        setOrgCheeseAp();
        try {
            //Perform checks in each case to see whether the new spot is a valid movement(no barriers)
            if (direction == "left") {
                countSteps++;
                //Check to see if the new location is a barrier
                if (!myMap.getLevelMap()[myMouse.getCurrentY()][myMouse.getCurrentX() - 1].getisBarrier()) {

                    //If the new location is not a barrier, perform check to see whether there was any cheese there.
                    if (myMap.getLevelMap()[myMouse.getCurrentY()][myMouse.getCurrentX() - 1].getisCheese()) {

                        //if the location had cheese, update score
                        this.myMouse.setCheeseEaten(this.myMouse.getCheeseEaten() + 1);
                        score += cheese1.value;
                        scoreText.setText("Score: " + score);
                        count++;
                        this.repaint();

                        //Remove cheese label for the cheese that the mouse ate
                        if (myMouse.getCurrentX() - 1 == cheese1.getY() && myMouse.getCurrentY() == cheese1.getX()) {
                            cheese1.cheeseLabel.setVisible(false);
                            this.repaint();
                        } else {
                            cheese2.cheeseLabel.setVisible(false);
                            this.repaint();
                        }
                    }
                    //If there wasn't cheese check to see if there was organic cheese
                    if (myMap.getLevelMap()[myMouse.getCurrentY()][myMouse.getCurrentX() - 1].getIsOrganicCheese()) {
                            score += orgCheese2.value;
                            scoreText.setText("Score: " + score);
                            orgCheese2.organicLabel.setVisible(false);
                            this.repaint();
                            myMap.getLevelMap()[1][3].setIsOrganicCheese(false);

                    }


                    //If there isn't any type of reward, check if there was a mousetrap
                     if (myMap.getLevelMap()[myMouse.getCurrentY()][myMouse.getCurrentX() - 1].getisMouseTrap() == true) {

                        //Subtract penalty from score and check if the score is negative
                        score -= trap1.getPenalty();
                        scoreText.setText("Score: " + score);

                        if(scoreBelowZero()){
                            myMap.getLevelMap()[myMouse.getCurrentY()][myMouse.getCurrentX()] = new Tile( false, false, false, false, true,false,false,false);
                            myMouse.setCurrentX(myMouse.getCurrentX() - 1);
                            myMap.getLevelMap()[myMouse.getCurrentY()][myMouse.getCurrentX()] = new Tile( false, false, false, true, false,false,false,false);
                            myMouse.mouseLabel.setLocation(myMouse.mouseLabel.getX() - 100, myMouse.mouseLabel.getY());
                            inGame = false;
                            gameOver();
                        }
                        //Remove trap label
                        trap2.trapLabel.setVisible(false);
                        this.repaint();
                    }

                    //Update the position of the mouse label, the mouse tile, and mouse object
                    myMap.getLevelMap()[myMouse.getCurrentY()][myMouse.getCurrentX()] = new Tile( false, false, false, false, true,false,false,false);
                    myMouse.setCurrentX(myMouse.getCurrentX() - 1);
                    myMap.getLevelMap()[myMouse.getCurrentY()][myMouse.getCurrentX()] = new Tile( false, false, false, true, false,false,false,false);
                    myMouse.mouseLabel.setLocation(myMouse.mouseLabel.getX() - 100, myMouse.mouseLabel.getY());

                }

            } else if (direction == "right") {
                countSteps++;
                if (!myMap.getLevelMap()[myMouse.getCurrentY()][myMouse.getCurrentX() + 1].getisBarrier()) {

                    //If the new location is not a barrier, perform checks to see whether there was any cheese there.
                    if (myMap.getLevelMap()[myMouse.getCurrentY()][myMouse.getCurrentX() + 1].getisCheese()) {

                        //if the location had cheese, update score
                        this.myMouse.setCheeseEaten(this.myMouse.getCheeseEaten() + 1);
                        score += cheese1.value;
                        scoreText.setText("Score: " + score);
                        count++;
                        this.repaint();

                        //Remove cheese label for the cheese that the mouse ate
                        if (myMouse.getCurrentX() + 1 == cheese1.getY() && myMouse.getCurrentY() == cheese1.getX()) {
                            cheese1.cheeseLabel.setVisible(false);
                            this.repaint();
                        } else {
                            cheese2.cheeseLabel.setVisible(false);
                            this.repaint();
                        }
                    }

                    //If there wasn't cheese check to see if there was organic cheese
                    if (myMap.getLevelMap()[myMouse.getCurrentY()][myMouse.getCurrentX() + 1].getIsOrganicCheese()) {
                            score += orgCheese2.value;
                            scoreText.setText("Score: " + score);
                            orgCheese2.organicLabel.setVisible(false);
                            this.repaint();
                            myMap.getLevelMap()[1][3].setIsOrganicCheese(false);

                    }


                    //If there isn't any type of reward, check if there was a mousetrap
                    if (myMap.getLevelMap()[myMouse.getCurrentY()][myMouse.getCurrentX() + 1].getisMouseTrap() == true) {

                        //Subtract penalty from score and check if the score is negative
                        score -= trap1.getPenalty();
                        scoreText.setText("Score: " + score);

                        if (scoreBelowZero()) {
                            myMap.getLevelMap()[myMouse.getCurrentY()][myMouse.getCurrentX()] = new Tile( false, false, false, false, true, false, false, false);
                            myMouse.setCurrentX(myMouse.getCurrentX() + 1);
                            myMap.getLevelMap()[myMouse.getCurrentY()][myMouse.getCurrentX()] = new Tile( false, false, false, true, false, false, false, false);
                            myMouse.mouseLabel.setLocation(myMouse.mouseLabel.getX() + 100, myMouse.mouseLabel.getY());
                            inGame = false;
                            gameOver();
                        }
                        //Remove trap label
                        if (myMouse.getCurrentX() + 1 == trap1.getCurrentY() && myMouse.getCurrentY() == trap1.getCurrentX()) {
                            trap1.trapLabel.setVisible(false);
                            this.repaint();
                        } else {
                            trap2.trapLabel.setVisible(false);
                            this.repaint();
                        }

                    }
                    //Update the position of the mouse label, the mouse tile, and mouse object
                    myMap.getLevelMap()[myMouse.getCurrentY()][myMouse.getCurrentX()] = new Tile( false, false, false, false, true, false, false, false);
                    myMouse.setCurrentX(myMouse.getCurrentX() + 1);
                    myMap.getLevelMap()[myMouse.getCurrentY()][myMouse.getCurrentX()] = new Tile( false, false, false, true, false, false, false, false);
                    myMouse.mouseLabel.setLocation(myMouse.mouseLabel.getX() + 100, myMouse.mouseLabel.getY());

                }
            }

            else if (direction == "up") {
                countSteps++;
                //If the new location is not a barrier, perform checks to see whether there was any cheese there.
                if (!myMap.getLevelMap()[myMouse.getCurrentY() - 1][myMouse.getCurrentX()].getisBarrier()) {

                    //if the location had cheese, update score
                    if (myMap.getLevelMap()[myMouse.getCurrentY() - 1][myMouse.getCurrentX()].getisCheese()) {

                        this.myMouse.setCheeseEaten(this.myMouse.getCheeseEaten() + 1);
                        score += cheese1.value;
                        scoreText.setText("Score: " + score);
                        count++;
                        this.repaint();

                        //Remove cheese label for the cheese that the mouse ate
                        if (myMouse.getCurrentY() - 1 == cheese1.getX() && myMouse.getCurrentX() == cheese1.getY()) {
                            cheese1.cheeseLabel.setVisible(false);
                            this.repaint();
                        } else {
                            cheese2.cheeseLabel.setVisible(false);
                            this.repaint();
                        }
                    }

                    if (myMap.getLevelMap()[myMouse.getCurrentY() - 1][myMouse.getCurrentX()].getIsOrganicCheese()) {
                            orgCheese1.setValue(10);
                            score += orgCheese1.value;
                            scoreText.setText("Score: " + score);
                            orgCheese1.organicLabel.setVisible(false);
                            this.repaint();
                            myMap.getLevelMap()[7][3].setIsOrganicCheese(false);

                    }


                    //If there isn't any type of reward, check if there was a mousetrap
                    if (myMap.getLevelMap()[myMouse.getCurrentY() - 1][myMouse.getCurrentX()].getisMouseTrap() == true) {

                        //Subtract penalty from score and check if the score is negative
                        score -= trap1.getPenalty();
                        scoreText.setText("Score: " + score);

                        if (scoreBelowZero()) {
                            myMap.getLevelMap()[myMouse.getCurrentY()][myMouse.getCurrentX()] = new Tile( false, false, false, false, true, false, false,  false);
                            myMouse.setCurrentY(myMouse.getCurrentY() - 1);
                            myMap.getLevelMap()[myMouse.getCurrentY()][myMouse.getCurrentX()] = new Tile( false, false, false, true, false, false, false,  false);
                            myMouse.mouseLabel.setLocation(myMouse.mouseLabel.getX(), myMouse.mouseLabel.getY() - 100);
                            inGame = false;
                            gameOver();
                        }

                        //Remove trap label
                        trap1.trapLabel.setVisible(false);
                        this.repaint();


                    }
                    //Update the position of the mouse label, the mouse tile, and mouse object
                    myMap.getLevelMap()[myMouse.getCurrentY()][myMouse.getCurrentX()] = new Tile( false, false, false, false, true, false, false,  false);
                    myMouse.setCurrentY(myMouse.getCurrentY() - 1);
                    myMap.getLevelMap()[myMouse.getCurrentY()][myMouse.getCurrentX()] = new Tile( false, false, false, true, false, false, false,  false);
                    myMouse.mouseLabel.setLocation(myMouse.mouseLabel.getX(), myMouse.mouseLabel.getY() - 100);

                }

            } else if (direction == "down") {
                countSteps++;
                if (!myMap.getLevelMap()[myMouse.getCurrentY() + 1][myMouse.getCurrentX()].getisBarrier()) {

                    //If the new location is not a barrier, perform checks to see whether there was any cheese there.
                    if (myMap.getLevelMap()[myMouse.getCurrentY() + 1][myMouse.getCurrentX()].getisCheese()) {

                        //if the location had cheese, update score
                        this.myMouse.setCheeseEaten(this.myMouse.getCheeseEaten() + 1);
                        score += cheese1.value;
                        scoreText.setText("Score: " + score);
                        count++;
                        this.repaint();

                        //Remove cheese label for the cheese that the mouse ate
                        if (myMouse.getCurrentY() + 1 == cheese1.getX() && myMouse.getCurrentX() == cheese1.getY()) {
                            cheese1.cheeseLabel.setVisible(false);
                            this.repaint();
                        } else {
                            cheese2.cheeseLabel.setVisible(false);
                            this.repaint();
                        }
                    }

                    //Check to see if there was organic cheese
                    if (myMap.getLevelMap()[myMouse.getCurrentY()+1][myMouse.getCurrentX()].getIsOrganicCheese()) {
                            orgCheese1.setValue(10);
                            score += orgCheese1.value;
                            scoreText.setText("Score: " + score);
                            orgCheese1.organicLabel.setVisible(false);
                            this.repaint();
                            myMap.getLevelMap()[7][3].setIsOrganicCheese(false);

                    }


                    //If there isn't any type of reward, check if there was a mousetrap
                    if (myMap.getLevelMap()[myMouse.getCurrentY() + 1][myMouse.getCurrentX()].getisMouseTrap() == true) {
                        score -= trap2.getPenalty();
                        if (scoreBelowZero()) {
                            myMap.getLevelMap()[myMouse.getCurrentY()][myMouse.getCurrentX()] = new Tile( false, false, false, false, true, false, false,  false);
                            myMouse.setCurrentY(myMouse.getCurrentY() + 1);
                            myMap.getLevelMap()[myMouse.getCurrentY()][myMouse.getCurrentX()] = new Tile(false, false, false, true, false, false, false,  false);
                            myMouse.mouseLabel.setLocation(myMouse.mouseLabel.getX(), myMouse.mouseLabel.getY() + 100);
                            inGame = false;
                            gameOver();
                        }
                        //Remove trap label
                        trap2.trapLabel.setVisible(false);
                        this.repaint();
                    }

                    //Update the position of the mouse label, the mouse tile, and mouse object
                    myMap.getLevelMap()[myMouse.getCurrentY()][myMouse.getCurrentX()] = new Tile( false, false, false, false, true, false, false,  false);
                    myMouse.setCurrentY(myMouse.getCurrentY() + 1);
                    myMap.getLevelMap()[myMouse.getCurrentY()][myMouse.getCurrentX()] = new Tile(false, false, false, true, false, false, false,  false);
                    myMouse.mouseLabel.setLocation(myMouse.mouseLabel.getX(), myMouse.mouseLabel.getY() + 100);

                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Not move");
        }

        // In response to the movement by the mouse, move the cats
        int direction1 = cat1.chase(myMouse.getCurrentX(), myMouse.getCurrentY(), myMap.getLevelMap());
        int direction2 = cat2.chase(myMouse.getCurrentX(), myMouse.getCurrentY(), myMap.getLevelMap());
        catMoveDraw(direction1, direction2);

        //Collision detection using Catlabels
        if (Catscollide()){
            inGame = false;
            gameOver();
        }

        if(this.myMouse.getCheeseEaten() == 2 && this.myMouse.getCurrentY() == 1 && this.myMouse.getCurrentX() == 0) {
            inGame = false;
            winGame = true;
        }

        if(winGame == true) {
            inGame = false;
            winPage();
        }
    }

    /**
     * This void function setup the position of OrganicCheese on the tile map
     * Randomize the appearance time and disappearance time of organic cheese
     * @author Canh Nhat Minh Le
     * */
    public void setOrgCheeseAp ()
    {
        //Randomize the appearance of organic1  and make label and the OrganicCheese
        //appear when number of key counts == rand1
        if (countSteps == rand1) {
            orgCheese1.organicLabel.setVisible(true);
            myMap.getLevelMap()[7][3] = new Tile(false,false,false,false,false,false,true,false);

        }
        //Randomize the appearance of organic2 and make label and the OrganicCheese
        //appear when number of key counts == rand2
        if (countSteps == rand2) {
            //Set locations for organic Cheese
            myMap.getLevelMap()[1][3] = new Tile(false,false,false,false,false,false,true,false);
            orgCheese2.organicLabel.setVisible(true);
        }
        //Randomize the disappearance of organic2 and make labels and the
        //OrganicCheese disappear when number of key counts == rand3
        if (countSteps == rand3) {
            orgCheese2.organicLabel.setVisible(false);
            myMap.getLevelMap()[1][3].setIsOrganicCheese(false);
        }
        //Randomize the disappearance of organic1 and make the labels and the
        //OrganicCheese disappear when number of key counts == rand4
        if (countSteps == rand4) {
            orgCheese1.organicLabel.setVisible(false);
            myMap.getLevelMap()[7][3].setIsOrganicCheese(false);
        }
    }

    /**
     * This is the integer function, it will return random number
     * @author Tianyang Zhou
     * */
    public int getRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    /**
     * override method
     * @author Tianyang Zhou
     * */
    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * override method
     * @author Tianyang Zhou
     * */
    @Override
    public void keyReleased(KeyEvent e) {
    }

    /**
     * This is the void function that belongs to the keyListener interface which monitors for keyboard input and calls move mouse accordingly.
     * @author Yogesh Sonik
     * */

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
