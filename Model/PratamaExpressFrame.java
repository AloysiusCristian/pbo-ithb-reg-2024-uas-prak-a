package Model;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class PratamaExpressFrame extends JFrame {
    public PratamaExpressFrame(String title){
        innitComponent(title);
    }
    
    private void innitComponent(String title){
        ImageIcon logo = new ImageIcon("Photos/Pratama Express Logo.png");
        this.setIconImage(logo.getImage());
        this.setTitle(title);
    }
}
