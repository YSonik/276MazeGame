import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * <h1> Main </h1>
 * The Main program runs the UI and initials the game
 * <p>
 * @author TianYang Zhou  
 *
 */


public class Main extends JFrame{
    public static void main(String[] args) {
        JFrame f = new JFrame("CheeseRun");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(500,500);
        ImageIcon cheeseRunImageIcon = new ImageIcon("Images/cheeserun.png");
        JLabel cheeseRunLabel = new JLabel(cheeseRunImageIcon);
        JButton button = new JButton("Play");
        JPanel panel = new JPanel();
        panel.add(cheeseRunLabel);
        panel.add(button);
        f.getContentPane().add(panel);
        f.setResizable(false);
        f.getContentPane().setBackground(Color.WHITE);
        f.setVisible(true);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // dispose the old frame
                    f.dispose();
                    // initial the new frame
                    Game myGame = new Game(false);
                }
            });
    }
}
