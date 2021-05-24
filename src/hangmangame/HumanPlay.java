
package hangmangame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class HumanPlay extends JFrame {
  
    private final int WIDTH;
    private final int HEIGHT;
    private final int MAX_INCORRECT;
    private final int MAX_WORD_LENGTH;
    private final String HANGMAN_IMAGE_DIRECTORY;
    private final String HANGMAN_IMAGE_TYPE;
    private final String HANGMAN_IMAGE_BASE_NAME;
    private final String LETTER_IMAGE_DIRECTORY;
    private final String LETTER_IMAGE_TYPE;
   
    private LetterRack gameRack;
    private Hangman gameHangman;
    private int numIncorrect;
  
    private JLabel correct;
    private JLabel incorrect;
    private JLabel hint;
    private String guessedWord;
    private final Font font = new Font("Monospaced",Font.BOLD,24);
    private final Color color = new Color(0x00b3b3);
   
    private StringBuilder wordHidden;
    public boolean playAgain = false;
   
    public HumanPlay(){
    
        WIDTH = 800;
        HEIGHT = 600;
        MAX_INCORRECT = 6;
        MAX_WORD_LENGTH = 20;
       
        HANGMAN_IMAGE_DIRECTORY = LETTER_IMAGE_DIRECTORY = "images/";
        HANGMAN_IMAGE_TYPE = LETTER_IMAGE_TYPE = ".png";
        HANGMAN_IMAGE_BASE_NAME = "hangman";
        
        setTitle("Nguyen's Hangman Game");
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        addCloseWindowListener();
        initialize();
    }
   
    private void initialize(){
            
        numIncorrect = 0;
        guessedWord = new String();
        wordHidden = new StringBuilder();
        
        correct = new JLabel("Word: ");
        correct.setFont(font);
        correct.setForeground(Color.YELLOW);
        hint = new JLabel("Hint: " );
        hint.setForeground(Color.CYAN);
        hint.setFont(font);
       
        incorrect = new JLabel("Incorrect: " + numIncorrect);
        incorrect.setFont(font);
        incorrect.setForeground(new Color(0xFF5733));
         
        getWord();
        addTextPanel();
        addLetterRack();
        addHangman();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - getSize().width / 2,
                dim.height / 2 - getSize().height / 2 - 200);
        setVisible(true);
    }
    
   
    private void addCloseWindowListener(){
  
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter(){
        
            @Override
            public void windowClosing(WindowEvent we){
                 int prompt = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to quit?",
                        "Quit?", 
                        JOptionPane.YES_NO_OPTION);
                
                if (prompt == JOptionPane.YES_OPTION)
                    System.exit(0);
             
            }
        });
    }
  
    private void addTextPanel(){
        
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new GridLayout(1,3));
        textPanel.add(hint);
        textPanel.add(correct);
        textPanel.add(incorrect);
        textPanel.setBackground(new Color(0x154360));
        add(textPanel, BorderLayout.NORTH);
    }
   
    private void addLetterRack(){
    
        gameRack = new LetterRack(guessedWord, LETTER_IMAGE_DIRECTORY, LETTER_IMAGE_TYPE);
        gameRack.setBackground(new Color(0x154360));
        gameRack.attachListeners(new TileListener());
        add(gameRack, BorderLayout.SOUTH);
    }
   
    private void addHangman(){
    
        JPanel hangmanPanel = new JPanel();
        hangmanPanel.setBackground(new Color(0x154360));
        gameHangman = new Hangman(HANGMAN_IMAGE_BASE_NAME,
                HANGMAN_IMAGE_DIRECTORY,
                HANGMAN_IMAGE_TYPE);
        hangmanPanel.add(gameHangman);
        add(hangmanPanel, BorderLayout.CENTER);
    }
   
    private void getWord(){
    
        String[] options = {"Let's Play", "Quit"};
        JPanel wordPanel = new JPanel();
        wordPanel.setLayout(new BoxLayout(wordPanel,BoxLayout.Y_AXIS));
        wordPanel.setBackground(color);
        wordPanel.setBorder(new EmptyBorder(new Insets(20, 20, 20, 20)));

        JLabel wordLabel = new JLabel("Enter Word to Be Guessed: ");
 
        JTextField wordText = new JTextField(MAX_WORD_LENGTH);
        JLabel hintLabel = new JLabel("Enter a Hint (optional): ");
        JTextField hintText = new JTextField();
        wordPanel.add(wordLabel);
        wordPanel.add(wordText);
        wordPanel.add(hintLabel);
        wordPanel.add(hintText);
        int confirm = -1;

        while (guessedWord.isEmpty()){
        
            confirm = JOptionPane.showOptionDialog(null, 
                    wordPanel, 
                    "Play with a Friend", 
                    JOptionPane.DEFAULT_OPTION, 
                    JOptionPane.QUESTION_MESSAGE, 
                    null, 
                    options, 
                    options[0]);

            if (confirm == 0){
            
                guessedWord = wordText.getText();
          
                if (!guessedWord.matches("[a-zA-Z]+") || 
                    guessedWord.length() > MAX_WORD_LENGTH){
                
                    JOptionPane.showMessageDialog(null, 
                            "Word must be less than 20 characters and " +
                            "only contain letters A-Z or a-z.", 
                            "Invalid Password", 
                            JOptionPane.ERROR_MESSAGE);
                    guessedWord = ""; 
                }
            }
                    
            else if (confirm == 1){
                int prompt = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to quit?",
                        "Quit?", 
                        JOptionPane.YES_NO_OPTION);
                
                if (prompt == JOptionPane.YES_OPTION)
                    System.exit(0);
             
            }
        }
      
        wordHidden.append(guessedWord.replaceAll(".", "*"));
        correct.setText(correct.getText() + wordHidden.toString());
        hint.setText("Hint: " + hintText.getText());
    }
   
    private void newGameDialog(){
    
        int dialogResult = JOptionPane.showConfirmDialog(null, 
                "The word was: " + guessedWord +
                "\nWould You Like to Start a New Game?",
                "Play Again?",
                JOptionPane.YES_NO_OPTION);
        if (dialogResult == JOptionPane.YES_OPTION)
            initialize(); 
        
        else{
            
                int prompt = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to quit?",
                        "Quit?", 
                        JOptionPane.YES_NO_OPTION);
                
                if (prompt == JOptionPane.YES_OPTION)
                    System.exit(0);
             
            }
    }
    
    private class TileListener implements MouseListener {
    
        @Override
        public void mousePressed(MouseEvent e) {
        
            Object source = e.getSource();
            if(source instanceof LetterTile){
            
                char c = ' ';
                int index = 0;
                boolean updated = false;
               
                LetterTile tilePressed = (LetterTile) source;
                c = tilePressed.guess();
              
                while ((index = guessedWord.toLowerCase().indexOf(c, index)) != -1){
                
                    wordHidden.setCharAt(index, guessedWord.charAt(index));
                    index++;
                    updated = true;
                }
      
                if (updated){
                
                    correct.setText("Word: " + wordHidden.toString());
                    
                    if (wordHidden.toString().equals(guessedWord)){
                    
                        gameRack.removeListeners();
                        gameHangman.winImage();
                        newGameDialog();
                    }
                }
         
                else{
                
                    incorrect.setText("Incorrect: " + ++numIncorrect);
                    
                    if (numIncorrect >= MAX_INCORRECT){
                    
                        gameHangman.loseImage();
                        gameRack.removeListeners();
                        newGameDialog();
                    }
                    
                    else
                        gameHangman.nextImage(numIncorrect);
                }
            }
        }
     
        @Override
        public void mouseClicked(MouseEvent e) {}  

        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {}
        
        @Override
        public void mouseExited(MouseEvent e) {}
    }
}
