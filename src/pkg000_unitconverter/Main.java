package pkg000_unitconverter;

    import javax.swing.*;
    import java.awt.*;
    import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
    import java.util.ArrayList;
    import java.util.HashMap;
    import java.util.LinkedList;
    import java.util.Map;
    import javax.swing.event.ListSelectionEvent;
    import javax.swing.event.ListSelectionListener;
    import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private DefaultListModel<String> iUnitChooserModel = new DefaultListModel<String>();
    private DefaultListModel<String> oUnitChooserModel = new DefaultListModel<String>();
    private JList<String> iUnitChooser = new JList<String>(iUnitChooserModel);  // input unit chooser
    private JList<String> oUnitChooser = new JList<String>(oUnitChooserModel);  // output unit chooser
    private JScrollPane iUnitChooser_Scroll = new JScrollPane(iUnitChooser,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    private JScrollPane oUnitChooser_Scroll = new JScrollPane(oUnitChooser,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    
    private JTextField cArgument = new JTextField(); // conversion argument
    private JTextField cResult = new JTextField();   // conversion result

    private Map<String, Category> cMatrix = new HashMap<String,Category>();
    
    private Category cLenght = new Category("Lenght", new String[]{"Millimeter","Centimeter","Meter","Kilometer", "Inch", "Foot", "Yard","Mile"}); 
    private Category cVolume = new Category("Volume", new String[]{"Cubic meter","Cubic kilometer","Cubic centimeter","Cubic millimeter","Liter","Milliliter","Cubic mile", "Cubic yard","Cubic foot", "Cubic inch"}); 
    private Category cArea = new Category("Area", new String[]{"Square meter","Square kilometer","Square centimeter","Square millimeter","Hectare","Square mile","Square yard","Square foot","Square inch", "Acre"}); 
    private Category cTemperature = new Category ("Temperature", new String[]{"Celsius", "Kelvin", "Farenheit"});
    private Category cWeight = new Category ("Weight", new String[]{"Kilogram","Gram","Milligram","Metric ton","Long ton","Short ton","Pound","Ounce","Carrat","Atomic mass unit"});
    private Category cTime = new Category ("Time", new String[]{"Second","Millisecond","Microsecond","Nanosecond","Picosecond","Minute","Hour","Day","Week","Month","Year"});
    
    
    
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
                sPanel_LeftVerticalPart.setPreferredSize(new Dimension(300,50));
                sPanel_LeftVerticalPart.add(cArgument);
                sPanel_LeftVerticalPart.add(Box.createRigidArea(new Dimension(0,fHEIGHT * 1/100)));
                sPanel_LeftVerticalPart.add(iResultUnit);
                sPanel.add(Box.createRigidArea(new Dimension(fWIDTH * 1/10, 0)));
                Box sPanel_RightVerticalPart = Box.createVerticalBox();
                sPanel.add(sPanel_RightVerticalPart);
                cResult.setPreferredSize(new Dimension(0, fHEIGHT / 14));
                sPanel_RightVerticalPart.setPreferredSize(new Dimension(300, 50));
                sPanel_RightVerticalPart.add(cResult);
                sPanel_RightVerticalPart.add(Box.createRigidArea(new Dimension(0,fHEIGHT * 1/100)));
                sPanel_RightVerticalPart.add(oResultUnit);
                sPanel.add(Box.createRigidArea(new Dimension(fWIDTH * 1/100,0)));

    /**
     *  Creating listener for category chooser
     */
        sUnitCategory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                if (((JComboBox)e.getSource()).getSelectedItem()== "Lenght") 
                {
                    iUnitChooserModel.clear();
                    oUnitChooserModel.clear();
                    for (String s: cMatrix.get("Lenght").cUnits)
                    {
                    iUnitChooserModel.addElement(s);
                    oUnitChooserModel.addElement(s);
                    }
                }
                else if (((JComboBox)e.getSource()).getSelectedItem()== "Volume") 
                {
                    iUnitChooserModel.clear();
                    oUnitChooserModel.clear();
                    for (String s: cMatrix.get("Volume").cUnits)
                    {
                    iUnitChooserModel.addElement(s);
                    oUnitChooserModel.addElement(s);
                    }        }
                else if (((JComboBox)e.getSource()).getSelectedItem()== "Area") 
                {
                    iUnitChooserModel.clear();
                    oUnitChooserModel.clear();
                    for (String s: cMatrix.get("Area").cUnits)
                    {
                    iUnitChooserModel.addElement(s);
                    oUnitChooserModel.addElement(s);
                    }
                }
                else if (((JComboBox)e.getSource()).getSelectedItem()== "Temperature") 
                {
                    iUnitChooserModel.clear();
                    oUnitChooserModel.clear();
                    for (String s: cMatrix.get("Temperature").cUnits)
                    {
                    iUnitChooserModel.addElement(s);
                    oUnitChooserModel.addElement(s);
                    }
                }
                else if (((JComboBox)e.getSource()).getSelectedItem()== "Weight") 
                {
                    iUnitChooserModel.clear();
                    oUnitChooserModel.clear();
                    for (String s: cMatrix.get("Weight").cUnits)
                    {
                    iUnitChooserModel.addElement(s);
                    oUnitChooserModel.addElement(s);
                    }
                }
                else if (((JComboBox)e.getSource()).getSelectedItem()== "Time") 
                {
                    iUnitChooserModel.clear();
                    oUnitChooserModel.clear();
                    for (String s: cMatrix.get("Time").cUnits)
                    {
                    iUnitChooserModel.addElement(s);
                    oUnitChooserModel.addElement(s);
                    }
                }
            }
        });
        
        iUnitChooser.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) 
            {
                String tmp = (String)((JList)e.getSource()).getSelectedValue();
                iResultUnit.setText(tmp);
                //cArgument.setText(cMatrix.get(tmp));
            }
        });
        
        oUnitChooser.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) 
            {
                oResultUnit.setText((String)((JList)e.getSource()).getSelectedValue());
            }
        });
        
        cMatrix.put("Lenght", cLenght); 
        cMatrix.put("Volume", cVolume); 
        cMatrix.put("Area", cArea); 
        cMatrix.put("Temperature", cTemperature);
        cMatrix.put("Weight", cWeight);
        cMatrix.put("Time", cTime);
    
        sUnitCategory.setSelectedIndex(0);
        iUnitChooser.setSelectedIndex(0);
        oUnitChooser.setSelectedIndex(0);
        
        cMatrix.get("Temperature").crm.addConversionRatesToMatrix("Temperature", 3);
        cMatrix.get("Area").crm.addConversionRatesToMatrix("Area", 10);
        cMatrix.get("Lenght").crm.addConversionRatesToMatrix("Lenght", 8);
        cMatrix.get("Time").crm.addConversionRatesToMatrix("Time", 11);
        cMatrix.get("Volume").crm.addConversionRatesToMatrix("Volume", 10);
        cMatrix.get("Weight").crm.addConversionRatesToMatrix("Weight", 10);
        
    }
    
    public static void main(String[] args) 
    {
       new Main().setVisible(true);
    }
    
