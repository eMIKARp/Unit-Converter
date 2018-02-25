package pkg000_unitconverter;

    import javax.swing.*;
    import java.awt.*;
    import java.awt.event.*;

    /**
      * Simple program which helps to convert amounts in one unint 
      * into amount in other unit.
      * @author Emil Karpowicz
      * @version 1.0
      * @since version 1.0
    */


public class Main extends JFrame 
{
    public static int sWIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
    public static int sHEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
    
    public static final int fWIDTH = 600;
    public static final int fHEIGHT = 400;
            
    private JPanel mPanel = new JPanel();   //main panel
    private JPanel nPanel = new JPanel();   //north panel
    private JPanel cPanel = new JPanel();   //center panel
    private JPanel sPanel = new JPanel();   //south panel
    
    private JMenuBar mMenuBar = new JMenuBar(); //Main menu bar
    private JMenu mFile = new JMenu("File"); // File menu
    private JMenu mEdit = new JMenu("Edit"); // Edit menu
    private JMenu mHelp = new JMenu("Help"); // Help menu
    
    private JLabel sCategory = new JLabel("Select Category");
    private JLabel sUnit = new JLabel("Select Unit");
    private JLabel iUnit = new JLabel("Input");
    private JLabel oUnit = new JLabel("Output");
    private JLabel sResult = new JLabel("Result");
    
    private JComboBox<String> sUnitCategory = new JComboBox<String>(); // unit category chooser
    private JTextArea iUnitArea = new JTextArea();  // input unit chooser
    private JTextArea oUnitArea = new JTextArea();  // output unit chooser
    
    private JTextField cArgument = new JTextField(); // conversion argument
    private JTextField cResult = new JTextField();   // conversion result
    
    
    public Main()
    {
        this.setTitle("Unit Converter");
        this.setBounds((sWIDTH - 600)/ 2, (sHEIGHT-400) / 2, fWIDTH, fHEIGHT);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
        this.getContentPane().add(mMenuBar, BorderLayout.NORTH);
            
            mMenuBar.add(mFile);
            mMenuBar.add(mEdit);
            mMenuBar.add(mHelp);

        mPanel.setLayout(new BorderLayout());    
        this.getContentPane().add(mPanel);
        
            mPanel.add(nPanel, BorderLayout.NORTH);
                nPanel.setBorder(BorderFactory.createTitledBorder("Select Category"));
                nPanel.setLayout(new BoxLayout(nPanel, BoxLayout.LINE_AXIS));
                nPanel.add(Box.createHorizontalGlue());
                nPanel.add(sUnitCategory);
                    sUnitCategory.setPreferredSize(new Dimension(fWIDTH / 3,fHEIGHT / 14));
                nPanel.add(Box.createRigidArea(new Dimension(fWIDTH - sUnitCategory.getSize().width -  220, 0)));
                    this.addUnitCategory("");
                    this.addUnitCategory("Lenght");
                    this.addUnitCategory("Temperature");
                    this.addUnitCategory("Area");
                    this.addUnitCategory("Weight");
                    this.addUnitCategory("Time");

            mPanel.add(cPanel, BorderLayout.CENTER);
                cPanel.setBorder(BorderFactory.createTitledBorder("Select Unit"));
            mPanel.add(sPanel, BorderLayout.SOUTH);
                sPanel.setBorder(BorderFactory.createTitledBorder("Conversion results"));
            

            
    }
    
    public static void main(String[] args) 
    {
        new Main().setVisible(true);
    }
    
    public void addUnitCategory(String cName)
    {
        sUnitCategory.addItem(cName);
    }
}
