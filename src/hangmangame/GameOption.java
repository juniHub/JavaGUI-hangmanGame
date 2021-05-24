package hangmangame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class GameOption extends JFrame {

    private final JLabel gameOptionLabel;
    private final JButton humanButton;
    private final JButton computerButton;
    private final Color color = new Color(0x00b3b3);
    private final Font font = new Font("Monospaced",Font.BOLD,20);
    private final Container pane;
    private final JPanel panel;
    private final JLabel manualLabel;
    private JScrollPane scrollPane;
    private final Border border = BorderFactory.createLineBorder(new Color(0xFF5733),5);
    
    public GameOption(){
  
        pane = getContentPane();
        pane.setLayout(new BorderLayout());
        pane.setBackground(new Color(0x154360));
        gameOptionLabel = new JLabel("CHOOSE A GAME MODE BELOW: ");
        gameOptionLabel.setFont(font);
        gameOptionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gameOptionLabel.setForeground(Color.CYAN);
        pane.add(gameOptionLabel,BorderLayout.NORTH);
        
        manualLabel = new JLabel();
        manualLabel.setBorder(border);
        manualLabel.setForeground(Color.BLUE);
        scrollPane = new JScrollPane();
             
        panel = new JPanel();
        panel.setLayout(new GridLayout(2,1,30,30));
        panel.setBorder(new EmptyBorder(new Insets(30,30,30,30)));
        panel.setBackground(new Color(0x154360));
   
        humanButton = new JButton("<html><img src='https://cdn2.iconfinder.com/data/icons/fatcow/32x32/user_bart.png'>Play with a Friend</html>");
        
        computerButton = new JButton("<html><img src='https://cdn2.iconfinder.com/data/icons/vivid/48/controller-48.png'>Play with Your Computer</html>");
        humanButton.setFont(font);
        humanButton.setBackground(color);
        humanButton.setForeground(Color.YELLOW);
        computerButton.setFont(font);
        computerButton.setBackground(color);
        computerButton.setForeground(Color.BLUE);
       
        panel.add(humanButton);
        humanButton.addActionListener((ActionEvent event) -> {
            HumanPlay humanPlayers = new HumanPlay();
        
         });

        panel.add(computerButton);
        computerButton.addActionListener((ActionEvent event) -> {
            
            ComputerPlay computerPlayer = new ComputerPlay();
            
        
         });
        
        pane.add(panel,BorderLayout.CENTER);
      
       JMenuBar menuBar = new JMenuBar();
       setJMenuBar(menuBar);
       menuBar.setBackground(new Color(0x154360));
       menuBar.setForeground(Color.BLUE);

     
      JMenu homeMenu = new JMenu("HOME");
      homeMenu.setForeground(new Color(0xFF5733));
      JMenu helpMenu = new JMenu("HELP");
      helpMenu.setForeground(new Color(0xFF5733));
  
      menuBar.add(homeMenu);
      menuBar.add(helpMenu);
      
      JMenuItem playGame = new JMenuItem("PLAY");
     
      JMenuItem exitItem = new JMenuItem("EXIT");
      exitItem.addActionListener((ActionEvent event) -> {
          System.exit(0);
      });
      
      JMenuItem aboutItem = new JMenuItem("ABOUT");
      JMenuItem helpItem = new JMenuItem("HELP/USER MANUAL");
      helpMenu.add(aboutItem);
      helpMenu.addSeparator();
      helpMenu.add(helpItem);
    
      homeMenu.add(playGame);
      homeMenu.addSeparator();
      homeMenu.add(exitItem);
      
      playGame.addActionListener((ActionEvent event) -> {

          gameOptionLabel.setText("CHOOSE A GAME MODE BELOW: ");
          panel.setVisible(true);
          scrollPane.setVisible(false);
         
      });
 
      helpItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
       
                panel.setVisible(false);
                gameOptionLabel.setText("HELP/USER MANUAL");
                
                String text = "<html>"
                        +"1. Game Language: English"
                        + "<br><br>2. Game Options: "
                        + "<ul><li>Play with your friends.</li>"
                        + "<li>Play with your computer.</li></ul>"
                        + "<br> 3. How To Play: "
                        + "<ul><li>Select a game mode to play.</li>"
                        + "<li>Friend Mode: you or your friend enter a word for the other to guess.</li>"
                        + "<li>Computer Mode: select one item on the dropdown list to play."
                        + "<br>Your computer will choose random word for you to guess.</li>"
                        + "<li>After 6 guesses, you will lose if you can't find the correct word.</li></ul>"
                        + "<br> 4. Home Menu Bar: "
                        + "<ul><li>Select PLAY to go to main window.</li>"
                        + "<li>Select EXIT to exit.</li></ul>"
                        + "</html>";
                
                manualLabel.setText(text);
                manualLabel.setFont(font);
                manualLabel.setVisible(true);
                
                pane.add(manualLabel,BorderLayout.CENTER);
                scrollPane = new JScrollPane(manualLabel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                scrollPane.setViewportBorder( BorderFactory.createLineBorder(Color.WHITE,5));
                pane.add(scrollPane,BorderLayout.CENTER);
            }
        });
  
     
      aboutItem.addActionListener((ActionEvent event) -> {
        
          JOptionPane.showMessageDialog(null, "Author: Thuy Tien Nguyen - Hangman Game (Version 1)", "About", JOptionPane.INFORMATION_MESSAGE);
         
          
      });
   
    }

}
