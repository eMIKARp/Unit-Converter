package pkg000_unitconverter;

    import javax.swing.*;
    import java.awt.*;
    import java.awt.event.*;

public class Main extends JFrame 
{
    public static int sWIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
    public static int sHEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
            
    private JPanel mPanel = new JPanel();   //main panel
    private JPanel nPanel = new JPanel();   //north panel
    private JPanel cPanel = new JPanel();   //center panel
    private JPanel sPanel = new JPanel();   //south panel
    private JPanel ePanel = new JPanel();   //east panel
    private JPanel wPanel = new JPanel();   //west panel
    
    public Main()
    {
        this.setTitle("Unit Converter");
        this.setBounds((sWIDTH - 600)/ 2, (sHEIGHT-400) / 2, 600, 400);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  
        this.getContentPane().add(mPanel);
    }
    
    public static void main(String[] args) 
    {
        new Main().setVisible(true);
    }
    
}
