import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game extends JFrame implements KeyListener {

    public Tile[][] levelMap;
    Mouse myMouse;
    JLabel mouseLabel;
    ImageIcon mouseIcon;


    Game()
    {
        //Create the window
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000,1000);
        this.setLayout(null);
        this.addKeyListener(this);
        //Display the label
        mouseLabel = new JLabel();
        mouseLabel.setBounds(0,0,100,100);
        mouseLabel.setBackground(Color.red);
        mouseLabel.setOpaque(true);
        this.getContentPane().setBackground(Color.BLUE);
        this.add(mouseLabel);
        this.setVisible(true);





        //Create a Mouse
        myMouse = new Mouse();


        //Set the outer boundaries of tile map:
        levelMap = new Tile[10][10];
        for(int i = 0; i < 10; i++)
        {
            for(int j = 0; j< 10; j++)
            {
                levelMap[i][j] = new Tile(false,true,false,false,false, false);
            }
        }
        //Left column
        for(int i = 0; i< 10; i++)
        {
            levelMap[i][0].setBarrier(true);
            levelMap[i][0].setReward(false);

        }

        //Right Column
        for(int i = 0; i < 10; i++)
        {
            levelMap[i][9].setBarrier(true);
            levelMap[i][9].setReward(false);
        }

        //Top row-leaving opening for exit
        for(int i = 0; i<10;i++)
        {
            levelMap[0][i].setBarrier(true);
            levelMap[0][i].setReward(false);
        }
        levelMap[0][1] = new Tile(false,false,false,true,false,false);

        //Bottom row-leaving opening for entrance
        for(int i = 0; i < 10;i++)
        {
            levelMap[9][i].setBarrier(true);
            levelMap[9][i].setReward(false);
        }
        levelMap[9][8] = new Tile(false,false,true,false,false,false);

        // Place Mouse at Entrance
        levelMap[8][8] = new Tile(false,false,false,false,true,false);


    }

    public void moveMouse(String direction)
    {

        //Perform checks in each case to see whether the new spot is a valid movement(no barriers)
        if(direction == "left")
        {
            //if valid update the tile map
            levelMap[myMouse.getCurrentY()][myMouse.getCurrentX()] = new Tile(false,false,false,false,false,true);
            myMouse.setCurrentX( myMouse.getCurrentX() -1);
            levelMap[myMouse.getCurrentY()][myMouse.getCurrentX()] = new Tile(false,false,false,false,true,false);
        }
        else if(direction == "right")
        {
            //if valid update the tile map
            levelMap[myMouse.getCurrentY()][myMouse.getCurrentX()] = new Tile(false,false,false,false,false,true);
            myMouse.setCurrentX( myMouse.getCurrentX() +1);
            levelMap[myMouse.getCurrentY()][myMouse.getCurrentX()] = new Tile(false,false,false,false,true,false);
        }
        else if(direction == "up")
        {
            //if valid update the tile map
            levelMap[myMouse.getCurrentY()][myMouse.getCurrentX()] = new Tile(false,false,false,false,false,true);
            myMouse.setCurrentY( myMouse.getCurrentY() -1);
            levelMap[myMouse.getCurrentY()][myMouse.getCurrentX()] = new Tile(false,false,false,false,true,false);
        }
        else if(direction == "down")
        {
            //if valid update the tile map
            levelMap[myMouse.getCurrentY()][myMouse.getCurrentX()] = new Tile(false,false,false,false,false,true);
            myMouse.setCurrentY( myMouse.getCurrentY() +1);

            levelMap[myMouse.getCurrentY()][myMouse.getCurrentX()] = new Tile(false,false,false,false,true,false);
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
                else if(levelMap[i][j].getisEmpty() == true)
                {
                    System.out.print("   ");
                }
                else{
                    System.out.print("   ");
                }
            }
            System.out.print("\n");
        }
        System.out.print("\n");


    }


    public static void main(String[] args) {
        //initial static View
        Game myGame = new Game();

        for(int i = 0; i< 10; i++)
        {
            for(int j = 0; j <10; j++)
            {
                if(myGame.levelMap[i][j].getisBarrier() == true ) {
                    System.out.print("[ ]");
                }
                else if(myGame.levelMap[i][j].getisReward() == true)
                {
                    System.out.print(" * ");
                }
                else if(myGame.levelMap[i][j].getIsCharacter() == true)
                {
                    System.out.print(" M ");
                }
                else{
                    System.out.print("   ");
                }
            }
            System.out.print("\n");
        }


    }






    @Override
    public void keyTyped(KeyEvent e) {
        switch(e.getKeyChar())
        {
            case 'a': moveMouse("left");
                break;
            case 'd': moveMouse("right");
                break;
            case 's': moveMouse("down");
                break;
            case 'w': moveMouse("up");
                break;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode())
        {
            case 37: mouseLabel.setLocation(mouseLabel.getX()-100, mouseLabel.getY());
                break;
            case 39: mouseLabel.setLocation(mouseLabel.getX()+100, mouseLabel.getY());
                break;
            case 40: mouseLabel.setLocation(mouseLabel.getX(), mouseLabel.getY()+100);
                break;
            case 38 : mouseLabel.setLocation(mouseLabel.getX(), mouseLabel.getY()-100);
                break;
//            case 37: moveMouse("left");
//                break;
//            case 39: moveMouse("right");
//                break;
//            case 40: moveMouse("down");
//                break;
//            case 38 : moveMouse("up");
//                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
