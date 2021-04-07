import java.awt.*;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * <h1> MouseTrap </h1>
 * The MouseTrap program sets and gets the coordinates
 * for the stationary enemies, and has a penalty used to reduce player's score. 
 * <p>
 * @author Scott Luu  
 *
 */
public class MouseTrap extends Enemies {
    private int penalty;
    private BufferedImage MTImage;
    public JLabel trapLabel;
    
    MouseTrap(int currentX, int currentY){
        super(currentX,currentY);
        this.penalty = 6; 
        // Get image for the traps
        try {
            Path MTPath = Paths.get("Images/mousetrap.jpg").toRealPath();;
            this.MTImage = ImageIO.read(new File(MTPath.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        createTrapLabel();
    }
    /**
     * This is the void function, it will create mouse trap label
     * */
    public void createTrapLabel()
    {
        trapLabel = new JLabel("Trap");
        trapLabel.setBounds(this.currentY*100,(this.currentX +1)*100,100,100);
        trapLabel.setBackground(Color.pink);
        trapLabel.setOpaque(true);
        Image trapImg1 = this.getMTImage().getScaledInstance(trapLabel.getWidth(), trapLabel.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon icon1 = new ImageIcon(trapImg1);
        trapLabel.setIcon(icon1);
    }
    
    //getPenalty method used to obtain the penalty value
    public int getPenalty(){
        return penalty;
    }

    public BufferedImage getMTImage () {
        return MTImage;
    }
}
