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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class ComputerPlay extends JFrame {
   
    private final int WIDTH;
    private final int HEIGHT;
    private final int MAX_INCORRECT;
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
    private String guessedWord;
    private final Font font = new Font("Monospaced",Font.BOLD,24);
    private final Color color = new Color(0x00b3b3);
   
    private StringBuilder wordHidden;
    public boolean playAgain = false;
   
    public ComputerPlay() {
    
        WIDTH = 800;
        HEIGHT = 600;
        MAX_INCORRECT = 6;
           
        HANGMAN_IMAGE_DIRECTORY = LETTER_IMAGE_DIRECTORY = "images/";
        HANGMAN_IMAGE_TYPE = LETTER_IMAGE_TYPE = ".png";
        HANGMAN_IMAGE_BASE_NAME = "hangman";
        
        setTitle("Nguyen's Hangman Game");
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        addCloseWindowListener();
        initialize();
    }
   
    private void initialize() {
            
        numIncorrect = 0;
        guessedWord = new String();
        wordHidden = new StringBuilder();
        
        correct = new JLabel("Word: ");
        correct.setFont(font);
        correct.setForeground(Color.YELLOW);
   
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
   
    private void getWord() {
    
        String[] options = {"Let's Play", "Quit"};
        JPanel wordPanel = new JPanel();
        JComboBox faceCombo = new JComboBox();
        wordPanel.setLayout(new BoxLayout(wordPanel,BoxLayout.Y_AXIS));
        wordPanel.setBackground(color);
        wordPanel.setBorder(new EmptyBorder(new Insets(20, 20, 20, 20)));

        JLabel wordLabel = new JLabel("Select One Item Below: ");
        wordLabel.setFont(font);
        wordLabel.setHorizontalAlignment(SwingConstants.CENTER);
     
        wordPanel.add(wordLabel);
        
        for(int i=0;i<8;i++){
            String[] items = {"Fruits", "Vegetables", "Flowers", "Mathematics", "Occupations", "Animals", "Geography", "Universe"};
  
            faceCombo.setEditable(true);
            faceCombo.addItem(items[i]);
            
      }
        faceCombo.setFont( new Font("Monospaced",Font.BOLD,20));
        faceCombo.setForeground(color);
        faceCombo.setBackground(Color.WHITE);
        wordPanel.add(faceCombo);
    
        int confirm = -1;

        while (guessedWord.isEmpty()){
        
            confirm = JOptionPane.showOptionDialog(null, 
                    wordPanel, 
                    "Play with Your Computer", 
                    JOptionPane.DEFAULT_OPTION, 
                    JOptionPane.QUESTION_MESSAGE, 
                    null, 
                    options, 
                    options[0]);

            if (confirm == 0){
                
                if ("Fruits".equals((String)faceCombo.getSelectedItem())){
            
                    guessedWord = getItem("fruits.txt");
           
                }
                
                if ("Vegetables".equals((String)faceCombo.getSelectedItem())){
            
                    guessedWord = getItem("vegetables.txt");
           
                }
                
                if ("Flowers".equals((String)faceCombo.getSelectedItem())){
                    
                     guessedWord = getItem("flowers.txt");
             
                }
                
                if ("Mathematics".equals((String)faceCombo.getSelectedItem())){
                    
                    guessedWord = getItem("mathematics.txt");
            
                }
                
                if ("Occupations".equals((String)faceCombo.getSelectedItem())){
            
                    guessedWord = getItem("occupations.txt");
           
                }
                
                if ("Animals".equals((String)faceCombo.getSelectedItem())){
                    
                    guessedWord = getItem("animals.txt");
             
                }
                
                if ("Geography".equals((String)faceCombo.getSelectedItem())){
                    
                    guessedWord = getItem("geography.txt");
             
                }
                
                if ("Universe".equals((String)faceCombo.getSelectedItem())){
                    
                    guessedWord = getItem("universe.txt");
           
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
    
    }
   
    private void newGameDialog() {
    
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
    
    private String getItem(String filename) {
    
        int totalLines = 0;
        String word;
        File file = new File(filename);
        BufferedReader br = null;
        
        try{
     
            br = new BufferedReader(new FileReader(file));
            while ((br.readLine())!=null){
                
                totalLines++;
            }
            
            br.close();
            br = new BufferedReader(new FileReader(file));
            Random random = new Random();
            int randomInt = random.nextInt(totalLines);
            int count = 0;
            while((word=br.readLine())!= null){
                if(count == randomInt){
                   br.close();
                   return word;
                }
                count++;
            }
            br.close();
        } catch(FileNotFoundException e){
            
            System.out.println("File not found: "+ file.toString());
        } catch(IOException e){
            System.out.println("Unable to read file: " + file.toString());
        }
        
        return "random";
    
}


}
