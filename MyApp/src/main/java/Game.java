import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;
import java.util.Random;

/**<h1>Game Class<h1/>
 * This is the aggregate class which brings together all of the various pieces of CheeseRun. It is here that all of the objects are instantiated through various functions and
 * where their visual representations are created and manipulated.
 *
 *  @author Yogesh Sonik, Tian Yang Zhou, Scott Luu, and Canh Nhat Minh Le
 *  @version 2.0
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
    public boolean testing;

    public Boolean getWinGame() {
        return winGame;
    }

    public void setRand1(int rand1) {
        this.rand1 = rand1;
    }

    public Boolean getInGame() {
        return inGame;
    }

    public void setInGame(Boolean inGame) {
        this.inGame = inGame;
    }

    public Mouse getMyMouse() {
        return myMouse;
    }

    public Cheese getCheese1() {
        return cheese1;
    }

    public Cheese getCheese2() {
        return cheese2;
    }

    public OrganicCheese getOrgCheese1() {
        return orgCheese1;
    }

    public OrganicCheese getOrgCheese2() {
        return orgCheese2;
    }

    public MouseTrap getTrap2() {
        return trap2;
    }

    public MouseTrap getTrap1() {
        return trap1;
    }

    public Cat getCat2() {
        return cat2;
    }

    public Cat getCat1() {
        return cat1;
    }

    public LevelOne getMyMap() {
        return myMap;
    }

    public int getScore() {
        return score;
    }

    public void setRand2(int rand2) {
        this.rand2 = rand2;
    }

    public void setCountSteps(int steps) {
        this.countSteps = steps;
    }

    public void setScore(int score) {
        this.score = score;
    }

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
     * */

    Game()
    {
        testing = false;

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
        myMouse = new Mouse(8,8);//coords set to 8,8
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
     * */
    public void winPage() {

            ImageIcon testIcon = new ImageIcon("Images/win.png");
            Object[] option = {"Play Again"};
            int test = JOptionPane.showOptionDialog(null, "Total Score: " + score + "\n" +
                            "Time: " + hours_string + ":" + minutes_string + ":" + seconds_string, " Win",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                    testIcon, option, option[0]);
            if (test == -1) {
                System.exit(ABORT);
            } else {
                this.setVisible(false);
                new Game();
            }
    }

    /**
     * This is the boolean function, it will check score below zero or not
     * */
    public boolean scoreBelowZero(){
        return score < 0;
    }

    /**
     * This is the void function, it will set gameover
     * */
    public void gameOver() {

            ImageIcon testIcon = new ImageIcon("Images/MouseLose.jpg");
            Object[] option = {"Play Again"};
            int test = JOptionPane.showOptionDialog(null, "Total Score: " + score + "\n" +
                            "Time: " + hours_string + ":" + minutes_string + ":" + seconds_string, " GAME OVER",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                    testIcon, option, option[0]);
            if (test == -1) {
                System.exit(ABORT);
            } else {
                this.setVisible(false);
                new Game();
            }

	}

    /**
     * This is the boolean function, it will check cat's collision
     * */
    public boolean Catscollide(){
        return ((cat1.catLabel.getX() == myMouse.mouseLabel.getX() && cat1.catLabel.getY() == myMouse.mouseLabel.getY()) ||
                (cat2.catLabel.getX() == myMouse.mouseLabel.getX() && cat2.catLabel.getY() == myMouse.mouseLabel.getY()));
    }

     /**
     * This is the void function, it will create and draw cat label
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
                //undo cat2's movement if colliding with cat1
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
                if(isaBarrier(i, j) == true ) {
                    tempLabel = new JLabel();
                    tempLabel.setBounds(x,y,100,100);
                    tempLabel.setBackground(Color.red);
                    tempLabel.setOpaque(true);
                }
                else if(isaCheese(i, j) == true)
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
     * @param direction the direction in which the mouse label should be moved in response to the key input detected by the KeyPressed method.
     * */
    public void moveMouse(String direction) {
        //Setup Organic Cheese as mouse starts to move
        setOrgCheeseAp();
        try {
            //Perform checks in each case to see whether the new spot is a valid movement(no barriers)
            if (direction == "left") {

                //If the new location is a barrier:
                if (isaBarrier(myMouse.getCurrentY(), myMouse.getCurrentX() - 1)) {
                    MoveCats();
                }

                //If the new location a cheese.
                else if (isaCheese(myMouse.getCurrentY(), myMouse.getCurrentX() - 1)) {
                    eatCheese(myMouse.getCurrentX(), cheese1.getY(), myMouse.getCurrentY(), cheese1.getX());

                }

                //If the new location is an organic cheese
                else if (isOrganicCheese(myMouse.getCurrentY(), myMouse.getCurrentX() - 1)) {
                    score += orgCheese2.value;
                    scoreText.setText("Score: " + score);
                    orgCheese2.organicLabel.setVisible(false);
                    this.repaint();
                    myMap.getLevelMap()[1][3].setIsOrganicCheese(false);
                }

                //If the new location is a mousetrap
                else if (isaMouseTrap(myMouse.getCurrentY(), myMouse.getCurrentX() - 1)) {

                    //Subtract penalty from score and check if the score is negative
                    score -= trap1.getPenalty();
                    scoreText.setText("Score: " + score);

                    if(scoreBelowZero()){
                        UpdateMouseCoordX(myMouse.getCurrentX() - 1, myMouse.mouseLabel.getX() - 100);
                        inGame = false;
                        if(!testing)
                        {
                            gameOver();
                        }
                    }
                    //Remove trap label
                    trap2.trapLabel.setVisible(false);
                    this.repaint();
                }

                //Update the position of the mouse and cat positions,given that the new location was not a barrier
                if(!isaBarrier(myMouse.getCurrentY(), myMouse.getCurrentX() - 1) && !scoreBelowZero()) {

                    UpdateMouseCoordX(myMouse.getCurrentX() - 1, myMouse.mouseLabel.getX() - 100);
                    MoveCats();
                }
            }
            //Right
            else if (direction == "right") {

                //If the new location is a barrier:
                if (isaBarrier(myMouse.getCurrentY(), myMouse.getCurrentX() + 1)) {
                    MoveCats();
                }

                //If the new location is a cheese.
                else if (isaCheese(myMouse.getCurrentY(), myMouse.getCurrentX() + 1)) {

                    //update score
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

                //If the new location is an organic cheese
                else if (isOrganicCheese(myMouse.getCurrentY(), myMouse.getCurrentX() + 1)) {
                    score += orgCheese2.value;
                    scoreText.setText("Score: " + score);
                    orgCheese2.organicLabel.setVisible(false);
                    this.repaint();
                    myMap.getLevelMap()[1][3].setIsOrganicCheese(false);
                }

                //If the new location is a mousetrap
                else if (isaMouseTrap(myMouse.getCurrentY(), myMouse.getCurrentX() + 1)) {

                    //Subtract penalty from score and check if the score is negative
                    score -= trap1.getPenalty();
                    scoreText.setText("Score: " + score);

                    if (scoreBelowZero()) {
                        UpdateMouseCoordX(myMouse.getCurrentX() + 1, myMouse.mouseLabel.getX() + 100);
                        inGame = false;
                        if(!testing)
                        {
                            gameOver();
                        }
                    }
                    //Remove trap label
                    if(myMouse.getCurrentX() + 1 == trap1.getCurrentY() && myMouse.getCurrentY() == trap1.getCurrentX()) {
                        trap1.trapLabel.setVisible(false);
                        this.repaint();
                    } else {
                        trap2.trapLabel.setVisible(false);
                        this.repaint();
                    }
                }

                //Update the position of the mouse and cat positions,given that the new location was not a barrier
                if(!isaBarrier(myMouse.getCurrentY(), myMouse.getCurrentX() + 1) && !scoreBelowZero()) {

                    UpdateMouseCoordX(myMouse.getCurrentX() + 1, myMouse.mouseLabel.getX() + 100);
                    MoveCats();
                }
            }
            //UP
            else if (direction == "up") {

                //If the new location is a barrier:
                if (isaBarrier(myMouse.getCurrentY() - 1, myMouse.getCurrentX())) {
                    MoveCats();
                }

                //if the new location is a cheese
                else if (isaCheese(myMouse.getCurrentY() - 1, myMouse.getCurrentX())) {

                    //update score
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

                //If the new location is an organic cheese
                else if (isOrganicCheese(myMouse.getCurrentY() - 1, myMouse.getCurrentX())) {
                    orgCheese1.setValue(10);
                    score += orgCheese1.value;
                    scoreText.setText("Score: " + score);
                    orgCheese1.organicLabel.setVisible(false);
                    this.repaint();
                    myMap.getLevelMap()[7][3].setIsOrganicCheese(false);
                }

                //If the new location is a mousetrap
                else if (isaMouseTrap(myMouse.getCurrentY() - 1, myMouse.getCurrentX())) {

                    //Subtract penalty from score and check if the score is negative
                    score -= trap1.getPenalty();
                    scoreText.setText("Score: " + score);

                    if (scoreBelowZero()) {
                        UpdateMouseCoordY(myMouse.getCurrentY() - 1, myMouse.mouseLabel.getY() - 100);
                        inGame = false;
                        if(!testing)
                        {
                            gameOver();
                        }

                    }

                    //Remove trap label
                    trap1.trapLabel.setVisible(false);
                    this.repaint();
                }

                //Update the position of the mouse and cat positions,given that the new location was not a barrier
                if(!isaBarrier(myMouse.getCurrentY() - 1, myMouse.getCurrentX()) && !scoreBelowZero()) {

                    UpdateMouseCoordY(myMouse.getCurrentY() - 1, myMouse.mouseLabel.getY() - 100);
                    MoveCats();
                }
            }
            //DOWN
            else if (direction == "down") {

                //If the new location is a barrier:
                if (isaBarrier(myMouse.getCurrentY() + 1, myMouse.getCurrentX())) {
                    MoveCats();
                }

                //If the new location is a cheese
                else if (isaCheese(myMouse.getCurrentY() + 1, myMouse.getCurrentX())) {

                    //update score
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

                //If the new location is an organic cheese
                else if (isOrganicCheese(myMouse.getCurrentY()+1, myMouse.getCurrentX())) {
                    orgCheese1.setValue(10);
                    score += orgCheese1.value;
                    scoreText.setText("Score: " + score);
                    orgCheese1.organicLabel.setVisible(false);
                    this.repaint();
                    myMap.getLevelMap()[7][3].setIsOrganicCheese(false);
                }

                //If the new location is a mousetrap
                else if (isaMouseTrap(myMouse.getCurrentY() + 1, myMouse.getCurrentX())) {
                    score -= trap2.getPenalty();
                    if (scoreBelowZero()) {
                        UpdateMouseCoordY(myMouse.getCurrentY() + 1, myMouse.mouseLabel.getY() + 100);
                        inGame = false;
                        if(!testing)
                        {
                            gameOver();
                        }
                    }
                    //Remove trap label
                    trap2.trapLabel.setVisible(false);
                    this.repaint();
                }

                //Update the position of the mouse and cat positions,given that the new location was not a barrier
                if(!isaBarrier(myMouse.getCurrentY() + 1, myMouse.getCurrentX()) && !scoreBelowZero()) {

                    UpdateMouseCoordY(myMouse.getCurrentY() + 1, myMouse.mouseLabel.getY() + 100);
                    MoveCats();
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Not move");
        }

        countSteps++;

        if(this.myMouse.getCheeseEaten() == 2 && this.myMouse.getCurrentY() == 1 && this.myMouse.getCurrentX() == 0) {

            inGame = false;
            winGame = true;
        }

        if(winGame == true) {
            inGame = false;
            if(!testing) {
                winPage();
            }
        }
    }

    private void eatCheese(int currentX, int y, int currentY, int x) {
        //update score
        this.myMouse.setCheeseEaten(this.myMouse.getCheeseEaten() + 1);
        score += cheese1.value;
        scoreText.setText("Score: " + score);
        count++;
        this.repaint();

        //Remove cheese label for the cheese that the mouse ate
        if (currentX - 1 == y && currentY == x) {
            cheese1.cheeseLabel.setVisible(false);
            this.repaint();
        } else {
            cheese2.cheeseLabel.setVisible(false);
            this.repaint();
        }
    }

    /**
 * This void function handles updating the levelmap's encoding of the mouse's position in addition to updating the mouse's jLabel when the mouse is moved up or down
 * @param yCoord the int by which we want to change the mouse's y coordinate both on the levelmap and the mouse object
 * @param yPixel the int by which we want to change the mouse label's y position measured int pixels
 */
    private void UpdateMouseCoordY(int yCoord, int yPixel) {
        myMap.getLevelMap()[myMouse.getCurrentY()][myMouse.getCurrentX()] = new Tile(false, false, false, false, true, false, false, false);
        myMouse.setCurrentY(yCoord);
        myMap.getLevelMap()[myMouse.getCurrentY()][myMouse.getCurrentX()] = new Tile(false, false, false, true, false, false, false, false);
        myMouse.mouseLabel.setLocation(myMouse.mouseLabel.getX(), yPixel);
    }

    /**
     * This void function handles updating the levelmap's encoding of the mouse's position in addition to updating the mouse's jLabel when the mouse is moved left or right
     * @param xCoord the int by which we want to change the mouse's x coordinate both on the levelmap and the mouse object
     * @param xPixel the int by which we want to change the mouse label's x position measured int pixels
     */
    private void UpdateMouseCoordX(int xCoord, int xPixel) {
        myMap.getLevelMap()[myMouse.getCurrentY()][myMouse.getCurrentX()] = new Tile(false, false, false, false, true, false, false, false);
        myMouse.setCurrentX(xCoord);
        myMap.getLevelMap()[myMouse.getCurrentY()][myMouse.getCurrentX()] = new Tile(false, false, false, true, false, false, false, false);
        myMouse.mouseLabel.setLocation(xPixel, myMouse.mouseLabel.getY());
    }

    /**
     * This function checks to see if the position passed has a barrier or not within the levelmap.
     * @param x the x coordinate at which to check
     * @param y the y coordinate at which to check
     * @return  the boolean result of the check condition
     */
    private boolean isaBarrier(int y, int x) {
        return myMap.getLevelMap()[y][x].getisBarrier();
    }

    /**
     * This function checks to see if the position passed has a mouse trap or not within the levelmap.
     * @param x the
     * @param y the
     * @return  the boolean result of the check condition
     */
    private boolean isaMouseTrap(int y, int x) {
        return myMap.getLevelMap()[y][x].getisMouseTrap();
    }

    /**
     * This function checks to see if the position passed has a Qrganic Cheese or not within the levelmap.
     * @param x the x coordinate at which to check
     * @param y the y coordinate at which to check
     * @return  the boolean result of the check condition
     */
    private boolean isOrganicCheese(int y, int x) {
        return myMap.getLevelMap()[y][x].getIsOrganicCheese();
    }

    /**
     * This function checks to see if the position passed has a Cheese or not within the levelmap.
     * @param x the x coordinate at which to check
     * @param y the y coordinate at which to check
     * @return  the boolean result of the check condition
     */
    private boolean isaCheese(int y, int x) {
        return myMap.getLevelMap()[y][x].getisCheese();
    }

    /**
     * This void function calls the chase function on both cats to update their position within their objects and then calls the catMovedraw function which updates the cat labels according to the new coordinates.
     * It also calls the Catscollide function which checks whether either of the cats have collided with the mouse to end the game.
     */
    private void MoveCats() {
        int direction1 = cat1.chase(myMouse.getCurrentX(), myMouse.getCurrentY(), myMap.getLevelMap());
        int direction2 = cat2.chase(myMouse.getCurrentX(), myMouse.getCurrentY(), myMap.getLevelMap());
        catMoveDraw(direction1, direction2);
        //Collision detection using Catlabels
        if (Catscollide()){
            inGame = false;
            if(!testing) {
                gameOver();
            }
        }
    }

    /**
     * This void function setup the position of OrganicCheese on the tile map
     * Randomize the appearance time and disappearance time of organic cheese
     */
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
     */
    public int getRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    /**
     * override method
     * */
    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * override method
     * */
    @Override
    public void keyReleased(KeyEvent e) {
    }

    /**
     * This is the void function that belongs to the keyListener interface which monitors for keyboard input and calls move mouse accordingly.
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
