package main.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame{
    public static void main(String[] args) {
        //Game game = new Game();
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
                f.dispose();
                Game myGame = new Game();
            }
        });
    }
}
