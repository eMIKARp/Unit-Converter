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
    
    private JLabel iUnit = new JLabel("                  Input Unit");
    private JLabel oUnit = new JLabel("                  Output Unit");
    private JLabel iResultUnit = new JLabel("Input Result Unit");
    private JLabel oResultUnit = new JLabel("Output Result Unit");
    
    private JComboBox<String> sUnitCategory = new JComboBox<String>(); // unit category chooser
    private JTextArea iUnitChooser = new JTextArea();  // input unit chooser
    private JTextArea oUnitChooser = new JTextArea();  // output unit chooser
    private JScrollPane iUnitChooser_Scroll = new JScrollPane(iUnitChooser,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    private JScrollPane oUnitChooser_Scroll = new JScrollPane(oUnitChooser,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    
    private JTextField cArgument = new JTextField(); // conversion argument
    private JTextField cResult = new JTextField();   // conversion result
    
    private Category Length = new Category("Lenght", new String[]{"Meter","Kilometer","Centimeter","Millimeter","Nanometer","Mile","Yard","Foot","Inch"}); 
            
    public Main()
    {
        
    /**
     *  Building a main window and its components
     */
        
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
                nPanel.add(Box.createRigidArea(new Dimension(fWIDTH * 1/100, 0)));
                nPanel.add(sUnitCategory);
                    sUnitCategory.setPreferredSize(new Dimension(0,fHEIGHT / 14));
                nPanel.add(Box.createRigidArea(new Dimension(fWIDTH * 55/100, 0)));
            
            mPanel.add(cPanel, BorderLayout.CENTER);
                cPanel.setBorder(BorderFactory.createTitledBorder("Select Unit"));
                cPanel.setLayout(new BoxLayout(cPanel, BoxLayout.LINE_AXIS));
                    cPanel.add(Box.createRigidArea(new Dimension(fWIDTH * 1/100,0)));
                    Box cPanel_LeftVerticalPart = Box.createVerticalBox();
                    cPanel.add(cPanel_LeftVerticalPart);
                    cPanel_LeftVerticalPart.add(iUnit);
                    cPanel_LeftVerticalPart.add(Box.createRigidArea(new Dimension(0,fHEIGHT * 1/100)));
                    cPanel_LeftVerticalPart.add(iUnitChooser_Scroll);
                    cPanel.add(Box.createRigidArea(new Dimension(fWIDTH * 1/10, 0)));
                    Box cPanel_RightVerticalPart = Box.createVerticalBox();
                    cPanel.add(cPanel_RightVerticalPart);
                    cPanel_RightVerticalPart.add(oUnit);
                    cPanel_RightVerticalPart.add(Box.createRigidArea(new Dimension(0,fHEIGHT * 1/100)));
                    cPanel_RightVerticalPart.add(oUnitChooser_Scroll);
                    cPanel.add(Box.createRigidArea(new Dimension(fWIDTH * 1/100,0)));
                    
            mPanel.add(sPanel, BorderLayout.SOUTH);
            
                sPanel.setBorder(BorderFactory.createTitledBorder("Conversion results"));
                sPanel.setLayout(new BoxLayout(sPanel, BoxLayout.LINE_AXIS));
                sPanel.add(Box.createRigidArea(new Dimension(fWIDTH * 1/100,0)));
                Box sPanel_LeftVerticalPart = Box.createVerticalBox();
                sPanel.add(sPanel_LeftVerticalPart);
                cArgument.setPreferredSize(new Dimension(0, fHEIGHT/ 14));
                sPanel_LeftVerticalPart.add(cArgument);
                sPanel_LeftVerticalPart.add(Box.createRigidArea(new Dimension(0,fHEIGHT * 1/100)));
                sPanel_LeftVerticalPart.add(iResultUnit);
                sPanel.add(Box.createRigidArea(new Dimension(fWIDTH * 1/10, 0)));
                Box sPanel_RightVerticalPart = Box.createVerticalBox();
                sPanel.add(sPanel_RightVerticalPart);
                cResult.setPreferredSize(new Dimension(0, fHEIGHT / 14));
                sPanel_RightVerticalPart.add(cResult);
                sPanel_RightVerticalPart.add(Box.createRigidArea(new Dimension(0,fHEIGHT * 1/100)));
                sPanel_RightVerticalPart.add(oResultUnit);
                sPanel.add(Box.createRigidArea(new Dimension(fWIDTH * 1/100,0)));

    /**
     *  Building a main window and its components
     */
                
//        this.addUnitCategory("");
//        this.addUnitCategory("Lenght");
//        this.addUnitCategory("Temperature");
//        this.addUnitCategory("Area");
//        this.addUnitCategory("Weight");
//        this.addUnitCategory("Time");

    }
    
    public static void main(String[] args) 
    {
        new Main().setVisible(true);
    }
    
}

class Category
{
    String cName;
    String[] cUnits;
    
    public Category(String cName, String[] cUnits)
    {
        this.cName = cName;
        this.cUnits = cUnits;
    }
    
    public String getName()
    {
        return cName;
    }
    
    public void getUnits()
    {
        for (int i = 0; i < cUnits.length; i++)
            System.out.println(cUnits[i]);
    }
}

class Unit
{
    String uName;
}