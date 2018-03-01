import javax.swing.* ;
import javax.swing.event.* ;
import javax.swing.border.LineBorder ;
import java.awt.* ;
import java.awt.event.ActionEvent ;
import java.awt.event.ActionListener ;

class Gui implements ActionListener {
  JFrame frame ;
  JPanel [] panel ; //p0, p1, p2, p3, p4, p5, p6, p7, p8, p9 ;
  final int PANELS = 10;
  JSlider s0 ;
  JButton [] button ; //b0, b1, b2, b3 ;
  final int BUTTONS = 4;

  Gui() {
    frame = new JFrame("Logistics Arena GuiTest") ;
    frame.setLayout(new GridBagLayout());
    
    panel = new JPanel[PANELS] ;
    for (int i = 0; i < PANELS; i++){
      panel[i] = new JPanel() ;
      panel[i].setBorder(new LineBorder(Color.BLACK));
    }
    
    s0 = new JSlider(1, 4, 2) ; // (int min, int max, int value)
    
    button = new JButton[BUTTONS] ;
    for (int i = 0; i < BUTTONS; i++){
      button[i] = new JButton("Click!") ;
      button[i].addActionListener (this) ;
    }
    
    //CONSTRAINTS
    GridBagConstraints c = new GridBagConstraints(); //RESET after every use!!
    
    //CONFIGURE FRAME
    frame.setSize(800,600) ;
    frame.setVisible(true) ;
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
    
    
    //CONFIGURE SPEED SETTING
    panel[0].setLayout(new GridBagLayout());
    
    //Speed LABEL Constraints
    c = new GridBagConstraints();
    c.gridx = 0 ;
    c.gridy = 0 ;
    c.ipadx = 10 ;
    c.ipady = 20 ;
    c.anchor = GridBagConstraints.NORTH ;
    JLabel sLabel = new JLabel("Speed") ;
    sLabel.setFont(new Font("STFangsong",3,22)) ;    
    c.fill = GridBagConstraints.BOTH;
    panel[0].add(sLabel, c) ;
    
    //Speed SLIDER Constraints
    c = new GridBagConstraints();
    c.gridx = 0 ;
    c.gridy = 1 ;
    c.gridwidth = 3 ;
    c.ipady = 10 ;
    c.weightx = 1 ;
    s0.setFont(new Font("STFangsong",2,18)) ;
    s0.setMajorTickSpacing(1) ;
    s0.setPaintTicks(true) ;
    java.util.Hashtable<Integer,JLabel> labelTable = new java.util.Hashtable<Integer,JLabel>() ;
    labelTable.put(new Integer(1), new JLabel("0.5")) ;
    labelTable.put(new Integer(2), new JLabel("1")) ;
    labelTable.put(new Integer(3), new JLabel("1.5")) ;
    labelTable.put(new Integer(4), new JLabel("2")) ;
    s0.setLabelTable(labelTable) ;
    s0.setPaintLabels(true) ;
    panel[0].add(s0, c) ;
    
    //Add both to frame
    c = new GridBagConstraints();
    c.ipadx = 20 ;
    c.fill = GridBagConstraints.BOTH;
    frame.add(panel[0], c) ;
    panel[0].setBorder(new LineBorder(Color.BLACK, 2));
    
    
    
    //Clock
    c = new GridBagConstraints();
    c.gridx = 2 ;
    c.gridy = 0 ;
    c.ipadx = 10 ;
    c.anchor = GridBagConstraints.NORTH ;
    JLabel clock = new JLabel("00:01") ; //placeholder
    clock.setFont(new Font("STFangsong",2,48)) ;
    panel[1].add(clock) ;
    panel[1].setBorder(new LineBorder(Color.BLACK, 2)) ;
    c.fill = GridBagConstraints.BOTH ;
    frame.add(panel[1], c) ;
    
    
    //Map
    c = new GridBagConstraints();
    c.gridx = 0 ;
    c.gridy = 1 ;
    c.gridwidth = 3 ;
    //c.gridheight = 12 ; //komisch
    panel[2].setBorder(new LineBorder(Color.BLACK, 2)) ;
    c.fill = GridBagConstraints.BOTH ;
    frame.add(panel[2], c) ;
    
    //Inventory
    
    //Shotgun
    c = new GridBagConstraints();
    c.gridx = 3 ;
    c.gridy = 1 ;
    c.ipadx = 10 ;
    c.anchor = GridBagConstraints.NORTH ;
    JLabel shotgun = new JLabel("Shotgun") ; //placeholder
    shotgun.setFont(new Font("STFangsong",2,48)) ;
    panel[3].add(shotgun) ;
    panel[3].setBorder(new LineBorder(Color.BLACK, 2)) ;
    c.fill = GridBagConstraints.BOTH ;
    frame.add(panel[3], c) ;
    
    //LG
    c = new GridBagConstraints();
    c.gridx = 3 ;
    c.gridy = 2 ;
    c.ipadx = 10 ;
    c.anchor = GridBagConstraints.NORTH ;
    JLabel lg = new JLabel("Lightning") ; //placeholder
    lg.setFont(new Font("STFangsong",2,48)) ;
    panel[4].add(lg) ;
    panel[4].setBorder(new LineBorder(Color.BLACK, 2)) ;
    c.fill = GridBagConstraints.BOTH ;
    frame.add(panel[4], c) ;
    
    //Rail
    c = new GridBagConstraints();
    c.gridx = 3 ;
    c.gridy = 3 ;
    c.ipadx = 10 ;
    c.anchor = GridBagConstraints.NORTH ;
    JLabel rail = new JLabel("Rail") ; //placeholder
    rail.setFont(new Font("STFangsong",2,48)) ;
    panel[5].add(rail) ;
    panel[5].setBorder(new LineBorder(Color.BLACK, 2)) ;
    c.fill = GridBagConstraints.BOTH ;
    frame.add(panel[5], c) ;
    
    //Armor
    c = new GridBagConstraints();
    c.gridx = 3 ;
    c.gridy = 4 ;
    c.ipadx = 10 ;
    c.anchor = GridBagConstraints.NORTH ;
    JLabel armor = new JLabel("Armor") ; //placeholder
    armor.setFont(new Font("STFangsong",2,48)) ;
    panel[6].add(armor) ;
    panel[6].setBorder(new LineBorder(Color.BLACK, 2)) ;
    c.fill = GridBagConstraints.BOTH ;
    frame.add(panel[6], c) ;
    
    //Health
    c = new GridBagConstraints();
    c.gridx = 3 ;
    c.gridy = 5 ;
    c.ipadx = 10 ;
    c.anchor = GridBagConstraints.NORTH ;
    JLabel health = new JLabel("Health") ; //placeholder
    health.setFont(new Font("STFangsong",2,48)) ;
    panel[7].add(health) ;
    panel[7].setBorder(new LineBorder(Color.BLACK, 2)) ;
    c.fill = GridBagConstraints.BOTH ;
    frame.add(panel[7], c) ;
    
    
    //CONFIGURE BUTTONS
    c = new GridBagConstraints();
    
    
  }

  public void actionPerformed (ActionEvent ae) {
    System.out.println("clicked") ;
  }

}

public class GuiTest {
  /*final static boolean shouldFill = true;
  final static boolean shouldWeightX = true;
  final static boolean RIGHT_TO_LEFT = false;*/
    
  public static void main(String [] args) {
    Gui g = new Gui() ;
  }
}