class Category
{
    private String cName;
    private String[] cUnits;
    private ConversionRatesMatrix crm;
    
    public Category(String cName, String[] cUnits)
    {
        this.cName = cName;
        this.cUnits = cUnits;
        sUnitCategory.addItem(cName);
        crm = new ConversionRatesMatrix(cName, cUnits);
        
    }
    
    public String getName()
    {
        return cName;
    }
    
}

class ConversionRatesMatrix
{
    private Map<String, double[]> crm = new HashMap<String, double[]>();
    private double[] cu; 
    
    public ConversionRatesMatrix(String cName, String[] cUnits)
    {
        
        for (String s: cUnits)
        {
            cu = new double[cUnits.length];
            crm.put(s, cu);
        }
    }
    
    public double getConversionRate(String cName, int index)
    {
        return crm.get(cName)[index];
    }
    
    public void addConversionRatesToMatrix(String cName, int nOfUnits)
    {
        try {
            
            File file = new File(cName+".txt");
            Scanner input = new Scanner (file);
            
            for (int i = 0; i < nOfUnits; i ++)
            {
                for (int j = 0; j < nOfUnits; j ++)
                {
                    cu[j] = Double.parseDouble(input.nextLine());
                }
            }
            
            input.close();
            
        } 
        catch (FileNotFoundException ex) 
        {
            ex.getMessage();
        }
    }
    
}

}



    